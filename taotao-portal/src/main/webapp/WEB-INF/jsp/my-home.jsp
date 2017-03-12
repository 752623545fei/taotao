<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <meta http-equiv="pragma" content="no-cache">
   <meta http-equiv="cache-control" content="no-cache">
   <meta http-equiv="expires" content="0"> 
   <meta name="format-detection" content="telephone=no">  
   <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
   <meta name="format-detection" content="telephone=no">
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
   <link type="text/css" rel="stylesheet" href="/css/home_css1">
    <script type="text/javascript" src="/js/home_js"></script>
   <link type="text/css" rel="stylesheet" href="/css/home_css2" source="widget">
   <title>个人中心 - 淘淘商城</title>
    <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body> 
<!--shortcut start-->
<jsp:include page="commons/shortcut.jsp" />
<!--shortcut end-->
<div id="o-header-2013"><div id="header-2013" style="display:none;"></div></div>
<div id="nav">
    <div class="w">

        <div class="navitems">
            <ul>
                <li class="fore-1">
                    <a target="_self" href="/index.html" clstag="homepage|keycount|home2013|Homeindex">首页</a>
                </li>
                <li class="fore-3">
                    <div class="dl" clstag="click|keycount|myhome|hsz">
                        <div class="dt">
                            <span class="myjd-set"><a href="/user/myinfo.html">账户设置</a> </span>
                            <b></b>
                        </div>
                        <div class="dd">

                            <a tid="_MYJD_info" clstag="Homeyser" href="#" target="_self"><span>个人信息</span></a>

                            <a tid="_MYJD_safe" clstag="Homesafe" href="#" target="_self"><span>账户安全</span></a>

                            <a tid="_MYJD_accbinde" clstag="Homezhbd" href="#" target="_self"><span>账号绑定</span></a>

                            <a tid="_MYJD_grade" clstag="Homegrade" href="#" target="_self"><span>我的级别</span></a>

                            <a tid="_MYJD_comments" clstag="homeadd" href="#" target="_self"><span>收货地址</span></a>

                            <a tid="_MYJD_share" clstag="Homeshare" href="#" target="_self"><span>分享绑定</span></a>

                            <a tid="_MYJD_rss" clstag="Homeedm" href="#" target="_self"><span>邮件订阅</span></a>

                            <a tid="_MYJD_recor" clstag="Homeedm" href="#" target="_self"><span>消费记录</span></a>

                            <a tid="_MYJD_app" clstag="Homeedm" href="#" target="_blank"><span>应用授权</span></a>

                            <a tid="_MYJD_pay" clstag="Homequick" href="#" target="_blank"><span>快捷支付</span></a>

                            <a tid="_MYJD_zpzz" clstag="Homezpzz" href="#" target="_self"><span>增票资质</span></a>
                            <a tid="_MYJD_qyfp" clstag="Homeqyfp" href="#" target="_self"><span>企业发票</span></a>
                            <a tid="_MYJD_cgxqd" clstag="Homecgxqd" href="#" target="_self"><span>采购需求单</span></a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="nav-r">
            <div id="search-2014">
                <ul id="shelper" class="hide" style="display: none;"></ul>

            </div>

            <div id="hotwords-2014"><a href="#" target="_blank" style="color:#C81623;">购置税减半</a><a href="#" target="_blank">领免息券</a><a href="#" target="_blank">来瓶好酒</a><a href="#" target="_blank">GXG3折</a><a href="#" target="_blank">惊天满减</a><a href="#" target="_blank">全棉大牌说</a></div>
        </div>
        <div class="clr"></div>
    </div>
