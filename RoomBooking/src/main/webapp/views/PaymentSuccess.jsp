<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet" href="/RoomBooking/css/payment_method.css">


</head>
<body>
	<div class="container">
		<header class="row">
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
				<div class="container">
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
								href="http://localhost:8080/RoomBooking/Home">Trang chủ</a></li>
							<li class="nav-item"><a class="nav-link active" href="#">Đặt
									Phòng</a></li>
							<li class="nav-item"><a class="nav-link active" href="#">Liên
									Hệ</a></li>
							<li class="nav-item"><a class="nav-link active" href="#">Hỏi
									Đáp</a></li>
							<!-- Dropdown Menu -->
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-expanded="false">Tài
									khoản</a>
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<li><a class="dropdown-item" href="UserInformation.jsp">Thông
											tin cá nhân</a></li>
									<li><a class="dropdown-item" href="#">Thông báo</a></li>
									<li><a class="dropdown-item" href="login.jsp">Đăng
											nhập</a></li>
									<li><a class="dropdown-item" href="Register.jsp">Đăng
											ký</a></li>
									<li><a class="dropdown-item" href="#">Đăng xuất</a></li>
								</ul></li>
						</ul>
					</div>
			</nav>
		</header>
		<main>
			<div class="container">
				<h1 class="text-success">Thanh toán thành công</h1>
			</div>
		</footer>

	</div>

	<!-- Bootstrap JavaScript Libraries -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
</body>
</html>