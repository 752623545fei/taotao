<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="max-age=300" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的信息 - 淘淘</title>
<meta name="Keywords" content="java,淘淘java" />
<meta name="description" content="在淘淘中找到了29910件java的类似商品，其中包含了“图书”，“电子书”，“教育音像”，“骑行运动”等类型的java的商品。" />
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/myjd.common.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/myjd.info.css" media="all" />
    <link type="text/css" rel="stylesheet" href="/css/saved_resource">
    <link type="text/css" rel="stylesheet" href="/css/myjd.easebuy.css" source="combo">
    <link type="text/css" rel="stylesheet" href="/css/saved_resource(1)" source="widget">
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body>
<script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/easybuy.js" charset="utf-8"></script>
<!-- header start -->
<jsp:include page="commons/header.jsp" />
<!-- header end -->

<div id="container">
<div class="w">
	
<div id="main">
    <div class="g-0">
        <div id="content" class="c-3-5">
            <div class="mt">
                <a onclick="alertAddAddressDiag()" class="e-btn add-btn btn-5" href="javascript:;">新增收货地址</a>
                <span class="ftx-03">您已创建<span id="addressNum_top" class="ftx-02">${listSize} </span>个收货地址，最多可创建<span class="ftx-02">20</span>个</span>
            </div>

            <c:forEach items="${addressList}" var="address">
                <div class="sm easebuy-m " id="addresssDiv-137936251">
                    <div class="smt">
                        <h3>
                            ${address.consigneeName} &nbsp;&nbsp; ${address.provinceId}
                                <%--<a onclick="modifyAliasTips(137936251,event);" id="alias-edit-137936251" class="alias-edit" href="javascript:;"></a>--%>
                        </h3>
                        <div class="extra">
                            <a onclick="alertDelAddressDiag(${address.addressId});" class="del-btn" href="#none">删除</a>
                        </div>
                            <%--<div id="alias-form-137936251" class="alias-form hide">--%>
                            <%--<div class="alias-new">--%>
                            <%--<input type="text" class="ipt-text" id="ipt-text-137936251" value="家里" onblur="checkConsigneeAlias('ipt-text-137936251')" maxlength="20">--%>
                            <%--<button type="button" class="btn-save" onclick="saveAddessAlias(137936251,event)">保存</button>--%>
                            <%--</div>--%>
                            <%--<div class="alias-common">--%>
                            <%--<div class="ac-tip">建议填写常用名称：</div>--%>
                            <%--<div class="ac-con">--%>
                            <%--<a href="javascript:setAddressAilas(137936251,'home-137936251');" id="home-137936251" class="item">家里</a>--%>
                            <%--<a href="javascript:setAddressAilas(137936251,'parentHome-137936251');" id="parentHome-137936251" class="item">父母家</a>--%>
                            <%--<a href="javascript:setAddressAilas(137936251,'company-137936251');" id="company-137936251" class="item">公司</a>--%>
                            <%--</div>--%>
                            <%--<span class="error-msg" id="error_ipt-text-137936251"></span>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                    </div>

                    <div class="smc">
                        <div class="items new-items">
                            <div class="item-lcol">
                                <div class="item">
                                    <span class="label">收货人：</span>
                                    <div class="fl">
                                        ${address.consigneeName}
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <div class="item">
                                    <span class="label">所在地区：</span>
                                    <div class="fl">
                                        ${address.provinceId}${address.cityId}${address.countyId}
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <div class="item">
                                    <span class="label">地址：</span>
                                    <div class="fl">
                                        ${address.consigneeAddress}
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <div class="item">
                                    <span class="label">手机：</span>
                                    <div class="fl">
                                        ${address.mobile}
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <div class="item">
                                    <span class="label">固定电话：</span>
                                    <div class="fl">
                                        ${address.phone}
                                    </div>
                                    <div class="clr"></div>
                                </div>
                                <div class="item">
                                    <span class="label">电子邮箱：</span>
                                    <div class="fl">
                                        ${address.email}
                                    </div>
                                    <div class="clr"></div>
                                </div>
                            </div>

                            <div class="item-rcol">


                                <div class="extra">
                                    <a class="ml10 ftx-05" href="javascript:;" onclick="alertUpdateAddressDiag(137936251);">编辑</a>
                                </div>
                            </div>
                            <div class="clr"></div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
    <div id="left" class="g-3-5 c-0">
        <!--js 加载异步加载的左侧菜单 -->
        <div id="menu">
            <dl class="fore1">
                <dt id="_MYJD_setting">设置</dt>
                <dd class="fore1_1 curr" id="_MYJD_info">
                    <a clstag="homepage|keycount|home2013|Homeyser" href="#" target="_self">个人信息</a>
                </dd>
                <dd class="fore1_2" id="_MYJD_safe">
                    <a clstag="homepage|keycount|home2013|Homesafe" href="#" target="_self">账户安全</a>
                </dd>
                <dd class="fore1_3" id="_MYJD_accbinde">
                    <a clstag="homepage|keycount|home2013|Homezhbd" href="#" target="_self">账号绑定</a>
                </dd>
                <dd class="fore1_4" id="_MYJD_grade">
                    <a clstag="homepage|keycount|home2013|Homegrade" href="#" target="_self">我的级别</a>
                </dd>
                <dd class="fore1_5" id="_MYJD_comments">
                    <a clstag="homepage|keycount|home2013|homeadd" href="/user/myaddress.html" target="_self">收货地址</a>
                </dd>
                <dd class="fore1_6" id="_MYJD_share">
                    <a clstag="homepage|keycount|home2013|Homeshare" href="#" target="_self">分享绑定</a>
                </dd>
                <dd class="fore1_7" id="_MYJD_rss">
                    <a clstag="homepage|keycount|home2013|Homeedm" href="#" target="_self">邮件订阅</a>
                </dd>
                <dd class="fore1_8" id="_MYJD_recor">
                    <a clstag="homepage|keycount|home2013|Homeedm" href="#" target="_self">消费记录</a>
                </dd>
                <dd class="fore1_9" id="_MYJD_app">
                    <a clstag="homepage|keycount|home2013|Homeedm" href="#" target="_blank">应用授权</a>
                </dd>
                <dd class="fore1_10" id="_MYJD_pay">
                    <a clstag="homepage|keycount|home2013|Homequick" href="#" target="_blank">快捷支付</a>
                </dd>
                <dd class="fore1_11" id="_MYJD_zpzz">
                    <a clstag="homepage|keycount|home2013|Homezpzz" href="#" target="_self">增票资质</a>
                </dd>
                <dd class="fore1_12" id="_MYJD_qyfp">
                    <a clstag="homepage|keycount|home2013|Homeqyfp" href="#" target="_self">企业发票</a>
                </dd>
                <dd class="fore1_13" id="_MYJD_cgxqd">
                    <a clstag="homepage|keycount|home2013|Homecgxqd" href="#" target="_self">采购需求单</a>
                </dd>
            </dl>
        </div>
        <div id="da-game" class="da-box m"><a
                href="#"><img
                width="100%" src="/images/547e6a57N75c2f016.gif" alt=""></a></div>
        <div id="da-home" class="da-box"><a
                href="#"
                target="_blank"><img width="100%" height="100%" alt="" app="image:poster"
                                     src="/images/549d03d0N59b1f026.jpg"></a>
        </div>
    </div>

    <span class="clr"></span>
</div>
</div>
</div>

<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->
</body>
</html>