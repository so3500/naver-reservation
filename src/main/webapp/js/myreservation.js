document.addEventListener("DOMContentLoaded", function() {
	myReservInfo = new MyReservInfo();
	myReservInfo.setSummaryBoardEvent();
	myReservInfo.setReservInfoCancelEvent();
	myReservInfo.setReservInfoCancelPopupEvent();
});

function MyReservInfo() {
	this.ajaxUtil = new AjaxUtil();
	
	this.summaryBoard = document.querySelector("ul.summary_board");
	this.confirmedReservInfoCnt = document.querySelector("#confirmed_reserv_cnt");
	this.canceledReservInfoCnt = document.querySelector("#canceled_reserv_cnt");
	this.confirmedReservInfos = document.querySelector("li.card.confirmed");
	this.usedReservInfos = document.querySelector("li.card.used");
	this.canceledReservInfos = document.querySelector("li.card.used.cancel");
	this.confirmedReservInfos = document.querySelector("li.card.confirmed");
	this.cancelPopup = document.querySelector("div.popup_booking_wrapper");
	this.cancelReservItem = null;

}

MyReservInfo.prototype.setSummaryBoardEvent = function() {
	this.summaryBoard.addEventListener("click", function(event) {
		let clickedTag = event.target;
		let clickedTagName = event.target.tagName;
		if(clickedTagName === "A" || clickedTagName === "I" 
			|| clickedTagName === "EM" || clickedTagName === "SPAN") {
			let linkSummaryBoard = clickedTag.closest("a.link_summary_board");
			
			this.summaryBoard.querySelector("a.link_summary_board.on").classList.remove("on");
			linkSummaryBoard.classList.add("on");
			
			let summaryBoard = linkSummaryBoard.parentNode;
			if(summaryBoard.classList.contains("summary_all")) {
				this.confirmedReservInfos.style.display = "block";
				this.usedReservInfos.style.display = "block";
				this.canceledReservInfos.style.display = "block";
			} else if (summaryBoard.classList.contains("summary_confirmed")) {
				this.confirmedReservInfos.style.display = "block";
				this.usedReservInfos.style.display = "none";
				this.canceledReservInfos.style.display = "none";
			} else if (summaryBoard.classList.contains("summary_used")) {
				this.confirmedReservInfos.style.display = "none";
				this.usedReservInfos.style.display = "block";
				this.canceledReservInfos.style.display = "none";
			} else if (summaryBoard.classList.contains("summary_canceled")) {
				this.confirmedReservInfos.style.display = "none";
				this.usedReservInfos.style.display = "none";
				this.canceledReservInfos.style.display = "block";
			}
		}
	}.bind(this));
}

MyReservInfo.prototype.changeSummaryBoardCnt = function() {
	this.confirmedReservInfoCnt.innerText = parseInt(this.confirmedReservInfoCnt.innerText) - 1;
	this.canceledReservInfoCnt.innerText = parseInt(this.canceledReservInfoCnt.innerText) + 1;
}

MyReservInfo.prototype.setReservInfoCancelEvent = function() {
	this.confirmedReservInfos.addEventListener("click", function(event) {
		let clickedTag = event.target;
		if (clickedTag.tagName === "SPAN" || clickedTag.tagName === "BUTTON") {
			if (clickedTag.closest("div").classList.contains("booking_cancel")) {
				event.stopPropagation();
				event.preventDefault();
				this.showCancelPopup(clickedTag.closest("article.card_item"));
			}
		}
	}.bind(this));
}

MyReservInfo.prototype.cancelReservInfo = function() {
	let reservInfoId = this.cancelReservItem.dataset.reservInfoId;
	const RESERV_PUT_URL = `/api/reservationInfos?reservationId=${reservInfoId}`;
	this.ajaxUtil.sendPutAjax(RESERV_PUT_URL)
			.then(responseText => {
				myReservInfo.cancelReservItem.querySelector("div.booking_cancel").remove();
				myReservInfo.canceledReservInfos.append(this.cancelReservItem);
				myReservInfo.hideCancelPopup();
				myReservInfo.changeSummaryBoardCnt();
			})
			.catch(err => {
				console.log(`put err(reservInfo): ${err}`);
			});
}

MyReservInfo.prototype.showCancelPopup = function(reservInfo) {
	this.cancelReservItem = reservInfo;
	let description = reservInfo.querySelector("h4.tit").innerText;
	let reservDate = reservInfo.querySelector("em.item_dsc.date").innerText;
	
	this.cancelPopup.querySelector("h1.pop_tit > span").innerText = description;
	this.cancelPopup.querySelector("h1.pop_tit > small > span.reserv_date").innerText = reservDate;
	this.cancelPopup.style.display = "block";
}

MyReservInfo.prototype.hideCancelPopup = function() {
	this.cancelReservItem = null;
	this.cancelPopup.dataset.reservInfoId = -1;
	this.cancelPopup.style.display = "none";
}

MyReservInfo.prototype.setReservInfoCancelPopupEvent = function() {
	this.cancelPopup.querySelector("div.btn_gray").addEventListener("click", function(event) {
		event.stopPropagation();
		event.preventDefault();
		this.hideCancelPopup();
	}.bind(this));
	
	this.cancelPopup.querySelector("div.btn_green").addEventListener("click", function(event) {
		this.cancelReservInfo();
	}.bind(this))
	
	this.cancelPopup.querySelector("a.popup_btn_close").addEventListener("click", function(event) {
		event.stopPropagation();
		event.preventDefault();
		this.hideCancelPopup();
	}.bind(this));
	
}


