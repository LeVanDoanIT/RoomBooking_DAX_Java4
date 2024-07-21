<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!--link icon gắn trực tiếp trên bootstrap-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
<!-- AngularJS -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-route.js"></script>
<!--link css gắn trực tiếp trên bootstrap-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" />
<!-- <link rel="stylesheet" href="/RoomBooking/css/ForgotPassword.css">-->
<script src="https://kit.fontawesome.com/e4743b053a.js"
	crossorigin="anonymous"></script>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<style>
.custom-textarea {
	width: 100%;
	height: 200px;
	resize: vertical; /* Cho phép điều chỉnh chiều cao dọc */
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<%@include file="/views/compoment/header.jsp"%>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col col-sm-8 py-5">
					<div class="card">
						<h5 class="card-header">FAQ</h5>
						<div class="card-body">
							<form action="/RoomBooking//account/FAQ" method="post">
								<div class="mb-3">
									<label for="exampleFormControlInput1"
										class="form-label fs-5 fw-bold fst-italic">Tiêu đề: </label> <input
										class="form-control" name="subject" type="Text"
										placeholder="Nhập tiêu đề">
								</div>
								<label for="exampleFormControlInput1"
									class="form-label fs-5 fw-bold fst-italic">Nhập câu hỏi
									của bạn:</label>
								<div class="mb-3">
									<textarea name="body" rows="4" cols="70"
										class="custom-textarea"></textarea>
								</div>
								<hr>
								<button
									class="btn btn-outline-primary fs-5 fw-bold fst-italic float-end">
									Gửi <i class="fa-solid fa-paper-plane"></i>
								</button>
							</form>
						</div>
						<div class="card-footer">${message}</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/views/compoment/footer.jsp"%>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
