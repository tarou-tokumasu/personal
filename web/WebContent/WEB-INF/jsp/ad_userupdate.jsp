<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="jp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset="utf-8">
	<link rel="stylesheet" type="text/css" href="css/style.css">
    <title>ユーザー情報更新</title>
    <!-- Bootstrap��CSS�ǂݍ��� -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery�ǂݍ��� -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Bootstrap��JS�ǂݍ��� -->
    <script src="js/bootstrap.min.js"></script>
	</head>

<body class="backg mt-4">

	<div class="container">

<c:if test="${Err !=null}">
<div class="alert alert-danger" role="alert">
${Err}
</div>
</c:if>
<div class="jumbotron">
        <h5 class="mb-3 text-center">ユーザー情報更新</h5>
      <form action="UserUpdate" method="post">
      <input type="hidden" name="url" value="${url}">
      <br><div class="ml-1">ユーザーID　${thisUser.login_id}</div><br>
       <input type="hidden" class="form-control mb-3" value="${thisUser.id}" name="ID">
             <input type="hidden" class="form-control mb-3" value="${thisUser.login_id}" name="login_ID">
       <input type="text" class="form-control mb-3" value="${thisUser.user_name}" name="user_name">
      <input type="password" id="inputPassword" class="form-control mb-3" placeholder="パスワード変更したい場合は入力して下さい"  name="password">
      <input type="password" id="inputPassword" class="form-control mb-3" placeholder="パスワード（確認）"  name="kakunin">
      <input type="date" class="form-control mb-3" value="${thisUser.birth_date}" name="birth_date">
      <input type="text" class="form-control mb-3" value="${thisUser.address}" name="address">

    <input class="mt-4 btn btn-light form-control" type="submit"  value="変更">
    </form>

      </div>
       <button class="mt-4 btn btn-secondary form-control" type="submit" onClick=location.href="UserList">戻る</button>
		</div>
</body>
</html>