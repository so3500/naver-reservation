function AjaxUtil() {}

AjaxUtil.prototype.sendGetAjax = function(url) {
	return new Promise((resolve, reject) => {
		const req = new XMLHttpRequest();
		req.open("GET", url);
		req.onload = () => resolve(req.responseText);
		req.onerror = () => reject(req.status);
		req.send();
	});
}

AjaxUtil.prototype.sendPostAjax = function(url, data) {
	return new Promise((resolve, reject) => {
		const req = new XMLHttpRequest();
		req.open("POST", url);
		req.setRequestHeader("Content-type", "application/json;charset=utf-8");
		req.onload = () => resolve(req.responseText);
		req.onerror = () => reject(req.status);
		req.send(data);
	});
}

AjaxUtil.prototype.sendPutAjax = function(url) {
	return new Promise((resolve, reject) => {
		const req = new XMLHttpRequest();
		req.open("PUT", url);
		req.onload = () => resolve(req.responseText);
		req.onerror = () => reject(req.status);
		req.send();
	});
}
