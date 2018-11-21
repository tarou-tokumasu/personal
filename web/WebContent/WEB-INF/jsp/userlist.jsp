<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="jp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="style.css">
    <title>メイン画面</title>
    <!-- BootstrapのCSS読み込み -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- BootstrapのJS読み込み -->
    <script src="js/bootstrap.min.js"></script>
	</head>
<body class="backg">
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-light border-bottom shadow-sm">
      <h5 class="my-0 mr-md-auto font-weight-normal">ECサイト</h5>
      <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2  href="#">ユーザー名</a>
        <a class="p-2  href="userdetail.html">ユーザーリスト</a>
        <a class="p-2  href="mylist.html">マイリスト</a>
      </nav>
      <a  class="text-danger" link href="login.html">ログアウト</a>
    </div>
<div class="container">

<div class="jumbotron">
<h5 class="text-center mb-3">ユーザー検索</h5>

		<form class="text-center" action="UserList" method="post" >

		<input type="text" name="loginId" placeholder="ログインID（完全一致）"><br><br>
		<input type="text" name="userName"  placeholder="ユーザー名（部分一致）"><br><br>
		<input type="text" name="address"  placeholder="住所（部分一致）"><br><br>
		<input type="date" name="since"  size="5"> ～
		<input type="date" name="until" maxlength="10" size="5"><br><br>
		<input  class="mb-2 btn secondary" type="submit" value="検索">


		</form>
		</div>



    <div class="jumbotron">
    <h5 class="text-center mt-3 mb-3">ユーザー一覧</h5>

    <table class="table">
  <thead class="thead-dark">
    <tr class="text-center">
      <th>ユーザーID</th>
      <th>ユーザーネーム</th>
      <th>生年月日</th>
      <th>住所</th>
      <th>ポイント</th>
      <th>メニュー</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach var="u" items="${userList}">
   <c:if test="${u.login_id !='admin'}">
    <tr>
       <td>${u.login_id}</td>
      <td>${u.user_name}</td>
      <td>${u.birth_date}</td>
      <td>${u.address }</td>
      <td class="text-right">${u.point}</td>
      <td class="text-center">
       <button class="btn  btn-secondary" type="submit" onClick=location.href="UserDetail?id=${u.id}">詳細</button>
       <button class="btn  btn-secondary" type="submit" onClick=location.href="UserUpdate?id=${u.id}">更新</button>
       <button class="btn  btn-secondary" type="submit" onClick=location.href="UserDelete?id=${u.id}">削除</button>
      </td>
    </tr>
    </c:if>
    </c:forEach>
    </table>
    </div>

        <button class="mt-3 btn  btn-secondary form-control" type="submit" onClick=location.href="Ad_Menu">戻る</button>
    </div>
</body>
</html>