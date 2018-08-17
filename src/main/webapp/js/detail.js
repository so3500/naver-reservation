document.addEventListener("DOMContentLoaded", function() {
    detail.init();
});

let detail = {
    init() {
        this.infoTab.init();
        this.productImage.init();
        this.review.init();
        this.ReserveButton.setReserveButtonEvent();
        this.infoTab.setInfoTabClickEvent();
        this.detailContent.setCloseDetailEvent();
        this.detailContent.setOpenDetailEvent();

        this.loadDetailPage();
    },

    displayInfoId: document.querySelector("#detail_main").dataset.displayInfoId,
    loadDetailPage() {
        const GET_PRODUCT_URL = `/api/products/${detail.displayInfoId}`;
        let productRequest = new XMLHttpRequest();
        productRequest.addEventListener("load", function() {
            const response = JSON.parse(this.responseText);

            detail.productImage.loadImages(response.productImages, response.product.description);
            detail.infoTab.loadDisplayInfoImage(response.displayInfoImages);
            detail.review.loadComments(response.comments, response.avgScore);
        })
        productRequest.open("GET", GET_PRODUCT_URL);
        productRequest.send();
    },

    ReserveButton: {
        reserve_btn: document.querySelector("#reserve_btn"),

        setReserveButtonEvent() {
            reserve_btn.addEventListener("click", function(event) {
                document.location.href = `/reserve?id=${detail.displayInfoId}`;
            });
        },
    },

    productImage: {
        productImageBox: document.querySelector(".visual_img.detail_swipe"),
        prevButton: document.querySelector(".prev_inn"),
        nextButton: document.querySelector(".nxt_inn"),
        leftImageNumber: document.querySelector(".pagination > .figure_pagination > .num"),
        rightImageNumber: document.querySelector(".pagination > .figure_pagination > .num.off > span"),
        SLIDE_SHOW_DURATION: "0.1s",
        slideDirection: "",
        PREV: 0,
        NEXT: 1,
        IMAGE_BOX_HEIGHT: document.querySelector("div.group_visual").offsetHeight,

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
            productMainImage["IMAGE_BOX_HEIGHT"] = this.IMAGE_BOX_HEIGHT;

            productImageHtml = this.bindProductImageTemplate(productMainImage);
            this.productImageBox.innerHTML += productImageHtml;
        },

        addEtcProductImageToProductImageBox(productImages, description, imageNumber) {
            let productImageHtml = "";
            let productEtcImage = this.getProductEtcImage(productImages);
            productEtcImage["description"] = description;
            productEtcImage["imageNumber"] = imageNumber;
            productEtcImage["IMAGE_BOX_HEIGHT"] = this.IMAGE_BOX_HEIGHT;

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

    },

    detailContent: {
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
    },

    review: {
        reviewAvgScoreGraph: document.querySelector("#review_avg_score_graph"),
        reviewAvgScore: document.querySelector("#review_avg_score"),
        reviewCount: document.querySelector("#review_count"),
        reviewList: document.querySelector("#review_list"),
        reviewMoreButton: document.querySelector("a.btn_review_more"),
        TOTAL_AVG_SCORE: 5.0,

        init() {
            let reviewTemplate = document.querySelector("#review_template").innerText;
            this.bindReviewTemplate = Handlebars.compile(reviewTemplate);
            Handlebars.registerHelper("firstImageSaveFileName", function(reservationUserCommentImages) {
                return reservationUserCommentImages[0].saveFileName;
            })
        },

        loadComments(comments, avgScore) {
            this.addThreeReviewsToReviewList(comments);
            this.setReviewCount(comments.length);
            this.setAvgScoreAndGraph(avgScore);
        },

        setReviewCount(totalCount) {
            this.reviewCount.innerText = `${totalCount}건`;
        },

        setAvgScoreAndGraph(avgScore) {
            this.reviewAvgScore.innerText = avgScore.toFixed(1);
            const AVG_SCORE_RATE = avgScore / this.TOTAL_AVG_SCORE * 100;
            this.reviewAvgScoreGraph.style.width = `${AVG_SCORE_RATE}%`;
        },

        addThreeReviewsToReviewList(comments) {
            if (comments.length > 0) {
                let reviewHtml = "";
                reviewHtml = this.bindReviewTemplate(comments.slice(0, 3));
                this.reviewList.innerHTML = reviewHtml;
            }
            if (comments.length <= 3) {
                utils.blindElement(this.reviewMoreButton);
            }
        }
    },

    infoTab: {
        infoTabList: document.querySelector("ul.info_tab_lst"),
        detailArea: document.querySelector("div.detail_area_wrap"),
        detailLocation: document.querySelector("div.detail_location"),

        init() {
            this.currentActivatedInfoTabItem = this.infoTabList.dataset.currentActivatedInfoTabItem;
            this.boxStoreInfo = this.detailLocation.querySelector("div.box_store_info");

            let storeLocationTemplate = document.querySelector("#display_info_image_template").innerText;
            this.bindStoreLocationTemplate = Handlebars.compile(storeLocationTemplate);
        },

        setInfoTabClickEvent() {
            this.infoTabList.addEventListener("click", function(event) {
                let activatedInfoTabItem = this.infoTabList.querySelector("li.active");
                let clickedTagName = event.target.tagName;
                let clickedTarget = event.target;

                if (this.isNotValidClickedArea(clickedTagName) || this.isAlreadyClickedElement(clickedTarget)) {
                    return;
                }

                this.toggleInfoTab(activatedInfoTabItem, clickedTarget, clickedTagName);
                this.toggleInfo(activatedInfoTabItem);
            }.bind(this));
        },

        isAlreadyClickedElement(clickedTarget) {
            if (clickedTarget.closest("LI").classList.contains(this.currentActivatedInfoTabItem)) {
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
                utils.hideElement(this.detailArea);
                utils.notHideElement(this.detailLocation);
                this.currentActivatedInfoTabItem = "_path";
            } else if (activatedInfoTabItem.classList.contains("_path")) {
                utils.hideElement(this.detailLocation);
                utils.notHideElement(this.detailArea);
                this.currentActivatedInfoTabItem = "_detail";
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
            displayInfoImages.forEach(displayInfoImage => displayInfoImageHtml += this.bindStoreLocationTemplate(displayInfoImage));
            this.boxStoreInfo.insertAdjacentHTML("afterbegin", displayInfoImageHtml);
        },
    }
}
