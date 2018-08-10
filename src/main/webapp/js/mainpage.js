const LEFT = 0;
const RIGHT = 1;
let eventSection = document.querySelector("div.section_event_lst");
let eventBox = document.querySelectorAll("ul.lst_event_box");
let leftEventBox = eventBox[LEFT];
let rightEventBox = eventBox[RIGHT];
let moreProductsButton = document.querySelector("div.more > button");
let productTemplate = document.querySelector("#product_template").innerText;
let bindProductTemplate = Handlebars.compile(productTemplate);

document.addEventListener("DOMContentLoaded", function() {
    promotion.init();
    category.init();

    promotion.loadPromotions();
    category.loadCateogires();
    loadProducts();

    moreProductsButton.addEventListener("click", loadProducts);
    category.setCategoryClickEvent();
});

let promotion = {
    init() {
        this.promotionBox = document.querySelector(".container_visual ul.visual_img");
        let promotionTemplate = document.querySelector("#promotion_template").innerText;
        this.bindPromotionTemplate = Handlebars.compile(promotionTemplate);
    },

    loadPromotions() {
        const GET_PROMOTIONS_URL = "/reservation/api/promotions";
        let promotionRequest = new XMLHttpRequest();
        promotionRequest.addEventListener("load", function() {
            const response = JSON.parse(this.responseText);
            const promotions = response.promotions;
            const promotionCount = response.size;

            let promotionsHtml = "";
            promotions.forEach((promotionItem) => {
                promotionsHtml += promotion.bindPromotionTemplate(promotionItem);
            });
            promotion.promotionBox.innerHTML = promotionsHtml;

            promotion.setPromotionBoxSlideShow(promotionCount);
        });

        promotionRequest.open("GET", GET_PROMOTIONS_URL);
        promotionRequest.send();
    },

    setPromotionBoxSlideShow(promotionCount) {
        if (promotionCount >= 2) {
            this.promotionBox.addEventListener("transitionend", this.initSlideShowPosition);
            requestAnimationFrame(this.promotionBoxSlideShow);
        }
    },

    initSlideShowPosition() {
        this.style.transitionDuration = "0s";
        this.style.transform = "translateX(0)";
        this.insertAdjacentElement("beforeend", this.firstElementChild);
    },

    slideShowStartTime: 0,
    SLIDE_SHOW_DELAY: 1500,
    SLIDE_SHOW_DURATION: "1.7s",
    promotionBoxSlideShow(timestamp) {
        if (promotion.slideShowStartTime === 0) {
            promotion.slideShowStartTime = timestamp;
        }
        let progress = timestamp - promotion.slideShowStartTime;

        if (progress > promotion.SLIDE_SHOW_DELAY) {
            const PROMOTION_ITEM_WIDTH = promotion.promotionBox.firstElementChild.offsetWidth;
            promotion.promotionBox.style.transitionDuration = promotion.SLIDE_SHOW_DURATION;
            promotion.promotionBox.style.transform = `translateX(-${PROMOTION_ITEM_WIDTH}px)`;
            promotion.slideShowStartTime = 0;
        }

        requestAnimationFrame(promotion.promotionBoxSlideShow);
    }
}

let category = {
    init() {
        let categoryTemplate = document.querySelector("#category_template").innerText;
        this.bindCategoryTemplate = Handlebars.compile(categoryTemplate);
    },

    loadCateogires() {
        const GET_CATEGORIES_URL = "/reservation/api/categories"
        let categoryRequest = new XMLHttpRequest();
        categoryRequest.addEventListener("load", this.addCategoryToEventTab);
        categoryRequest.open("GET", GET_CATEGORIES_URL);
        categoryRequest.send();
    },

    setCategoryClickEvent() {
        let categoryTabs = document.querySelector("ul.event_tab_lst.tab_lst_min");
        categoryTabs.addEventListener("click", function(event) {
            let clickedTagName = event.target.tagName;
            if (clickedTagName === "A" || clickedTagName === "SPAN") {
                let categoryItem = event.target.closest(".item");
                let categoryTab = event.target.closest(".anchor");
                let categoryId = parseInt(categoryItem.dataset.categoryId);
                leftEventBox.innerHTML = "";
                rightEventBox.innerHTML = "";
                eventSection.dataset.categoryId = categoryId;
                eventSection.dataset.startProductNo = 0;

                category.activateCategoryTab(categoryTab);
                loadProducts();
            }
        });
    },

    addCategoryToEventTab() {
        const response = JSON.parse(this.responseText);
        const categories = response.categories;
        let categoriesHtml = "";

        categories.forEach((categoryItem) => {
            categoriesHtml += category.bindCategoryTemplate(categoryItem);
        });

        let eventTab = document.querySelector("ul.event_tab_lst");
        eventTab.innerHTML += categoriesHtml;
    },

    activateCategoryTab(toActivatingCategoryTab) {
        let currentActivatingCategoryTab = document.querySelector("a.anchor.active");
        utils.deactivateElement(currentActivatingCategoryTab);
        utils.activateElement(toActivatingCategoryTab);
    }
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
            utils.blindElement(moreProductsButton);
        } else {
            utils.notBlindElement(moreProductsButton);
        }
    });
    productRequest.open("GET", GET_PRODUCTS_URL);
    productRequest.send();
}

function addProductsToEventBox(products) {
    let direction = LEFT;
    products.forEach((productItem) => {
        let productHtml = bindProductTemplate(productItem);
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
