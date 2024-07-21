<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sign Up Form</title>
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
<!-- Jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div class="container">
		<%@include file="/views/compoment/header.jsp"%>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6">
					<div class="border border-dark rounded-4 mt-2 mb-1">
						<div class="container mt-3">
							<h4 class="text-center mb-3">Đăng Ký Tài Khoản Mới</h4>
							<form action="" method="POST">
								<div class="mb-3">
									<label for="username" class="form-label">Tên Đăng Nhập</label>
									<input type="text" class="form-control rounded-4" id="username"
										aria-describedby="usernameHelp" name="username"> <span
										class="text-danger">${alertUsername}</span>
								</div>
								<div class="mb-3">
									<label for="fullname" class="form-label">Họ và Tên</label> <input
										type="text" class="form-control rounded-4" id="fullname"
										aria-describedby="fullnameHelp" name="fullname"> <span
										class="text-danger">${alertFullname}</span>
								</div>
								<div class="mb-3">
									<label for="password" class="form-label">Mật Khẩu</label> <input
										type="password" class="form-control rounded-4" id="password"
										aria-describedby="passwordHelp" name="password"> <span
										class="text-danger">${alertPassword}</span>
								</div>
								<div class="mb-3">
									<label for="confirmPassword" class="form-label">Xác
										Nhận Mật Khẩu</label> <input type="password"
										class="form-control rounded-4" id="confirmPassword"
										aria-describedby="confirmPasswordHelp" name="confirmPassword">
									<span class="text-danger">${alertConfirmPass}</span>
								</div>
								<div class="mb-3">
									<label for="email" class="form-label">Email</label> <input
										type="email" class="form-control rounded-4" id="email"
										aria-describedby="emailHelp" name="email"> <span
										class="text-danger">${alertEmail}</span>
								</div>
								<div class="mb-3">
									<label for="phone" class="form-label">Số điện thoại</label> <input
										type="tel" class="form-control rounded-4" id="phone"
										aria-describedby="phoneHelp" name="phone"> <span
										class="text-danger">${alertPhone}</span>
								</div>
								<div class="mb-3 form-check">
									<input type="checkbox" class="form-check-input"
										id="showPasswordCheckbox"> <label
										class="form-check-label" for="showPasswordCheckbox">Hiện
										mật khẩu</label>
								</div>
								<span class="text-danger mb-1">${message}</span>
								<button type="submit"
									class="btn btn-primary mb-2 offset-3 me-2 w-50 rounded-5">Đăng
									kí</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/views/compoment/footer.jsp"%>
	</div>
	<!--link js gắn trực tiếp trên bootstrap-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#showPasswordCheckbox').change(function() {
				var isChecked = $(this).is(':checked');
				if (isChecked) {
					$('#password').attr('type', 'text');
					$('#confirmPassword').attr('type', 'text');
				} else {
					$('#password').attr('type', 'password');
					$('#confirmPassword').attr('type', 'password');
				}
			});
		});
	</script>

</body>
</html>