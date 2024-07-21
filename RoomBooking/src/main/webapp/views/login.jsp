<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

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
<link rel="stylesheet" href="/RoomBooking/css/login.css">

</head>
<body>
	<div class="container">
		<header class="row">
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
				<div class="container-fluid">
					<!-- Logo -->
					<a class="navbar-brand" href="#"><img
						src="/RoomBooking/img/logo.png" alt="Logo"></a>
					<!-- Navbar Toggle Button -->
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<!-- Navbar Content -->
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
							<!-- Navigation Links -->
							<li class="nav-item"><a class="nav-link active"
								href="/RoomBooking/Home">Trang chủ</a></li>
							<li class="nav-item"><a class="nav-link active" href="/RoomBooking/views/contact.jsp">Liên
									Hệ</a></li>
							<li class="nav-item"><a class="nav-link active" href="/RoomBooking/views/FAQ.jsp">Hỏi
									Đáp</a></li>
							<!-- Dropdown Menu -->
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-expanded="false">Tài
									khoản</a>
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<li><a class="dropdown-item"
										href="/RoomBooking/account/info">Thông tin cá nhân</a></li>
									<li><a class="dropdown-item" href="#">Thông báo</a></li>
									<li><a class="dropdown-item"
										href="/RoomBooking/account/login">Đăng nhập</a></li>
									<li><a class="dropdown-item"
										href="/RoomBooking/account/register">Đăng ký</a></li>
									<li><a class="dropdown-item"
										href="/RoomBooking/account/logout">Đăng xuất</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
		<main>
			<div class="container-fluid mt-3 mb-5">
				<c:if test="${not empty messageResponse}">
					<div class="alert alert-${alert}" role="alert">${messageResponse}</div>
				</c:if>
				<div class="col-6 offset-3 row border border-dark rounded-5">
					<div id="col-6" class="col-sm-6">
						<div class="offset-2 col-8 mt-5 text-center">
							<img class="rounded-5 logo text-center" alt=""
								src="/RoomBooking/img/logofooter.png" />
						</div>
					</div>
					<div class="col-sm-6 mt-1 p-3 form-login">
						<form action="/RoomBooking/account/login" method="post">
							<h2 class="text-center mb-4">Đăng Nhập</h2>
							<div class="mb-4">
								<input type="text" value=""
									class="form-control border-dark rounded-5" name="username"
									id="usernameInput" aria-describedby="helpId"
									placeholder="Tên Đăng Nhập" />
							</div>
							<div class="mb-4">
								<input type="password" value=""
									class="form-control border-dark rounded-5" name="password"
									id="passwordInput" aria-describedby="helpId"
									placeholder="Mật khẩu" />
							</div>
							<div class="mb-3 form-check">
								<input type="checkbox" class="form-check-input"
									id="showPasswordCheckbox"> <label
									class="form-check-label" for="showPasswordCheckbox">Hiện
									mật khẩu</label>
							</div>
							<button type="submit"
								class="btn btn-primary border border-dark rounded-5 offset-4 mb-3">
								Đăng nhập</button>
							<div class="text-center mb-3">
								<a class="text-decoration-none text-dark"
									href="/RoomBooking/views/ForgotPassword.jsp">Quên mật khẩu</a>
							</div>
							<div class="text-center mb-2">
								Bạn đã có tài khoản? <a class=""
									href="http://localhost:8080/RoomBooking/account/register">Đăng
									ký</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</main>
		<footer class="row bg-dark text-light py-5 mt-2">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-md-3">
						<img src="/RoomBooking/img/logofooter.png" alt="Logo"
							style="max-width: 100px" />
					</div>
					<div class="col-md-4">
						<h3>Về chúng tôi</h3>
						<p>Cao đẳng thực hành FPT Polytechnic Cần Thơ</p>
					</div>

					<div class="col md-5" style="min-height: 150px">
						<h5>Theo dõi chúng tôi</h5>
						<p>
							Phone: 09xx xxx xxx <br /> Email: abc@gmail.com <br /> Địa Chỉ:
							Tòa nhà FPT Cần Thơ, Phường Thường Thạnh, Quận Cái Răng, TP Cần
							Thơ
						</p>
					</div>
				</div>
			</div>
		</footer>

	</div>

	<!--link js gắn trực tiếp trên bootstrap-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

	<script>
		$(document).ready(function() {
			$('#showPasswordCheckbox').change(function() {
				var isChecked = $(this).is(':checked');
				if (isChecked) {
					$('#passwordInput').attr('type', 'text');
				} else {
					$('#passwordInput').attr('type', 'password');
				}
			});
		});
	</script>

</body>
</html>
