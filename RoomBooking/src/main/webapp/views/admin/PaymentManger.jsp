<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
</head>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>
	<div class="table-responsive">
		<table class="table table-light table-hover table-bordered">
			<thead>
				<tr>
					<th scope="col">STT</th>
					<th scope="col">Mã thanh toán</th>
					<th scope="col">Mã người dùng</th>
					<th scope="col">Mã booking</th>
					<th scope="col">Thời gian thanh toán</th>
					<th scope="col">Phương thức thanh toán</th>
					<th scope="col">Ghi chú</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${itemsManagerPayment}"
					varStatus="loop">
					<tr class="">
						<td scope="row">${loop.index + 1}</td>
						<td>${item.id}</td>
						<td>${item.userid}</td>
						<td>${item.bookingid}</td>
						<td>${item.timestamp}</td>
						<td>${item.paymentmethod}</td>
						<td>${item.note}</td>
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