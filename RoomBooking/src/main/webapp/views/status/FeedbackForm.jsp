<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Rating Form</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<%@include file="/views/compoment/header.jsp"%>
		<div class="container m-5">
			<div class="row justify-content-center">
				<div class="col-md-6">
					<a style="width: 100px;" href="/RoomBooking/account/status/history"
						type="submit" class="btn btn-warning"> Quay lại</a>
					<h2 class="text-center mb-4">Đánh Giá</h2>
					<!-- Form -->
					<form action="/RoomBooking/account/status/addfeedback" method="post">
						<div class="mb-3">
							<label for="commentInput" class="form-label">Nhận xét:</label>
							<!-- Ô nhập nhận xét -->
							<textarea class="form-control" id="commentInput" name="comment"
								rows="3" required></textarea>
							<div class="invalid-feedback">Vui lòng nhập nhận xét của
								bạn.</div>
						</div>
						<!-- Nút Submit -->
						<button type="submit" class="btn btn-primary">Gửi đánh
							giá</button>
					</form>
				</div>
			</div>
		</div>
		<%@include file="/views/compoment/footer.jsp"%>
	</div>

	<script>
		// JavaScript function để quay lại trang trước
		function goBack() {
			window.history.back();
		}
	</script>
	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>