document.addEventListener("DOMContentLoaded", function() {
    review.init();
    review.loadReviewPage();
});

let review = {
    reviewAvgScoreGraph: document.querySelector("#review_avg_score_graph"),
    reviewAvgScore: document.querySelector("#review_avg_score"),
    reviewCount: document.querySelector("#review_count"),
    reviewList: document.querySelector("#review_list"),
    reviewGuide: document.querySelector("#review_guide"),
    TOTAL_AVG_SCORE: 5.0,

    init() {
    	this.ajaxUtil = new AjaxUtil();
    	
        let reviewTemplate = document.querySelector("#review_template").innerText;
        this.bindReviewTemplate = Handlebars.compile(reviewTemplate);
        Handlebars.registerHelper("firstImageSaveFileName", function(reservationUserCommentImages) {
            return reservationUserCommentImages[0].saveFileName;
        });
    },

    loadReviewPage() {
        productId = document.querySelector("#review_main").dataset.productId;
        const GET_REVIEWS_URL = `/api/reservationUserComments/${productId}`;
        this.ajaxUtil.sendGetAjax(GET_REVIEWS_URL)
        			.then(responseText => {
        	            const response = JSON.parse(responseText);
        	            review.loadReviews(response.comments, response.avgScore);
        			})
        			.catch(status => {
        				console.log(`then err: ${status}`);
        			});
    },

    loadReviews(reviews, avgScore) {
        this.addReviewsToReviewList(reviews);
        this.setReviewCount(reviews.length);
        this.setAvgScoreAndGraph(avgScore);
    },

    setReviewCount(totalCount){
        this.reviewCount.innerText = `${totalCount}건`;
    },

    setAvgScoreAndGraph(avgScore) {
        this.reviewAvgScore.innerText = avgScore.toFixed(1);
        const AVG_SCORE_RATE = avgScore / this.TOTAL_AVG_SCORE * 100;
        this.reviewAvgScoreGraph.style.width = `${AVG_SCORE_RATE}%`;
    },

    addReviewsToReviewList(reviews) {
        let reviewHtml = "";
        reviewHtml = this.bindReviewTemplate(reviews);
        this.reviewList.innerHTML = reviewHtml;
    },

}
