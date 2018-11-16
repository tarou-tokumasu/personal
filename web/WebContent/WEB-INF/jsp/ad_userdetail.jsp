<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="jp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="style.css">
    <title>ユーザー詳細</title>
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
<h5 class="text-center mb-3">ユーザー詳細</h5>
	<br>

		<table class="mx-auto">
		<tr><td class="p-2">ログインID</td><td>${thisUser.login_id }</td></tr>
		<tr><td class="p-2">ユーザ名</td><td>${thisUser.user_name}</td></tr>
		<tr><td class="p-2">生年月日</td><td>${thisUser.formatBirthDate}</td></tr>
		<tr><td class="p-2">住所</td><td>${thisUser.address}</td></tr>
		<tr><td class="p-2">登録日時</td><td>${thisUser.formatCreateDate}</td></tr>
		<tr><td class="p-2">更新日時</td><td>${thisUser.formatUpdateDate}</td></tr>
		</table>
	<br>



		</form>
		</div>


        <button class="mt-3 btn  btn-secondary form-control" type="submit" onClick=location.href="UserList">戻る</button>
    </div>
</body>
</html>