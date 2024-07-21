<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
</head>

<body class="bg-light">
	<div class="container col-9 mt-5">
		<!-- 1 item -->
		<table class="table table-hover">
			<thead class=" table-dark">
				<tr class="text-center">
					<th scope="col">Ngày đặt</th>
					<th scope="col">Ngày nhận phòng</th>
					<th scope="col">Ngày rời đi</th>
					<th scope="col">Thành Tiền</th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${listBooking}">
					<tr class="text-center">
						<th>${item.getBookDay()}</th>
						<th>${item.getCheckIn()}</th>
						<td>${item.getCheckOut()}</td>
						<td>${item.getTotalPrice()}</td>
						<td><a name="" id="" class="btn btn-primary text-center"
							href="/RoomBooking/product/${item.getRoomId()}" role="button">Chi
								tiết phòng</a></td>
						<td><a name="" id="" class="btn btn-warning text-center"
							href="/RoomBooking/account/status/newfeedback/${item.getBookingId()}" role="button">Đánh giá</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
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
