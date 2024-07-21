<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Bảo-Hào-Toàn</title>
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
<link rel="stylesheet" href="/RoomBooking/css/UserInfo.css">
<script src="https://kit.fontawesome.com/446a91b231.js"
	crossorigin="anonymous"></script>
</head>

<body>
	<div class="container">
		<%@include file="/views/compomentAdmin/header.jsp"%>

		<div class="row mt-4 mb-4">
			<article class="col-md-12">
				<div class="row">
					<div class="col-md-3 d-flex">
						<div class="card" style="width: 18rem;">
							<img src="/RoomBooking/img/logo.png" class="card-img-top"
								alt="...">
							<div class="card-body">
								<h5 class="card-title text-center ">Họ và Tên</h5>
								<hr>
								<ul class="list-group nav-tabs ">
									<li class="list-group-item active" aria-current="page"
										href="#page1" data-bs-toggle="tab">Quản lý người dùng</li>
									<li class="list-group-item" href="#page2" data-bs-toggle="tab">Quản
										lý phòng</li>
									<li class="list-group-item" href="#page3" data-bs-toggle="tab">Quản
										lý đơn đặt phòng</li>
									<li class="list-group-item" href="#page4" data-bs-toggle="tab">Quản
										lý thanh toán</li>
									<li class="list-group-item" href="#page5" data-bs-toggle="tab">Quản
										lý phản hồi</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-9">
						<div class="tab-content">
							<div class="tab-pane active" id="page1">
								<div class="card">
									<div class="card-header bg-primary text-light fw-bold fs-3">
										Quản lý người dùng
										<c:url var="url" value="/user" />
									</div>
									<div class="card-body">
										<ul class="nav nav-tabs">
											<li class="nav-item"><a class="nav-link active"
												href="#detailManagerUser" data-bs-toggle="tab">Chi tiết</a></li>
											<li class="nav-item"><a class="nav-link"
												href="#listManagerUser" data-bs-toggle="tab">Danh sách
													người dùng</a></li>
										</ul>
										<div class="tab-content">
											<div class="tab-pane active" id="detailManagerUser">
												<c:url var="url" value="/user" />
												<form action="${url}/index" method="post">
													<div class="mb-3">
														<label for="exampleInputUser" class="form-label">Tên
															đăng nhập</label> <input type="text" class="form-control"
															id="exampleInputUser" aria-describedby="UserHelp"
															disabled value="${formManagerUser.username}">
													</div>
													<div class="mb-3">
														<label for="exampleInputName" class="form-label">Họ
															và tên</label> <input type="text" class="form-control"
															id="exampleInputName" aria-describedby="nameHelp"
															disabled value="${formManagerUser.fullname}">
													</div>
													<div class="mb-3">
														<label for="exampleInputEmail" class="form-label">Email</label>
														<input type="email" class="form-control"
															id="exampleInputEmail" aria-describedby="emailHelp"
															disabled value="${formManagerUser.email}">
													</div>
													<div class="mb-3">
														<label for="exampleInputPhone" class="form-label">Số
															điện thoại</label> <input type="number" class="form-control"
															id="exampleInputPhone" aria-describedby="phoneHelp"
															disabled value="${formManagerUser.phone}">
													</div>
												</form>
												<h3>Lịch sử đặt phòng</h3>
												<div class="table-responsive">
													<table class="table table-primary">
														<thead>
															<tr>
																<th scope="col">STT</th>
																<th scope="col">Mã phòng</th>
																<th scope="col">Loại phòng</th>
																<th scope="col">Ngày đặt phòng</th>
																<th scope="col">Ngày nhận phòng</th>
																<th scope="col">Ngày trả phòng</th>
															</tr>
														</thead>
														<tbody>
															<tr class="">
																<td scope="row"></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
															</tr>
															<c:forEach var="item" items="${itemsId}" varStatus="loop">
																<tr class="">
																	<td scope="row">${loop.index + 1}</td>
																	<td>${item.RoomId}</td>
																	<td>${item.RoomType}</td>
																	<td>${item.BookDay}</td>
																	<td>${item.CheckIn}</td>
																	<td>${item.CheckOut}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>

											</div>
											<div class="tab-pane mt-5" id="listManagerUser">
												<div class="table-responsive">
													<table class="table table-light table-hover table-bordered">
														<thead>
															<tr>
																<th scope="col">STT</th>
																<th scope="col">Mã người dùng</th>
																<th scope="col">Họ và tên</th>
																<th scope="col">Email</th>
																<th scope="col">Số điện thoại</th>
																<th scope="col"></th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="item" items="${itemsManagerUser}"
																varStatus="loop">
																<tr class="">
																	<td scope="row">${loop.index + 1}</td>
																	<td>${item.id}</td>
																	<td>${item.fullname}</td>
																	<td>${item.email}</td>
																	<td>${item.phone}</td>

																	<td><a name="" id=""
																		class="btn btn-outline-primary"
																		href="${url}/edit/${item.id}" role="button">Chi
																			tiết...</a></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="page2">
								<div class="card">
									<div class="card-header bg-primary text-light fw-bold fs-3">Quản
										lý phòng</div>
									<c:url var="url" value="/room" />
									<div class="card-body">
										<ul class="nav nav-tabs">
											<li class="nav-item"><a class="nav-link active"
												href="#detailManagerRoom" data-bs-toggle="tab">Chi tiết</a></li>
											<li class="nav-item"><a class="nav-link"
												href="#listManagerRoom" data-bs-toggle="tab">Danh sách
													phòng</a></li>

										</ul>

										<div class="tab-content">
											<div class="tab-pane active mt-5" id="detailManagerRoom">
												<c:url var="url" value="/room" />
												<form action="${url}/edit" method="post">

													<div class="row">

														<div class="col col-6">
															<div class="mb-3">
																<label for="exampleFormControlInput1" class="form-label">ID
																</label> <input type="text" value="${room.id}"
																	class="form-control rounded"
																	id="exampleFormControlInput1">
															</div>
															<div class="mb-3">
																<label for="exampleFormControlInput1" class="form-label">Tạo
																	khi nào </label> <input type="text" value="${room.createdAt}"
																	class="form-control rounded"
																	id="exampleFormControlInput1">
															</div>
															<div class="mb-3">
																<label for="exampleFormControlInput1" class="form-label">Cập
																	nhật khi nào </label> <input type="text"
																	value="${room.updatedAt}" class="form-control rounded"
																	id="exampleFormControlInput1">
															</div>
															<div class="mb-3">
																<label for="exampleFormControlInput1" class="form-label">Tạo
																	Bởi </label> <input type="text" value="${room.createdBy}"
																	class="form-control rounded"
																	id="exampleFormControlInput1">
															</div>

															<div class="mb-3">
																<label for="exampleFormControlInput1" class="form-label">Cập
																	nhật bởi </label> <input type="text" value="${room.updatedBy}"
																	class="form-control rounded"
																	id="exampleFormControlInput1">
															</div>
															<div class="mb-3">
																<label for="exampleFormControlInput1" class="form-label">Trạng
																	Thái </label> <input type="text" value="${room.availability}"
																	class="form-control rounded"
																	id="exampleFormControlInput1">
															</div>
															<div class="mb-3">
																<label for="exampleFormControlInput1" class="form-label">Loại
																	Phòng </label> <input type="text" value="${room.roomType}"
																	class="form-control rounded"
																	id="exampleFormControlInput1">
															</div>
															<div class="mb-3">
																<label for="exampleFormControlInput1" class="form-label">Vị
																	Trí </label> <input type="text" value="${room.location}"
																	class="form-control rounded"
																	id="exampleFormControlInput1">
															</div>
															<div class="mb-3">
																<label for="exampleFormControlInput1" class="form-label">Tiện
																	Nghi </label> <input type="text" value="${room.amenities}"
																	class="form-control rounded"
																	id="exampleFormControlInput1">
															</div>
															<div class="mb-3">
																<label for="exampleFormControlInput1" class="form-label">Giá
																</label> <input type="text" value="${room.price}"
																	class="form-control rounded"
																	id="exampleFormControlInput1">
															</div>

														</div>
														<div class="col col-6">
															<label class="mb-2">Hình Ảnh</label> <img
																src=${ room.image } class="img-fluid mt-0" alt="...">
															<div class="mb-3 mt-3">
																<label for="exampleFormControlTextarea1"
																	class="form-label">Mô Tả</label>
																<textarea class="form-control rounded"
																	id="exampleFormControlTextarea1" rows="5" cols="50">${room.description}</textarea>
															</div>

														</div>
													</div>

													<hr>
													<div class="float-md-end">

														<a type="button"
															class="btn btn-outline-success me-md-5 mb-3 mb-md-0"
															href="${url}/update">Cập nhật</a> <a type="button"
															class="btn btn-outline-success me-md-5 mb-3 mb-md-0"
															href="${url}/create">Thêm</a> <a type="button"
															class="btn btn-outline-success me-md-5 mb-3 mb-md-0"
															href="${url}/delete?id=${room.id}">Xóa</a>

													</div>

												</form>
											</div>


											<div class="tab-pane mt-5" id="listManagerRoom">
												<div class="table-responsive">
													<table class="table table-bordered">
														<thead>
															<tr>
																<th scope="col">Loại Phòng</th>
																<th scope="col">Vị Trí</th>
																<th scope="col">Tiện Nghi</th>
																<th scope="col">Giá</th>
																<th scope="col">Mô Tả</th>
																<th scope="col"></th>


															</tr>
														</thead>
														<tbody>
															<c:forEach var="items" items="${items}">
																<tr class="">
																	<td scope="row">${items.roomType}</td>
																	<td>${items.location}</td>
																	<td>${items.amenities}</td>
																	<td>${items.price}</td>
																	<td>${items.description}</td>

																	<td><a href="${url}/edit/${items.id}">Edit</a></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>

											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="page3">
								<div class="card">
									<div class="card-header bg-primary text-light fw-bold fs-3">
										Quản lý đơn đặt phòng</div>
									<div class="card-body">
										<ul class="nav nav-tabs">
											<li class="nav-item"><a class="nav-link active"
												href="#detailPR" data-bs-toggle="tab">Chi tiết</a></li>
											<li class="nav-item"><a class="nav-link" href="#listPR"
												data-bs-toggle="tab">Danh sách đơn đặt phòng</a></li>
										</ul>
										<div class="tab-content">
											<div class="tab-pane active mt-5" id="detailPR">
												<h3>Mã đặt phòng:</h3>
												<hr>
												<div class="container">
													<div class="row">
														<div class="col col-6">
															<ul style="list-style-type: none;" class="fs-5">
																<li class="lh-lg">Tên người dùng:</li>
																<li class="lh-lg">Email:</li>
																<li class="lh-lg">Số điện thoại:</li>
																<li class="lh-lg">Tên phòng:</li>
																<li class="lh-lg">Giá phòng:</li>
																<li class="lh-lg">Loại phòng:</li>
																<li class="lh-lg">Ngày đặt phòng:</li>
																<li class="lh-lg">Ngày nhận phòng:</li>
																<li class="lh-lg">Ngày trả phòng:</li>
															</ul>
															<div class="input-group mb-3">
																<label class="input-group-text" for="inputGroupSelect01">Trạng
																	thái</label> <select class="form-select"
																	id="inputGroupSelect01">
																	<option selected>Choose...</option>
																	<option value="1">Đã thanh toán</option>
																	<option value="2">Chưa thanh toán</option>
																	<option value="3">Chờ xác nhận</option>
																</select>
															</div>
														</div>
														<div class="col col-6">
															<img
																src="https://th.bing.com/th/id/OIP.jC1NOQWCBo1SPXzAjWqKsAHaEK?rs=1&pid=ImgDetMain"
																class="img-fluid" alt="...">
														</div>
													</div>
												</div>
												<hr>
												<div class="float-end ">
													<button type="button"
														class="btn btn-outline-success flex-column">Cập
														nhật</button>

												</div>
											</div>
											<div class="tab-pane mt-5" id="listPR">
												<div class="table-responsive">
													<table class="table table-bordered">
														<thead>
															<tr>
																<th scope="col">STT</th>
																<th scope="col">Mã phòng</th>
																<th scope="col">Mã người dùng</th>
																<th scope="col">Ngày đặt phòng</th>
																<th scope="col">Ngày nhận phòng</th>
																<th scope="col">Ngày trả phòng</th>
																<th scope="col">Trạng thái</th>
																<th scope="col"></th>
															</tr>
														</thead>
														<tbody>
															<tr class="">
																<td scope="row"></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td></td>
																<td><button type="button" class="btn text-primary">Chi
																		tiết....</button></td>
															</tr>
														</tbody>
													</table>
												</div>

											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- Quản lý thanh toán -->
							<!-- Chuyển tab khi nhấn  -->
							<div class="tab-pane" id="page4">
								<!-- Khung chứa nội dung -->
								<div class="card">
									
									<div class="card-header bg-primary text-light fw-bold fs-3">
										Quản lý thanh toán
										<c:url var="url" value="/payment" />
									</div>
									<div class="card-body">
										<ul class="nav nav-tabs">
											<li class="nav-item"><a class="nav-link active"
												href="#detailManagerPayment" data-bs-toggle="tab">Chi
													tiết</a></li>
											<li class="nav-item"><a class="nav-link"
												href="#listManagerPayment" data-bs-toggle="tab">Danh sách
													người dùng</a></li>
										</ul>
										<div class="tab-content">
											<div class="tab-pane active" id="detailManagerPayment">
												<c:url var="url" value="/payment" />
												<form action="${url}/index" method="post">
													<div class="mb-3">
														<label for="exampleInputUser" class="form-label">Tên
															đăng nhập</label> <input type="text" class="form-control"
															id="exampleInputUser" aria-describedby="UserHelp"
															disabled value="${formManagerUser.username}">
													</div>
													<div class="mb-3">
														<label for="exampleInputName" class="form-label">Họ
															và tên</label> <input type="text" class="form-control"
															id="exampleInputName" aria-describedby="nameHelp"
															disabled value="${formManagerUser.fullname}">
													</div>
													<div class="mb-3">
														<label for="exampleInputEmail" class="form-label">Email</label>
														<input type="email" class="form-control"
															id="exampleInputEmail" aria-describedby="emailHelp"
															disabled value="${formManagerUser.email}">
													</div>
													<div class="mb-3">
														<label for="exampleInputPhone" class="form-label">Số
															điện thoại</label> <input type="number" class="form-control"
															id="exampleInputPhone" aria-describedby="phoneHelp"
															disabled value="${formManagerUser.phone}">
													</div>
												</form>
											</div>
											<div class="tab-pane mt-5" id="listManagerPayment">
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
											</div>
										</div>
									</div>
								</div>
							</div>
							<!--END Quản lý thanh toán -->
						</div>
					</div>
				</div>
			</article>
		</div>

		<%@include file="/views/compoment/footer.jsp"%>
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

	<script>
		// Chuyển hướng và set active cho tab page1
		document
				.getElementById("page1")
				.addEventListener(
						"click",
						function() {
							window.location.href = "http://localhost:8080/RoomBooking/user/index#detailManagerUser"; // Set đường dẫn
							document.getElementById("page2").classList
									.remove("active"); // Bỏ active của tab page2
							document.getElementById("page1").classList
									.add("active"); // Thêm active cho tab page1
						});

		// Chuyển hướng và set active cho tab page2
		document
				.getElementById("page2")
				.addEventListener(
						"click",
						function() {
							window.location.href = "http://localhost:8080/RoomBooking/room/edit/1#detailManagerRoom"; // Set đường dẫn
							document.getElementById("page1").classList
									.remove("active"); // Bỏ active của tab page1
							document.getElementById("page2").classList
									.add("active"); // Thêm active cho tab page2
						});
		// Chuyển hướng và set active cho tab page4
		document
				.getElementById("page4")
				.addEventListener(
						"click",
						function() {
							window.location.href = "http://localhost:8080/RoomBooking/payment/index#listManagerPayment"; // Set đường dẫn
							document.getElementById("page1").classList
									.remove("active"); // Bỏ active của tab page1
							document.getElementById("page4").classList
									.add("active"); // Thêm active cho tab page4
						});
		// Xử lý khi tải lại trang
		window.onload = function() {
			if (window.location.hash === "#detailManagerRoom") {
				document.getElementById("page1").classList.remove("active"); // Bỏ active của tab page1
				document.getElementById("page2").classList.add("active"); // Thêm active cho tab page2
			} else if(window.location.hash === "#detailManagerUser") {
				document.getElementById("page2").classList.remove("active"); // Bỏ active của tab page2
				document.getElementById("page1").classList.add("active"); // Thêm active cho tab page1
			}else{
				document.getElementById("page4").classList.remove("active"); // Bỏ active của tab page2
				document.getElementById("page1").classList.add("active"); // Thêm active cho tab page1
			}
		};
	</script>
</body>
</html>
