document.addEventListener("DOMContentLoaded", function() {
	const loginObj = new login();
	loginObj.checkFormEmail();
});

function login() {
	this.formEmail = document.querySelector("#resrv_id");
	this.loginFormBtn = document.querySelector("#login_form_btn");
	this.warningMsg = document.querySelector("#email_validation_warning_msg");
}

login.prototype.checkFormEmail = function() {
	this.formEmail.addEventListener("change", function(event) {
		let emailRegex = /^\w+(\.\w+)?@(\w+)(-\w+)?\.([a-z]+.)?[a-z]+$/; // e.g. my.email@nts-corp.co.kr
		if (emailRegex.test(this.formEmail.value)) {
			this.warningMsg.innerText = "";
			this.loginFormBtn.style.pointerEvents = "auto";
		} else {
			this.warningMsg.innerText = "형식이 틀렸거나 너무 짧아요";
			this.loginFormBtn.style.pointerEvents = "none";
		}
	}.bind(this));
}
