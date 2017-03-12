<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>$商品已成功加入购物车</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="/css/taotao.css" media="all" />
    <link rel="stylesheet" type="text/css" href="/css/pshow.css" media="all" />
    <link rel="stylesheet" type="text/css" href="/css/saved_resource" media="all" />
    <link rel="stylesheet" type="text/css" href="/css/saved_resource(2)" media="all" />

</head>
<body version="140120">

<jsp:include page="commons/header.jsp" />
<!-- header end -->
<div class="main">
    <div class="success-wrap">
        <div class="w" id="result">
            <div class="m succeed-box">
                <div class="mc success-cont">
                    <div class="success-lcol">
                        <div class="success-top"><b class="succ-icon"></b>
                            <h3 class="ftx-02">商品已成功加入购物车！</h3></div>
                    </div>
                    <div class="success-btns success-btns-new">
                        <!--
                        <div class="success-ad">
                            <a href="https://cart.jd.com/addToCart.html?rcd=1&amp;pid=1317252&amp;pc=1&amp;nr=1&amp;rid=1481711154744&amp;em=#none"></a>
                        </div>
                        -->
                        <div>
                            <!-- todo -->
                            <a class="btn-tobback" href="#" target="_blank" clstag="pageclick|keycount|201601152|3">查看商品详情</a>
                            <a class="btn-addtocart" href="http://www.hongde508.top/cart/cart.html" id="GotoShoppingCart" clstag="pageclick|keycount|201601152|4"><b></b>去购物车结算</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/js/lib-v1.js"></script>
<script type="text/javascript" src="/js/product.js"></script>
<script type="text/javascript" src="/js/iplocation_server.js"></script>

</body>
</html>