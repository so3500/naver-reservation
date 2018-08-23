document.addEventListener("DOMContentLoaded", function() {
	myReservInfoObj = new myReservInfo();
	myReservInfoObj.setReservInfoCancelEvent();
});

function myReservInfo() {
	this.confirmedReservInfoCnt = document.querySelector("#confirmed_reserv_cnt");
	this.usedReservInfoCnt = document.querySelector("#used_reserv_cnt");
	this.canceldReservInfoCnt = document.querySelector("#canceld_reserv_cnt");
	this.confirmedReservInfos = document.querySelector("li.card.confirmed");
	this.usedReservInfos = document.querySelector("li.card.used");
	this.cancledReservInfos = document.querySelector("li.card.used.cancel");
	this.confirmedReservInfos = document.querySelector("li.card.confirmed");

}

myReservInfo.prototype.setReservInfoCancelEvent = function() {
	this.confirmedReservInfos.addEventListener("click", function(event) {
		let clickedTag = event.target;
		if (clickedTag.tagName === "SPAN" || clickedTag.tagName === "BUTTON") {
			if (clickedTag.closest("div").classList.contains("booking_cancel")) {
				// TODO 검증 this.cancelReservInfo(clickedTag.closest("article.card_item"));
			}
		}
	}.bind(this));
}

myReservInfo.prototype.cancelReservInfo = function(reserv) {
	let reservId = reserv.dataset.reservId;
	const RESERV_PUT_URL = `/api/reservaionInfos?reservationId=${reservId}`;
	const ajaxUtil = new ajaxUtil();
	ajaxUtil.sendPutAjax("/api/reservationInfos/" + rsvId)
			.then(msg => {
				console.log(mst);
			});

}
