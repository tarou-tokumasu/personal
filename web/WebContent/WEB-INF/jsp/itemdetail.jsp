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
			<c:if test="${revote.upvote>0 or revote.downvote<0 }"><div class="p-2"><b class="text-success">↑${revote.upvote}</b> / <b class="text-danger">${revote.downvote}↓</b>
			 <small>${revote.percent}のユーザーがこの商品を高評価しました</small></c:if>
			 <c:if test="${revote.upvote==0}"><div class="p-2">
			 <small>まだ誰もレビュー・評価をしていません。</small></c:if>

			<c:if test="${rev==true}">
			<a class="text-dark" link href="Review?id=${thisItem.id}" ><b>レビューする</b></a></c:if></div>

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

<c:if test="${revs!=null}">
<select onChange="top.location.href=value">
<option value="SortReview?sort=1&id=${thisItem.id}" <c:if test="${sort2==1}"> selected="selected"</c:if>> 新着順</option>
<option value="SortReview?sort=2&id=${thisItem.id}"<c:if test="${sort2==2}"> selected="selected"</c:if>>評価順</option>
</select>
</c:if>
<br>

<c:forEach var="c" items="${revs}">
		<div class="row border bg-light waku2 mx-auto mb-3">
		<div class="col-sm-1">
		<c:if test="${c.vote==1}"><div class="mx-auto"><img src=img/upvote.png></div></c:if>
		<c:if test="${c.vote==-1}"><div class="mx-auto"><img src=img/downvote.png></div></c:if>
		</div>

		<div class="col">
		<div class="d-flex align-items-start flex-column">
			<div class="p-2">${c.reviewer}</div>
			<div class="p-2">${c.review }</div>
			<div class="p-2"><small>${c.formaDate}</small></div>
<form  class="ml-auto" action="UItemDetail?id=${c.id}&idd=${thisItem.id}" method="post"><div class="mx-auto p-2"><b>${c.plus}</b>

			<c:if test="${c.user_id == userInfo.id or userInfo.id==1}">
			 <input type="submit" class="btn btn-secondary" value="削除"onclick="return confirm('本当に削除してよろしいですか？');">
			 </c:if>

			<c:if test="${c.user_id != userInfo.id and userInfo!=null}">
			このレビューは参考になりましたか？
	<div class="btn-group btn-group-toggle" data-toggle="buttons">
  <label class="btn btn-secondary <c:if test="${recheck==1}">active</c:if>">
    <input type="radio" name="options"  onChange=location.href="Rereview?vote=1&recheck=${recheck}&id=${c.id}&idd=${c.item_id}"  autocomplete="off"> はい
  </label>
  <label class="btn btn-secondary <c:if test="${recheck==-1}">active</c:if>">
    <input type="radio" name="options"  onChange=location.href="Rereview?vote=-1&recheck=${recheck}&id=${c.id}&idd=${c.item_id}" autocomplete="off"  > いいえ
  </label>
</div>
			  </c:if>
			   </form>
			 </div>
			 </div>
			</div>
		</div>


</c:forEach>
		<button class="mt-3 btn  btn-secondary form-control" type="submit" onClick=location.href="AddCart?id=${thisItem.id}">カートに入れる</button>
		<button class="mt-3 btn  btn-secondary form-control" type="submit" onClick="history.back()">戻る</button>

</div>
</body>
</html>