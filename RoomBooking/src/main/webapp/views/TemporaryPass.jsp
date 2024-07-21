<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

</head>
<body>
	<div class="container">
	<%@include file="/views/compoment/header.jsp"%>
		<div class="row">
			<div class="col col-sm-8 offset-sm-2 py-5">
				<div class="card text-center">
					<div class="card-header">Xác nhận Mật Khẩu</div>
					<div class="card-body">
						<form action="/RoomBooking/ForgotPass/CheckRandomPass" method="POST"">
							<h5 class="card-title">Nhập mật khẩu tạm thời</h5>
							<br>
							<div class="mb-3">
								<input type="password" class="form-control" id="TemporaryPass"
									name="TemporaryPass">
							</div>
							<br>
							<div class="mb-3 form-check d-flex justify-content-start">
								<input type="checkbox" class="form-check-input"
									id="showPasswordCheckbox"> <label
									class="form-check-label" for="showPasswordCheckbox">Hiện
									mật khẩu</label>
							</div>
							<button type="submit"
								class="btn btn-primary mb-3 offset-sm-3 col-sm-6 rounded-5 d-flex justify-content-center">Xác
								nhận</button>
						</form>
					</div>
					<div class="card-footer text-body-secondary">${Rmessage}</div>
				</div>
			</div>
		</div>
		<%@include file="/views/compoment/footer.jsp"%>
	</div>
	<script>
		// Lấy tham chiếu đến checkbox và ô nhập mật khẩu
		const showPasswordCheckbox = document
				.getElementById('showPasswordCheckbox');
		const passwordInput = document.getElementById('TemporaryPass');

		// Thêm sự kiện khi checkbox thay đổi trạng thái
		showPasswordCheckbox.addEventListener('change', function() {
			if (showPasswordCheckbox.checked) {
				// Hiện mật khẩu
				passwordInput.type = 'text';
			} else {
				// Ẩn mật khẩu
				passwordInput.type = 'password';
			}
		});
	</script>
	<%
	String Rmessage = (String) request.getAttribute("Rmessage");
	if ("Mật khẩu đúng, bạn có thể mở trang email.".equals(Rmessage)) {
		response.sendRedirect("http://localhost:8080/RoomBooking/views/ChangePass.jsp");
		return; // Dừng thực hiện các dòng mã tiếp theo
	}
	%>
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