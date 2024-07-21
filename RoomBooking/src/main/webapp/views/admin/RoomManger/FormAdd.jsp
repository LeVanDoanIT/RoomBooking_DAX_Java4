<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản lý phòng</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container bg-light">


		<form action="/RoomBooking/UploadServlet" method="post"
			enctype="multipart/form-data">
			<c:if test="${!empty(uploadedPhotoURL)}">
				<img style="width: 100%; height: 100%; object-fit: cover;"
					src="${uploadedPhotoURL}" alt="${uploadedPhotoURL}" width="300"
					height="300" />
			</c:if>

			<label for="photo" class="form-label"></label> <input type="file"
				accept=".jpg, .png, .mp4" name="photo">

			<button type="submit" name="btn" value=""
				class="btn btn-primary mt-3">Upload Ảnh</button>
		</form>

		<form action="/RoomBooking/admin/management/room-manager"
			method="post">
			<div class="mb-3">
				<label for="roomName" class="form-label">Tên Phòng</label> <input
					value="${room.roomName}" name="roomName" type="text"
					class="form-control" id="roomName"> <span
					class="text-danger">${alertName}</span>
			</div>
			<div class="mb-3">
				<label name="roomType" for="roomType" class="form-label">Loại
					Phòng</label> <input value="${room.roomType}" name="roomType" type="text"
					class="form-control" id="roomType"> <span
					class="text-danger">${alertType}</span>
			</div>
			<div class="mb-3">
				<label for="roomStatus" class="form-label">Trạng Thái</label> <input
					value="${room.availability}" name="availability" type="text"
					class="form-control" id="availability"> <span
					class="text-danger">${alertAvai}</span>
			</div>
			<div class="mb-3">
				<label for="roomAddress" class="form-label">Địa Chỉ</label> <input
					value="${room.location}" name="location" type="text"
					class="form-control" id="roomAddress"> <span
					class="text-danger">${alertLocal}</span>
			</div>
			<div class="mb-3">
				<label for="roomAmenities" class="form-label">Tiện Nghi</label>
				<textarea name="amenities" type="text" class="form-control"
					id="roomAmenities">${room.amenities}</textarea>
				<span class="text-danger">${alertameni}</span>
			</div>
			<div class="mb-3">
				<label for="roomNotes" class="form-label">Mô tả</label> <input
					value="${room.description}" name="description" class="form-control"
					id="roomNotes"> <span class="text-danger">${alertDes}</span>
			</div>
			<div class="mb-3">
				<label for="roomPrice" class="form-label">Giá</label> <input
					value="${room.price}" name="price" type="number"
					class="form-control" id="roomPrice"> <span
					class="text-danger">${alertPrice}</span>
			</div>
			<input name="image" value="${uploadedPhotoName}" type="hidden"
				class="form-control" id="roomAddress">
			<button type="submit" name="btn" value="create"
				class="btn btn-primary">Thêm</button>
			<button type="submit" name="btn" value="update"
				class="btn  btn-warning">Sửa</button>
			<button type="submit" name="btn" value="reset"
				class="btn btn-secondary">Làm mới</button>
		</form>
	</div>
	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js"></script>
</body>
</html>