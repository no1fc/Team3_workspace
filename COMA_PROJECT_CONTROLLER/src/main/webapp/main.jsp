<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <!-- Fonts and icons -->
  <script src="assets/js/plugin/webfont/webfont.min.js"></script>
  <script>
    WebFont.load({
      google: {
        families: ["Public Sans:300,400,500,600,700"]
      },
      custom: {
        families: [
          "Font Awesome 5 Solid",
          "Font Awesome 5 Regular",
          "Font Awesome 5 Brands",
          "simple-line-icons",
        ],
        urls: ["assets/css/fonts.min.css"],
      },
      active: function () {
        sessionStorage.fonts = true;
      },
    });
  </script>

  <!-- CSS Files -->
  <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
  <link rel="stylesheet" href="assets/css/plugins.min.css" />
  <link rel="stylesheet" href="assets/css/kaiadmin.css" />

</head>
<body>
  <div class="main-header">
    <div class="main-header-logo">
      <!-- Logo Header -->
      <div class="logo-header" data-background-color="dark">
        <a href="index.html" class="logo">
          <img src="assets/img/kaiadmin/logo_light.svg" alt="navbar brand" class="navbar-brand" height="20" />
        </a>
        <div class="nav-toggle">
          <button class="btn btn-toggle toggle-sidebar">
            <i class="gg-menu-right"></i>
          </button>
          <button class="btn btn-toggle sidenav-toggler">
            <i class="gg-menu-left"></i>
          </button>
        </div>
        <button class="topbar-toggler more">
          <i class="gg-more-vertical-alt"></i>
        </button>
      </div>
      <!-- End Logo Header -->
    </div>
    <!-- Navbar Header -->
    <nav class="navbar navbar-header navbar-header-transparent navbar-expand-lg border-bottom">
      <div class="container-fluid justify-content-between">
        <nav class="navbar navbar-header-left navbar-expand-lg navbar-form nav-search p-0 d-none d-lg-flex">
          <a class="navbar-brand">
            <img src="assets/img/kaiadmin/logo_light.svg" alt="navbar brand" class="navbar-brand" height="20" />
          </a>
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="">상점</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="">암벽장</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="">크루</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="">선수페이지</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="">뉴스페이지</a>
            </li>
          </ul>
        </nav>

        <ul class="navbar-nav topbar-nav ms-md-auto align-items-center">
          <li class="nav-item">
            <a class="nav-link" href="#">
              login
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              마이페이지
            </a>
          </li>
        </ul>
      </div>
    </nav>
    <!-- End Navbar -->
  </div>
  <!-- container start -->
  <div class="container">
    <div class="page-inner">
      <div class="row pt-3">
        <div class="col-md-3 col-lg-3">
          <div class="form-group">
            <select class="form-select form-control-lg" id="largeSelect">
              <option>글 제목</option>
              <option>작성자</option>
              <option>아이디</option>
            </select>
          </div>
        </div>
        <div class="col-md-6 col-lg-7">
          <div class="form-group">
            <div class="input-icon">
              <input type="text" class="form-control" placeholder="Search for..." />
              <span class="input-icon-addon">
                <i class="fa fa-search"></i>
              </span>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-lg-2 d-flex align-items-center justify-content-end">
          <button class="btn btn-primary btn-round px-5 py-3">글 작성</button>
        </div>
      </div>
      <div class="row pt-5">
        <div class="col-md-10">
          <div class="card card-stats card-round">
            <div class="card-body p-5 d-flex justify-content-between">
              <h3 class="card-title">
                <a href="" class="link-dark">
                  글 제목입니다.
                </a>
              </h3>
              <div class="info-user">
                <div class="username">작성자 : 코마</div>
                <div class="status">글 조회수 : 1</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row pt-2">
        <div class="col-md-10">
          <div class="card card-stats card-round">
            <div class="card-body p-5 d-flex justify-content-between">
              <h3 class="card-title">
                <a href="" class="link-dark">
                  글 제목입니다.
                </a>
              </h3>
              <div class="info-user">
                <div class="username">작성자 : 코마</div>
                <div class="status">글 조회수 : 1</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row pt-2">
        <div class="col-md-10">
          <div class="card card-stats card-round">
            <div class="card-body p-5 d-flex justify-content-between">
              <h3 class="card-title">
                <a href="" class="link-dark">
                  글 제목입니다.
                </a>
              </h3>
              <div class="info-user">
                <div class="username">작성자 : 코마</div>
                <div class="status">글 조회수 : 1</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row pt-2">
        <div class="col-md-10">
          <div class="card card-stats card-round">
            <div class="card-body p-5 d-flex justify-content-between">
              <h3 class="card-title">
                <a href="" class="link-dark">
                  글 제목입니다.
                </a>
              </h3>
              <div class="info-user">
                <div class="username">작성자 : 코마</div>
                <div class="status">글 조회수 : 1</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row pt-2">
        <div class="col-md-10">
          <div class="card card-stats card-round">
            <div class="card-body p-5 d-flex justify-content-between">
              <h3 class="card-title">
                <a href="" class="link-dark">
                  글 제목입니다.
                </a>
              </h3>
              <div class="info-user">
                <div class="username">작성자 : 코마</div>
                <div class="status">글 조회수 : 1</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row pt-2">
        <div class="col-md-10">
          <div class="card card-stats card-round">
            <div class="card-body p-5 d-flex justify-content-between">
              <h3 class="card-title">
                <a href="" class="link-dark">
                  글 제목입니다.
                </a>
              </h3>
              <div class="info-user">
                <div class="username">작성자 : 코마</div>
                <div class="status">글 조회수 : 1</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row pt-2">
        <div class="col-md-10">
          <div class="card card-stats card-round">
            <div class="card-body p-5 d-flex justify-content-between">
              <h3 class="card-title">
                <a href="" class="link-dark">
                  글 제목입니다.
                </a>
              </h3>
              <div class="info-user">
                <div class="username">작성자 : 코마</div>
                <div class="status">글 조회수 : 1</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row pt-2">
        <div class="col-md-10">
          <div class="card card-stats card-round">
            <div class="card-body p-5 d-flex justify-content-between">
              <h3 class="card-title">
                <a href="" class="link-dark">
                  글 제목입니다.
                </a>
              </h3>
              <div class="info-user">
                <div class="username">작성자 : 코마</div>
                <div class="status">글 조회수 : 1</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row pt-2">
        <div class="col-md-10">
          <div class="card card-stats card-round">
            <div class="card-body p-5 d-flex justify-content-between">
              <h3 class="card-title">
                <a href="" class="link-dark">
                  글 제목입니다.
                </a>
              </h3>
              <div class="info-user">
                <div class="username">작성자 : 코마</div>
                <div class="status">글 조회수 : 1</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row pt-2">
        <div class="col-md-10">
          <div class="card card-stats card-round">
            <div class="card-body p-5 d-flex justify-content-between">
              <h3 class="card-title">
                <a href="" class="link-dark">
                  글 제목입니다.
                </a>
              </h3>
              <div class="info-user">
                <div class="username">작성자 : 코마</div>
                <div class="status">글 조회수 : 1</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row pt-2">
        <div class="col-md-10 d-flex justify-content-center">
          <nav aria-label="Page navigation example">
            <ul class="pagination">
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span>
                </a>
              </li>
              <li class="page-item"><a class="page-link rounded-0" href="#">1</a></li>
              <li class="page-item"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Next">
                  <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
    <!-- container end -->
    <div class="cookies" id="cookies">
        <div class="cookies-header p-3 text-end">
            <a id="cookies-cancel" class="btn btn-toggle">x</a>
        </div>
        <div class="cookies-body p-3 text-center">
            <h2 class="text-white">
               최고의 클라이밍 슈즈
            </h2>
            <p class="text-white">모멘텀 암벽화</p>
            <img src="assets/img/shoes.png"/>
            <h2 class="text-white py-3"> ~ 50%</h2>
            <button class="btn btn-info btn-round">바로 구매하기</button>
        </div>
    </div>
  </div>
  <!--   Core JS Files   -->
  <script src="assets/js/core/jquery-3.7.1.min.js"></script>
  <script src="assets/js/core/popper.min.js"></script>
  <script src="assets/js/core/bootstrap.min.js"></script>


  <!-- jQuery Scrollbar -->
  <script src="assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

  <!-- Chart JS -->
  <script src="assets/js/plugin/chart.js/chart.min.js"></script>

  <!-- jQuery Sparkline -->
  <script src="assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

  <!-- Chart Circle -->
  <script src="assets/js/plugin/chart-circle/circles.min.js"></script>

  <!-- Datatables -->
  <script src="assets/js/plugin/datatables/datatables.min.js"></script>

  <!-- Bootstrap Notify -->
  <script src="assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>

  <!-- jQuery Vector Maps -->
  <script src="assets/js/plugin/jsvectormap/jsvectormap.min.js"></script>
  <script src="assets/js/plugin/jsvectormap/world.js"></script>

  <!-- Sweet Alert -->
  <script src="assets/js/plugin/sweetalert/sweetalert.min.js"></script>

  <!-- Kaiadmin JS -->
  <script src="assets/js/kaiadmin.min.js"></script>

  <!-- Kaiadmin DEMO methods, don't include it in your project! -->
  <script src="assets/js/setting-demo.js"></script>
  <script src="assets/js/demo.js"></script>
  <script>
        var cookies = document.getElementById("cookies");
        var cancel = document.getElementById("cookies-cancel");

        cancel.addEventListener("click",(e)=>{
            cookies.style.display = "none";
        });

  </script>
</body>

</html>