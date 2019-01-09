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
			</div>

			<div class="mt-auto text-right p-2">
			 <c:choose>
    <c:when test="${thisItem.item_price_down ==0 }">${thisItem.item_pricec} </c:when>
     <c:when test="${thisItem.item_price_down !=0 }"><s>${thisItem.item_pricec}</s>${thisItem.item_pricew} </c:when>
    </c:choose>b

   <c:if test="${thisItem.item_price_down !=0 }"> <font color="red">${thisItem.item_price_down}%off</font> </c:if>

			</div>
		</div>
</div>
<hr>

<br>
		<div class="row border bg-white waku2 mx-auto">
		<div class="col-sm-2 text-center">
		評価
		<form action="Review" method="post">
		<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="vote" id="inlineRadio1" value="1" <c:if test="${def.vote==1}"> checked="checked"</c:if>>
  <label class="form-check-label" for="inlineRadio1"><img src="img/upvote.png"></label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="vote" id="inlineRadio1" value="-1" <c:if test="${def.vote==-1}"> checked="checked"</c:if>>
  <label class="form-check-label" for="inlineRadio1"><img src="img/downvote.png"></label>
</div>
		</div>

		<div class="col">
		<div class="d-flex align-items-start flex-column">

			<div class="p-2">本文<br>
	<div class="form-group">
    <textarea class="form-control" name="review" placeholder="空欄ok" rows="5" cols="100">${def.review}</textarea>
   <input type="submit" class="btn btn-secondary form-control" value="投稿" onclick="return confirm('投稿してよろしいですか？');">
    <input type="hidden" name = "item" value="${thisItem.id}">
     <input type="hidden" name = "user" value="${userInfo.id}">
    </form>
  </div>


			</div>
		</div>
		</div>

</div>

		<button class="mt-3 btn  btn-secondary form-control" type="submit" onClick=location.href="UItemDetail?id=${thisItem.id}">戻る</button>
</body>
</html>