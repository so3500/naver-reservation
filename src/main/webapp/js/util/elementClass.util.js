function ElementClassUtil() {}

ElementClassUtil.prototype.activateElement = function(elm) {
	elm.classList.add("active");
}

ElementClassUtil.prototype.deactivateElement = function(elm) {
	elm.classList.remove("active");
}

ElementClassUtil.prototype.hideElement = function(elm) {
	elm.classList.add("hide");
}

ElementClassUtil.prototype.notHideElement = function(elm) {
	elm.classList.remove("hide");
}

ElementClassUtil.prototype.blindElement = function(elm) {
	elm.classList.add("blind");
}

ElementClassUtil.prototype.notBlindElement = function(elm) {
	elm.classList.remove("blind");
}

ElementClassUtil.prototype.enableElement = function(elm) {
	elm.classList.remove("disabled");
}

ElementClassUtil.prototype.disableElement = function(elm) {
	elm.classList.add("disabled");
}

ElementClassUtil.prototype.openElement = function(elm) {
	elm.classList.add("open");
}

ElementClassUtil.prototype.closeElement = function(elm) {
	elm.classList.remove("open");
}
