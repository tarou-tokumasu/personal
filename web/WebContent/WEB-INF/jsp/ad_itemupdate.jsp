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
        <h5 class="mb-3 text-center">商品情報更新</h5>
      <form action="ItemUpdate" method="post">
      <br><div class="ml-1">商品名　${thisItem.item_name}</div><br>
             <input type="hidden" class="form-control mb-3" value="${thisItem.id}" name="item_id">
      <select class="form-control mb-3" name="item_cate">
      <option value="1">カテゴリーを選択</option>

      <c:forEach var="c" items="${cateList}">
      <option value="${c.id}"  <c:if test="${thisItem.cate_id == c.id }"> selected="selected" </c:if>> ${c.cate_name}</option>
      </c:forEach>
      </select>
       <select class="form-control mb-3" name="item_maker">
      <option value="">メーカーを選択</option>>
      <c:forEach var="m" items="${makerList}">
       <option value="${m.id}"  <c:if test="${thisItem.maker_id == m.id }"> selected="selected" </c:if>> ${m.maker_name}</option>
      </c:forEach>
      </select>

      <input type="text" class="form-control mb-3 text-right" value="${thisItem.item_price}" name="item_price" placeholder="値段">
      <input type="text" class="form-control mb-3 text-right" value="${thisItem.item_price_down}" name="item_price_down" placeholder="割引率">

    <input class="mt-4 btn btn-light form-control" type="submit"  value="変更">
    </form>

      </div>
       <button class="mt-4 btn btn-secondary form-control" type="submit" onClick=location.href="UserList">戻る</button>
		</div>
</body>
</html>