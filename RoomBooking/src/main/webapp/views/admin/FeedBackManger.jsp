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
	<style type="text/css">
	.img-custom{
	height: 50px;
	width: 100px
	}
	</style>
</head>
<body class="bg-light">
	<div class="m-4">
		<!-- 1 item -->
		<table class="table table-hover">
			<thead class="table-dark">
				<tr >
					<th scope="col">Mã đánh giá</th>
					<th scope="col">Người đánh giá</th>
					<th scope="col">Thời gian</th>
					<th scope="col">Nội dung</th>
					<th scope="col">Mã phòng</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="fb" items="${listFB}">
					<tr>
						<th >${fb.getFeedbackId()}</th>
						<th>${fb.getUserId()}</th>
						<td>${fb.getTimestamp()}</td>
						<td>${fb.getMessage()}</td>
						<td>${fb.getRoomID()}</td>
						<td><a name="" id="" class="btn btn-danger text-center" href="/RoomBooking/admin/management/feedback-manager/delete/${fb.getFeedbackId()}"
							role="button">Xoá</a></td>
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
