document.addEventListener("DOMContentLoaded", function() {
    infoTab.init();

    infoTab.setInfoTabClickEvent();
});

let infoTab = {
    init() {
        this.infoTabList = document.querySelector("ul.info_tab_lst"),
            this.detailArea = document.querySelector("div.detail_area_wrap"),
            this.detailLocation = document.querySelector("div.detail_location"),
            this.currentActivatedInfoTabItem = this.infoTabList.dataset.currentActivatedInfoTabItem;
    },

    setInfoTabClickEvent() {
        this.infoTabList.addEventListener("click", function(event) {
            let activatedInfoTabItem = infoTab.infoTabList.querySelector("li.active");
            let clickedTagName = event.target.tagName;
            let clickedTarget = event.target;

            if(infoTab.isAlreadyClickedElement(clickedTarget) || infoTab.isNotValidClickedArea(clickedTagName)) {
                return;
            }

            infoTab.toggleInfoTab(activatedInfoTabItem, clickedTarget, clickedTagName);
            infoTab.toggleInfo(activatedInfoTabItem);
        });
    },

    isAlreadyClickedElement(clickedTarget){
        if (clickedTarget.closest("LI").classList.contains(infoTab.currentActivatedInfoTabItem)) {
            return true;
        } else {
            false;
        }
    },

    isNotValidClickedArea(clickedTagName){
        if(clickedTagName === "UL") {
            return true;
        } else {
            return false;
        }
    },

    toggleInfo(activatedInfoTabItem){
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

    toggleInfoTab(activatedInfoTabItem, clickedTarget, clickedTagName){
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
    }
}