</div>
<!-- main -->
<div id="container">
    <!--获取服务器时间 -->
    <input id="serverTime" type="hidden" value="1482300291">
    <div class="w">
        <div id="content">
            <div id="sub">
                <!--  /widget/menu/menu.tpl -->
                <div id="menu">
                    <dl class="fore1">
                        <dt id="_MYJD_order">订单中心</dt>
                        <dd class="fore1_1" id="_MYJD_ordercenter">
                            <a clstag="homepage|keycount|home2013|Homeoder" href="/order/myorders.html" target="_self">我的订单</a>
                        </dd>
                        <dd class="fore1_2" id="_MYJD_tuan">
                            <a clstag="homepage|keycount|home2013|Hometuan" href="#" target="_blank">团购订单</a>
                        </dd>
                        <dd class="fore1_3" id="_MYJD_locallife">
                            <a clstag="homepage|keycount|home2013|Homelife" href="#" target="_self">本地生活订单</a>
                        </dd>
                        <dd class="fore1_4" id="_MYJD_yushou">
                            <a clstag="homepage|keycount|home2013|Homeys" href="#" target="_self">我的预售</a>
                        </dd>
                        <dd class="fore1_5" id="_MYJD_comments">
                            <a clstag="homepage|keycount|home2013|Homecomments" href="#" target="_self">评价晒单</a>
                        </dd>
                        <dd class="fore1_6" id="_MYJD_refundment">
                            <a clstag="homepage|keycount|home2013|Homerefund" href="#" target="_self">取消订单记录</a>
                        </dd>

                    </dl>
                    <dl class="fore2">
                        <dt id="_MYJD_gz">关注中心</dt>
                        <dd class="fore2_1" id="_MYJD_product">
                            <a clstag="homepage|keycount|home2013|Homefollow" href="#" target="_self">关注的商品</a>
                        </dd>
                        <dd class="fore2_2" id="_MYJD_vender">
                            <a clstag="homepage|keycount|home2013|Homefollowv" href="#" target="_self">关注的店铺</a>
                        </dd>
                        <dd class="fore2_3" id="_MYJD_followwhat">
                            <a clstag="homepage|keycount|home2013|Homewhat" href="#" target="_self">关注的专辑</a>
                        </dd>
                        <dd class="fore2_4" id="_MYJD_brand">
                            <a clstag="homepage|keycount|home2013|Homebrand" href="#" target="_self">关注的品牌</a>
                        </dd>
                        <dd class="fore2_5" id="_MYJD_activity">
                            <a clstag="homepage|keycount|home2013|Homefollowa" href="#" target="_self">关注的活动</a>
                        </dd>
                        <dd class="fore2_6" id="_MYJD_history">
                            <a clstag="homepage|keycount|home2013|Homelist" href="#" target="_blank">浏览历史</a>
                        </dd>
                    </dl>
                    <dl class="fore3">
                        <dt id="_MYJD_zc">资产中心</dt>
                        <dd class="fore3_1" id="_MYJD_cashbox">
                            <a clstag="homepage|keycount|home2013|Homexjk" href="#" target="_self">小金库</a>
                        </dd>
                        <dd class="fore3_2" id="_MYJD_credit"><a target="_blank" class="" clstag="homepage|keycount|home2013|hbt" tag="213" href="#">京东白条</a></dd>
                        <dd class="fore3_3" id="_MYJD_baina">
                            <a clstag="homepage|keycount|home2013|jdbn" href="#" target="_self">京东白拿</a>
                        </dd>
                        <dd class="fore3_4" id="_MYJD_xblc"><a target="_blank" class="" clstag="homepage|keycount|home2013|xblc" tag="213" href="#">小白理财</a></dd>
                        <dd class="fore3_5" id="_MYJD_jdfk">
                            <a clstag="homepage|keycount|home2013|Homejdfk" href="#" target="_self">京东福卡</a>
                        </dd>
                        <dd class="fore3_6" id="_MYJD_balance">
                            <a clstag="homepage|keycount|home2013|Homemoney" href="#" target="_self">余额</a>
                        </dd>
                        <dd class="fore3_7" id="_MYJD_ticket">
                            <a clstag="homepage|keycount|home2013|Homequan" href="#" target="_self">优惠券</a><b>(1)</b>
                        </dd>
                        <dd class="fore3_8" id="_MYJD_card">
                            <a clstag="homepage|keycount|home2013|Homegift" href="#" target="_self">京东卡/E卡</a>
                        </dd>
                        <dd class="fore3_9" id="_MYJD_bookcard">
                            <a href="#" target="_self">京东图书卡</a>
                        </dd>
                        <dd class="fore3_10" id="_MYJD_bean">
                            <a clstag="homepage|keycount|home2013|Homedou" href="#" target="_self">京豆</a>
                        </dd>

                    </dl>
                    <dl class="fore4">
                        <dt id="_MYJD_ts">特色业务</dt>
                        <dd class="fore4_1" id="_MYJD_tc">
                            <a clstag="homepage|keycount|home2013|Homemyyyt" href="#" target="_self">我的营业厅</a>
                        </dd>
                        <dd class="fore4_2" id="_MYJD_tx">
                            <a clstag="homepage|keycount|home2013|Homemobile" href="#" target="_self">京东通信</a>
                        </dd>
                        <dd class="fore4_3" id="_MYJD_ding">
                            <a clstag="homepage|keycount|home2013|Homeding" href="#" target="_self">定期送</a>
                        </dd>
                        <dd class="fore4_4" id="_MYJD_dxd">
                            <a clstag="homepage|keycount|home2013|Homedxd" href="#" target="_self">京东代下单</a>
                        </dd>
                        <dd class="fore4_5" id="_MYJD_hsd">
                            <a clstag="homepage|keycount|home2013|Homewhsd" href="#" target="_self">我的回收单</a>
                        </dd>
                        <dd class="fore4_6" id="_MYJD_jnbt">
                            <a clstag="homepage|keycount|home2013|Homejnbt" href="#" target="_self">节能补贴</a>
                        </dd>
                        <dd class="fore4_7" id="_MYJD_yyfw">
                            <a clstag="homepage|keycount|home2013|Homeyyfw" href="#" target="_self">医药服务</a>
                        </dd>
                        <dd class="fore4_8" id="_MYJD_lljyz">
                            <a clstag="homepage|keycount|home2013|Homelljyz" href="#" target="_self">流量加油站</a>
                        </dd>
                        <dd class="fore4_9" id="_MYJD_hjtg">
                            <a clstag="homepage|keycount|home2013|Homehjtg" href="#" target="_self">黄金托管</a>
                        </dd>
                        <dd class="fore4_10" id="_MYJD_edu">
                            <a clstag="homepage|keycount|home2013|Homespjydd" href="#" target="_self">视频教育订单</a>
                        </dd>
                        <dd class="fore4_11" id="_MYJD_hwfc">
                            <a clstag="homepage|keycount|home2013|Homehwfcyy" href="#" target="_self">海外房产预约</a>
                        </dd>

                    </dl>
                    <dl class="fore5">
                        <dt id="_MYJD_fw">客户服务</dt>
                        <dd class="fore5_1" id="_MYJD_repair">
                            <a clstag="homepage|keycount|home2013|Homerepair" href="#" target="_self">返修退换货</a>
                        </dd>
                        <dd class="fore5_2" id="_MYJD_protection">
                            <a clstag="homepage|keycount|home2013|Homejb" href="#" target="_self">价格保护</a>
                        </dd>
                        <dd class="fore5_3" id="_MYJD_complaint">
                            <a clstag="homepage|keycount|home2013|Homeopinion" href="#" target="_self">意见建议</a>
                        </dd>
                        <dd class="fore5_4" id="_MYJD_question">
                            <a clstag="homepage|keycount|home2013|Homewdwd" href="#" target="_self">我的问答</a>
                        </dd>
                        <dd class="fore5_5" id="_MYJD_consultation">
                            <a clstag="homepage|keycount|home2013|Homeconsu" href="#" target="_self">购买咨询</a>
                        </dd>
                        <dd class="fore5_6" id="_MYJD_referee">
                            <a clstag="homepage|keycount|home2013|Homemjyjf" href="#" target="_self">交易纠纷</a>
                        </dd>
                        <dd class="fore5_7" id="_MYJD_foreign">
                            <a clstag="homepage|keycount|home2013|Homemjdwx" href="#" target="_self">京东维修 </a>
                        </dd>
                        <dd class="fore5_8" id="_MYJD_smyy">
                            <a clstag="homepage|keycount|home2013|Homesmyyfw" href="#" target="_self">上门预约服务</a>
                        </dd>
                        <dd class="fore5_9" id="_MYJD_wdfp">
                            <a clstag="homepage|keycount|home2013|Homezzfp" href="#" target="_self">我的发票</a>
                        </dd>
                        <dd class="fore5_10" id="_MYJD_jbzx">
                            <a clstag="homepage|keycount|home2013|Homejbzx" href="#" target="_self">举报中心</a>
                        </dd>
                    </dl>
                    <dl class="fore6">
                        <dt id="_MYJD_sz">设置</dt>
                        <dd class="fore6_1" id="_MYJD_info">
                            <a clstag="homepage|keycount|home2013|Homeyser2" href="#" target="_self">个人信息</a>
                        </dd>
                        <dd class="fore6_2" id="_MYJD_add">
                            <a clstag="homepage|keycount|home2013|Homeadd2" href="#" target="_self">收货地址</a>
                        </dd>
                    </dl>
                </div>
                <div id="menu-ads">
                    <!--广告全部放这里-->
                    </div>
                <!--/ /widget/menu/menu.tpl -->
            </div>
            <div id="main">
                <!--home-media 首页响应式布局 start-->
                <div class="lyt-c-0">
                    <div id="fc-msg-link" class="fc-msg" data-vip="false">
                        <!-- span 标签内包含圆角空格，用于占取链接这一行的空间-->
                        <span>　</span>
                        <!--js 异步获取后台配置，包括，链接，埋点，链接名称-->

                        <a href="#" clstag="homepage|keycount|myhome|homezfyl" target="_blank"> 支付有礼 </a><b>|</b><a href="#" clstag="homepage|keycount|myhome|intelligent_contact" target="_blank"> 智能客服 </a></div>
                    <!-- 用户信息 -->
                    <!--  /widget/userinfo/userinfo.tpl -->
                    <div id="user-info" class="user-info">
                        <div class="info-lcol">
                            <div class="u-pic" clstag="homepage|keycount|home2013|Hometxlogo">
                                <img alt="用户头像" src="/images/no-img_mid_.jpg">
                                <a id="accountImg" href="#"><div class="mask"></div></a>
                            </div>
                            <div class="info-m">
                                <div class="u-name" clstag="homepage|keycount|home2013|Homeinname">
                                    <a href="#" target="_blank">jd_131800acv</a>
                                </div>
                                <div class="u-level">
			<span class="rank r3" clstag="homepage|keycount|home2013|Homeindj"> <s></s><a href="#" target="_blank">银牌会员</a>
			</span><a href="#" target="_blank" clstag="homepage|keycount|home2013|Homeplus"><img id="userPlusIcon" src="/images/weikaitong.png" alt="plus_icon" title="购买PLUS会员，尊享顶级特权"> </a>
                                </div>
                                <div class="u-safe" clstag="homepage|keycount|home2013|Homeinsafe">
                                    <a id="accountSafe" href="#"><span>账户安全：</span></a>
                                    <i id="cla" class="safe-rank05"></i>
                                    <strong id="rank-text" class="rank-text ftx-05">较高</strong>
                                    <a id="up" href="#" style="display: none;">提升</a>
                                </div>
                                <div class="clr mb10"></div>

                                <div class="u-medal" clstag="homepage|keycount|home2013|Homexunzhang">
                                    <a href="#" target="_blank">我的勋章</a>
                                </div>
                                <div class="info-line">
                                    <span class="top-icon"></span>
                                    <span class="bottom-icon"></span>
                                    <span class="left-icon"></span>
                                </div>
                            </div>
                        </div>
                        <div class="info-rcol">
                            <div class="user-counts">
                                <ul>
                                    <li>
                                        <div class="count-item">
                                            <a href="#" target="_self" clstag="homepage|keycount|home2013|Homedfkk">
                                                <i class="count-icon count-icon01"></i> 待付款
                                                <em id="waitPay">0</em>
                                            </a>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="count-item">
                                            <a href="#" target="_self" clstag="homepage|keycount|home2013|Homedsh">
                                                <i class="count-icon count-icon02"></i> 待收货
                                                <em id="waitReceive">0</em>
                                            </a>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="count-item">
                                            <a href="#" target="_self" clstag="homepage|keycount|home2013|Homedzt">
                                                <i class="count-icon count-icon03"></i> 待自提
                                                <em id="waitPick">0</em>
                                            </a>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="count-item">
                                            <a href="#" clstag="homepage|keycount|home2013|Homedpj">
                                                <i class="count-icon count-icon04"></i> 待评价
                                                <em id="productCount">3</em>
                                            </a>
                                        </div>
                                    </li>
                                </ul>
                            </div>

                            <div class="acco-info">
                                <ul>
                                    <li class="fore1">
                                        <div class="acco-item">
                                            <div>
                                                <label>余额：</label>
                                                <a href="#" id="BalanceCount" clstag="homepage|keycount|home2013|Hometopye">0.00</a>
                                            </div>
                                            <div>
                                                <label>京豆：</label>
                                                <a href="#" id="JingdouCount" clstag="homepage|keycount|home2013|Hometopd"><em>526</em></a>

                                            </div>
                                            <div>
                                                <label>京东卡/E卡：</label>
                                                <a href="#" id="LipinkaCount" clstag="homepage|keycount|home2013|Hometopk">0</a>
                                            </div>
                                            <div>
                                                <label>优惠券：</label>
                                                <a href="#" id="CouponCount" clstag="homepage|keycount|home2013|Hometopj"><em>1</em></a>
                                                　<a href="#" id="" class="alink" clstag="homepage|keycount|home2013|Homeaquan" target="_blank">领券</a>
                                            </div><div>	                                        <label>通信B：</label>	                                        <a clstag="homepage|keycount|home2013|Hometopb" class="mr5" href="#" id="BValue">0</a>	                                        	                                    </div>
                                        </div>
                                    </li>
                                    <li class="fore2">
                                        <div class="acco-item">
                                            <div id="baitiao-info" class="baitiao-info"><div>白条欠款<span class="ftx03">（元）</span></div>                    <div class="ftx01 "><a clstag="homepage|keycount|home2013|Hometopbt" href="#" target="_blank"><em>268.58</em></a></div>                    <div>白条额度</div>                    <div><a clstag="homepage|keycount|home2013|Hometopbte" href="#" target="_blank"><em>2050</em></a></div></div>
                                            <div class="baitiao-info">
                                                <div class="u-auth" id="userVerifyRight_passed" sytle="display:none;" style="display: block;"><a href="#" target="_blank" clstag="homepage|keycount|home2013|Homesmrz2">小白信用：</a><a href="#" target="_blank" clstag="homepage|keycount|home2013|Homesmrz2"><em>83.4</em></a></div>
                                                <div class="u-auth" id="userVerifyRight_unpassed" sytle="display:none;"></div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="fore3">
                                        <div class="acco-item">
                                            <div id="jinku-info" class="jinku-info "><div id="income">昨天收益<span class="ftx03">（元）</span></div>                    <div class="ftx01 profit"><a class="ftx01 jk-income" href="#">0.00</a><span clstag="homepage|keycount|home2013|Hometopyc" class="btn-show">显示收益和金额</span></div>                    <div id="balance"><a href="#">小金库</a>：<a class="jk-total" href="#">0.00</a></div>                    <div id="xjk0"><a class="alink" target="_blank" href="#">转入小金库，马上赚钱</a></div></div>
                                        </div>
                                    </li>
                                </ul>
                            </div>

                        </div>
                    </div>
                    <!--/ /widget/userinfo/userinfo.tpl -->
                </div>
                <div class="lyt-c-1">
                    <!-- 我的订单 -->
                    <!--  /widget/order-list/order-list.tpl -->
                    <!-- economical-->
                    <div class="mod-main">
                        <div class="mt">
                            <h3>我的订单</h3>
                            <div class="extra-r">
                                <a href="/order/myorders.html" target="_self" clstag="homepage|keycount|home2013|Homeckddk">查看全部订单</a>
                            </div>
                        </div>
                        <div class="mc">
                            <div id="tb-order" class="tb-order">

                                <table width="100%" cellspacing="0" cellpadding="0" border="0">
                                    <colgroup>
                                        <col class="col-1">
                                        <col class="col-2">
                                        <col class="col-3">
                                        <col class="col-4">
                                        <col class="col-5">
                                        <col> </colgroup>
                                    <c:forEach items="${orderList}" var="order">
                                        <tbody class="fore0">
                                    <tr>
                                        <td clstag="homepage|keycount|home2013|Homedd1">
                                            <div class="img-list">
                                                <c:forEach items="${order.orderItems}" var="item" begin="0" end="3">
                                                    <a href="/item/${item.itemId}.html" target="_blank" clstag="click|keycount|orderinfo|order_product">
                                                        <img src="${item.picPath}" width="50" height="50" border="1px" title="${item.itemId}">
                                                    </a>
                                                </c:forEach>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="u-name">${order.orderShipping.receiverName}</div>
                                        </td>
                                        <td>￥${order.payment}<br>在线支付</td>
                                        <td><span class="ftx-03">${order.time}</span></td>
                                        <td class="td-01"><strong class="ftx-04 order-statu">${order.status}</strong>
                                        </td>
                                        <td clstag="homepage|keycount|home2013|Homeddck1" class="order-doi">
                                            <a target="_blank" href="#" clstag="click|keycount|orderinfo|order_check">查看</a>
                                        </td>
                                    </tr>
                                    </tbody>
                                    </c:forEach>
                                </table>
                                <div class="nocont-box nocont-order" style="display: none;"><b class="icon-order"></b>您买的东西太少了，这里都空空的，快去挑选合适的商品吧！</div>

                            </div>
                        </div>
                    </div>
                    <!-- economical -->
                    <!--/ /widget/order-list/order-list.tpl -->
                </div>
                <div class="lyt-c-2">
                    <div class="lyt-c-3">
                        <!--  /widget/economical/economical.tpl -->
                        <!-- economical-->
                        <div class="mod-main">
                            <div class="mt">
                                <h3>会员推荐 </h3>
                            </div>
                            <div class="mc">
                                <!--帮你省 start-->
                                <div id="economical" class="economical">
                                    <ul class="gate-list"><li class="fore1"><div class="gate-item"><a href="#" target="_blank" clstag="homepage|keycount|home2013|Homegg1k"><span class="fore1">京东会员</span><span class="fore2"><b>&gt;</b></span></a></div></li><li class="fore2"><div class="gate-item"><a href="#" target="_blank" clstag="homepage|keycount|home2013|Homegg2k"><span class="fore1">金融会员</span><span class="fore2"><b>&gt;</b></span></a></div></li></ul>

                                </div>
                                <!--帮你省 end-->
                            </div>
                        </div>
                        <!-- economical -->
                        <!--/ /widget/economical/economical.tpl -->
                    </div>
                    <div class="lyt-c-7">






                        <div class="lyt-c-8">
                            <!-- 推荐商品 -->

                            <!--  /widget/recmd-entry/recmd-entry.tpl -->
                            <!-- recmd-entry-->
                            <div id="recmd-entry">
                                <div class="mt">
                                    <h3>您的专属推荐</h3>
                                </div>

                                <div class="mc">
                                    <div class="noinfo-con">
                                        <div class="txt">不知道买什么好？我们根据您的喜好，为您量身打造了专属购物，快来体验吧！</div>
                                        <div class="btn-con">
                                            <a class="btn-green" href="javascript:void(0)" onclick="modifyPersonStyle();return false;" target="_blank" clstag="homepage|keycount|home2013|foryouSZ">
                                                点击设置
                                            </a>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <!-- recmd-entry -->
                            <!--/ /widget/recmd-entry/recmd-entry.tpl -->
                        </div>
                    </div>
                </div>
                <div class="lyt-c-9">

                    <!-- 关注的商品 -->

                    <!--  /widget/fol-goods/fol-goods.tpl -->
                    <!-- economical-->
                    <!-- economical -->
                    <!--/ /widget/bargain-goods/bargain-goods.tpl -->
                </div>
                <!--  /widget/free-tryout/free-tryout.tpl -->
                <!-- economical-->

                <!-- economical -->
                <!--/ /widget/free-tryout/free-tryout.tpl -->
                <div class="lyt-c-6">


                    <!--  /widget/show/show.tpl -->
                    <!--/ /widget/show/show.tpl -->
                </div>
                <!--  /widget/da-main/da-main.tpl -->
                <div id="da-main">
                    <div class="da-list">                                <img id="105005962" width="0" height="0" border="0" src="/images/np" style="position: absolute; top: 0px; left: 0px;visibility: hidden;">                                <a title="1090x90.jpg" href="#">                                                                    </a>                            </div></div>
                <!--/ /widget/da-main/da-main.tpl -->
                <!--home-media 首页响应式布局 end-->
            </div>
            <div class="backpanel-inner">
                <div class="bp-item " id="bp-research">
                    <a class="research" target="_blank" href="#"><b></b>调查问卷</a>
                </div>
                <div class="bp-item " id="bp-gotop">
                    <a title="使用快捷键T也可返回顶部哦！" class="gotop" href="#" target="_self"><b></b>返回顶部</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!--推荐位html修改处-->


<script type="text/javascript" src="/js/base-v1.js"></script>
<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->

<!-- 购物车相关业务 -->


</html>