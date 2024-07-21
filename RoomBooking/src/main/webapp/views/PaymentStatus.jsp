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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<style>
.h-400px{
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
		<%@include file="/views/compoment/header.jsp"%>

		<c:url var="account" value="/account/status">
		</c:url>
		<div class="h-400px">
		<div class="container col-9 mt-3">
			<nav class="menu bg-white">
				<li class="li-menu"><a href="${account}/history">Lịch sử
						đặt phòng</a></li>
				<li class="li-menu"><a href="${account}/check-in">Chờ nhận
						phòng</a></li>
				<li class="li-menu"><a href="${account}/cancle-booking">Lịch
						sử huỷ phòng</a></li>
			</nav>
			<!-- 1 item -->
		</div>
		<jsp:include page="${view}"></jsp:include>
		</div>
		<%@include file="/views/compoment/footer.jsp"%>
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
