const LEFT = 0;
const RIGHT = 1;
let eventSection = document.querySelector(".section_event_lst");
let eventBox = document.querySelectorAll(".lst_event_box");
let leftEventBox = eventBox[LEFT];
let rightEventBox = eventBox[RIGHT];
let moreProductsButton = document.querySelector(".more .btn");

document.addEventListener("DOMContentLoaded", function() {
    loadPromotions();
    loadCateogires();
    loadProducts();

    moreProductsButton.addEventListener("click", loadProducts);

    categoryTabs = document.querySelector(".event_tab_lst.tab_lst_min");
    categoryTabs.addEventListener("click", function(event) {
        let category = event.target.closest(".item");
        let categoryTab = event.target.closest(".anchor");
        if (category != null && categoryTab != null) {
            let categoryId = parseInt(category.dataset.categoryId);
            leftEventBox.innerHTML = "";
            rightEventBox.innerHTML = "";
            eventSection.dataset.categoryId = categoryId;
            eventSection.dataset.startProductNo = 0;

            activateCategoryTab(categoryTab);
            loadProducts();
        }
    })
});

function loadPromotions() {
    const GET_PROMOTIONS_URL = "/reservation/api/promotions";
    let promotionRequest = new XMLHttpRequest();
    promotionRequest.addEventListener("load", function() {
        let promotionBox = document.querySelector(".visual_img");
        const response = JSON.parse(this.responseText);
        const promotions = response.promotions;

        let promotionsHtml = "";
        promotions.forEach((promotion) => {
            promotionsHtml += getPromotionHtml(promotion);
        });
        promotionBox.innerHTML = promotionsHtml;
    });

    promotionRequest.open("GET", GET_PROMOTIONS_URL);
    promotionRequest.send();
}

function activateCategoryTab(toActivatingCategoryTab) {
    let currentActivatingCategoryTab = document.querySelector(".anchor.active");
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
    if(element.classList.contains("blind") === false){
        element.classList.toggle("blind");
    }
}

function showElement(element) {
    if(element.classList.contains("blind") === true){
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
    let eventText = document.querySelector(".event_lst_txt");
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

    let eventTab = document.querySelector(".event_tab_lst");
    eventTab.innerHTML += categoriesHtml;
}

function getProductHtml(product) {
    return `
    <li class="item">
        <a href="detail.html?id=${product.id}" class="item_book">
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

function getPromotionHtml(promotion){
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
