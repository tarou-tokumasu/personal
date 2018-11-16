<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="jp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset="utf-8" />
	<link rel="stylesheet" type="text/css" href="css/style.css">
    <title>新規ID</title>
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

<div class="jumbotron signup">
        <h5 class="mb-3 text-center">新規ID作成</h5>
      <form action="CreateID" method="post">
      <input type="text" class="form-control mb-3" placeholder="ログインID"  autofocus="" name="login_ID">
       <input type="text" class="form-control mb-3" placeholder="ユーザー名"  autofocus="" name="user_name">
      <input type="password" id="inputPassword" class="form-control mb-3" placeholder="パスワード" required="" name="password">
      <input type="password" id="inputPassword" class="form-control mb-3" placeholder="パスワード（確認）" required="" name="kakunin">
      <input type="date" class="form-control mb-3" placeholder="生年月日"  autofocus="" name="birth_date">
      <input type="text" class="form-control mb-3" placeholder="住所"  autofocus="" name="address">
     <input class="mt-4 btn btn-light form-control" type="submit" value="ID作成">
    </form>
     <button class="mt-4 btn btn-secondary form-control"onClick=location.href="Login">戻る</button>
      </div>
		</div>
</body>
</html>