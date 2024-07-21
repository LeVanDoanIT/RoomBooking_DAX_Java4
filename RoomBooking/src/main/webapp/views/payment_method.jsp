<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet" href="/RoomBooking/css/payment_method.css">


</head>
<body>
	<div class="container">
		<%@include file="/views/compoment/header.jsp" %>
		<main>
			<div class="container">
        <h1 class="h3 mb-5 text-center">Lựa chọn phương thức thanh toán</h1>
        <div class="row">
            <!-- Chi tiết đơn hàng -->
            <div class="col-lg-3">
                <div class="card position-sticky top-0">
                    <div class="p-3 bg-light bg-opacity-10">
                        <!-- Thông tin đơn đặt phòng -->
                        <h6 class="card-title mb-3 fs-6 fw-bold">Chi tiết đơn đặt</h6>
                        <div class=" mb-1 small">
                            <p class="fs-6 fw-bold ">Check-in</p>
                            <p>Thursday 18 April 2024 from 14:00</p>
                        </div>
                        <div class=" mb-1 small">
                            <p class="fs-6 fw-bold ">Check-out</p>
                            <p>Thursday 18 April 2024 from 14:00</p>
                        </div>
                        <div class=" mb-1 small">
                            <p class="fs-6 fw-bold ">Thời gian ở</p>
                            <p>1 tuần</p>
                        </div>
                        <hr>
                        <!-- Thông tin chi phí đặt -->
                        <p class="fs-6 fw-bold ">Tất cả chi phí</p>
                        <div class="d-flex justify-content-between mb-1 small">
                            <span class="col-6 ">Giá phòng: Deluxe Twin Room, No Window</span> <span
                                class="col-6">6.000.000 VND</span>
                        </div>
                        <div class="d-flex justify-content-between mb-1 small">
                            <span class="col-6">Mã giảm giá: (NEWYEAR)</span> <span class="text-danger col-6">-100.000
                                VND</span>
                        </div>
                        <div class="d-flex justify-content-between mb-4 small">
                            <span class="col-6 ">TOTAL</span> <strong class="text-dark col-6">5.900.000 VND</strong>
                        </div>                        
                        <hr>
                        <!-- Chi phí hủy phòng -->
                        <div class=" mb-1 small">
                            <div class="fs-6 fw-bold ">Chi phí hủy phòng</div>
                            <div>
                                <p class="text-success">Free cancellation until 23:59 on 14 April 2024</p>
                                <!-- Chi phí 100% nếu quá giờ -->
                                <p>From 00:00 on 15 April 2024
                                     5.900.000VND</p>
                            </div>
                        </div>
                        <hr>
                        <div class="form-check mb-1 small">
                            <input class="form-check-input" type="checkbox" value="" id="tnc">
                            <label class="form-check-label" for="tnc">
                                I agree to the <a href="#">terms and conditions</a>
                            </label>
                        </div>
                        <button class="btn btn-primary w-100 mt-2">Place order</button>
                    </div>
                </div>

            </div>
            <!-- Lựa chọn phương thưc thanht toán -->
            <div class="col-lg-9">
                <div class="accordion" id="accordionPayment">
                    <!--Thanh toán qua ứng dụng ngân hàng-->
                    <div class="accordion-item mb-3">
                        <h2 class="h5 px-4 py-3 accordion-header d-flex justify-content-between align-items-center">
                            <div class="form-check w-100 collapsed" data-bs-toggle="collapse"
                                data-bs-target="#collapseMM" aria-expanded="false">
                                <input class="form-check-input" type="radio" name="payment" id="paymentMM">
                                <label class="form-check-label pt-1" for="paymentMM">
                                    Thanh toán qua ví MoMo
                                </label>
                            </div>
                            <span>
                                <img width="34" height="25"
                                    src="https://is1-ssl.mzstatic.com/image/thumb/Purple128/v4/ef/e1/40/efe14039-34a6-4519-1838-42c96d05dd75/AppIcon-1x_U007emarketing-85-220-3.png/1024x1024bb.png"
                                    alt="Logo MoMo">
                            </span>
                        </h2>
                        <div id="collapseMM" class="accordion-collapse collapse show"
                            data-bs-parent="#accordionPayment">
                            <div class="accordion-body">
                                <div class="mb-3">
                                    <button type="button" class="btn btn-primary">Xác nhận thanh toán</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--Thanh toán qua ứng dụng ngân hàng-->
                    <div class="accordion-item mb-3">
                        <h2 class="h5 px-4 py-3 accordion-header d-flex justify-content-between align-items-center">
                            <div class="form-check w-100 collapsed" data-bs-toggle="collapse"
                                data-bs-target="#collapseCC" aria-expanded="false">
                                <input class="form-check-input" type="radio" name="payment" id="payment1">
                                <label class="form-check-label pt-1" for="payment1">
                                    Thanh toán qua ứng dụng ngân hàng
                                </label>
                            </div>
                            <span>
                                <svg width="34" height="25" xmlns="http://www.w3.org/2000/svg">
                                    <g fill-rule="nonzero" fill="#333840">
                                        <path
                                            d="M29.418 2.083c1.16 0 2.101.933 2.101 2.084v16.666c0 1.15-.94 2.084-2.1 2.084H4.202A2.092 2.092 0 0 1 2.1 20.833V4.167c0-1.15.941-2.084 2.102-2.084h25.215ZM4.203 0C1.882 0 0 1.865 0 4.167v16.666C0 23.135 1.882 25 4.203 25h25.215c2.321 0 4.203-1.865 4.203-4.167V4.167C33.62 1.865 31.739 0 29.418 0H4.203Z">
                                        </path>
                                        <path
                                            d="M4.203 7.292c0-.576.47-1.042 1.05-1.042h4.203c.58 0 1.05.466 1.05 1.042v2.083c0 .575-.47 1.042-1.05 1.042H5.253c-.58 0-1.05-.467-1.05-1.042V7.292Zm0 6.25c0-.576.47-1.042 1.05-1.042H15.76c.58 0 1.05.466 1.05 1.042 0 .575-.47 1.041-1.05 1.041H5.253c-.58 0-1.05-.466-1.05-1.041Zm0 4.166c0-.575.47-1.041 1.05-1.041h2.102c.58 0 1.05.466 1.05 1.041 0 .576-.47 1.042-1.05 1.042H5.253c-.58 0-1.05-.466-1.05-1.042Zm6.303 0c0-.575.47-1.041 1.051-1.041h2.101c.58 0 1.051.466 1.051 1.041 0 .576-.47 1.042-1.05 1.042h-2.102c-.58 0-1.05-.466-1.05-1.042Zm6.304 0c0-.575.47-1.041 1.051-1.041h2.101c.58 0 1.05.466 1.05 1.041 0 .576-.47 1.042-1.05 1.042h-2.101c-.58 0-1.05-.466-1.05-1.042Zm6.304 0c0-.575.47-1.041 1.05-1.041h2.102c.58 0 1.05.466 1.05 1.041 0 .576-.47 1.042-1.05 1.042h-2.101c-.58 0-1.05-.466-1.05-1.042Z">
                                        </path>
                                    </g>
                                </svg>
                            </span>
                        </h2>
                        <div id="collapseCC" class="accordion-collapse collapse show"
                            data-bs-parent="#accordionPayment">
                            <div class="accordion-body">
                                <h3 class="mb-3">Danh sách ngân hàng</h3>
                                <div class="row">
                                    <!-- Việt tin bank -->
                                    <div class="col-md-2">
                                        <div class="bank-card" data-bank="Vietinbank">
                                            <img width="100" height="100"
                                                src="https://brandlogos.net/wp-content/uploads/2021/09/vietinbank-logo-512x512.png"
                                                alt="Vietinbank" class="bank-logo">

                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="bank-card" data-bank="Agribank">
                                            <img width="100" height="100"
                                                src="https://brandlogos.net/wp-content/uploads/2020/09/agribank-logo.png"
                                                alt="Agribank" class="bank-logo">

                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="bank-card" data-bank="Vietcombank">
                                            <img width="100" height="100"
                                                src="https://seeklogo.net/wp-content/uploads/2016/07/vietcombank-vector-logo.png"
                                                alt="Vietcombank" class="bank-logo">

                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="bank-card" data-bank="BIDV">
                                            <img width="100" height="100"
                                                src="https://static.wixstatic.com/media/9d8ed5_ee14e082b86542d49a8a4460f290c976~mv2.png/v1/fit/w_500,h_500,q_90/file.png"
                                                alt="BIDV" class="bank-logo">

                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="bank-card" data-bank="Techcombank">
                                            <img width="100" height="100"
                                                src="https://th.bing.com/th/id/R.2185df14ae8cce75d385fac9f851dc6c?rik=FapODXxX4S8j8g&pid=ImgRaw&r=0"
                                                alt="Techcombank" class="bank-logo">

                                        </div>
                                    </div>

                                    <div class="col-md-2">
                                        <div class="bank-card" data-bank="Sacombank">
                                            <img width="100" height="100"
                                                src="https://brandlogos.net/wp-content/uploads/2020/11/sacombank-logo-512x512.png"
                                                alt="Sacombank" class="bank-logo">

                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="bank-card" data-bank="ACB">
                                            <img width="100" height="100"
                                                src="https://static.wixstatic.com/media/9d8ed5_c7ef97f22ded44eb8581373d639a5221~mv2.png/v1/fit/w_500,h_500,q_90/file.png"
                                                alt="ACB" class="bank-logo">

                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="bank-card" data-bank="TPBank">
                                            <img width="50" height="100"
                                                src="https://cdn.haitrieu.com/wp-content/uploads/2022/02/Icon-TPBank-1024x1024.png"
                                                alt="TPBank" class="bank-logo">

                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="bank-card" data-bank="VIB">
                                            <img width="100" height="100"
                                                src="https://play-lh.googleusercontent.com/RRo7NaHuZaHpyJHJFPLp3ue4rK-BDe8MI4LquEFHs_mMtVET2ofH21PJn3t0vU7F3Q"
                                                alt="VIB" class="bank-logo">

                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div id="selectedBank" class="mt-3 col-6"></div>
                                    <div class="mt-3 col-6">
                                        <button class="btn btn-primary float-end">Xác nhận thanh toán</button>
                                    </div>
                                </div>
                                <script
                                    src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.2/js/bootstrap.min.js"></script>
                            </div>

                        </div>
                    </div>
                    <!-- PayPal -->
                    <div class="accordion-item mb-3 border">
                        <h2 class="h5 px-4 py-3 accordion-header d-flex justify-content-between align-items-center">
                            <div class="form-check w-100 collapsed" data-bs-toggle="collapse"
                                data-bs-target="#collapsePP" aria-expanded="false">
                                <input class="form-check-input" type="radio" name="payment" id="payment2">
                                <label class="form-check-label pt-1" for="payment2">
                                    PayPal
                                </label>
                            </div>
                            <span>
                                <svg width="103" height="25" xmlns="http://www.w3.org/2000/svg">
                                    <g fill="none" fill-rule="evenodd">
                                        <path
                                            d="M8.962 5.857h7.018c3.768 0 5.187 1.907 4.967 4.71-.362 4.627-3.159 7.187-6.87 7.187h-1.872c-.51 0-.852.337-.99 1.25l-.795 5.308c-.052.344-.233.543-.505.57h-4.41c-.414 0-.561-.317-.452-1.003L7.74 6.862c.105-.68.478-1.005 1.221-1.005Z"
                                            fill="#009EE3"></path>
                                        <path
                                            d="M39.431 5.542c2.368 0 4.553 1.284 4.254 4.485-.363 3.805-2.4 5.91-5.616 5.919h-2.81c-.404 0-.6.33-.705 1.005l-.543 3.455c-.082.522-.35.779-.745.779h-2.614c-.416 0-.561-.267-.469-.863l2.158-13.846c.106-.68.362-.934.827-.934h6.263Zm-4.257 7.413h2.129c1.331-.051 2.215-.973 2.304-2.636.054-1.027-.64-1.763-1.743-1.757l-2.003.009-.687 4.384Zm15.618 7.17c.239-.217.482-.33.447-.062l-.085.642c-.043.335.089.512.4.512h2.323c.391 0 .581-.157.677-.762l1.432-8.982c.072-.451-.039-.672-.38-.672H53.05c-.23 0-.343.128-.402.48l-.095.552c-.049.288-.18.34-.304.05-.433-1.026-1.538-1.486-3.08-1.45-3.581.074-5.996 2.793-6.255 6.279-.2 2.696 1.732 4.813 4.279 4.813 1.848 0 2.674-.543 3.605-1.395l-.007-.005Zm-1.946-1.382c-1.542 0-2.616-1.23-2.393-2.738.223-1.507 1.665-2.737 3.206-2.737 1.542 0 2.616 1.23 2.394 2.737-.223 1.508-1.664 2.738-3.207 2.738Zm11.685-7.971h-2.355c-.486 0-.683.362-.53.808l2.925 8.561-2.868 4.075c-.241.34-.054.65.284.65h2.647a.81.81 0 0 0 .786-.386l8.993-12.898c.277-.397.147-.814-.308-.814H67.6c-.43 0-.602.17-.848.527l-3.75 5.435-1.676-5.447c-.098-.33-.342-.511-.793-.511h-.002Z"
                                            fill="#113984"></path>
                                        <path
                                            d="M79.768 5.542c2.368 0 4.553 1.284 4.254 4.485-.363 3.805-2.4 5.91-5.616 5.919h-2.808c-.404 0-.6.33-.705 1.005l-.543 3.455c-.082.522-.35.779-.745.779h-2.614c-.417 0-.562-.267-.47-.863l2.162-13.85c.107-.68.362-.934.828-.934h6.257v.004Zm-4.257 7.413h2.128c1.332-.051 2.216-.973 2.305-2.636.054-1.027-.64-1.763-1.743-1.757l-2.004.009-.686 4.384Zm15.618 7.17c.239-.217.482-.33.447-.062l-.085.642c-.044.335.089.512.4.512h2.323c.391 0 .581-.157.677-.762l1.431-8.982c.073-.451-.038-.672-.38-.672h-2.55c-.23 0-.343.128-.403.48l-.094.552c-.049.288-.181.34-.304.05-.433-1.026-1.538-1.486-3.08-1.45-3.582.074-5.997 2.793-6.256 6.279-.199 2.696 1.732 4.813 4.28 4.813 1.847 0 2.673-.543 3.604-1.395l-.01-.005Zm-1.944-1.382c-1.542 0-2.616-1.23-2.393-2.738.222-1.507 1.665-2.737 3.206-2.737 1.542 0 2.616 1.23 2.393 2.737-.223 1.508-1.665 2.738-3.206 2.738Zm10.712 2.489h-2.681a.317.317 0 0 1-.328-.362l2.355-14.92a.462.462 0 0 1 .445-.363h2.682a.317.317 0 0 1 .327.362l-2.355 14.92a.462.462 0 0 1-.445.367v-.004Z"
                                            fill="#009EE3"></path>
                                        <path
                                            d="M4.572 0h7.026c1.978 0 4.326.063 5.895 1.45 1.049.925 1.6 2.398 1.473 3.985-.432 5.364-3.64 8.37-7.944 8.37H7.558c-.59 0-.98.39-1.147 1.449l-.967 6.159c-.064.399-.236.634-.544.663H.565c-.48 0-.65-.362-.525-1.163L3.156 1.17C3.28.377 3.717 0 4.572 0Z"
                                            fill="#113984"></path>
                                        <path
                                            d="m6.513 14.629 1.226-7.767c.107-.68.48-1.007 1.223-1.007h7.018c1.161 0 2.102.181 2.837.516-.705 4.776-3.793 7.428-7.837 7.428H7.522c-.464.002-.805.234-1.01.83Z"
                                            fill="#172C70"></path>
                                    </g>
                                </svg>
                            </span>
                        </h2>
                        <div id="collapsePP" class="accordion-collapse collapse" data-bs-parent="#accordionPayment"
                            style="">
                            <div class="accordion-body">
                                <div class="px-2 col-lg-6 mb-3">
                                    <div class="mb-3">
                                        <button type="button" class="btn btn-primary">Xác nhận thanh toán</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
		</main>
			<%@include file="/views/compoment/footer.jsp" %>
	</div>
 <!-- Hiển thị thông tin ngân hàng chi click vào ngân hàng -->
    <script>
        const banks = document.querySelectorAll('.bank-card');

        banks.forEach(bank => {
            bank.addEventListener('click', () => {
                deselectAllBanks();
                selectBank(bank);
                updateSelectedBankInfo(bank.dataset.bank);
            });
        });

        function deselectAllBanks() {
            banks.forEach(bank => {
                bank.classList.remove('selected');
            });
        }

        function selectBank(bank) {
            bank.classList.add('selected');
        }

        function updateSelectedBankInfo(bank) {
            const selectedBank = document.getElementById('selectedBank');
            selectedBank.innerHTML = `<p><strong>Ngân hàng đã chọn:</strong> ${bank}</p>`;
        }
    </script>
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