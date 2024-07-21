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
				<div class="card">
					<div class="card-header text-center">ĐÔI MẬT KHẨU</div>
					<div class="card-body">
						<form action="/RoomBooking/ForgotPass/ChangePass" method="POST"">
							<div class="mb-3">
								<label for="exampleInputEmail1" class="form-label">Mật
									khẩu mới</label> <input type="password" class="form-control"
									id="NewPass" name="NewPass" aria-describedby="emailHelp">
								<div id="NewPassError" class="form-text text-danger"></div>
							</div>
							<div class="mb-3">
								<label for="exampleInputPassword1" class="form-label">Xác
									nhận mật khẩu mới</label> <input type="password" class="form-control"
									id="ConfirmNewPass" name="ConfirmNewPass">
								<div id="ConfirmNewPassError" class="form-text text-danger"></div>
							</div>
							<div class="mb-3 form-check">
								<input type="checkbox" class="form-check-input"
									id="showPasswordCheckbox"> <label
									class="form-check-label" for="showPasswordCheckbox">Hiện
									mật khẩu</label>
							</div>
							<button type="submit" class="btn btn-primary float-end">Xác
								nhận</button>
						</form>
					</div>
					<div class="card-footer text-body-secondary">${Cmessage}</div>
				</div>
			</div>
		</div>
		<%@include file="/views/compoment/footer.jsp"%>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
	<script>
	document.addEventListener("DOMContentLoaded", function() {
	    const newPassword = document.getElementById("NewPass");
	    const confirmNewPassword = document.getElementById("ConfirmNewPass");
	    const newPassError = document.getElementById("NewPassError");
	    const confirmNewPassError = document.getElementById("ConfirmNewPassError");
	    const message = document.querySelector(".card-footer");
	    const submitButton = document.querySelector("button[type='submit']");

	    function checkErrors() {
	        if (newPassError.innerHTML || confirmNewPassError.innerHTML) {
	            submitButton.disabled = true;
	        } else {
	            submitButton.disabled = false;
	        }
	    }

	    // Kiểm tra mật khẩu mới khi người dùng rời khỏi ô input
	    newPassword.addEventListener("blur", function() {
	        newPassError.innerHTML = ""; // Reset error message

	        if (newPassword.value.length === 0) {
	            newPassError.innerHTML = "Mật khẩu không được bỏ trống.";
	        } else if (newPassword.value.length < 8) {
	            newPassError.innerHTML = "Mật khẩu mới phải có ít nhất 8 ký tự.";
	        } else if (!/[a-zA-Z]/.test(newPassword.value) || !/[0-9]/.test(newPassword.value)) {
	            newPassError.innerHTML = "Mật khẩu cần ít nhất 1 chữ và 1 số.";
	        }

	        checkErrors();
	    });

	    // Kiểm tra xác nhận mật khẩu khi người dùng rời khỏi ô input
	    confirmNewPassword.addEventListener("blur", function() {
	        confirmNewPassError.innerHTML = ""; // Reset error message

	        if (confirmNewPassword.value !== newPassword.value) {
	            confirmNewPassError.innerHTML = "Xác nhận mật khẩu không khớp.";
	        }

	        checkErrors();
	    });

	    // Hiển thị mật khẩu
	    const showPasswordCheckbox = document.getElementById("showPasswordCheckbox");
	    showPasswordCheckbox.addEventListener("change", function() {
	        if (this.checked) {
	            newPassword.type = "text";
	            confirmNewPassword.type = "text";
	        } else {
	            newPassword.type = "password";
	            confirmNewPassword.type = "password";
	        }
	    });
	});

	</script>
</body>
</html>
