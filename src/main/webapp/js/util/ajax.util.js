function AjaxUtil() {}

AjaxUtil.prototype.sendGetAjax = (url) => {
	return new Promise((resolve, reject) => {
		const req = new XMLHttpRequest();
		req.open("GET", url);
	    req.onload = () => {
	        if (req.status === 200) {
	          resolve(req.responseText);
	        } else {
	          reject(req.status);
	        }
	    };
		req.onerror = () => reject(req.status);
		req.send();
	});
}

AjaxUtil.prototype.sendPostAjax = (url, data) => {
	return new Promise((resolve, reject) => {
		const req = new XMLHttpRequest();
		req.open("POST", url);
		req.setRequestHeader("Content-type", "application/json; charset=utf-8");
	    req.onload = () => {
	        if (req.status === 200 || req.status === 201) {
	          resolve(req.responseText);
	        } else {
	        	debugger;
	          reject(req.status);
	        }
	    };
		req.onerror = () => reject(req.status);
		req.send(data);
	});
}

AjaxUtil.prototype.sendFormDataPostAjax = (url, data) => {
	return new Promise((resolve, reject) => {
		const req = new XMLHttpRequest();
		req.open("POST", url);
	    req.onload = () => {
	        if (req.status === 200 || req.status === 201) {
	          resolve(req.responseText);
	        } else {
	        	debugger;
	          reject(req.status);
	        }
	    };
		req.onerror = () => reject(req.status);
		req.send(data);
	});
}

AjaxUtil.prototype.sendPutAjax = (url) => {
	return new Promise((resolve, reject) => {
		const req = new XMLHttpRequest();
		req.open("PUT", url);
		req.setRequestHeader("Content-type", "application/json; charset=utf-8");
	    req.onload = () => {
	        if (req.status === 200 || req.status === 202 || req.status === 204) {
	          resolve(req.responseText);
	        } else {
	          reject(req.status);
	        }
	    };
		req.onerror = () => reject(req.status);
		req.send();
	});
}
