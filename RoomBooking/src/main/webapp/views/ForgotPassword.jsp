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
<link rel="stylesheet" href="/RoomBooking/css/ForgotPassword.css">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<%@include file="/views/compoment/header.jsp"%>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-6">
					<div class="border border-dark rounded-4 mt-2 mb-2">
						<div class="container mt-3">
							<form action="/RoomBooking/ForgotPass/SendMail" method="POST">
								<div class="col-6 offset-3 mb-2">
									<div class="figure">
										<img class="img-fluid border rounded-4"
											src="/RoomBooking/img/iconblock.jpg" alt="..."
											style="width: 300px; height: 250px;">
									</div>
								</div>
								<h4 class="text-center">Quên mật khẩu</h4>
								<p class="text-center">Hãy nhập email và kiểm tra email của
									bạn</p>
								<div class="mb-3">
									<!--  -->
									<input type="text" class="form-control rounded-4" id="email"
										aria-describedby="email" placeholder="Nhập email của bạn"
										name="YourEmail" onblur="validateEmail()">
									<div id="emailError" class="form-text text-danger">${message}</div>
									<!--  -->
								</div>
								<button type="submit"
									class="btn btn-primary mb-3 offset-3 me-2 w-50 rounded-5">Gửi
									mật khẩu đến email</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/views/compoment/footer.jsp"%>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		function validateForm() {
			var emailInput = document.getElementById("email");
			var email = emailInput.value.trim();
			var emailError = document.getElementById("emailError");

			// Kiểm tra nếu email không được nhập
			if (email === "") {
				emailError.textContent = "Vui lòng nhập địa chỉ email.";
				emailInput.focus(); // Di chuyển con trỏ đến ô input
				return false;
			}

			// Kiểm tra định dạng email
			var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			if (!emailRegex.test(email)) {
				emailError.textContent = "Địa chỉ email không hợp lệ.";
				emailInput.focus(); // Di chuyển con trỏ đến ô input
				return false;
			}

			// Xóa thông báo lỗi nếu email hợp lệ
			emailError.textContent = "";
			return true;
		}
	</script>

</body>
</html>
