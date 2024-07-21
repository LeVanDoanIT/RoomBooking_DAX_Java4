<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--link icon gắn trực tiếp trên bootstrap-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
<!--link css gắn trực tiếp trên bootstrap-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" />
<!-- Jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="/RoomBooking/css/UserInfo.css">
<head>
<meta charset="UTF-8">
<title>Hào - Bảo - Toàn - Khoa</title>
</head>
<body>
	<div class="container">
		<%@include file="/views/compomentAdmin/header.jsp"%>
		<div class="row mt-4 mb-4">
			<article class="col-md-12">
				<div class="row">
					<div class="col-md-3 d-flex">
						<div class="card" style="width: 18rem;">
							<img src="/RoomBooking/img/logofooter.png" class="card-img-top"
								alt="...">
							<div class="card-body">
								<h5 class="card-title text-center ">${user.fullname}</h5>
								<hr>
								<ul class="list-group nav-tabs px-0 ">
									<li class="list-group-item active" aria-current="page"
										href="#page1" data-bs-toggle="tab">Thông tin cá nhân</li>
									<li class="list-group-item" href="#page5" data-bs-toggle="tab">Đổi
										mật khẩu</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-9">
						<div class="tab-content">
							<!-- B1 đặt id duy nhất cho 2 nội dung -->
							<div class="tab-pane active mt-2" id="page1">
								<form action="/RoomBooking/account/info" method="post">
									<div class="card">
										<div class="card-header fw-bold fs-3">Thông tin cá nhân</div>
										<div class="card-body">
											<div class="mb-3">
												<label for="exampleFormControlInput1" class="form-label">Tên
													Đăng Nhập </label> <input value="${user.username}" name="username"
													type="text" class="form-control"
													id="exampleFormControlInput1" placeholder="Tên Đăng Nhập"
													disabled>
											</div>
											<div class="mb-3">
												<label for="exampleFormControlInput1" class="form-label">Họ
													Và Tên </label> <input value="${user.fullname}" name="fullname"
													type="text" class="form-control"
													id="exampleFormControlInput1" placeholder="Họ Và Tên">
												<span class="text-danger">${alertFullname}</span>
											</div>
											<div class="mb-3">
												<label for="exampleFormControlInput1" class="form-label">Email
												</label> <input value="${user.email}" name="email" type="email"
													class="form-control" id="exampleFormControlInput1"
													placeholder="email"> <span class="text-danger">${alertEmail}</span>
											</div>
											<div class="mb-3">
												<label for="exampleFormControlInput1" class="form-label">Số
													Điện Thoại </label> <input value="${user.phone}" name="phone"
													type="text" class="form-control"
													id="exampleFormControlInput1" placeholder="Số Điện Thoại">
												<span class="text-danger">${alertPhone}</span>
											</div>
										</div>

										<div class="card-footer ">
											<button class="btn btn-outline-dark float-end " type="submit">Cập
												Nhật Thông Tin</button>
										</div>
									</div>
								</form>
							</div>
								<div class="tab-pane mt-2" id="page5">
									<form action="/RoomBooking/account/change-password"
										method="post">

										<div class="card">
											<div class="card-header fw-bold fs-3">Đổi mật khẩu</div>
											<div class="card-body">
												<div class="mb-3">
													<label for="exampleFormControlInput1" class="form-label">Mật
														khẩu hiện tại</label> <input type="password" class="form-control"
														id="oldPassword" placeholder="Mật khẩu hiện tại"
														name="oldPassword"> <span class="text-danger">${alertoldPassword}</span>
												</div>
												<div class="mb-3">
													<label for="exampleFormControlTextarea1" class="form-label">Mật
														khẩu mới</label> <input type="password" class="form-control"
														id="newPassword" placeholder="Mật khẩu mới"
														name="newPassword"> <span class="text-danger">${alertnewPassword}</span>
												</div>
												<div class="mb-3">
													<label for="exampleFormControlTextarea1" class="form-label">Xác
														nhận mật khẩu mới</label> <input type="password"
														class="form-control" id="confirmPassword"
														placeholder="Xác nhận mật khẩu mới" name="confirmPassword">
													<span class="text-danger">${alertconfirmPassword}</span>
												</div>
												<div class="mb-3 form-check">
													<input type="checkbox" class="form-check-input"
														id="showPasswordCheckbox"> <label
														class="form-check-label" for="showPasswordCheckbox">Hiện
														mật khẩu</label>
												</div>
												<lable class="text-danger fs-4">${message}</label>
											</div>
											<div class="card-footer ">
												<button type="submit"
													href="/RoomBooking/account/change-password"
													class="btn btn-primary float-end ">Đổi mật khẩu</button>
											</div>
										</div>
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>



		<%@include file="/views/compoment/footer.jsp"%>

	</div>

	<!--link js gắn trực tiếp trên bootstrap-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#showPasswordCheckbox').change(function() {
				var isChecked = $(this).is(':checked');
				if (isChecked) {
					$('#oldPassword').attr('type', 'text');
					$('#newPassword').attr('type', 'text');
					$('#confirmPassword').attr('type', 'text');
				} else {
					$('#oldPassword').attr('type', 'password');
					$('#newPassword').attr('type', 'password');
					$('#confirmPassword').attr('type', 'password');
				}
			});
		});
	</script>
</body>
</html>
