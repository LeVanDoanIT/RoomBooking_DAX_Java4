<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<!-- Jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Trúc</title>
    <style>
        .carousel-item img {
            width: 100%;
            height: 300px;
        }
    </style>
</head>
<body>
	<div class="container">
		<header class="row">
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
				<div class="container-fluid">
					<!-- Logo -->
					<a class="navbar-brand" href="#"><img
						src="/RoomBooking/img/logo.png" alt="Logo"></a>
					<!-- Navbar Toggle Button -->
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<!-- Navbar Content -->
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
							<!-- Navigation Links -->
							<li class="nav-item"><a class="nav-link active"
								href="http://localhost:8080/RoomBooking/Home">Trang chủ</a></li>
							<li class="nav-item"><a class="nav-link active" href="/RoomBooking/views/contact.jsp">Liên
									Hệ</a></li>
							<li class="nav-item"><a class="nav-link active" href="/RoomBooking/views/FAQ.jsp">Hỏi
									Đáp</a></li>
							<!-- Dropdown Menu -->
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-expanded="false">Tài
									khoản</a>
								<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
									<li><a class="dropdown-item"
										href="/RoomBooking/account/info">Thông tin cá nhân</a></li>
									<li><a class="dropdown-item" href="/RoomBooking/account/status/history">Thông báo</a></li>
									<li><a class="dropdown-item"
										href="/RoomBooking/account/login">Đăng nhập</a></li>
									<li><a class="dropdown-item"
										href="/RoomBooking/account/register">Đăng ký</a></li>
									<li><a class="dropdown-item"
										href="/RoomBooking/account/logout">Đăng xuất</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</nav>

			<div id="headerCarousel" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active img-fluid">
						<img src="/RoomBooking/img/room1.jpg" class="d-block w-100"
							alt="Image 1">
					</div>
					<div class="carousel-item">
						<img src="/RoomBooking/img/room2.jpg" class="d-block w-100"
							alt="Image 2">
					</div>
					<div class="carousel-item">
						<img src="/RoomBooking/img/room3.jpg" class="d-block w-100"
							alt="Image 3">
					</div>
				</div>
			</div>

			<form action="/RoomBooking/find" method="GET">
				<div class="row mt-3">
					<div class="col md-4">
						<label for="roomType" class="form-label">Loại phòng</label> <select
							class="form-select" id="roomType" name="roomType"
							aria-label="Chọn Loại phòng">
							<option value="All" selected>Chọn tất cả</option>
							<option value="Khách Sạn">Khách Sạn</option>
							<option value="Resort">Resort</option>
							<option value="Homestay">Homestay</option>
							<option value="Phòng Hội Nghị và Tiệc Cưới">Phòng Hội
								Nghị và Tiệc Cưới</option>
							<option value="Nhà Nghĩ">Nhà Nghĩ</option>
						</select>
					</div>

					<div class="col">
						<label for="location" class="form-label">Tỉnh/Thành phố</label> <select
							class="form-select" id="location" name="location"
							aria-label="Chọn Tỉnh/Thành phố">
							<option value="All" selected>Chọn tất cả</option>
							<option value="Bà Rịa - Vũng Tàu">Bà Rịa - Vũng Tàu</option>
							<option value="Bắc Giang">Bắc Giang</option>
							<option value="Bắc Kạn">Bắc Kạn</option>
							<option value="Bạc Liêu">Bạc Liêu</option>
							<option value="Bắc Ninh">Bắc Ninh</option>
							<option value="Bến Tre">Bến Tre</option>
							<option value="Bình Định">Bình Định</option>
							<option value="Bình Dương">Bình Dương</option>
							<option value="Bình Phước">Bình Phước</option>
							<option value="Bình Thuận">Bình Thuận</option>
							<option value="Cà Mau">Cà Mau</option>
							<option value="Cần Thơ">Cần Thơ</option>
							<option value="Cao Bằng">Cao Bằng</option>
							<option value="Đà Nẵng">Đà Nẵng</option>
							<option value="Đắk Lắk">Đắk Lắk</option>
							<option value="Đắk Nông">Đắk Nông</option>
							<option value="Điện Biên">Điện Biên</option>
							<option value="Đồng Nai">Đồng Nai</option>
							<option value="Đồng Tháp">Đồng Tháp</option>
							<option value="Gia Lai">Gia Lai</option>
							<option value="Hà Giang">Hà Giang</option>
							<option value="Hà Nam">Hà Nam</option>
							<option value="Hà Nội">Hà Nội</option>
							<option value="Hà Tĩnh">Hà Tĩnh</option>
							<option value="Hải Dương">Hải Dương</option>
							<option value="Hải Phòng">Hải Phòng</option>
							<option value="Hậu Giang">Hậu Giang</option>
							<option value="Hòa Bình">Hòa Bình</option>
							<option value="Hưng Yên">Hưng Yên</option>
							<option value="Khánh Hòa">Khánh Hòa</option>
							<option value="Kiên Giang">Kiên Giang</option>
							<option value="Kon Tum">Kon Tum</option>
							<option value="Lai Châu">Lai Châu</option>
							<option value="Lâm Đồng">Lâm Đồng</option>
							<option value="Lạng Sơn">Lạng Sơn</option>
							<option value="Lào Cai">Lào Cai</option>
							<option value="Long An">Long An</option>
							<option value="Nam Định">Nam Định</option>
							<option value="Nghệ An">Nghệ An</option>
							<option value="Ninh Bình">Ninh Bình</option>
							<option value="Ninh Thuận">Ninh Thuận</option>
							<option value="Phú Thọ">Phú Thọ</option>
							<option value="Phú Yên">Phú Yên</option>
							<option value="Quảng Bình">Quảng Bình</option>
							<option value="Quảng Nam">Quảng Nam</option>
							<option value="Quảng Ngãi">Quảng Ngãi</option>
							<option value="Quảng Ninh">Quảng Ninh</option>
							<option value="Quảng Trị">Quảng Trị</option>
							<option value="Sóc Trăng">Sóc Trăng</option>
							<option value="Sơn La">Sơn La</option>
							<option value="Tây Ninh">Tây Ninh</option>
							<option value="Thái Bình">Thái Bình</option>
							<option value="Thái Nguyên">Thái Nguyên</option>
							<option value="Thanh Hóa">Thanh Hóa</option>
							<option value="Thừa Thiên Huế">Thừa Thiên Huế</option>
							<option value="Tiền Giang">Tiền Giang</option>
							<option value="Trà Vinh">Trà Vinh</option>
							<option value="Tuyên Quang">Tuyên Quang</option>
							<option value="Vĩnh Long">Vĩnh Long</option>
							<option value="Vĩnh Phúc">Vĩnh Phúc</option>
							<option value="Yên Bái">Yên Bái</option>
							<option value="Thành phố Hồ Chí Minh">Thành phố Hồ Chí
								Minh</option>
						</select>
					</div>

					<div class="col-md-auto">
						<!-- Sử dụng lớp col-md-auto để nút tìm kiếm tự điều chỉnh kích thước -->
						<label class="form-label">Tìm kiếm</label>
						<div class="input-group">
							<button class="btn btn-primary" type="submit">
								<i class="bi bi-search"></i>
							</button>
						</div>
					</div>
				</div>
			</form>
		</header>

		<div class="row mt-4">
			<nav class="col">
				<h2>Giới Thiệu</h2>
				<div class="row">
					<div class="card" style="width: 50%">
						<div class="card-body">
							<h5 class="card-title">Bee Booking</h5>
							<h6 class="card-subtitle mb-2 text-body-secondary">Đặt phòng
								online</h6>
							<p class="card-text">Website đặt lịch book phòng trực tuyến
								nhằm tạo ra một nền tảng trực tuyến giúp người dùng dễ dàng tìm
								kiếm, đặt và quản lý các đơn đặt phòng cho các loại hình không
								gian như phòng họp, phòng hội nghị, phòng sinh hoạt cộng đồng,
								phòng làm việc, hoặc các khu vực tiệc cưới và sự kiện.</p>
							<a href="#" class="card-link">Tìm Phòng</a>
						</div>
					</div>

					<div id="headerCarousel" class="carousel slide"
						data-bs-ride="carousel" style="width: 50%">
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img src="/RoomBooking/img/room3.jpg"
									class="d-block w-100 h-100" alt="Image 1">
							</div>
							<div class="carousel-item">
								<img src="/RoomBooking/img/Sheraton1.jpg"
									class="d-block w-100 h-100" alt="Image 2">
							</div>
							<div class="carousel-item">
								<img src="/RoomBooking/img/room1.jpg"
									class="d-block w-100 h-100" alt="Image 3">
							</div>
						</div>
					</div>
				</div>
			</nav>
		</div>

		<div class="row mt-4">
			<article>
                <form action="/RoomBooking/arrange" method="GET" class="mb-4">
                    <div class="row align-items-end">
                        <div class="col-md-10">
                            <select class="form-select" id="sort" name="orderBy" aria-label="Xắp sếp theo giá">
                                <option selected disabled>Xắp sếp theo giá</option>
                                <option value="asc">Tăng dần</option>
                                <option value="desc">Giảm dần</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-primary w-100">Xắp sếp</button>
                        </div>
                    </div>
                </form>
                <!-- sản phẩm-->
                <%@include file="/views/products.jsp"%>
                <!-- /sản phẩm-->
            </article>
		</div>

		<footer class="row bg-dark text-light py-5">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-md-3">
						<img src="/RoomBooking/img/logofooter.png" alt="Logo"
							style="max-width: 100px;">
					</div>
					<div class="col-md-4">
						<h3>Về chúng tôi</h3>
						<p>Cao đẳng thực hành FPT Polytechnic Cần Thơ</p>
					</div>

					<div class="col md-5" style="min-height: 150px;">
						<h5>Theo dõi chúng tôi</h5>
						<p>
							Phone: 09xx xxx xxx <br> Email: abc@gmail.com <br> Địa
							Chỉ: Tòa nhà FPT Cần Thơ, Phường Thường Thạnh, Quận Cái Răng, TP
							Cần Thơ
					</div>
				</div>
			</div>
		</footer>
	</div>
