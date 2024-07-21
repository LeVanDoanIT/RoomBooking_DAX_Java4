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
		<div class="row mt-4 mb-4">
			<article class="col-md-12">
				<div class="row">
					<div class="col-md-12">
						<div class="tab-content">
							<div class="tab-pane active" id="page1">
								<div class="card">
									<div class="card-header bg-primary text-light fw-bold fs-3">
										Quản lý người dùng</div>
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

												<form action="/RoomBooking/admin/management/user-manager/index" method="post">
												<div class="mb-3">
														<label for="exampleInputUser" class="form-label">UserID
															</label> <input type="text" name="id" class="form-control"
															id="id"  aria-describedby="UserHelp"
															 value="${formManagerUser.id}">
															 
													</div>
													<div class="mb-3">
														<label for="exampleInputUser" class="form-label">Tên
															đăng nhập</label> <input type="text" name="username"
															class="form-control" id="username"
															aria-describedby="UserHelp"
															value="${formManagerUser.username}"> <span
															class="text-danger">${alertUsername}</span>
													</div>
													<div class="mb-3">
														<label for="exampleInputName" class="form-label">Password
														</label> <input type="text" name="password" class="form-control"
															id="password" aria-describedby="nameHelp"
															value="${formManagerUser.password}"> <span
															class="text-danger">${alertPassword}</span>

													</div>
													<div class="mb-3">
														<label for="exampleInputName" class="form-label">Họ
															và tên</label> <input type="text" name="fullname"
															class="form-control" id="fullname"
															aria-describedby="nameHelp"
															value="${formManagerUser.fullname}"> <span
															class="text-danger">${alertFullname}</span>
													</div>
													<div class="mb-3">
														<label for="exampleInputEmail" class="form-label">Email</label>
														<input type="email" name="email" name="email"
															class="form-control" id="email"
															aria-describedby="emailHelp"
															value="${formManagerUser.email}"> <span
															class="text-danger">${alertEmail}</span>
													</div>
													<div class="mb-3">
														<label for="exampleInputPhone" class="form-label">Số
															điện thoại</label> <input type="number" name="phone"
															class="form-control" id="phone"
															aria-describedby="phoneHelp"
															value="${formManagerUser.phone}"> <span
															class="text-danger">${alertPhone}</span>
													</div>
													<div>
														
														<button type="submit" class="btn btn-outline-warning"
															formaction="/RoomBooking/admin/management/user-manager/update">Update</button>
														<a name="" id="" class="btn btn-outline-primary"
															href="/RoomBooking/admin/management/user-manager/index"
															role="button">Reset</a>
													</div>

												</form>
												<h2 class="mb-5 text-center">${message}</h2>
												<h3>Lịch sử đặt phòng</h3>
												<div class="table-responsive">
													<table class="table table-primary">
														<thead>
															<tr>
																<th scope="col">STT</th>
																<th scope="col">Mã Phòng</th>
																<th scope="col">UserID</th>
																<th scope="col">Ngày đặt phòng</th>
																<th scope="col">Ngày nhận phòng</th>
																<th scope="col">Ngày trả phòng</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="item" items="${itemsId}" varStatus="loop">
																<tr class="">
																	<td scope="row">${loop.index + 1}</td>
																	<td>${item.roomId}</td>
																	<td>${item.userid }</td>
																	<td>${item.bookDay}</td>
																	<td>${item.checkIn}</td>
																	<td>${item.checkOut}</td>
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
																		href="/RoomBooking/admin/management/user-manager/edit/${item.id}"
																		role="button">Chi tiết...</a></td>

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
						</div>
					</div>
				</div>
			</article>
		</div>
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



</body>
</html>
