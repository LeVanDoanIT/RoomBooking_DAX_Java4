<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="com.vnpay.common.Config"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>KẾT QUẢ THANH TOÁN</title>
<!-- Bootstrap core CSS -->
<link href="assets/bootstrap.min.css" rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="assets/jumbotron-narrow.css" rel="stylesheet">
<script src="assets/jquery-1.11.3.min.js"></script>
<style>
#loadingText {
	font-weight: bold;
	white-space: nowrap;
	overflow: hidden;
	border-right: 2px solid;
	padding-right: 5px;
	box-sizing: content-box;
}
</style>
</head>
<body>
	<%
	//Begin process return from VNPAY
	Map fields = new HashMap();
	for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
		String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
		String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
		if ((fieldValue != null) && (fieldValue.length() > 0)) {
			fields.put(fieldName, fieldValue);
		}
	}

	String vnp_SecureHash = request.getParameter("vnp_SecureHash");
	if (fields.containsKey("vnp_SecureHashType")) {
		fields.remove("vnp_SecureHashType");
	}
	if (fields.containsKey("vnp_SecureHash")) {
		fields.remove("vnp_SecureHash");
	}
	String signValue = Config.hashAllFields(fields);
	%>
	<!--Begin display -->
	<div class="container">
		<div class="header clearfix">
			<h3 class="text-muted">
				Thanh toán thành công vui lòng đợi giây lát <span id="loadingText"></span>
			</h3>
		</div>
		<div class="table-responsive">
			<div class="form-group">
				<label>Mã giao dịch thanh toán:</label> <label><%=request.getParameter("vnp_TxnRef")%></label>
			</div>
			<div class="form-group">
				<label>Đã thanh toán thành công số tiền:</label> <label><%=request.getParameter("vnp_Amount")%></label>
			</div>
			<div class="form-group">
				<label>Mô tả giao dịch:</label> <label><%=request.getParameter("vnp_OrderInfo")%></label>
			</div>
			<div class="form-group">
				<label>Mã lỗi thanh toán:</label> <label><%=request.getParameter("vnp_ResponseCode")%></label>
			</div>
			<div class="form-group">
				<label>Mã giao dịch tại CTT VNPAY-QR:</label> <label><%=request.getParameter("vnp_TransactionNo")%></label>
			</div>
			<div class="form-group">
				<label>Mã ngân hàng thanh toán:</label> <label><%=request.getParameter("vnp_BankCode")%></label>
			</div>
			<div class="form-group">
				<label>Thời gian thanh toán:</label> <label><%=request.getParameter("vnp_PayDate")%></label>
			</div>
			<div class="form-group">
				<label>Tình trạng giao dịch:</label> <label> <%
 if (signValue.equals(vnp_SecureHash)) {
 	if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
 		out.print("Thành công");
 	} else {
 		out.print("Không thành công");
 	}

 } else {
 	out.print("invalid signature");
 }
 %></label>
			</div>
		</div>
		<p>&nbsp;</p>
		<footer class="footer">
			<p>&copy; VNPAY 2020</p>
		</footer>
	</div>
	<!-- Làm cho dấu ... chuyển động -->
	<script>
        function loadTextAnimation() {
            const loadingText = document.getElementById("loadingText");
            let i = 0;
            const text = "....";
            const intervalId = setInterval(() => {
                if (i < text.length) {
                    loadingText.textContent += text[i];
                    i++;
                } else {
                    clearInterval(intervalId);
                    i = 0;
                    setTimeout(() => {
                        loadingText.textContent = "";
                        loadTextAnimation();
                    }, 1000);
                }
            }, 100);
        }

        loadTextAnimation();
    </script>
	<!-- Khi tải trang web lên thành công thì thực hiện đoạn mã sau 
        	Có thể getValue của label để xác định
        -->
	<script>
		$(document).ready(function() {
			var totalPrice = '<%=request.getParameter("totalPrice")%>';
		    var transactionId = '<%=request.getParameter("transactionId")%>';
			console.log(totalPrice);
			console.log(transactionId);
			$.ajax({
				type : "Post",
				url : "PaymentSuccess",//Gửi thông tin đến Servlet
				data : {
					transactionId : transactionId,
					totalPrice : totalPrice
				},
				success : function(data) {
					// Thực hiện các thao tác xử lý khi gửi thành công
					alert('Thanh toán thành công');
					window.location.href = "<%=request.getContextPath()%>/Home";
				}
			});
		});
	</script>
</body>
</html>
