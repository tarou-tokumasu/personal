<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="jp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" type="text/css" href="css/style.css">
    <title>ランキング</title>
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
       <a class="text-dark" link href="Ranking">ランキング</a>
       <c:if test="${userInfo!=null}">
        <a class="p-2 text-dark" link href="UUserDetail">ユーザー情報</a>
        </c:if>
         <a class="p-2 text-dark" link href="Cart">カートの確認</a>
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


 <h5 class="text-center mt-4 mb-4">ランキング</h5>


 <select class="custom-select custom-select-lg mb-3" onChange="location.href=value;">
  <option value="Ranking" <c:if test="${scate==99}">selected="selected"</c:if>>総合ランキング</option>
      <c:forEach var="c" items="${cateList}">
      <option value="Ranking?id=${c.id}" <c:if test="${scate==c.id}">selected="selected"</c:if>>${c.cate_name}</option>
      </c:forEach>
</select>
			<c:forEach var="c" items="${ranking}">
			 <div class="card flex-md-row mb-4 shadow-sm h-md-250">
            <div class="card-body d-flex flex-column align-items-start">
              <h3 class="mb-0">
                <a class="text-dark" href="UItemDetail?id=${c.id}">${c.item_name}</a>
              </h3>
              <div class="mb-1 text-muted">${c.item_maker}</div>
              <p class="card-text mb-auto">${c.item_cate}</p>
             <p>     <c:choose>
    <c:when test="${c.item_price_down ==0 }">${c.item_pricec} </c:when>
     <c:when test="${c.item_price_down !=0 }"><s>${c.item_pricec}</s>${c.item_pricew} </c:when>
    </c:choose>

   <c:if test="${c.item_price_down !=0 }"> <font color="red">${c.item_price_down}%off</font> </c:if>
   </p>
   販売本数　${c.sales}
               </div>
            <img class="card-img-right flex-auto d-none d-lg-block"  style="width: auto; height: 200px;" src="img/${c.item_pic}" >
          </div>
          </c:forEach>

    <button class="mt-4 mb-4 btn btn-secondary form-control" type="submit" onClick=location.href="index">戻る</button>
         </div>
         </body>
         </html>