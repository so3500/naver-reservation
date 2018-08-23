document.addEventListener("DOMContentLoaded", function() {
	reserve.init();
});

let reserve = {
	productId: document.querySelector("div.ct").dataset.productId,

	init() {
		this.bookingTicket.setTickectBodyClickEvent();
		this.bookingForm.setBookingFormChangeEvent();
		this.bookingForm.setTermClickEvent();
		this.bookingForm.setFormSubmitEvent();
	},

	bookingTicket: {
		ticketBody: document.querySelector("#ticket_body"),
		DELETE_ONE_TICKET: 0,
		ADD_ONE_TICKET: 1,

		setTickectBodyClickEvent() {
			this.ticketBody.addEventListener("click", function(event) {
				switch (this.getKindOfBtn(event.target)) {
					case this.ADD_ONE_TICKET:
						this.addOneTicket(event.target);
						break;
					case this.DELETE_ONE_TICKET:
						this.subOneTicket(event.target);
						break;
				}
			}.bind(this));
		},

		getKindOfBtn(clickedBtn) {
			let addOneTicketBtn = clickedBtn.closest("a.btn_plus_minus.spr_book2.ico_plus3");
			if (addOneTicketBtn != null) {
				return this.ADD_ONE_TICKET;
			}
			let subOneTicketBtn = clickedBtn.closest("a.btn_plus_minus.spr_book2.ico_minus3");
			if (subOneTicketBtn != null) {
				return this.DELETE_ONE_TICKET;
			}
		},

		addOneTicket(clickedBtn) {
			let addOneTicketBtn = clickedBtn.closest("a.btn_plus_minus.spr_book2.ico_plus3");
			let ticket = clickedBtn.closest(".qty");
			let subOneTicketBtn = ticket.querySelector("a.btn_plus_minus.spr_book2.ico_minus3");

			let productPrice = parseInt(ticket.dataset.productPrice);
			let ticketCountInput = ticket.querySelector(".count_control_input");
			let ticketCount = parseInt(ticketCountInput.value);

			ticketCount++;
			this.setTotalTicketPrice(ticket, productPrice, ticketCount);
			reserve.bookingForm.addTotalTicket();
			ticketCountInput.value = ticketCount;

			if (ticketCount === 1) {
				utils.enableElement(ticketCountInput);
				utils.enableElement(subOneTicketBtn);
				ticket.querySelector("div.individual_price").classList.add("on_color");
				reserve.bookingForm.checkAllCondition();
			}
		},

		subOneTicket(clickedBtn) {
			let subOneTicketBtn = clickedBtn.closest("a.btn_plus_minus.spr_book2.ico_minus3");
			let ticket = clickedBtn.closest(".qty");

			let productPrice = parseInt(ticket.dataset.productPrice);
			let ticketCountInput = ticket.querySelector(".count_control_input");
			let ticketCount = parseInt(ticketCountInput.value);

			if (ticketCount > 0) {
				ticketCount--;
				this.setTotalTicketPrice(ticket, productPrice, ticketCount);
				reserve.bookingForm.subTotalTicket();
				ticketCountInput.value = ticketCount;
			}

			if (ticketCount === 0) {
				utils.disableElement(ticketCountInput);
				utils.disableElement(subOneTicketBtn);
				ticket.querySelector("div.individual_price").classList.remove("on_color");
				reserve.bookingForm.checkAllCondition();
			}
		},

		setTotalTicketPrice(ticket, productPrice, ticketCount) {
			let totalPrice = ticket.querySelector(".total_price");
			totalPrice.innerText = (productPrice * ticketCount).toLocaleString();
		},

	},

	bookingForm: {
		formName: document.querySelector("#name"),
		formTel: document.querySelector("#tel"),
		formEmail: document.querySelector("#email"),
		isNameValid: false,
		isTelValid: false,
		isEmailValid: false,
		totalTicketCountElm: document.querySelector("#total_count"),
		totalTicketCount: 0,
		WARNING_MSG_SHOW_TIME: 1500,
		termAgreementBody: document.querySelector("div.section_booking_agreement"),
		termAgreement: document.querySelector("#chk3"),
		isCheckedTermAgreement: false,
		rsvFormSummitBtn: document.querySelector("#rsv_form_summit_btn"),
		rsvForm: document.querySelector("#rsv_form"),
		rsvYearMonthDay: document.querySelector("#rsv_year_month_day").dataset.rsvYearMonthDay,

		setBookingFormChangeEvent() {
			this.formName.addEventListener("change", function(event) {
				this.checkFormName(event);
				this.checkAllCondition();
			}.bind(this));

			this.formTel.addEventListener("change", function(event) {
				this.checkFormTel(event);
				this.checkAllCondition();
			}.bind(this));

			this.formEmail.addEventListener("change", function(event) {
				this.checkFormEmail(event);
				this.checkAllCondition();
			}.bind(this));

			this.termAgreement.addEventListener("change", function() {
				this.checkTermAgreement();
				this.checkAllCondition();
			}.bind(this));
		},

		checkFormName(event) {
			let blankRegex = /^\s*$/;
			if (blankRegex.test(this.formName.value)) {
				this.isNameValid = false;
				let inputWrap = event.target.parentElement;
				let warningMsg = inputWrap.querySelector("div.warning_msg");
				this.showWarningMsgForAWhile(warningMsg);
			} else {
				this.isNameValid = true;
			}
		},

		checkFormTel(event) {
			let phoneNumRegex = /^\(?([0-9]{3})\)?-([0-9]{3,4})?-([0-9]{3,4})$/;
			if (phoneNumRegex.test(this.formTel.value)) {
				this.isTelValid = true;
			} else {
				this.isTelValid = false;
				let inputWrap = event.target.parentElement;
				let warningMsg = inputWrap.querySelector("div.warning_msg");
				this.showWarningMsgForAWhile(warningMsg);
			}
		},

		checkFormEmail(event) {
			let emailRegex = /^\w+(\.\w+)?@(\w+)(-\w+)?\.([a-z]+.)?[a-z]+$/; // e.g. my.email@nts-corp.co.kr
			if (emailRegex.test(this.formEmail.value)) {
				this.isEmailValid = true;
			} else {
				this.isEmailValid = false;
				let inputWrap = event.target.parentElement;
				let warningMsg = inputWrap.querySelector("div.warning_msg");
				this.showWarningMsgForAWhile(warningMsg);
			}
		},

		checkTermAgreement() {
			if (this.termAgreement.checked) {
				this.isCheckedTermAgreement = true;
			} else {
				this.isCheckedTermAgreement = false;
			}
		},

		showWarningMsgForAWhile(warningMsg) {
			warningMsg.style.visibility = "visible";
			setTimeout(() => {
				warningMsg.style.visibility = "hidden";
			}, this.WARNING_MSG_SHOW_TIME);
		},

		checkAllCondition() {
			let rsvFormSummitBtnWrap = this.rsvFormSummitBtn.closest("div.bk_btn_wrap");
			if (this.isValidAllCondition()) {
				rsvFormSummitBtnWrap.classList.remove("disable");
			} else {
				rsvFormSummitBtnWrap.classList.add("disable");
			}
		},

		isValidAllCondition() {
			if (this.totalTicketCount > 0 && this.isNameValid && this.isTelValid &&
				this.isEmailValid && this.isCheckedTermAgreement) {
				return true;
			} else {
				return false;
			}
		},

		addTotalTicket() {
			this.totalTicketCount++;
			this.totalTicketCountElm.innerText = reserve.bookingForm.totalTicketCount;
		},

		subTotalTicket() {
			this.totalTicketCount--;
			this.totalTicketCountElm.innerText = reserve.bookingForm.totalTicketCount;
		},

		setTermClickEvent() {
			reserve.bookingForm.termAgreementBody.addEventListener("click", function(event) {
				const clickedTagName = event.target.tagName;
				if ((clickedTagName === "SPAN" && event.target.classList.contains("btn_text")) 
				|| clickedTagName === "A" || clickedTagName === "I") {
					event.preventDefault();
					event.stopPropagation();
					const agreement = event.target.closest("div.agreement");
					if (agreement.classList.contains("open")) {
						this.hideAgreement(agreement);
					} else {
						this.showAgreement(agreement);
					}
				}
			}.bind(this));
		},

		showAgreement(agreement) {
			agreement.classList.add("open");
			agreement.querySelector("span.btn_text").innerText = "접기";
			agreement.querySelector("i.fn").classList.replace("fn-down2", "fn-up2");
		},

		hideAgreement(agreement) {
			agreement.classList.remove("open");
			agreement.querySelector("span.btn_text").innerText = "보기";
			agreement.querySelector("i.fn").classList.replace("fn-up2", "fn-down2");
		},

		setFormSubmitEvent() {
			this.rsvFormSummitBtn.addEventListener("click", function() {
				const RSV_POST_URL = "/api/reservationInfos";
				this.sendPostAjax(RSV_POST_URL, this.getRsvReqBody(), this.rsvPostCallBack);
			}.bind(this));
		},

		sendPostAjax(url, reqBody, callback) {
			let rsvPostReq = new XMLHttpRequest();
			rsvPostReq.onreadystatechange = this.rsvPostCallBack;
			rsvPostReq.open("POST", url);
			rsvPostReq.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
			rsvPostReq.send(JSON.stringify(reqBody));
		},

		getRsvReqBody() {
			const rsvReqData = {
				"productId": reserve.productId,
				"reservationName": this.rsvForm.name.value,
				"reservationTel": this.rsvForm.tel.value,
				"reservationEmail": this.rsvForm.email.value,
				"reservationDate": this.rsvYearMonthDay,
				"reservationPrices": this.getProductPrices()
			}
			return rsvReqData;
		},

		getProductPrices() {
			const productPrices = reserve.bookingTicket.ticketBody.querySelectorAll("div.qty");
			const prices = [];
			productPrices.forEach((productPrice) => {
				price = {
					"productPriceId": productPrice.dataset.productPriceId,
					"count": productPrice.querySelector("input.count_control_input").value
				};
				prices.push(price);
			});
			return prices;
		},

		rsvPostCallBack() {
			if(this.readyState == 4) {
				if (this.status == 200) {
					alert("예약이 완료되었습니다.");
					window.location.href = `/mainpage`;
				} else {
					alert("예약이 되지않았습니다");
				}
			}
		},

	},

}
