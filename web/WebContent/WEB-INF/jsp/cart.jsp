<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="jp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
     <h5 class="my-0 mr-md-auto font-weight-normal"> <a  class="text-dark" link href="index">ECサイト</a></h5>
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
       <a class="text-dark" link href="ranking.html">ランキング</a>
        <a class="p-2 text-dark" link href="userdetail.html">ユーザー情報</a>
         <a class="p-2 text-dark" link href="cart.html">カートの確認</a>
        <a class="p-2 text-dark"  link href="mylist.html">マイリスト</a>
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

<c:if test="${notice !=null}">
<div class="alert alert-success" role="alert">
${notice}
</div>
</c:if>

<div class="jumbotron">
<h5 class="text-center mt-3 mb-3">カート</h5>
<table class="table">
  <thead class="thead-dark">
    <tr class="text-center">
      <th>商品名</th>
      <th>値段</th>
      <th></th>
    </tr>
  </thead>
   <tbody><form action="Cart" method="post">
  <c:forEach var="c" items="${cart}">

    <tr>
      <td>${c.item_name}</td>
      <td class="text-center">
      <c:choose>
    <c:when test="${c.item_price_down ==0 }">${c.item_pricec} </c:when>
     <c:when test="${c.item_price_down !=0 }"><s>${c.item_pricec}</s>${c.item_pricew} </c:when>
    </c:choose>

   <c:if test="${c.item_price_down !=0 }"> <font color="red">${c.item_price_down}%off</font> </c:if>

      </td>
      <td class="text-right">
 <input type="checkbox" value="${c.id}" name="delete">削除
	</td>
    </tr>
 </c:forEach>
    </td></tr>

 <tr></tr>
<c:if test="${items!=1}">
   <tr>
   <td class="">小計</td>
   <td class="text-center">￥${total}</td>
   <td class="center"></td>
</tr>
</c:if>
<c:if test="${items==1}">
   <tr>
   <td class="">カートに商品が入っていません</td>
</tr>
</c:if>

<c:if test="${userInfo!= null}">
<tr> <td></td><td></td><td class="text-right">
    <select name="deli">
   <c:forEach var="d" items="${deliList}">
   <option value="${d.id}">
   ${d.deli_name} ￥${d.deli_price}
   </option>
   </c:forEach>
   </select>
</td></tr>
<tr> <td></td><td></td><td class="text-right">
   現在所持ポイント: ${userInfo.point} <input type="text" name="point" placeholder="1P=1円の値引き">
</td></tr>
</c:if>
   </tbody>

    </table>


    </div>
  <c:if test="${items!=1}">
    <button class="mt-3 btn  btn-secondary form-control" type="submit" name="action" value="doDel">チェックされた商品を削除</button>
    </c:if>
	<button class="mt-3 btn  btn-secondary form-control" type="submit" name="action" value="doRegi">レジへ進む（要ログイン）</button>

	</form>
	<button class="mt-3 btn  btn-secondary form-control" type="submit" onClick="history.go(-2)">戻る</button>
</div>
</body>
</html>
