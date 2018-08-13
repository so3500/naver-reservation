document.addEventListener("DOMContentLoaded", function() {
    infoTab.init();
    productImage.init();
    infoTab.setInfoTabClickEvent();

    loadDetailPage();
});

function loadDetailPage() {
    displayInfoId = document.querySelector(".ct.main").dataset.displayInfoId;
    const GET_PRODUCT_URL = `/api/products/${displayInfoId}`;
    let productRequest = new XMLHttpRequest();
    productRequest.addEventListener("load", function() {
        const response = JSON.parse(this.responseText);

        const product = response.product;
        const productImages = response.productImages;
        productImage.loadImages(productImages, product.description);
        const displayInfoImages = response.displayInfoImages;
        infoTab.loadDisplayInfoImage(displayInfoImages);

        // TODO 각 object에서 처리
        const comments = response.comments;
        const avgScore = response.avgScore;
        const productPrices = response.productPrices;
    })
    productRequest.open("GET", GET_PRODUCT_URL);
    productRequest.send();
}

let productImage = {
    productImageBox: document.querySelector(".visual_img.detail_swipe"),
    prevButton: document.querySelector(".prev_inn"),
    nextButton: document.querySelector(".nxt_inn"),
    pageNumberLeft: document.querySelector(".pagination > .figure_pagination > .num"),
    PageNumberRight: document.querySelector(".pagination > .figure_pagination > .num.off > span"),

    init() {
        let productImageTemplate = document.querySelector("#product_image_template").innerText;
        this.bindProductImageTemplate = Handlebars.compile(productImageTemplate);
    },

    loadImages(productImages, description) {
        let productImageHtml = "";

        productImage.addProductMainImagetoProductImageBox(productImages, description);
        if (productImages.length >= 2) {
            productImage.addEtcProductImageToProductImageBox(productImages, description);
            productImage.bindClickEventToPrevNextButton();
        } else {
            productImage.setPageNumber(productImage.PageNumberRight, 1);
            utils.blindElement(productImage.prevButton);
            utils.blindElement(productImage.nextButton);
        }
    },

    addProductMainImagetoProductImageBox(productImages, description) {
        let productImageHtml = "";
        let productMainImage = productImage.getProductMainImage(productImages);
        productMainImage["description"] = description;

        productImageHtml = productImage.bindProductImageTemplate(productMainImage);
        productImage.productImageBox.innerHTML += productImageHtml;
    },

    addEtcProductImageToProductImageBox(productImages, description) {
        let productImageHtml = "";
        let productEtcImage = productImage.getProductEtcImage(productImages);
        productEtcImage["description"] = description;

        productImageHtml = productImage.bindProductImageTemplate(productEtcImage);
        productImage.productImageBox.innerHTML += productImageHtml;
    },

    getProductMainImage(productImages) {
        return productImages.find((productImage) => productImage.type === "ma");
    },

    getProductEtcImage(productImages) {
        return productImages.find((productImage) => productImage.type === "et");
    },

    bindClickEventToPrevNextButton() {
        productImage.prevButton.addEventListener("click", productImage.goImagesPrev);
        productImage.nextButton.addEventListener("click", productImage.goImagesNext);
    },

    // TODO 무한 슬라이딩 구현
    goImagesPrev() {

    },

    goImagesNext() {

    },

    setPageNumber(element, number) {
        element.innerText = number;
    },

}

let discountEvent = {

}

let comment = {

}

let infoTab = {
    init() {
        this.infoTabList = document.querySelector("ul.info_tab_lst");
        this.detailArea = document.querySelector("div.detail_area_wrap");
        this.detailLocation = document.querySelector("div.detail_location");
        this.currentActivatedInfoTabItem = this.infoTabList.dataset.currentActivatedInfoTabItem;

        this.boxStoreInfo = this.detailLocation.querySelector("div.box_store_info");
        let storeLocationTemplate = document.querySelector("#display_info_image_template").innerText;
        this.bindStoreLocationTemplate = Handlebars.compile(storeLocationTemplate);
    },

    setInfoTabClickEvent() {
        this.infoTabList.addEventListener("click", function(event) {
            let activatedInfoTabItem = infoTab.infoTabList.querySelector("li.active");
            let clickedTagName = event.target.tagName;
            let clickedTarget = event.target;

            if (infoTab.isAlreadyClickedElement(clickedTarget) || infoTab.isNotValidClickedArea(clickedTagName)) {
                return;
            }

            infoTab.toggleInfoTab(activatedInfoTabItem, clickedTarget, clickedTagName);
            infoTab.toggleInfo(activatedInfoTabItem);
        });
    },

    isAlreadyClickedElement(clickedTarget) {
        if (clickedTarget.closest("LI").classList.contains(infoTab.currentActivatedInfoTabItem)) {
            return true;
        } else {
            false;
        }
    },

    isNotValidClickedArea(clickedTagName) {
        if (clickedTagName === "UL") {
            return true;
        } else {
            return false;
        }
    },

    toggleInfo(activatedInfoTabItem) {
        if (activatedInfoTabItem.classList.contains("_detail")) {
            utils.hideElement(infoTab.detailArea);
            utils.notHideElement(infoTab.detailLocation);
            infoTab.currentActivatedInfoTabItem = "_path";
        } else if (activatedInfoTabItem.classList.contains("_path")) {
            utils.hideElement(infoTab.detailLocation);
            utils.notHideElement(infoTab.detailArea);
            infoTab.currentActivatedInfoTabItem = "_detail";
        }
    },

    toggleInfoTab(activatedInfoTabItem, clickedTarget, clickedTagName) {
        utils.deactivateElement(activatedInfoTabItem.querySelector("a.anchor"));
        utils.deactivateElement(activatedInfoTabItem);
        if (clickedTagName === "SPAN") {
            utils.activateElement(clickedTarget.closest("a.anchor"));
            utils.activateElement(clickedTarget.closest("li.item"));
        } else if (clickedTagName === "A") {
            utils.activateElement(clickedTarget);
            utils.activateElement(clickedTarget.closest("li.item"));
        } else if (clickedTagName === "LI") {
            utils.activateElement(clickedTarget.querySelector("a.anchor"));
            utils.activateElement(clickedTarget);
        }
    },

    loadDisplayInfoImage(displayInfoImages) {
        let displayInfoImageHtml = "";
        displayInfoImages.forEach(displayInfoImage => displayInfoImageHtml += infoTab.bindStoreLocationTemplate(displayInfoImage));
        infoTab.boxStoreInfo.insertAdjacentHTML("afterbegin", displayInfoImageHtml);
    },
}
