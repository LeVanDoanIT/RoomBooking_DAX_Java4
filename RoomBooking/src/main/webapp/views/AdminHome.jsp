<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Thông báo</title>
<!-- Required meta tags -->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<!-- Bootstrap CSS v5.2.1 -->
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
<style>
.h-400px {
	min-height: 400px;
}

.menu {
	border: 1px;
	display: flex;
	justify-content: center;
	font-size: large;
}

.menu .li-menu {
	list-style: none;
	margin: 10px;
}

.li-menu a {
	text-decoration: none;
	color: black;
	padding: 20px;
}

.li-menu a:hover {
	color: red;
	border-bottom: 2px solid red;
}
</style>

</head>

<body class="bg-light">
	<div class="container">
		<%@include file="/views/compomentAdmin/header.jsp"%>

		<c:url var="admin" value="/admin/management">
		</c:url>
		<div class="h-400px mb-5" >
			<div class="container mt-3">
				<nav class="menu bg-white">
					<li class="li-menu"><a href="${admin}/room-manager">Quản lý
							phòng</a></li>
					<li class="li-menu"><a href="${admin}/user-manager">Quản
							lý người dùng</a></li>
					<li class="li-menu"><a href="${admin}/payment-manager">Quản lý
							thanh toán</a></li>
					<li class="li-menu"><a href="${admin}/feedback-manager">Quản lý
							phản hồi</a></li>
				</nav>
				<!-- 1 item -->
			</div>
			<jsp:include page="${view}"></jsp:include>
		</div>
		<%@include file="/views/compomentAdmin/footer.jsp"%>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