<script>
    // Một biến giả để lưu trữ trạng thái đăng nhập
    var isLoggedIn = true; // Giả sử người dùng chưa đăng nhập
    
    window.addEventListener('DOMContentLoaded', (event) => {
        // Lấy phần tử dropdown-menu
        var dropdownMenu = document.getElementById('navbarDropdownMenu');
        
        // Kiểm tra và cập nhật trạng thái đăng nhập từ local storage hoặc cookie (nếu có)
        // isLoggedIn = localStorage.getItem('isLoggedIn') === 'true'; // Ví dụ sử dụng localStorage

        // Kiểm tra xem trang hiện tại có phải là trang chủ hay không
        var isHomePage = window.location.pathname === '/RoomBooking/account/login'; 
        var isHomePage2 = window.location.pathname === '/RoomBooking/Home';// Điều chỉnh đường dẫn trang chủ tùy theo website của bạn
        
        if (isHomePage) {
            if (isLoggedIn) {
                // Ẩn liên kết "Đăng nhập" và "Đăng ký"
                hideMenuItems('.dropdown-item[href="/RoomBooking/account/login"]');
                hideMenuItems('.dropdown-item[href="/RoomBooking/account/register"]');
                // Hiển thị dropdown menu
                dropdownMenu.style.display = 'block';
            } else {
                // Hiển thị toàn bộ menu
                showMenuItems('.dropdown-item[href="/RoomBooking/account/login"]');
                showMenuItems('.dropdown-item[href="/RoomBooking/account/register"]');
                // Hiển thị dropdown menu
                dropdownMenu.style.display = 'block';
            }
        } else if(isHomePage2){
        	hideMenuItems('.dropdown-item[href="/RoomBooking/account/login"]');
            hideMenuItems('.dropdown-item[href="/RoomBooking/account/register"]');
        }
    });

    // Hàm ẩn một menu item
    function hideMenuItems(selector) {
        var menuItems = document.querySelectorAll(selector);
        menuItems.forEach(function(item) {
            item.style.display = 'none';
        });
    }

    // Hàm hiển thị một menu item
    function showMenuItems(selector) {
        var menuItems = document.querySelectorAll(selector);
        menuItems.forEach(function(item) {
            item.style.display = 'block';
        });
    }
</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
