<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="jp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <title>新規商品登録</title>
    <!-- BootstrapのCSS読み込み -->

    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- BootstrapのJS読み込み -->
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
        <h5 class="mb-3 text-center">新規商品登録</h5>
      <form action="AddItem" method="post" enctype="multipart/form-data">
      <input type="text" class="form-control mb-3" placeholder="商品名" name="item_name">
      <select class="form-control mb-3" name="item_cate">
      <option value="">カテゴリーを選択</option>
      <c:forEach var="c" items="${cateList}">
      <option value="${c.id}">${c.cate_name}</option>
      </c:forEach>
      </select>
      <select class="form-control mb-3" name="item_maker">
      <option value="">メーカーを選択</option>>
      <c:forEach var="m" items="${makerList}">
      <option value="${m.id}">${m.maker_name}</option>
      </c:forEach>
      </select>

      <input type="text" class="form-control mb-3" placeholder="値段" name="item_price">

	<input type="file" class="mb-3" name="file">

       <input type="text" class="form-control mb-3" placeholder="割引率 （0=割引なし 10=10%オフ" name="item_price_down">
    <input  class="mt-4 btn btn-light form-control" type="submit" value="新規登録">
    </form>
        <button class="mt-3 mb-3 btn  btn-secondary form-control" type="submit" onClick=location.href="ItemList">戻る</button>
      </div>
		</div>
</body>
</html>