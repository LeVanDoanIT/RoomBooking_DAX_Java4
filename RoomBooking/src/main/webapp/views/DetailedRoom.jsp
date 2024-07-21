<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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
<head>
<meta charset="UTF-8">
<title>DetailedRoom-Trúc - Đoàn</title>
<style>
#thumbnailRow .thumbnail {
	width: 100px; /* Đặt kích thước chiều rộng mong muốn */
	height: 100px; /* Đặt kích thước chiều cao mong muốn */
	overflow: hidden;
	/* Đảm bảo ảnh không vượt quá kích thước được chỉ định */
}

#thumbnailRow .thumbnail img {
	width: 100%;
	/* Đảm bảo hình ảnh bên trong thumbnail sẽ đầy đủ kích thước */
	height: auto;
}
</style>
</head>
<body>
	<div class="container">
		<%@include file="/views/compoment/header.jsp"%>

		<div class="row mt-4 mb-4">
			<article class="col-md-12">
				<div class="row">
					<div class="col-md-4">
						<form action="/RoomBooking/Booking" method="post">
							<div class="d-flex flex-column justify-content-between h-100">
								<div class="card mb-4" style="height: 650px">


									<input type="hidden" name="RoomId" value="${room.id}" /> <input
										type="hidden" name="price" value="${room.price}" />


									<div class="card-header text-center">Tên Phòng:
										${room.roomType}</div>
									<div class="card-body">
										<p class="card-text">Loại phòng: ${room.roomType}</p>
										<p class="card-text">Vị trí: ${room.location}</p>
										<p class="card-text">Tiện Nghi: ${room.amenities}</p>
										<p class="card-text">Mô tả: ${room.description}</p>
										<p class="card-text">
											Trạng thái: <span class="text-${classTextCSS}">${room.availability}</span>
										</p>
										<!-- Giá phòng -->
										<strong class="card-text">Giá phòng: <fmt:formatNumber
												value="${room.price}" type="number" groupingUsed="true"
												pattern="###,###" /> VND/day
										</strong>
										<div class="mb-3 mt-3">
											<label for="" class="form-label">Ngày nhận phòng</label> <input
												name="CheckIn" type="datetime-local" class="form-control"
												id="CheckIn" aria-describedby="helpId" placeholder="" /> <span
												class="text-danger">${ErrorNgayCheckIn}</span>
										</div>
										<div class="mb-3">
											<label for="" class="form-label">Ngày trả phòng</label> <input
												name="CheckOut" type="datetime-local" class="form-control"
												id="CheckOut" aria-describedby="helpId" placeholder="" /> <span
												class="text-danger">${ErrorNgayCheckOut}</span>
										</div>

										<button type="submit" onclick="validateDates()"
											class="btn btn-primary w-100 mt-3 fs-5">Đặt phòng
											ngay</button>
										<span
												class="text-danger">${ErrorBookingDateChoose}</span>
									</div>

								</div>
							</div>
						</form>
					</div>

					<div class="col-md-8">
						<div>
							<img src="/RoomBooking/img/can-tho-ecolodge.jpg" id="mainImage"
								alt="Image" style="max-width: 100%; height: 650px;">
							<!-- Đặt kích thước cố định cho hình ảnh hàng trên -->
						</div>

						<!-- Hàng dưới -->
						<div class="row mt-4" id="thumbnailRow">
							<div class="col-md-2">
								<img src="/RoomBooking/img/can-tho-ecolodge%20(1).jpg"
									class="img-thumbnail thumbnail" alt="">
							</div>
							<div class="col-md-2">
								<img src="/RoomBooking/img/can-tho-ecolodge%20(2).jpg"
									class="img-thumbnail thumbnail" alt="">
							</div>
							<div class="col-md-2">
								<img src="/RoomBooking/img/can-tho-ecolodge%20(3).jpg"
									class="img-thumbnail thumbnail" alt="">
							</div>
							<div class="col-md-2">
								<img src="/RoomBooking/img/can-tho-ecolodge%20(4).jpg"
									class="img-thumbnail thumbnail" alt="">
							</div>
							<div class="col-md-2">
								<img src="/RoomBooking/img/can-tho-ecolodge%20(5).jpg"
									class="img-thumbnail thumbnail" alt="">
							</div>
						</div>
						<!-- /Hàng dưới -->
					</div>
				</div>


				<div class="row mt-4">
					<div class="card">
						<div class="card-header">Đánh giá</div>
						<c:forEach var="fb" items="${list}">
							<div class="card-body mb-3">
								<h5 class="card-title">
									<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i>
									<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i>
									<i class="bi bi-star-fill"></i>
								</h5>
								<p class="card-text">${fb.getMessage()}</p>
								<p class="card-text">${fb.getTimestamp()}</p>
							</div>
						</c:forEach>
					</div>
				</div>
			</article>
		</div>
		<%@include file="/views/compoment/footer.jsp"%>
	</div>

	<!-- Jquery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function() {
			$('.thumbnail').on('click', function() {
				console.log('Thumbnail clicked');
				var src = $(this).attr('src');
				$('#mainImage').attr('src', src);
			});
		});
	</script>
	<script>
		function validateDates() {
			var checkInInput = document.getElementById("CheckIn");
			var checkOutInput = document.getElementById("CheckOut");

			var checkInValue = checkInInput.value;
			var checkOutValue = checkOutInput.value;

			if (checkInValue === "" || checkOutValue === "") {
				alert("Vui lòng điền đầy đủ cả hai trường Check In và Check Out.");
				return;
			}
		}
	</script>
	<!--link js gắn trực tiếp trên bootstrap-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
