function ajaxUtil() {}

ajaxUtil.prototype.sendGetAjax = function() {
	return new Promise((resolve, reject) => {
		const req = new XMLHttpRequest();
		req.open("GET", url);
		req.onload = () => resolve(req.responseText);
		req.onerror = () => reject(req.status);
		req.send();
	});
}

ajaxUtil.prototype.sendPostAjax = function() {
	return new Promise((resolve, reject) => {
		const req = new XMLHttpRequest();
		req.open("POST", url);
		req.setRequestHeader("Content-type", "application/json;charset=utf-8");
		req.onload = () => resolve(req.responseText);
		req.onerror = () => reject(req.status);
		req.send(data);
	});
}

ajaxUtil.prototype.sendPutAjax = function() {
	return new Promise((resolve, reject) => {
		const req = new XMLHttpRequest();
		req.open("PUT", url);
		req.onload = () => resolve(req.responseText);
		req.onerror = () => reject(req.status);
		req.send();
	});
}
