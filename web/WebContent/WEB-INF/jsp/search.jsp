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
<div class="row">
 <div class="col-sm-3 w-25">
  <form action="SearchItem" method="get">
  <input type="text" class="form-control mb-3" name="item_name" placeholder="商品名" value="${item_name}">
      <select class="mb-3 form-control" name="item_cate">
      <option value="">カテゴリーを選択</option>
      <c:forEach var="c" items="${cateList}">
      <option value="${c.id}"
      <c:if test="${c.id == item_cate}">
      selected="selected"
      </c:if>
      >${c.cate_name}</option>
      </c:forEach>
      </select>
      <select class="mb-3 form-control" name="item_maker">
      <option value="">メーカーを選択</option>>
      <c:forEach var="m" items="${makerList}">
      <option value="${m.id}">${m.maker_name}</option>
      </c:forEach>
      </select>
      		<input type="date"  class="form-control" name="since"  size="5" value="${since}"> ～
		<input type="date"  class="form-control mb-3" name="until" maxlength="10" size="5" value="${until}">
	<input type="submit" class="form-control" value="検索">
  </form>
  </div>

    <div class="col-sm-9 ">
  <div class="jumbotron">
    <h5 class="text-center mt-3 mb-3">検索結果</h5>

<form class="text-right mb-2">
<select onChange="top.location.href=value">
<option value="SearchItem?item_name=${item_name}&item_cate=${item_cate}&item_maker=${item_maker}&since=${since}&until=${until}&sort=1" <c:if test="${sort==1}"> selected="selected"</c:if>> 新着順</option>
<option value="SearchItem?item_name=${item_name}&item_cate=${item_cate}&item_maker=${item_maker}&since=${since}&until=${until}&sort=2"<c:if test="${sort==2}"> selected="selected"</c:if>>価格が低い順</option>
<option value="SearchItem?item_name=${item_name}&item_cate=${item_cate}&item_maker=${item_maker}&since=${since}&until=${until}&sort=3"<c:if test="${sort==3}"> selected="selected"</c:if>>価格が高い順</option>
<option value="SearchItem?item_name=${item_name}&item_cate=${item_cate}&item_maker=${item_maker}&since=${since}&until=${until}&sort=4"<c:if test="${sort==4}"> selected="selected"</c:if>>割引率順</option>
<option value="SearchItem?item_name=${item_name}&item_cate=${item_cate}&item_maker=${item_maker}&since=${since}&until=${until}&sort=5"<c:if test="${sort==5}"> selected="selected"</c:if>>評価が高い順</option>
</select>
</form>
    <table class="table small">
  <thead class="thead-dark">
    <tr class="text-center">
      <th class="w-25">商品名</th>
      <th>ジャンル</th>
      <th>メーカー</th>
      <th>価格</th>
      <th>評価</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="c" begin="${items * (page - 1)}" end="${page * items - 1}" items="${searchList}">
    <tr>
       <td> <a link href="UItemDetail?id=${c.id}" class="text-dark">${c.item_name}</a></td>
       <td class="text-center"> ${c.item_cate}</td>
       <td class="text-center"> ${c.item_maker}</td>
      <td class="text-right">
    <c:if test="${c.item_price_down !=0 }"> <font color="red" class="text-right">${c.item_price_down}%off</font> </c:if>
     <c:choose>
    <c:when test="${c.item_price_down ==0 }">${c.item_pricec} </c:when>
     <c:when test="${c.item_price_down !=0 }"><s class="text-right">${c.item_pricec}</s><span>${c.item_pricew}</span></c:when>
    </c:choose>

    </td>
      <td class="text-right"><b class="text-success">↑${c.upvote}</b> / <b class="text-danger">${c.downvote}↓</b></td>
    </tr>
  </c:forEach>
    </table>
    </div>
    <div class="text-center">
    			<c:forEach begin="1" end="${max}" varStatus="s">
			    		<c:if test="${page ==s.count }"> <a  class="text-light bg-dark strong "link href="SearchResult?page=${s.count}">${s.count} </a> </c:if>
			<c:if test="${page !=s.count }"> <a  class="text-dark strong"link href="SearchResult?page=${s.count}">${s.count} </a></c:if>
    			</c:forEach>

    </div>

</div>
    <button class="mt-3 btn  btn-secondary form-control" type="submit" onClick=location.href="index">戻る</button>
</div>
</div>
</body>
</html>