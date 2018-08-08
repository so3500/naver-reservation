const LEFT = 0;
const RIGHT = 1;
let eventSection = document.querySelector("div.section_event_lst");
let eventBox = document.querySelectorAll("ul.lst_event_box");
let leftEventBox = eventBox[LEFT];
let rightEventBox = eventBox[RIGHT];
let moreProductsButton = document.querySelector("div.more > button");

document.addEventListener("DOMContentLoaded", function() {
    loadPromotions();
    loadCateogires();
    loadProducts();

    moreProductsButton.addEventListener("click", loadProducts);
    categoryTabs = document.querySelector("ul.event_tab_lst.tab_lst_min");
    categoryTabs.addEventListener("click", function(event) {
        let clickedTagName = event.target.tagName;
        if (clickedTagName === "A" || clickedTagName === "SPAN") {
            let category = event.target.closest(".item");
            let categoryTab = event.target.closest(".anchor");
            let categoryId = parseInt(category.dataset.categoryId);
            leftEventBox.innerHTML = "";
            rightEventBox.innerHTML = "";
            eventSection.dataset.categoryId = categoryId;
            eventSection.dataset.startProductNo = 0;

            activateCategoryTab(categoryTab);
            loadProducts();
        }
    });
});

let promotionBox = document.querySelector(".container_visual ul.visual_img");

function loadPromotions() {
    const GET_PROMOTIONS_URL = "/reservation/api/promotions";
    let promotionRequest = new XMLHttpRequest();
    promotionRequest.addEventListener("load", function() {
        const response = JSON.parse(this.responseText);
        const promotions = response.promotions;
        const promotionCount = response.size;

        let promotionsHtml = "";
        promotions.forEach((promotion) => {
            promotionsHtml += getPromotionHtml(promotion);
        });
        promotionBox.innerHTML = promotionsHtml;

        setPromotionBoxSlideShow(promotionBox, promotionCount);
    });

    promotionRequest.open("GET", GET_PROMOTIONS_URL);
    promotionRequest.send();
}

function setPromotionBoxSlideShow(promotionBox, promotionCount) {
    if (promotionCount >= 2) {
        promotionBox.addEventListener("transitionend", initSlideShowPosition);
        requestAnimationFrame(promotionBoxSlideShow);
    }
}

function initSlideShowPosition() {
    this.style.transitionDuration = "0s";
    this.style.transform = "translateX(0)";
    this.insertAdjacentElement("beforeend", this.firstElementChild);
}

let slideShowStartTime = 0;
const SLIDE_SHOW_DELAY = 1500;
const SLIDE_SHOW_DURATION = "2s";
let promotionBoxSlideShow = function(timestamp) {
    if (slideShowStartTime === 0) {
        slideShowStartTime = timestamp;
    }
    let progress = timestamp - slideShowStartTime;

    if (progress > SLIDE_SHOW_DELAY) {
        const PROMOTION_ITEM_WIDTH = promotionBox.firstElementChild.offsetWidth;
        promotionBox.style.transitionDuration = SLIDE_SHOW_DURATION;
        promotionBox.style.transform = `translateX(-${PROMOTION_ITEM_WIDTH}px)`;
        slideShowStartTime = 0;
    }

    requestAnimationFrame(promotionBoxSlideShow);
}

function activateCategoryTab(toActivatingCategoryTab) {
    let currentActivatingCategoryTab = document.querySelector("a.anchor.active");
    currentActivatingCategoryTab.classList.toggle("active");
    toActivatingCategoryTab.classList.toggle("active");
}

function loadProducts() {
    let startProductNo = parseInt(eventSection.dataset.startProductNo);
    let categoryId = eventSection.dataset.categoryId;
    const GET_PRODUCTS_URL = `/reservation/api/products?categoryId=${categoryId}&start=${startProductNo}`;
    let productRequest = new XMLHttpRequest();
    productRequest.addEventListener("load", function() {
        const response = JSON.parse(this.responseText);
        const totalCount = response.totalCount;
        const products = response.products;
        const productsCount = response.productsCount;

        addProductsToEventBox(products);
        startProductNo = updateStartProudctNo(productsCount);
        updateEventCount(totalCount);
        if (startProductNo >= totalCount) {
            hideElement(moreProductsButton);
        } else {
            showElement(moreProductsButton);
        }
    });
    productRequest.open("GET", GET_PRODUCTS_URL);
    productRequest.send();
}

function hideElement(element) {
    if (element.classList.contains("blind") === false) {
        element.classList.toggle("blind");
    }
}

function showElement(element) {
    if (element.classList.contains("blind") === true) {
        element.classList.toggle("blind");
    }
}

function addProductsToEventBox(products) {
    let direction = LEFT;
    products.forEach((product) => {
        let productHtml = getProductHtml(product);
        switch (direction) {
            case LEFT:
                leftEventBox.innerHTML += productHtml;
                direction = RIGHT;
                break;
            case RIGHT:
                rightEventBox.innerHTML += productHtml;
                direction = LEFT;
                break;
        }
    });
}

function updateStartProudctNo(loadedProductsCount) {
    eventSection.dataset.startProductNo = parseInt(eventSection.dataset.startProductNo) + loadedProductsCount;
    return parseInt(eventSection.dataset.startProductNo);
}

function updateEventCount(eventCount) {
    let eventText = document.querySelector("p.event_lst_txt");
    let eventHtml = `바로 예매 가능한 행사가 <span class="pink">${eventCount}개</span> 있습니다`;
    eventText.innerHTML = eventHtml;
}

function loadCateogires() {
    const GET_CATEGORIES_URL = "/reservation/api/categories"
    let categoryRequest = new XMLHttpRequest();
    categoryRequest.addEventListener("load", addCategoryToEventTab);
    categoryRequest.open("GET", GET_CATEGORIES_URL);
    categoryRequest.send();
}

function addCategoryToEventTab() {
    const response = JSON.parse(this.responseText);
    const categories = response.categories;
    let categoriesHtml = "";

    categories.forEach((category) => {
        categoriesHtml += getCategoryHtml(category);
    });

    let eventTab = document.querySelector("ul.event_tab_lst");
    eventTab.innerHTML += categoriesHtml;
}

function getProductHtml(product) {
    return `
    <li class="item">
        <a href="./detail?id=${product.displayInfoId}" class="item_book">
            <div class="item_preview">
                <img alt="${product.description}" class="img_thumb" src="http://211.249.62.123/productImages/${product.id}?type=th">
                <span class="img_border"></span>
            </div>
            <div class="event_txt">
                <h4 class="event_txt_tit"> <span>${product.description}</span> <small class="sm">${product.placeName}</small> </h4>
                <p class="event_txt_dsc">${product.content}</p>
            </div>
        </a>
    </li>
    `;
}

function getCategoryHtml(category) {
    return `
    <li class="item" data-category-id="${category.id}">
    <a class="anchor"> <span>${category.name}</span> </a>
    </li>
    `;
}

function getPromotionHtml(promotion) {
    return `
    <li class="item" style="background-image: url(http://211.249.62.123/productImages/${promotion.productId}/${promotion.productImageId});">
        <a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
        <div class="event_txt">
            <h4 class="event_txt_tit"></h4>
            <p class="event_txt_adr"></p>
            <p class="event_txt_dsc"></p>
        </div>
    </a>
    </li>
    `;
}
