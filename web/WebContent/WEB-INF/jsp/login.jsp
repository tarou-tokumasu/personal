<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<title>ログイン画面</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</head>
<body class="backg text-center mt-4">
		<div class="container">


<div class="jumbotron signup">
        <h5 class="mb-3">ログイン</h5>
      <form action="index" method="post">
      <input type="text"  name="loginID" class="form-control mb-3" placeholder="ユーザーID" required="" autofocus="">
      <input type="password" name="password" id="inputPassword" class="form-control mb-3" placeholder="パスワード" required="">
      <input type="submit" class="mt-3 btn  btn-secondary form-control"  value="ログイン">
    </form>
    <input class="mt-4 btn btn-light form-control" type="submit" onClick=location.href="createid.html" >
      </div>
		</div>
	</body>
</html>
