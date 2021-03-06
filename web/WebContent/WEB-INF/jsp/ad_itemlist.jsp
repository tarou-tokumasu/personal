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

<div class="jumbotron">
<h5 class="text-center mb-3">商品検索</h5>

		<form class="text-center" action="ItemList" method="post">

		<input type="text" class="form" name="item_name" placeholder="商品名"><br><br>
      <select class="mb-3 form" name="item_cate">
      <option value="">カテゴリーを選択</option>
      <c:forEach var="c" items="${cateList}">
      <option value="${c.id}">${c.cate_name}</option>
      </c:forEach>
      </select>
      <br><br>
      <select class="mb-3 form" name="item_maker">
      <option value="">メーカーを選択</option>>
      <c:forEach var="m" items="${makerList}">
      <option value="${m.id}">${m.maker_name}</option>
      </c:forEach>
      </select>
      <br><br>
		<input type="date"  class="form" name="since"  size="5"> ～
		<input type="date"  class="form" name="until" maxlength="10" size="5"><br><br>
		<input  class="mb-2 btn  btn-secondary" type="submit" value="検索">

		</form>
<button class="btn btn-secondary mt-3 mb-3" onclick="location.href='AddItem'">新規登録</button>
		</div>



    <div class="jumbotron">
    <h5 class="text-center mt-3 mb-3">商品一覧</h5>

    <table class="table">
  <thead class="thead-dark">
    <tr class="text-center">
      <th>商品名</th>
      <th>カテゴリ</th>
      <th>メーカー</th>
      <th>値段</th>
      <th>割引情報</th>
      <th>取扱開始日</th>
      <th>メニュー</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="i" items="${itemList}">
    <tr>
       <td>${i.item_name}</td>
      <td>${i.item_cate}</td>
      <td>${i.item_maker}</td>
      <td class="text-right">${i.item_pricec}</td>
      <td class="text-right">${i.item_price_down}％</td>
      <td>${i.item_date}</td>
      <td class="text-center">
       <button class="btn  btn-secondary" type="submit" onClick=location.href="ItemDetail?id=${i.id}">詳細</button>
       <button class="btn  btn-secondary" type="submit" onClick=location.href="ItemUpdate?id=${i.id}">更新</button>
       <button class="btn  btn-secondary" type="submit" onClick=location.href="ItemDelete?id=${i.id}">削除</button>
      </td>
    </tr>
    </c:forEach>
    </table>
    </div>

        <button class="mt-3 mb-3 btn  btn-secondary form-control" type="submit" onClick=location.href="ad_menu.html">戻る</button>
    </div>
</body>
</html>