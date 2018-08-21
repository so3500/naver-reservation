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
    },

	enableElement(element) {
		element.classList.remove("disabled");
	},

	disableElement(element) {
		element.classList.add("disabled");
	},

	openElement(element) {
		element.classList.add("open");
	},

	closeElement(element) {
		element.classList.remove("open");
	},

}
