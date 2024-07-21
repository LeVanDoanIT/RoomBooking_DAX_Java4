<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>Products</title>
<!-- AngularJS -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
<!-- Bootstrap 5 CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap 5 Icons -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.0/icons/bootstrap-icons.min.css" rel="stylesheet">
</head>
<body ng-controller="myctrl">

    <div class="container mt-3">
        <div class="row">
            <c:forEach var="room" items="${items}">
                <div class="col-12">
                    <div class="card mb-3">
                        <div class="row g-0">
                            <div class="col-md-5">
                                <img src="${room.image}" class="img-fluid rounded-start"
                                    style="height: 350px; width: 100%" alt="...">
                            </div>
                            <div class="col-md-7">
                                <div class="card-body">
                                    <h5 class="card-title">${room.roomType}</h5>
                                    <p class="card-text">Vị trí: ${room.location}</p>
                                    <p class="card-text">Tiện nghi: ${room.amenities}</p>
                                    <p class="card-text">Mô tả: ${room.description}</p>
                                    <p class="card-text">Trạng thái: ${room.availability}</p>
                                    <!-- Sử dụng filter để định dạng tiền -->
                                    <p class="card-text">Giá: <fmt:formatNumber value = "${room.price}" pattern = "#,###.##"/> VNĐ</p>
                                    <!-- Thêm nút chi tiết -->
                                    <a type="button" class="btn btn-primary" id="detailButton"
                                        href="/RoomBooking/product/${room.id}">Chi tiết</a>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
     
    <!--link js gắn trực tiếp trên bootstrap-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
