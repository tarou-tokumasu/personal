<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="jp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="css/style.css">
    <title>メイン画面</title>
    <!-- BootstrapのCSS読み込み -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- BootstrapのJS読み込み -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="js/bootstrap.min.js"></script>
	</head>
<body class="backg">
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-light border-bottom shadow-sm">
     <h5 class="my-0 mr-md-auto font-weight-normal"> <a  class="text-dark" link href="index">ECサイト</a></h5>
      <nav class="my-2 my-md-0 mr-md-3">
      <c:if test="${userInfo !=null}">
        <a class="p-2">${userInfo.user_name} さん</a>
        </c:if>
       <c:if test="${userInfo ==null}">
        <a class="p-2">ゲスト さん</a>
        </c:if>
		<c:if test="${userInfo.login_id=='admin'}">
        <a class="p-2 text-dark" link href="Ad_Menu">管理者メニュー</a>
        </c:if>
       <a class="text-dark" link href="ranking.html">ランキング</a>
        <a class="p-2 text-dark" link href="userdetail.html">ユーザー情報</a>
         <a class="p-2 text-dark" link href="cart.html">カートの確認</a>
        <a class="p-2 text-dark"  link href="mylist.html">マイリスト</a>
      </nav>
      <c:if test="${userInfo !=null}">
      <a  class="text-danger" link href="LogOut">ログアウト</a>
      </c:if>
      <c:if test="${userInfo ==null}">
      <a  class="text-danger" link href="Login">ログイン</a>
      </c:if>
    </div>
<div class="container">


<div class="waku">
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">

    <a link href="itemdetail.html">
      <img class="d-block w-100" src="img/smartphone_neru_boy.png" alt="First slide" >
      </a>
    </div>
    <div class="carousel-item">

    <a link href="search.html">
      <img class="d-block w-100" src="img/smartphone_neru_boy2.png" alt="Second slide">
    </a>
    </div>
    <div class="carousel-item">
    <a link href="search.html">
      <img class="d-block w-100" src="img/smartphone_neru_boy3.png" alt="Third slide">
      </a>
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</div>

<div class="row">
  <div class="col-sm-3">
  <form>
  <input type="text" class="form" name="search" placeholder="商品名で検索">　<a href="search.html"><img src="img/search.png"></a>
  </form>
  <br><h5>ジャンルで探す</h5>
  <a class="text-dark" href="search.html">・ジャンルA</a><br>
  ・ジャンルB<br>
  ・ジャンルC<br>
   <br><h5>メーカーで探す</h5>
  <a class="text-dark" href="search.html">・ジャンルA</a><br>
  ・ジャンルB<br>
  ・ジャンルC<br>

  </div>
  <div class="col-sm-9 ">
 <a class="text-dark" link href="search.html"> <h5>割引中</h5></a>
 <div class="row">
 <div class="col-sm-3">
  <div class="card" style="width: 10rem;">
  <img class="card-img-top" src="img/terraria.jpg" alt="Card image cap">
  <div class="card-body">
    <h5 class="card-title">Terraria</h5>
    <p class="card-text">評価値</p>
     <p class="card-text">サンドボックス</p>
    <p class="card-text">500円(50% off)</p>
  </div>
  </div>
  </div>
   <div class="col-sm-3">
  <div class="card" style="width: 10rem;">
  <img class="card-img-top" src="img/terraria.jpg" alt="Card image cap">
  <div class="card-body">
    <h5 class="card-title">Terraria</h5>
     <p class="card-text">サンドボックス</p>
    <p class="card-text">500円(50% off)</p>
  </div>
  </div>
  </div>
   <div class="col-sm-3">
  <div class="card" style="width: 10rem;">
  <img class="card-img-top" src="img/terraria.jpg" alt="Card image cap">
  <div class="card-body">
    <h5 class="card-title">Terraria</h5>
     <p class="card-text">サンドボックス</p>
    <p class="card-text">500円(50% off)</p>
  </div>
  </div>
  </div>
   <div class="col-sm-3">
  <div class="card" style="width: 10rem;">
  <a href="itemdetail.html"><img class="card-img-top" src="img/terraria.jpg" alt="Card image cap"></a>
  <div class="card-body">
    <h5 class="card-title">Terraria</h5>
     <p class="card-text">サンドボックス</p>
    <p class="card-text">500円(50% off)</p>
  </div>
  </div>
  </div>
  </div><br>
    <a class="text-dark" link href="search.html"><h5>新作</h5></a>
 <div class="row mb-4">
 <div class="col-sm-3">
  <div class="card" style="width: 10rem;">
  <img class="card-img-top" src="img/terraria.jpg" alt="Card image cap">
  <div class="card-body">
    <h5 class="card-title">Terraria</h5>
     <p class="card-text">サンドボックス</p>
    <p class="card-text">500円(50% off)</p>
  </div>
  </div>
  </div>
   <div class="col-sm-3">
  <div class="card" style="width: 10rem;">
  <img class="card-img-top" src="img/terraria.jpg" alt="Card image cap">
  <div class="card-body">
    <h5 class="card-title">Terraria</h5>
     <p class="card-text">サンドボックス</p>
    <p class="card-text">500円(50% off)</p>
  </div>
  </div>
  </div>
   <div class="col-sm-3">
  <div class="card" style="width: 10rem;">
  <img class="card-img-top" src="img/terraria.jpg" alt="Card image cap">
  <div class="card-body">
    <h5 class="card-title">Terraria</h5>
     <p class="card-text">サンドボックス</p>
    <p class="card-text">500円(50% off)</p>
  </div>
  </div>
  </div>
   <div class="col-sm-3">
  <div class="card" style="width: 10rem;">
  <a href="item.html"><img class="card-img-top" src="img/terraria.jpg" alt="Card image cap"></a>
  <div class="card-body">
    <h5 class="card-title">Terraria</h5>
     <p class="card-text">サンドボックス</p>
    <p class="card-text">500円(50% off)</p>
  </div>
  </div>
  </div>
</div>
  </div>
</div>




</div>
	</body>
</html>
