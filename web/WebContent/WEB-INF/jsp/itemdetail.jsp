<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="jp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="css/style.css">
    <title>商品詳細</title>
    <!-- Bootstrap��CSS�ǂݍ��� -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery�ǂݍ��� -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Bootstrap��JS�ǂݍ��� -->
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

<c:if test="${Err !=null}">
<div class="alert alert-danger" role="alert">
${Err}
</div>
</c:if>

<c:if test="${notice !=null}">
<div class="alert alert-success" role="alert">
${notice}
</div>
</c:if>

<div class="row mt-4 justify-content-sm-center bg-light border">
		<div class="col-sm-4"><img src="img/${thisItem.item_pic}"></div>

		<div class="col-sm-8">
			<div class="d-flex align-items-end flex-column">
			<div class="p-2 flex-glow-1" >${thisItem.item_name}</div>
			<div class="p-2">${thisItem.item_cate}</div>
			<div class="p-2">${thisItem.item_maker}</div>
			<div class="p-2">評価（未実装）<a class="text-dark" link href="review.html" ><b>レビューする（未実装）</b></a></div>
			<div class="mt-auto p-2">
			 <c:choose>
    <c:when test="${thisItem.item_price_down ==0 }">${thisItem.item_pricec} </c:when>
     <c:when test="${thisItem.item_price_down !=0 }"><s>${thisItem.item_pricec}</s>${thisItem.item_pricew} </c:when>
    </c:choose>

   <c:if test="${thisItem.item_price_down !=0 }"> <font color="red">${thisItem.item_price_down}%off</font> </c:if>

			</div>
			</div>
		</div>
</div>
<hr>

<form class="text-right">
 <select name="rev">
<option value="1">新着順</option>
<option value="2">評価順</option>
</select>
</form>
<br>
		<div class="row border bg-light waku2 mx-auto">
		<div class="col-sm-1">
		<div class="mx-auto"><img src=img/upvote.png></div>
		</div>

		<div class="col">
		<div class="d-flex align-items-start flex-column">

			<div class="p-2">レビュアー名</div>
			<div class="p-2">見出し<br>ああああああああああああああああ</div>
			<div class="mx-auto p-2"><b>+6</b>このレビューは参考になりましたか？
			<button class="btn  btn-secondary" type="submit" onClick=location.href="itemdetail.html">はい</button>
			 <button class="btn  btn-secondary" type="submit" onClick=location.href="itemdetail.html">いいえ</button></div>
			</div>
		</div>
		</div>
		<button class="mt-3 btn  btn-secondary form-control" type="submit" onClick=location.href="AddCart?id=${thisItem.id}">カートに入れる</button>
		<button class="mt-3 btn  btn-secondary form-control" type="submit" onClick="history.back()">戻る</button>

</div>
</body>
</html>