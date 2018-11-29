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

<c:if test="${notice !=null}">
<div class="alert alert-success" role="alert">
${notice}
</div>
</c:if>

<div class="jumbotron">
<h5 class="text-center mt-3 mb-3">購入確認</h5>
<table class="table">
  <thead class="thead-dark">
    <tr class="text-center">
      <th>商品名</th>
      <th>値段</th>
      <th></th>
    </tr>
  </thead>
   <tbody><form action="Bought" method="post">
  <c:forEach var="c" items="${cart}">

    <tr>
      <td>${c.item_name}</td>
      <td class="text-center">
      <c:choose>
    <c:when test="${c.item_price_down ==0 }">${c.item_pricec} </c:when>
     <c:when test="${c.item_price_down !=0 }"><s>${c.item_pricec}</s>${c.item_pricew} </c:when>
    </c:choose>

   <c:if test="${c.item_price_down !=0 }"> <font color="red">${c.item_price_down}%off</font> </c:if>

    </tr>
 </c:forEach>
    </td></tr>

 <tr></tr>

   <tr>
   <td class="">小計</td>
   <td class="text-center">￥${total}</td>
   <td class="center"></td>
   </tr>
    <tr>
   <td class="">送料</td>
   <td class="text-center">￥${deli.deli_price}</td>
   <td class="center"></td>
   </tr>
   <c:if test="${point != 0}">
       <tr>
   <td class="">ポイント使用</td>
   <td class="text-center">${point}P</td>
   <td class="center"></td>
   </tr>
   </c:if>
    <tr>
   <td class="">合計</td>
   <td class="text-center">${total2}</td>
   <td class="center"></td>


   </tbody>

    </table>


    </div>
	<input type="submit" class="mt-3 btn  btn-secondary form-control" value="購入する">

	</form>
	<button class="mt-3 btn  btn-secondary form-control" type="submit" onClick=location.href="Cart">戻る</button>
</div>
</body>
</html>
