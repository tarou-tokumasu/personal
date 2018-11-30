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
<h5 class="text-center mb-3">ユーザー情報</h5>
<table class="table">
  <thead class="thead-dark">
    <tr class="text-center">
      <th>住所</th>
      <th>生年月日</th>
      <th>ポイント</th>
    </tr>
  </thead>
  <tbody>
    <tr class="text-center">
      <td>${user2.address}</td>
      <td>${user2.formatBirthDate}</td>
      <td>${user2.point}</td>
    </tr>
    </table>
    <a class="text-muted " link href="UserUpdate?id=${userInfo.id}&return=UUserDetail">更新する</a>


    </div>

    <div class="jumbotron">
    <h5 class="text-center mt-3 mb-3">購入履歴</h5>

    <table class="table">
  <thead class="thead-dark">
    <tr class="text-center">
      <th>購入日時</th>
      <th>総額</th>
      <th>配送方法</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="c" begin="${items * (page - 1)}" end="${page * items - 1}" items="${myDB}">
    <tr>
       <td class="text-center">  <a href="BuyDetail?id=${c.id}"><img src="img/search.png"></a>　${c.formatDate}</td>
      <td class="text-right">${c.item_pricec}</td>
      <td class="text-center">${c.deli_name}</td>
    </tr>
    </c:forEach>
    </table>
    <div class="text-center">
    <c:forEach begin="1" end="${max}" varStatus="s">
    		<c:if test="${page ==s.count }"> <a  class="text-light bg-dark strong "link href="UUserDetail?page=${s.count}">${s.count} </a> </c:if>
			<c:if test="${page !=s.count }"> <a  class="text-dark strong"link href="UUserDetail?page=${s.count}">${s.count} </a></c:if>
    			</c:forEach>
    			</div>
    </div>

        <button class="mt-3 btn  btn-secondary form-control" type="submit" onClick=location.href="index">戻る</button>
    </div>
</body>
</html>