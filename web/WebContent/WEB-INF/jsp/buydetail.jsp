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
     <h5 class="my-0 mr-md-auto font-weight-normal"> <a  class="text-dark" link href="Index">ECサイト</a></h5>
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
       <a class="text-dark" link href="Ranking">ランキング</a>
       <c:if test="${userInfo!=null}">
        <a class="p-2 text-dark" link href="UUserDetail">ユーザー情報</a>
         <a class="p-2 text-dark"  link href="MyList">マイリスト</a>
        </c:if>
         <a class="p-2 text-dark" link href="Cart">カートの確認</a>
      </nav>
      <c:if test="${userInfo !=null}">
      <a  class="text-danger" link href="LogOut">ログアウト</a>
      </c:if>
      <c:if test="${userInfo ==null}">
      <a  class="text-danger" link href="Login">ログイン</a>
      </c:if>
    </div>
<div class="container">

<div class="jumbotron">
    <h5 class="text-center mt-3 mb-3">購入情報詳細</h5>

     <table class="table">
  <thead class="thead-dark">
    <tr class="text-center">
      <th>購入日時</th>
      <th>総額</th>
      <th>配送方法</th>
      <c:choose>
      <c:when test="${iDB.pointmov >0}"><th>獲得ポイント</th></c:when>
       <c:when test="${iDB.pointmov <0}"><th>消費ポイント</th></c:when>
      </c:choose>
    </tr>
  </thead>
  <tbody>
    <tr>
       <td class="text-center"> ${iDB.formatDate}</td>
      <td class="text-right">${iDB.item_pricec}</td>
      <td class="text-center">${iDB.deli_name}</td>
            <td class="text-right">${iDB.pointmov}</td>
    </tr>
    </table>
    </div>

     <div class="jumbotron">
    <h5 class="text-center mt-3 mb-3">購入した商品</h5>

    <table class="table">
  <thead class="thead-dark">
    <tr class="text-center">
      <th>商品名</th>
      <th>単価</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="c" items="${BuyD}">
    <tr>
      <td class="text-center">${c.item_name}</td>
      <td class="text-right">${c.item_pricec}</td>
    </tr>
    </c:forEach>
    <tr>
      <td class="text-center">${iDB.deli_name}</td>
      <td class="text-right">${iDB.deli_price}</td>
    </tr>
    </table>
    </div>
    <button class="mt-3 btn  btn-secondary form-control" type="submit" onClick="history.back()">戻る</button>
    </div>
</body>
</html>