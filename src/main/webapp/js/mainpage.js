document.addEventListener("DOMContentLoaded", function() {
    main.init();
});

let main = {
    LEFT: 0,
    RIGHT: 1,
    eventSection: document.querySelector("div.section_event_lst"),
    eventBox: document.querySelectorAll("ul.lst_event_box"),
    moreProductsButton: document.querySelector("div.more > button"),


    init() {
    	this.elementClassUtil = new ElementClassUtil();
    	this.ajaxUtil = new AjaxUtil();
    	
        this.leftEventBox = this.eventBox[this.LEFT];
        this.rightEventBox = this.eventBox[this.RIGHT];

        let productTemplate = document.querySelector("#product_template").innerText;
        this.bindProductTemplate = Handlebars.compile(productTemplate);

        this.promotion.init();
        this.category.init();

        this.promotion.loadPromotions();
        this.category.loadCateogires();
        this.loadProducts();

        this.moreProductsButton.addEventListener("click", this.loadProducts);
        this.category.setCategoryClickEvent();
        
    },

    promotion: {
        init() {
            this.promotionBox = document.querySelector(".container_visual ul.visual_img");
            let promotionTemplate = document.querySelector("#promotion_template").innerText;
            this.bindPromotionTemplate = Handlebars.compile(promotionTemplate);
        },

        loadPromotions() {
            const GET_PROMOTIONS_URL = "/api/promotions";
            main.ajaxUtil.sendGetAjax(GET_PROMOTIONS_URL)
            			.then(responseText => {
                            const response = JSON.parse(responseText);
                            const promotions = response.promotions;
                            const promotionCount = response.size;

                            let promotionsHtml = "";
                            promotions.forEach((promotionItem) => {
                                promotionsHtml += main.promotion.bindPromotionTemplate(promotionItem);
                            });
                            main.promotion.promotionBox.innerHTML = promotionsHtml;
                            main.promotion.setPromotionBoxSlideShow(promotionCount);
            			})
            			.catch(status => {
            				console.log(`get err(promotion): ${status}`);
            			});
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
            if (main.promotion.slideShowStartTime === 0) {
                main.promotion.slideShowStartTime = timestamp;
            }
            let progress = timestamp - main.promotion.slideShowStartTime;

            if (progress > main.promotion.SLIDE_SHOW_DELAY) {
                const PROMOTION_ITEM_WIDTH = main.promotion.promotionBox.firstElementChild.offsetWidth;
                main.promotion.promotionBox.style.transitionDuration = main.promotion.SLIDE_SHOW_DURATION;
                main.promotion.promotionBox.style.transform = `translateX(-${PROMOTION_ITEM_WIDTH}px)`;
                main.promotion.slideShowStartTime = 0;
            }

            requestAnimationFrame(main.promotion.promotionBoxSlideShow);
        }
    },

    category: {
        init() {
            let categoryTemplate = document.querySelector("#category_template").innerText;
            this.bindCategoryTemplate = Handlebars.compile(categoryTemplate);
        },

        loadCateogires() {
            const GET_CATEGORIES_URL = "/api/categories";
            main.ajaxUtil.sendGetAjax(GET_CATEGORIES_URL)
            			.then(responseText => {
            				main.category.addCategoryToEventTab(responseText);
            			})
            			.catch(status => {
            				console.log(`get err(category): ${status}`);
            			});
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
                    main.leftEventBox.innerHTML = "";
                    main.rightEventBox.innerHTML = "";
                    main.eventSection.dataset.categoryId = categoryId;
                    main.eventSection.dataset.startProductNo = 0;

                    this.activateCategoryTab(categoryTab);
                    main.loadProducts();
                }
            }.bind(this));
        },

        addCategoryToEventTab(responseText) {
            const response = JSON.parse(responseText);
            const categories = response.categories;
            let categoriesHtml = "";

            categories.forEach((categoryItem) => {
                categoriesHtml += main.category.bindCategoryTemplate(categoryItem);
            });

            let eventTab = document.querySelector("ul.event_tab_lst");
            eventTab.innerHTML += categoriesHtml;
        },

        activateCategoryTab(toActivatingCategoryTab) {
            let currentActivatingCategoryTab = document.querySelector("a.anchor.active");
            main.elementClassUtil.deactivateElement(currentActivatingCategoryTab);
            main.elementClassUtil.activateElement(toActivatingCategoryTab);
        }
    },

    loadProducts() {
        let startProductNo = parseInt(main.eventSection.dataset.startProductNo);
        let categoryId = main.eventSection.dataset.categoryId;
        const GET_PRODUCTS_URL = `/api/products?categoryId=${categoryId}&start=${startProductNo}`;
        main.ajaxUtil.sendGetAjax(GET_PRODUCTS_URL)
        		.then(responseText => {
                    const response = JSON.parse(responseText);
                    const totalCount = response.totalCount;
                    const products = response.products;
                    const productsCount = response.productsCount;

                    main.addProductsToEventBox(products);
                    startProductNo = main.updateStartProudctNo(productsCount);
                    main.updateEventCount(totalCount);
                    if (startProductNo >= totalCount) {
                        main.elementClassUtil.blindElement(main.moreProductsButton);
                    } else {
                        main.elementClassUtil.notBlindElement(main.moreProductsButton);
                    }
        		})
        		.catch(status => {
        			console.log(`get err(product): ${status}`);
        		});
    },

    addProductsToEventBox(products) {
        let direction = this.LEFT;
        products.forEach((productItem) => {
            let productHtml = this.bindProductTemplate(productItem);
            switch (direction) {
                case this.LEFT:
                this.leftEventBox.innerHTML += productHtml;
                direction = this.RIGHT;
                break;
                case this.RIGHT:
                this.rightEventBox.innerHTML += productHtml;
                direction = this.LEFT;
                break;
            }
        });
    },

    updateStartProudctNo(loadedProductsCount) {
        this.eventSection.dataset.startProductNo = parseInt(this.eventSection.dataset.startProductNo) + loadedProductsCount;
        return parseInt(this.eventSection.dataset.startProductNo);
    },

    updateEventCount(eventCount) {
        let eventText = document.querySelector("p.event_lst_txt");
        let eventHtml = `바로 예매 가능한 행사가 <span class="pink">${eventCount}개</span> 있습니다`;
        eventText.innerHTML = eventHtml;
    },
}
