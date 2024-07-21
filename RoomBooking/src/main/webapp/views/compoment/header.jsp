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
<!-- Jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<head>
<meta charset="UTF-8">
</head>
<body>
	<header class="row">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container-fluid">
				<!-- Logo -->
				<a class="navbar-brand" href="/RoomBooking/Home"><img
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
