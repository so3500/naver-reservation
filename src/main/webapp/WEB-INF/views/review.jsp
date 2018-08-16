<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="/css/style.css" rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
<style>
.container_visual {
	height: 414px;
}
</style>
</head>
<body>
	<div id="container">
		<div id="review_main" class="ct" data-product-id=${product.id}>
			<div class="ct_wrap">
				<div class="top_title">
					<a href="/detail?id=${product.displayInfoId}" class="btn_back" title="이전 화면으로 이동">
						<i class="fn fn-backward1"></i>
					</a>
					<h2>
						<span class="title">${product.description}</span>
					</h2>
				</div>

				<div class="section_review_list">
					<div class="review_box">
						<h3 class="title_h3">예매자 한줄평</h3>
						<div class="short_review_area">
							<div class="grade_area">
								<!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
								<span class="graph_mask"> <em class="graph_value"
									id="review_avg_score_graph" style="width: 0%;"></em>
								</span> <strong class="text_value"> <span
									id="review_avg_score">0.0</span> <em class="total">5.0</em>
								</strong> <span class="join_count"><em id="review_count"
									class="green">0건</em> 등록</span>
							</div>
							<ul id="review_list" class="list_short_review">
							</ul>
						</div>
						<p class="guide">
							<i class="spr_book2 ico_bell"></i> <span id="review_guide">네이버
								예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>


	<script src="/js/lib/handlebars-v4.0.11.js"></script>
	<script src="/js/lib/jquery-3.3.1.min.js"></script>
	<script src="/js/utils.js"></script>
	<script src="/js/review.js"></script>
	<script type="rv-template" id="review_template">
		{{#each this}}
			<li class="list_item">
				<div>
					{{#if reservationUserCommentImages}}
					<div class="review_area">
						<div class="thumb_area">
							<a href="#" class="thumb" title="이미지 크게 보기">
								<img width="90" height="90" class="img_vertical_top" src="/{{firstImageSaveFileName reservationUserCommentImages}}" alt="리뷰이미지">
							</a>
							<span class="img_count" style="display:none;">1</span>
						</div>
					{{else}}
					<div class="review_area no_img">
					{{/if}}
						<h4 class="resoc_name">${product.description}</h4>
						<p class="review">{{comment}}</p>
					</div>
					<div class="info_area">
						<div class="review_info">
							<span class="grade">{{score}}.0</span>
							<span class="name">{{reservationEmail}}</span>
							<span class="date">{{reservationDate}} 방문</span>
						</div>
					</div>
				</div>
			</li>
		{{/each}}
	</script>
</body>
</html>
