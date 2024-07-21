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
.img-custom {
	height: 50px;
	width: 100px
}
</style>
</head>
<body class="bg-light">
	<div class="mt-4">
		<!-- 1 item -->
		<table class="table table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col">Hình ảnh</th>
					<th scope="col">Tên phòng</th>
					<th scope="col">Trạng thái</th>
					<th scope="col">Địa chỉ</th>
					<th scope="col">Tiện nghi</th>
					<th scope="col">Giá/ Ngày</th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="room" items="${rooms}">
					<tr>
						<th><img
							style="width: 100px; height: 50px; object-fit: cover;"
							src="http://localhost:8000/RoomBooking/img/${room.getImage()}" alt="${room.getImage()}" /></th>
						<th>${room.getRoomName()}</th>
						<td>${room.getAvailability()}</td>
						<td>${room.getLocation()}</td>
						<td>${room.getAmenities()}</td>
						<td>${room.getPrice()}</td>
						<td><a name="" id="" class="btn btn-primary text-center"
							href="/RoomBooking/admin/management/room-manager/edit/${room.getId()}"
							role="button">Edit</a></td>
						<td><a name="" id="" class="btn btn-danger text-center"
							href="/RoomBooking/admin/management/room-manager/delete/${room.getId()}"
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
