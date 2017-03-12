<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<link type="text/css" rel="stylesheet"  href="//misc.360buyimg.com/jdf/1.0.0/unit/??ui-base/1.0.0/ui-base.css,shortcut/2.0.0/shortcut.css,global-header/1.0.0/global-header.css,myjd/2.0.0/myjd.css,nav/2.0.0/nav.css,shoppingcart/2.0.0/shoppingcart.css,global-footer/1.0.0/global-footer.css,service/1.0.0/service.css,basePatch/1.0.0/basePatch.css"/>
	<link type="text/css" rel="stylesheet"  href="//misc.360buyimg.com/user/myjd-2015/widget/??common/common.css" source="widget"/>

	<link type="text/css" rel="stylesheet"  href="/css/myjd.easebuy.css" source="combo"/>
	<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
	<script type="text/javascript" src="/js/easybuy.js"></script>
	<title>收货地址</title>
</head>
<body>
<div class="m" id="edit-cont">
	<div class="mc">
		<div class="form">
			<div class="item">
				<span class="label"><em>*</em>收货人：</span>
				<div class="fl">
					<input id="consigneeName" type="text" class="text" onblur="checkConsigneeName()" />
					<span id="consigneeNameNote" class="error-msg hide"></span>
				</div>
				<div class="clr"></div>
			</div>
			<div class="item">
				<span class="label"><em>*</em>所在地区：</span>
				<div class="fl">
					<select id="provinceDiv" class="sele" onchange="loadCity()"><option  value='0'>请选择</option></select>
					<select id="cityDiv" class="sele" onchange="loadCounty()"><option value='0'>请选择</option></select>
					<select id="countyDiv" class="sele" onchange="fillTownName()"><option value='0'>请选择</option></select>
					<!--
					<select id="townDiv" class="sele hide" onchange="fillTownName()"><option>请选择</option value='0'></select>
					-->
					<span class="error-msg" id="areaNote"></span>
				</div>
				<script type="text/javascript">
					loadProvince();
				</script>
				<div class="clr"></div>
			</div>
			<div class="item">
				<span class="label"><em>*</em>详细地址：</span>
				<div class="fl">
					<span style="float: left;margin-right: 5px;line-height:32px;" id="areaName"></span>
					<input id="consigneeAddress" type="text" class="text text1" onblur="checkConsigneeAddress()"/>
				</div>
				<span class="error-msg" id="consigneeAddressNote"></span>
				<div class="clr"></div>
			</div>
			<div class="item">
				<div class="fl">
					<span class="label"><em>*</em>手机号码：</span>
					<input id="consigneeMobile" type="text" maxlength="11" class="text" onblur="checkMobile()" />
				</div>
				<div class="fl"><span class="extra-span ftx-03">或</span></div>
				<div class="fl">
					<span class="label">固定电话：</span>
					<input id="consigneePhone" type="text" class="text" onblur="checkMobile()"/>
					<span class="error-msg hide" id="consigneeMobileNote"></span>
					<span class="clr"></span>
				</div>
				<div class="clr"></div>
			</div>
			<div class="item">
				<span class="label">邮箱：</span>
				<div class="fl">
					<input id="consigneeEmail" type="text" class="text text1" maxlength="50"  onblur="checkEmail()"/>
					<span class="error-msg hide" id="emailNote"></span>
				</div>
				<div class="clr"></div>
			</div>
			<div class="item">
				<span class="label">地址别名：</span>
				<div class="fl">
					<input id="consigneeAlias" type="text" class="text" onblur="checkConsigneeAlias()" />
					<span class="ftx-03">建议填写常用名称</span>
					<span class="addr-alias">
                        <a href="javascript:setEditAddressAilas('home');" id="home" >家里</a>
						<a href="javascript:setEditAddressAilas('parentHome');" id="parentHome">父母家</a>
						<a href="javascript:setEditAddressAilas('company');" id="company">公司</a>
                    </span>
				</div>
				<div class="clr"></div>
			</div>
			<div class="btns">
				<a href="javascript:;" onclick="addAddress();" class="e-btn btn-9 save-btn">保存收货地址</a>
			</div>
		</div>
	</div>
</div>

</body>
</html>