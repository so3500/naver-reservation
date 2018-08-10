let utils = {
    activateElement (element) {
        element.classList.add("active");
    },

    deactivateElement(element) {
        element.classList.remove("active");
    },

    hideElement(element) {
        element.classList.add("hide");
    },

    notHideElement(element) {
        element.classList.remove("hide");
    },

    blindElement(element) {
        element.classList.add("blind");
    },

    notBlindElement(element) {
        element.classList.remove("blind");
    }

}
