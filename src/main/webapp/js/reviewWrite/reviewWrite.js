document.addEventListener("DOMContentLoaded", function() {
	ajaxUtil = new AjaxUtil();
	reviewWrite = new ReviewWrite();
	formValidChecker = new FormValidChecker();
	reviewRating = new ReviewRating();
	reviewContent = new ReviewContent();
	reviewImage = new ReviewImage();
	
	formValidChecker.checkAllCondition();
	reviewWrite.setFormSubmitEvent();
	reviewRating.initReviewRating();
	reviewRating.setReviewRatingEvent();
	reviewContent.setContentEvent();
	reviewImage.setImageEvent();
});

function ReviewWrite() {
	this.productId = parseInt(document.querySelector("div.ct").dataset.productId);
	this.displayInfoId = parseInt(document.querySelector("div.ct").dataset.displayInfoId);
	this.reservInfoId = parseInt(document.querySelector("div.ct").dataset.reservInfoId);
	this.formSubmitBtn = document.querySelector("button.bk_btn");
}

ReviewWrite.prototype.setFormSubmitEvent = function() {
	this.formSubmitBtn.addEventListener("click", event => {
		const REVIEW_POST_URL = "/api/reservationUserComments";
		let reviewFormData = new FormData();
		reviewFormData.append("productId", this.productId);
		reviewFormData.append("reservationInfoId", this.reservInfoId);
		reviewFormData.append("score", reviewRating.getReviewRatingScore());
		reviewFormData.append("comment", reviewContent.getContent());
		if(reviewImage.hasImage()){
			reviewFormData.append("reviewImage", reviewImage.getImage());
		}
		ajaxUtil.sendFormDataPostAjax(REVIEW_POST_URL, reviewFormData)
				.then(responseText => {
					location.replace(`/review?id=${this.displayInfoId}`);
				})
				.catch(status => {
					alert("리뷰 작성이 실패하였습니다");
					console.log(`review post err: ${status}`);
				})
	});
}

ReviewWrite.prototype.enableFormSubmitBtn = function() {
	this.formSubmitBtn.style.backgroundColor = "#0aba16"
		this.formSubmitBtn.style.pointerEvents = "auto";
}

ReviewWrite.prototype.disableFormSubmitBtn = function() {
	this.formSubmitBtn.style.backgroundColor = "#e9ecef"
	this.formSubmitBtn.style.pointerEvents = "none";
}


function ReviewRating() {
	this.reviewRating = document.querySelector("div.rating");
	this.reviewRatingScore = document.querySelector("#review_score");
	this.ratingScore = 0;
}

ReviewRating.prototype.initReviewRating = function() {
	this.reviewRating.querySelectorAll("input").forEach(reviewStar => {
		reviewStar.checked = false;
	});
}

ReviewRating.prototype.setReviewRatingEvent = function() {
	this.reviewRating.addEventListener("click", event => {
		let clickedReviewStar = event.target.closest("input");
		
		if(clickedReviewStar === null) {
			return;
		}
		
		let checked = true;
		this.ratingScore = 0;
		this.reviewRating.querySelectorAll("input").forEach((reviewStar, idx) => {
			reviewStar.checked = checked;
			if(reviewStar === clickedReviewStar) {
				checked = false;
				this.ratingScore = idx + 1;
			}
		});
		
		this.reviewRatingScore.innerText = this.ratingScore;
		this.reviewRatingScore.classList.remove("gray_star");
		formValidChecker.checkAllCondition();
	});
}

ReviewRating.prototype.getReviewRatingScore = function() {
	return this.ratingScore;
}

function ReviewContent() {
	this.content = document.querySelector(".review_textarea");
	this.contentInfo = document.querySelector("a.review_write_info");
	this.contentLength = document.querySelector("#content_length");
}

ReviewContent.prototype.setContentEvent = function() {
	this.contentInfo.addEventListener("click", event => {
		this.contentInfo.classList.add("hide");
		this.content.focus();
	});
	
	this.content.addEventListener("keyup", () => {
		this.contentLength.innerText = this.content.value.length;
		formValidChecker.checkAllCondition();
	});
	
	this.content.addEventListener("blur", () => {
		if(this.content.value === "") {
			this.contentInfo.classList.remove("hide");
		}
	});
}

ReviewContent.prototype.getContent = function() {
	return this.content.value;
}

function ReviewImage() {
	this.imageFileInput = document.querySelector("#review_image_file_open_input");
	this.imageThum = document.querySelector("img.item_thumb");
	this.deleteImageBtn = document.querySelector("#delete_image_btn");
}

ReviewImage.prototype.setImageEvent = function() {
	this.imageFileInput.addEventListener("change", event => {
		const image = event.target.files[0];
		if(formValidChecker.isValidImageType(image)){
			this.imageThum.parentNode.style.display = "inline-block";
			this.imageThum.src = window.URL.createObjectURL(image);
		} else {
			alert("upload image file!");
			console.warn(`invalid image file type: ${image.type}`);
		}
	});
	
	this.deleteImageBtn.addEventListener("click", event => {
		event.stopPropagation();
		this.imageThum.parentNode.style.display = "none";
		this.imageThum.src = "";
	});
}

ReviewImage.prototype.getImage = function() {
	return this.imageFileInput.files[0];
}

ReviewImage.prototype.hasImage = function () {
	return this.imageFileInput.files.length > 0;
}


function FormValidChecker() {}

FormValidChecker.prototype.isValidImageType = function(image) {
	const result = ([ 'image/jpeg', 'image/png', 'image/jpg' ].indexOf(image.type) > -1);
	return result;
}

FormValidChecker.prototype.checkAllCondition = function() {
	this.isValidRating = reviewRating.ratingScore > 0;
	this.isValidContent = reviewContent.content.value.length >= 5;
	
	if(this.isValidAll()) {
		reviewWrite.enableFormSubmitBtn();
	} else {
		reviewWrite.disableFormSubmitBtn();
	}
}

FormValidChecker.prototype.isValidAll = function() {
	if(this.isValidRating && this.isValidContent) {
		return true;
	} else {
		return false;
	}
}

