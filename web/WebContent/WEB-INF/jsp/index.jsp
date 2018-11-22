<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="jp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
       <c:if test="${userInfo!=null}">
        <a class="p-2 text-dark" link href="UUserDetail">ユーザー情報</a>
        </c:if>
         <a class="p-2 text-dark" link href="Cart">カートの確認</a>
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
  <form action="SearchItem" method="post">
  <input type="text" class="form-control mb-3" name="item_name" placeholder="商品名">
      <select class="mb-3 form-control" name="item_cate">
      <option value="">カテゴリーを選択</option>
      <c:forEach var="c" items="${cateList}">
      <option value="${c.id}">${c.cate_name}</option>
      </c:forEach>
      </select>
      <select class="mb-3 form-control" name="item_maker">
      <option value="">メーカーを選択</option>>
      <c:forEach var="m" items="${makerList}">
      <option value="${m.id}">${m.maker_name}</option>
      </c:forEach>
      </select>
      		<input type="date"  class="form-control" name="since"  size="5"> ～
		<input type="date"  class="form-control mb-3" name="until" maxlength="10" size="5">
	<input type="submit" class="form-control" value="検索">
  </form>


  </div>


  <div class="col-sm-9 ">

 <a class="text-dark" link href="SearchItem?sort=4"><h5>割引中</h5></a>
 <div class="row">

 <c:forEach var="n" items="${saleList}">
 <div class="col-sm-3">
  <a  class="text-dark" link href="UItemDetail?id=${n.id}">
   <div class="card" style="width: 14rem;">
 <img class="card-img-top" src="img/${n.item_pic}" alt="Card image cap">
  <div class="card-body">
    <h6 class="small">${n.item_name}</h6>
     <p class="card-text small">${n.item_cate}</p>
    <p class="card-text small">
    <c:choose>
    <c:when test="${n.item_price_down ==0 }">${n.item_pricec} </c:when>
     <c:when test="${n.item_price_down !=0 }"><s>${n.item_pricec}</s>${n.item_pricew} </c:when>
    </c:choose>

   <c:if test="${n.item_price_down !=0 }"> <font color="red">${n.item_price_down}%off</font> </c:if>

    <p class="card-text small">${n.item_date}</p>
    </a>

  </div>
  </div>
  </div>
  </c:forEach>
  </div>



   <a class="text-dark mt-5" link href="SearchItem?sort=1"><h5>新作</h5></a>
 <div class="row mb-4">

 <c:forEach var="n" items="${newList}">
 <div class="col-sm-3">

  <a  class="text-dark" link href="UItemDetail?id=${n.id}">
  <div class="card" style="width: 14rem;">
  <img class="card-img-top" src="img/${n.item_pic}" alt="Card image cap">
  <div class="card-body">
    <h6 class="small">${n.item_name}</h6>
     <p class="card-text small">${n.item_cate}</p>
    <p class="card-text small">
    <c:choose>
    <c:when test="${n.item_price_down ==0 }">${n.item_pricec} </c:when>
     <c:when test="${n.item_price_down !=0 }"><s>${n.item_pricec}</s>${n.item_pricew} </c:when>
    </c:choose>

   <c:if test="${n.item_price_down !=0 }"> <font color="red">${n.item_price_down}%off</font> </c:if>

    <p class="card-text small">${n.item_date}</p>
    </a>

  </div>
  </div>
  </div>
 </c:forEach>


</div>
  </div>
</div>




</div>
	</body>
</html>
