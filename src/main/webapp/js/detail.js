document.addEventListener("DOMContentLoaded", function() {
    loadDetailPage();

    infoTab.init();
    productImage.init();
    infoTab.setInfoTabClickEvent();
    detailContent.setCloseDetailEvent();
    detailContent.setOpenDetailEvent();
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
    leftImageNumber: document.querySelector(".pagination > .figure_pagination > .num"),
    rightImageNumber: document.querySelector(".pagination > .figure_pagination > .num.off > span"),
    SLIDE_SHOW_DURATION: "0.1s",
    slideDirection: "",
    PREV: 0,
    NEXT: 1,

    init() {
        let productImageTemplate = document.querySelector("#product_image_template").innerText;
        this.bindProductImageTemplate = Handlebars.compile(productImageTemplate);
    },

    loadImages(productImages, description) {
        let productImageHtml = "";
        let imageNumber = 1;
        this.addProductMainImagetoProductImageBox(productImages, description, imageNumber);
        if (productImages.length >= 2) {
            this.addEtcProductImageToProductImageBox(productImages, description, imageNumber + 1);
            this.setProductImageBoxSlideShow();
        } else {
            this.setElementInnerTextValue(this.rightImageNumber, 1);
            utils.blindElement(this.prevButton);
            utils.blindElement(this.nextButton);
        }
    },

    setElementInnerTextValue(element, value) {
        element.innerText = value;
    },

    addProductMainImagetoProductImageBox(productImages, description, imageNumber) {
        let productImageHtml = "";
        let productMainImage = this.getProductMainImage(productImages);
        productMainImage["description"] = description;
        productMainImage["imageNumber"] = imageNumber;

        productImageHtml = this.bindProductImageTemplate(productMainImage);
        this.productImageBox.innerHTML += productImageHtml;
    },

    addEtcProductImageToProductImageBox(productImages, description, imageNumber) {
        let productImageHtml = "";
        let productEtcImage = this.getProductEtcImage(productImages);
        productEtcImage["description"] = description;
        productEtcImage["imageNumber"] = imageNumber;

        productImageHtml = this.bindProductImageTemplate(productEtcImage);
        this.productImageBox.innerHTML += productImageHtml;
    },

    getProductMainImage(productImages) {
        return productImages.find((productImageItem) => productImageItem.type === "ma");
    },

    getProductEtcImage(productImages) {
        return productImages.find((productImageItem) => productImageItem.type === "et");
    },

    setProductImageBoxSlideShow() {
        this.prevButton.addEventListener("click", function() {
            this.slideDirection = this.PREV;
            this.initSlideShowPosition();
            setTimeout(function() {
                this.moveImagePrev();
                this.changeImageNumber();
            }.bind(this), 0);
        }.bind(this));

        this.nextButton.addEventListener("click", function() {
            this.slideDirection = this.NEXT;
            this.moveImageNext();
            setTimeout(function() {
                this.initSlideShowPosition();
                this.changeImageNumber();
            }.bind(this), 100);
        }.bind(this));
    },

    initSlideShowPosition() {
        switch (this.slideDirection) {
            case this.PREV:
                this.initMoveImagePrev();
                break;
            case this.NEXT:
                this.initMoveImageNext();
                break;
        }
    },

    initMoveImagePrev() {
        this.productImageBox.insertAdjacentElement("afterbegin", this.productImageBox.lastElementChild);
        this.productImageBox.style.transitionDuration = "0s";
        this.productImageBox.style.transform = "translateX(-100%)";
    },

    initMoveImageNext() {
        this.productImageBox.insertAdjacentElement("beforeend", this.productImageBox.firstElementChild);
        this.productImageBox.style.transitionDuration = "0s";
        this.productImageBox.style.transform = "translateX(0)";
    },

    moveImagePrev() {
        this.productImageBox.style.transition = `all ${this.SLIDE_SHOW_DURATION} ease`;
        this.productImageBox.style.transform = "translateX(0)";
    },

    moveImageNext() {
        this.productImageBox.style.transition = `all ${this.SLIDE_SHOW_DURATION} ease`;
        this.productImageBox.style.transform = "translateX(-100%)";
    },

    changeImageNumber() {
        this.setElementInnerTextValue(this.leftImageNumber, this.productImageBox.firstElementChild.dataset.imageNumber);
    },

}

let detailContent = {
    setOpenDetailEvent() {
        $("a.bk_more._open").click(() => {
            $("div.store_details.close3").toggleClass("close3");
            $("a.bk_more._open").css("display", "none");
            $("a.bk_more._close").css("display", "block");
        });
    },

    setCloseDetailEvent() {
        $("a.bk_more._close").click(() => {
            $("div.store_details").toggleClass("close3");
            $("a.bk_more._close").css("display", "none");
            $("a.bk_more._open").css("display", "block");
        });
    }
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
