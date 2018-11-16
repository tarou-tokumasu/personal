<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="jp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="style.css">
    <title>商品詳細</title>
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
<h5 class="text-center mb-3">商品詳細</h5>
	<br>

		<table class="mx-auto">
		<tr><td class="p-2">商品名</td><td>${thisItem.item_name }</td></tr>
		<tr><td class="p-2">カテゴリ</td><td>${thisItem.item_cate}</td></tr>
		<tr><td class="p-2">メーカー</td><td>${thisItem.item_maker}</td></tr>
		<tr><td class="p-2">値段</td><td>${thisItem.item_price}</td></tr>
		<tr><td class="p-2">割引</td><td>${thisItem.item_price_down}%</td></tr>
		<tr><td class="p-2">取扱開始日</td><td>${thisItem.item_date}</td></tr>
		</table>
	<br>



		</form>
		</div>


        <button class="mt-3 btn  btn-secondary form-control" type="submit" onClick=location.href="ItemList">戻る</button>
    </div>
</body>
</html>