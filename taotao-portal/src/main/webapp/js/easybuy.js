	
var appDomain = "http://www.hongde508.top/address/";
//document.domain="jd.com";

var ohtml = "<option value='0'>请选择</option>";

this._wuziti = {};
/**
 * 显示对应地址别名的提示
 * @param addressId
 * @return
 */
function modifyAliasTips(addressId,event){
	//取消事件冒泡  
	stopPropagation(event);
	$(".smt").each(function(){
		$(this).removeClass("z-alias-edit");
	});
	$('#alias-edit-'+addressId).parent().parent().addClass('z-alias-edit');
	
	$( "div" ).delegate( ".mc", "click", function(e) {
		var target = e.target;
		var isCon = $(".alias-form").find($("#"+target.id));
		var index = target.className.indexOf("alias-form");
		if(!(isCon.length || index == 0)){
			$(".z-alias-edit").removeClass("z-alias-edit");
		}
	  });
}

function stopPropagation(event) {
	 //阻止冒泡调用parent
	 var e = (event) ? event : window.event;
	 if (window.event) {//IE
		 e.cancelBubble=true;
	 } else { //FF
		 e.stopPropagation();
	 }
}


/**
 * 点击【家里】、【公司】给文本框赋值
 * @param addressId
 * @param itemId
 * @return
 */
function setAddressAilas(addressId , itemId){
	var alias = $("#"+itemId).text();
	var inputID = 'ipt-text-'+addressId;
	$("input[id="+inputID+"]").val(alias);
}
function setEditAddressAilas(itemId){
	var alias = $("#"+itemId).text();
	$("input[id=consigneeAlias]").val(alias);
}

function alertAddAddressDiag() {
	var num =$("#addressNum_top").text();
	if(!isEmpty(num) && num.replace(' ','')=="20"){
		alert("抱歉，地址信息最多可创建20条。");
		return;
	}
	openDialog();
}

function alertUpdateAddressDiag(addressId) {
	openDialog(addressId);
}

function openDialog(addressId){
	var title = addressId ? "编辑收货地址" : "添加收货地址";
	var sig = addressId ? "2" : "1";

	jQuery.jdThickBox({
        type: "html",
        title: title,
        width: 740,
        height: 490,
        source: '<div id="addressDiagDiv"></div>',
        _autoReposi: true
    });

    $.browser.version < 8.0 && $('.thickframe, .thickdiv').css({height : $.browser.client().bodyHeight});

    loadAddressDiag(sig, addressId);
}

function loadAddressDiag(isAdd,addressId){
	//var actionUrl = appDomain + "address/formatEditAddressDiag.action?addressId=" + addressId;
	if(isAdd == "1"){
		actionUrl = appDomain + "add/pop.html";
	}
	jQuery.ajax( {
	    type : "POST",
	    dataType : "text",
	    url  : actionUrl,
	    data :"",
	    cache : false,
	    success : function(dataResult) {
			if(isUserNotLogin(dataResult)){
				goToLogin();
				return;
			}
			if(dataResult){
				$("#addressDiagDiv").html(dataResult);
			}
		},
		error : function(XMLHttpResponse) {
		}
	});
}

function json2string(json){
	var str = '';
	for(var i in json){
		str += '' + i + '=' + json[i] + '&';
	}
	str = str.substring(0, str.length-1);
	return str;
}

function addAddress(){
	if(checkConsigneeName() || checkConsigneeAddress() || checkArea() || checkConsigneeAlias() || checkEmail() || checkMobileAndPhone()){
		return;
	}
	var consigneeName = $("#consigneeName").val();
	var email = $("#consigneeEmail").val();
	var consigneeAddress = $("#consigneeAddress").val();
	var mobile = $("#consigneeMobile").val();
	var phone =  $("#consigneePhone").val();

	var proEl = $("#provinceDiv option:selected");
	var cityEl = $("#cityDiv option:selected");
	var countyEl = $("#countyDiv option:selected");
	var townEl = $("#townDiv option:selected");

	var provinceId = proEl.val();
	var provinceName = proEl.text();

	var cityId = cityEl.val();
	var cityName = cityEl.text();

	var countyId = countyEl.val();
	var countyName = countyEl.text();

	var townId = townEl.val();
	var townName = townEl.text();

	if(isEmpty(townName) || townName=="请选择"){
		townName="";
	}

	var fullAddress = provinceName + cityName + countyName + townName + consigneeAddress;
	var consigneeAddressAlias = $("#consigneeAlias").val();
	var isEasybuy = $("#checkEasybuy").attr("checked");

	var params = {
		'consigneeName' : consigneeName,
		'provinceId'    : provinceId,
		'cityId'        : cityId,
		'countyId'      : countyId,
		'townId'        : townId,
		'consigneeAddress': consigneeAddress,
		'mobile'        : mobile,
		'fulladdress'   : fullAddress,
		'phone'         : phone,
		'email'         : email,
		'addressAlias'  : consigneeAddressAlias,
		//'easyBuy'       : isEasybuy
	};

	var actionUrl = appDomain+"addAddress.html";

	saveAddress(params, actionUrl);
}

function editAddress(addressId){
	if(checkConsigneeName() || checkConsigneeAddress() || checkArea() || checkConsigneeAlias() || checkEmailNew() || checkMobile()){
		return;
	}
	var isDefault = $("#isDefault").val();
	var isDefaultAllAddress = $("#isDefaultAllAddress").val();

	var consigneeName = $("#consigneeName").val();
	var email = $("#consigneeEmail").val();
	var consigneeAddress = $("#consigneeAddress").val();
	var mobile = $("#consigneeMobile").val();
	var phone =  $("#consigneePhone").val();

	var ceshi1 = $("#ceshi1").val();
	
	var provinceId = $("#provinceDiv option:selected").val();
	var cityId = $("#cityDiv option:selected").val();
	var countyId = $("#countyDiv option:selected").val();
	var townId = $("#townDiv option:selected").val();
	var consigneeAddressAlias = $("#consigneeAlias").val();

	var isEasybuy = $("#checkEasybuy").attr("checked");

	var paymentId=$("#update_paymentId").val();
	var pickId = $("#update_pickId").val();
	var pickName = "";

	if(paymentId == 0){
		paymentId =4;
	}
	if(pickId > 0){
		pickName = $("#update_pickName").val();
	}

	var params = {
		'id'            : addressId,
		'defaultAddress': isDefault,
		'defaultAllAddress': isDefaultAllAddress,
		'consigneeName' : consigneeName,
		'provinceId'    : provinceId,
		'cityId'        : cityId,
		'countyId'      : countyId,
		'townId'        : townId,
		'consigneeAddress': consigneeAddress,
		'mobile'        : mobile,
		'ceshi1'        : ceshi1,
		'phone'         : phone,
		'email'         : email,
		'addressAlias'  : consigneeAddressAlias,
		'easyBuy'       : isEasybuy,
		'paymentId'     : paymentId,
		'pickId'        : pickId,
		'pickName'      : pickName
	};

	var actionUrl = appDomain+"address/updateAddress.action";

	saveAddress(params, actionUrl, addressId);
	//修改地址别名埋点
	if(consigneeAddressAlias != $("#consigneeAlias_old").val()){
		log('changgouy', 'click',"shdzydizhibieming");
	}
}

function saveAddress(params, actionUrl, addressId){
	var param = json2string(params);
	var me = this;

	jQuery.ajax( {
	    type : "POST",
	    dataType : "text",
	    url : actionUrl,
	    data :param,
	    cache : false,
	    success : function(dataResult) {

			if(dataResult){
				//$("#addressList").html(dataResult);
				//jdThickBoxclose();
				location.reload();
			}

			me._wuziti[addressId] = '';
		},
		error : function(XMLHttpResponse) {
		}
	});
}

function saveAddressAndPayment(addressId,paymentId,pickId){
	if(checkConsigneeName() || checkConsigneeAddress() || checkArea() || checkConsigneeAlias() || checkEmailNew() || checkMobile()){
		return;
	}
	var isDefault = $("#isDefault").val();
	var isDefaultAllAddress = $("#isDefaultAllAddress").val();
	var consigneeName = $("#consigneeName").val();
	var email = $("#consigneeEmail").val();
	var consigneeAddress = $("#consigneeAddress").val();
	var mobile = $("#consigneeMobile").val();
	var phone =  $("#consigneePhone").val();
	var ceshi1 = $("#ceshi1").val();
	var provinceId = $("#provinceDiv option:selected").val();
	var cityId = $("#cityDiv option:selected").val();
	var countyId = $("#countyDiv option:selected").val();
	var townId = $("#townDiv option:selected").val();
	var consigneeAddressAlias = $("#consigneeAlias").val();

	var isEasybuy = $("#checkEasybuy").attr("checked");

	var paymentId=$("#update_paymentId").val();
	var pickId = $("#update_pickId").val();
	var pickName = "";

	if(paymentId == 0){
		paymentId =4;
	}
	if(pickId > 0){
		pickName = $("#update_pickName").val();
	}

	var params = {
		'id'            : addressId,
		'defaultAddress': isDefault,
		'alldefaultAddress': isDefaultAllAddress,
		'consigneeName' : consigneeName,
		'provinceId'    : provinceId,
		'cityId'        : cityId,
		'countyId'      : countyId,
		'townId'        : townId,
		'consigneeAddress': consigneeAddress,
		'mobile'        : mobile,
		'ceshi1'        : ceshi1,
		'phone'         : phone,
		'email'         : email,
		'addressAlias'  : consigneeAddressAlias,
		'easyBuy'       : isEasybuy,
		'paymentId'     : paymentId,
		'pickId'        : pickId,
		'pickName'      : pickName
	};

	var actionUrl = appDomain+"address/updateAddress.action";
	var param = json2string(params);
	var me = this;

	jQuery.ajax( {
	    type : "POST",
	    dataType : "text",
	    url : actionUrl,
	    data :param,
	    cache : false,
	    success : function(dataResult) {
			if(isUserNotLogin(dataResult)){
				goToLogin();
				return;
			}
			if(dataResult){
				$("#addressList").html(dataResult);
				jdThickBoxclose();
				getPayment(addressId,paymentId,pickId);
			}

			me._wuziti[addressId] = '';
		},
		error : function(XMLHttpResponse) {
		}
	});
}

function makeAddressDefault(addressId){
	var actionUrl = appDomain+"address/setAddressDefaultById.action";
	jQuery.ajax( {
	    type : "POST",
	    dataType : "text",
	    url : actionUrl,
	    data :"addressId="+addressId,
	    cache : false,
	    success : function(dataResult) {
			if(isUserNotLogin(dataResult)){
				goToLogin();
				return;
			}
			if(dataResult){
				$("#addressList").html(dataResult);
			}else{
				alert("该地址中“所在地区”信息不完整，您需要补充地区信息后才能继续设置！");
				alertUpdateAddressDiag(addressId);
				return;
			}
		},
		error : function(XMLHttpResponse) {
		}
	});
}

function makeAddressAllDefault(addressId){
	var actionUrl = appDomain+"address/setAddressAllDefaultById.action";
	jQuery.ajax( {
	    type : "POST",
	    dataType : "text",
	    url : actionUrl,
	    data :"addressId="+addressId,
	    cache : false,
	    success : function(dataResult) {
			if(isUserNotLogin(dataResult)){
				goToLogin();
				return;
			}
			if(dataResult){
				$("#addressList").html(dataResult);
			}else{
				alert("该地址中“所在地区”信息不完整，您需要补充地区信息后才能继续设置！");
				alertUpdateAddressDiag(addressId);
				return;
			}
		},
		error : function(XMLHttpResponse) {
		}
	});
}

function alertDelAddressDiag(addressId){
	jQuery.jdThickBox({
		type: "text",
		title: "删除",
		width: 300,
		height: 100,
		source: '<div class="m flexbox">'
    		+ '<div class="mc">'
    		+ '<div class="tip-box icon-box">'
    		+ '<span class="warn-icon m-icon"></span>'
    		+ '<div class="item-fore">'
    		+ 	'<h3 class="ftx04">您确定要删除该收货地址吗？'
    		+ 	'</h3>'
    		+ '<div class="op-btns">'
    		+ '<a href="javascript:void(0);" class="btn-9" onclick="delAddress(' + addressId + ')">确定</a>'
    		+ '<a href="javascript:void(0);" class="btn-9 ml10" onclick="jdThickBoxclose()">取消</a>'
    		+ '</div></div></div></div></div>'
	});
}

function delAddress(addressId){
	var actionUrl = appDomain + "/deleteAddress.html";
	jQuery.ajax( {
	    type : "POST",
	    dataType : "text",
	    url : actionUrl,
	    data : "addressId=" + addressId,
	    cache : false,
	    success : function(dataResult) {

			if(dataResult || dataResult=="true"){
				//隐藏删除的地址
				location.reload();
			}
		},
		error : function(XMLHttpResponse) {
		}
	});
	jdThickBoxclose();
}

/**
 * 获取支付配送方式 设置轻松购
 * @param paymentId
 * @param pickId
 */
function getPayment(addressId, paymentId, pickId){
	jQuery.jdThickBox({
		_box : "paymentDiv",
		type: "html",
		title: "设置支付和配送方式",
		width: 420,
		height: 260,
		source: "<div id='paymentDiagDiv'></div>"
	});
	$.browser.version < 8.0 && $('.thickframe, .thickdiv').css({height : $.browser.client().bodyHeight});

	var actionUrl = appDomain + "address/getPayment.action?addressId=" + addressId;
	jQuery.ajax({
	    type : "POST",
	    dataType : "text",
	    url : actionUrl,
	    data :null,
	    cache : false,
	    success : function(dataResult) {
			if(isUserNotLogin(dataResult)){
				goToLogin();
				return;
			}
			if(dataResult){
				$("#paymentDiagDiv").html(dataResult);

				//if(paymentId==200){
					var provinceId = $("#addressProvinceId").val();
					var cityId     = $("#addressCityId").val();
					var countyId   = $("#addressCountyId").val();
					var townId     = $("#addressTownId").val();
					var addressDetail = $("#addressDetail").val();

					loadPickList(provinceId,cityId,countyId,townId,pickId,addressDetail,addressId);
				//}

				$("#shipment-method-1").click(function(){
					$("#zitiList").hide();
					var _h = $('#paymentDiagDiv').height();
					$('#paymentDiv').css('height',_h+70)
							.find('.thickcon').css('height',_h+20)
							.find('iframe').css('height',_h);
				});
				$("#shipment-method-2").click(function(){
					//选中后改pickId 为1
					loadPickList(provinceId,cityId,countyId,townId,1,addressDetail,addressId);
				});
			}
		},
		error : function(XMLHttpResponse) {
		}
	});
}

/**
 * 异步加载自提点列表
 */
function loadPickList(provinceId,cityId,countyId,townId,pickId,consigneeAddress,addressId){
	var sel = $("#shipmentMessage");
	var zel = $("#zitiList");

	// 有数据时不请求。
	if(this._wuziti[addressId]){
		zel.hide();
		$("#shipment-method-2").hide();
		$("#pickBtn").hide();
		$("#shipmentMessage").hide();
		return;
	}

	var zval = zel.html();
	if(zval && pickId > 0){
		zel.show();
		var _h = $('#paymentDiagDiv').height();
		$('#paymentDiv').css('height',_h+70)
						.find('.thickcon').css('height',_h+20)
						.find('iframe').css('height',_h);
		return;
	}

	var actionUrl = appDomain + "address/getPicks.action";

	var params = {
		'provinceId'    : provinceId,
		'cityId'        : cityId,
		'countyId'      : countyId,
		'townId'        : townId,
		'consigneeAddress': consigneeAddress,
		'pickId'        : pickId
	};

	var param = json2string(params);

	var me = this;
	jQuery.ajax({
	    type : "POST",
	    dataType : "text",
	    url : actionUrl,
	    data : param,
	    cache : false,
	    success : function(dataResult) {
			if(isUserNotLogin(dataResult)){
				goToLogin();
				return;
			}
			if(dataResult){
				if(dataResult.indexOf("noPickSite")>-1 ){
					$("#shipment-method-1").attr("checked",true);

					$("#shipment-method-2").hide();
					$("#pickBtn").hide();
					$("#shipmentMessage").hide();

					me._wuziti[addressId] = "<font color='red'>所在地区无自提点，不支持上门自提</font>";
					zel.hide();
				}else{
					me._wuziti[addressId] = '';
					sel.html("");
					zel.html(dataResult);
					if($("#ziti"+pickId).length>0){
						$("#ziti"+pickId).attr("checked",true);
					}else{
						$("input:radio[name='pickSelfName']:first").attr("checked",true);
					}
					if(pickId > 0){
						zel.show();
					}
				}
			}
			var _h = $('#paymentDiagDiv').height();
			$('#paymentDiv').css('height',_h+70)
							.find('.thickcon').css('height',_h+20)
							.find('iframe').css('height',_h);
		},
		error : function(XMLHttpResponse) {
		}
	});
}

/**
 * 保存轻松购支付方式
 */
function savePayment(addressId){
	var pickId = 0;
	var pickName = '';

	if($('#zitiList')[0].style.display != 'none'){
		var pickEl = $("input:radio[name='pickSelfName']:checked");
		pickId = pickEl.val();
		pickName = pickEl.attr("pickName");
	}

	var paymentId = $("input:radio[name='payName']:checked").val();
	if(!pickId && !pickName){
		pickId = 0;
		pickName = "";
	}
	var param = "addressId="+addressId+"&paymentId="+paymentId+"&pickId="+pickId+"&pickName="+pickName;
	var actionUrl = appDomain+"address/savePayment.action";
	jdThickBoxclose();
	jQuery.ajax({
	    type : "POST",
	    dataType : "text",
	    url : actionUrl,
	    data :param,
	    cache : false,
	    success : function(dataResult) {
			if(isUserNotLogin(dataResult)){
				goToLogin();
				return;
			}
			if(dataResult){
				$("#addressList").html(dataResult);
			}
		},
		error : function(XMLHttpResponse) {
		}
	});
}

function setEasyBuyFlow(jumpFlowId){
	var actionUrl =  appDomain+"easybuy/setEasyBuyFlow.action?jumpFlowId="+jumpFlowId;
	jQuery.ajax( {
	    type : "POST",
	    dataType : "json",
	    url : actionUrl,
	    data :null,
	    cache : false,
	    success : function(dataResult) {
			if(isUserNotLogin(dataResult)){
				goToLogin();
				return;
			}
			if(dataResult){
				if(jumpFlowId==0){
					$("#ordermethod0").removeClass("order-method-selected");
					$("#ordermethod1").addClass("order-method-selected");
				}else if(jumpFlowId==1){
					$("#ordermethod1").removeClass("order-method-selected");
					$("#ordermethod0").addClass("order-method-selected");
				}
			}
		},
		error : function(XMLHttpResponse) {
		}
	});
}

/**
 * 勾选是否成为轻松购
 */
function makeAddressEasybuy(addressId){
	var isEasybuy = $("#checkEasybuy-"+addressId).attr("checked");
	var url = appDomain+"easybuy/makeAddressEasybuy.action";
	var param = "addressId="+addressId+"&isEasybuy="+isEasybuy;

	var checkEl = $("#checkEasybuy");

	checkEl.attr("disabled","disabled");

	jQuery.ajax( {
	    type : "POST",
	    dataType : "text",
	    url : url,
	    data :param,
	    cache : false,
	    success : function(dataResult) {
			if(isUserNotLogin(dataResult)){
				goToLogin();
				return;
			}
			if(isEasybuy && dataResult=="noTown"){
				alert("该地址中“所在地区”信息不完整，您需要补充地区信息后才能继续设置！");
				alertUpdateAddressDiag(addressId);
				checkEl.attr("checked","");
				checkEl.attr("disabled","");
				return;
			}
			if(dataResult=="true"){
				checkEl.attr("checked","checked");
			}else{
				checkEl.attr("checked","");
			}
			checkEl.attr("disabled","");
		},
		error : function(XMLHttpResponse) {
			checkEl.attr("disabled","");
		}
	});
}

function openOrCloseEasybuy(){
	var isOpen = false;
	if($("#op-set-eshop").hasClass('op-open')){
		isOpen = true;
	}else{
		isOpen = false;
	}
	var url = appDomain+"easybuy/openAndCloseEasybuy.action";
	var param = "isOpen=" + isOpen;
	jQuery.ajax( {
	    type : "POST",
	    dataType : "text",
	    url : url,
	    data :param,
	    cache : false,
	    success : function(dataResult) {
			if(isUserNotLogin(dataResult)){
				goToLogin();
				return;
			}
			if(isOpen && dataResult =="noEasybuy"){
				jQuery.jdThickBox({
					type: "text",
					title: "提示：",
					width: 330,
					height: 80,
					source: "<div class='tip'><span class='warn-icon'></span><h5 class='ftx-04'>您需要至少创建一条收货地址，并将它设为轻松购备选地址，才能开启轻松购功能</h5></div>"
				});
				return ;
			}
			if(dataResult=="true"){
				$("#op-set-eshop").addClass('op-close').removeClass('op-open').text('关闭');
				$("#op-set-eshop").prev().attr("class","ftx-02").text('已开启');
			}else{
				$("#op-set-eshop").addClass('op-open').removeClass('op-close').text('开启');
				$("#op-set-eshop").prev().attr("class","ftx-04").text('未开启');
			}
		},
		error : function(XMLHttpResponse) {
		}
	});
}

// 加载省份
function loadProvince(){
	var url =appDomain + "select.html";
	jQuery.ajax( {
	    type : "GET",
	    dataType : "json",
	    url : url,
	    //data : "",
	    cache : false,
	    success : function(dataResult) {

			if(dataResult){
				var provinceHtml="<option>请选择</option>";
				for(var key in dataResult){
					provinceHtml += "<option value='" + key + "'>" + dataResult[key] + "</option>";
				}
				$("#provinceDiv").html(provinceHtml);
			}
		},
		error : function(XMLHttpResponse) {
		}
	});
}

function loadCity(){
	var proEl = $("#provinceDiv option:selected");
	var provinceId = proEl.val();
	var provinceName = proEl.text();
	//84是钓鱼岛
	if(provinceId>0 && provinceId!=84){
		var url = appDomain + "select.html";
		jQuery.ajax( {
		    type : "GET",
		    dataType : "json",
		    url : url,
		    data : {"parent":provinceId},
		    cache : false,
		    success : function(dataResult) {

				var cityHtml = ohtml;
				if(dataResult){

					for(var key in dataResult){
						cityHtml += "<option value='" + key + "'>" + dataResult[key] + "</option>";
					}
				}
				$("#cityDiv").html(cityHtml);
				$("#countyDiv").html(ohtml);
				$("#townDiv").hide().empty();
				$("#areaName").text(provinceName);
			},
			error : function(XMLHttpResponse) {
			}
		});
	}else{
		$("#cityDiv").html(ohtml);
		$("#countyDiv").html(ohtml);
		$("#townDiv").hide().empty();
		$("#areaName").text("");
	}
}

function loadCounty(){
	var cityEl = $("#cityDiv option:selected");
	var cityId = cityEl.val();
	var provinceName = $("#provinceDiv option:selected").text();
	var cityName = cityEl.text();
	if(cityId>0){
		var url = appDomain + "select.html";
		jQuery.ajax( {
		    type : "GET",
		    dataType : "json",
		    url : url,
		    data : {"parent":cityId},
		    cache : false,
		    success : function(dataResult) {

				if(dataResult){
					var countyHtml = ohtml;
					for(var key in dataResult){
						countyHtml += "<option value='" + key + "'>" + dataResult[key] + "</option>";
					}
					$("#countyDiv").html(countyHtml);
					$("#townDiv").hide().empty();
					$("#countyDiv").show();
					$("#areaName").text(provinceName + cityName);

				}else {
					$("#countyDiv").hide().empty();
					$("#areaName").text(provinceName + cityName);
				}
			},
			error : function(XMLHttpResponse) {
			}
		});
	}else{
		$("#countyDiv").html(ohtml);

		$("#townDiv").hide().empty();
		$("#areaName").text(provinceName);
	}
}

/*function loadTown(){
	var countyEl = $("#countyDiv option:selected");
	var countyId = countyEl.val();
	var provinceName = $("#provinceDiv option:selected").text();
	var cityName = $("#cityDiv option:selected").text();
	var countyName = countyEl.text();
	if(countyId>0){
		var url = appDomain + "/address/getTowns.action";
		jQuery.ajax( {
		    type : "POST",
		    dataType : "json",
		    url : url,
		    data : "countyId="+countyId,
		    cache : false,
		    success : function(dataResult) {
				if(isUserNotLogin(dataResult)){
					goToLogin();
					return;
				}
				if(dataResult){
					var hasTown =0;
					for(var key in dataResult){
						hasTown++;
					}
					if(hasTown>0){
						var townHtml = ohtml;
						for(var key in dataResult){
							townHtml += "<option value='" + key + "'>" + dataResult[key] + "</option>";
						}
						$("#townDiv").html(townHtml);
						$("#townDiv").show();
					}
				}
				$("#areaName").text(provinceName + cityName + countyName);
			},
			error : function(XMLHttpResponse) {
			}
		});
	}else{
		$("#townDiv").hide().empty();
		$("#areaName").text(provinceName+cityName);
	}
}
*/
function fillTownName(){
	//var townEl = $("#townDiv option:selected");

	//var townId = townEl.val();
	var provinceName = $("#provinceDiv option:selected").text();
	var cityName     = $("#cityDiv option:selected").text();
	var countyName   = $("#countyDiv option:selected").text();

	//var townName = townId>0 ? townEl.text() : '';

	$("#areaName").text(provinceName + cityName + countyName);
}

// 修改地址时请求该地址的详细信息，用来渲染地址select元素。
function loadAllArea(provinceId,cityId,countyId,townId){
	var url = appDomain+"/address/loadAllArea.action";

	var params = {
		'provinceId'    : provinceId,
		'cityId'        : cityId,
		'countyId'      : countyId,
		'townId'        : townId
	};

	var param = json2string(params);

	jQuery.ajax( {
	    type : "POST",
	    dataType : "json",
	    url : url,
	    data : param,
	    cache : false,
	    success : function(dataResult) {
				if(isUserNotLogin(dataResult)){
					goToLogin();
					return;
				}
				if(dataResult){
					var provinceHtml="<option>请选择</option>";
					var provinceInfo = dataResult.province;
					var cityInfo = dataResult.city;
					var countyInfo = dataResult.county;
					var townInfo = dataResult.town;
					for(var key in provinceInfo){
						if(provinceId == key){
							provinceHtml += "<option value='"+key+"' selected>"+provinceInfo[key]+"</option>";
						}else{
							provinceHtml += "<option value='"+key+"'>"+provinceInfo[key]+"</option>";
						}
					}
					$("#provinceDiv").html(provinceHtml);
					var cityHtml = ohtml;
					for(var key in cityInfo){
						if(cityId==key){
							cityHtml += "<option value='"+key+"' selected>"+cityInfo[key]+"</option>";
						}else{
							cityHtml += "<option value='"+key+"'>"+cityInfo[key]+"</option>";
						}
					}
					$("#cityDiv").html(cityHtml);
					var countyHtml = ohtml;
					for(var key in countyInfo){
						if(key == countyId){
							countyHtml += "<option value='"+key+"' selected>"+countyInfo[key]+"</option>";
						}else{
							countyHtml += "<option value='"+key+"'>"+countyInfo[key]+"</option>";
						}
					}
					$("#countyDiv").html(countyHtml);
					if(!isEmpty(townInfo)){
						var hasTown =0;
						for(var key in townInfo){
							hasTown++;
						}
						if(hasTown>0){
							var townHtml = ohtml;
							for(var key in townInfo){
								if(townId == key){
									townHtml += "<option value='"+key+"' selected>"+townInfo[key]+"</option>";
								}else{
									townHtml += "<option value='"+key+"'>"+townInfo[key]+"</option>";
								}
							}
							$("#townDiv").html(townHtml);
							$("#townDiv").show();
						}
						var provinceName = $("#provinceDiv option:selected").text();
						var cityName = $("#cityDiv option:selected").text();
						var countyName = $("#countyDiv option:selected").text();
						var townName = "";
						if(hasTown>0){
							townName = $("#townDiv option:selected").text();
						}
						$("#areaName").text(provinceName+ cityName+countyName+townName);
					}
				}
		},
		error : function(XMLHttpResponse) {
		}
	});
}

function checkConsigneeAlias(id){
	var errorFlag = false;
	var errorMessage = "";
	var value="";
	if(id == null){
		
		value = $("#consigneeAlias").val();
	}else{
		value = $("#"+id).val();
	}
	if(isEmpty(value)){
		//	errorFlag = false;
		//errorMessage = "地址别名不能为空";
	}else{
		if (!is_forbid(value)) {
			errorFlag = true;
			errorMessage = "地址别名中含有非法字符";
		}
		if(value.length>20){
			errorFlag = true;
			errorMessage = "您输入的地址别名过长";
		}
	}
	var el;
	if(id == null){
		el= $("#consigneeAliasNote");
	}else{
		el=$("#error_"+id);
	}
	if(errorFlag){
		el.text(errorMessage);
		el.show();
	}else{
		el.hide();
	}	
	return errorFlag;
}

function checkConsigneeName(){
	var errorFlag = false;
	var errorMessage ="";
	var value = $("#consigneeName").val();
	if (isEmpty(value)) {
		errorFlag = true;
		errorMessage = "请您填写收货人姓名";
	}else{
		if (value.length > 25) {
			errorFlag = true;
			errorMessage = "收货人姓名不能大于25位";
		}
		if (!is_forbid(value)) {
			errorFlag = true;
			errorMessage = "收货人姓名中含有非法字符";
		}
	}
	var el = $("#consigneeNameNote");
	if(errorFlag){
		el.text(errorMessage);
		el.show();
	}else{
		el.hide();
	}
	return errorFlag;
}

function checkConsigneeAddress(){
	var errorFlag = false;
	var errorMessage = "";
	var value = $("#consigneeAddress").val();
	if (isEmpty(value)) {
		errorFlag = true;
		errorMessage = "请您填写收货人详细地址";
	}
	if (!is_forbid(value)) {
		errorFlag = true;
		errorMessage = "收货人详细地址中含有非法字符";
	}
	if (value.length>50) {
		errorFlag = true;
		errorMessage = "收货人详细地址过长";
	}
	var el = $("#consigneeAddressNote");
	if(errorFlag){
		el.text(errorMessage);
		el.show();
	}else{
		el.hide();
	}
	return errorFlag;
}

/**
 * 校验邮箱
 */
function checkEmail(){
	var errorFlag = false;
	var errorMessage ="";
	var value = $("#consigneeEmail").val();
	if(!isEmpty(value)){
		if (value.length > 50) {
			errorFlag = true;
			errorMessage = "邮箱长度不能大于50位";
		}
	   var myReg=/(^\s*)\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*(\s*$)/;
	   if(!myReg.test(value)){
		   errorFlag=true;
		   errorMessage = "邮箱格式不正确";
	   }
	}

	var el = $("#emailNote");
	if(errorFlag){
		el.text(errorMessage);
		el.show();
	}else{
		 el.hide();
	}
	return errorFlag;
}

/**
 * 校验邮箱
 */
function checkEmailNew(){
	var errorFlag = false;
	var errorMessage ="";
	var value = $("#consigneeEmail").val();
	if(!isEmpty(value)){
		if (value.length > 50) {
			errorFlag = true;
			errorMessage = "邮箱长度不能大于50位";
		}
	   var myReg=/(^\s*)\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*(\s*$)/;
	   var my=/^[0-9a-zA-Z_\-\.]{1,4}@\w+([-.]\w+)*\.\w+([-.]\w+)*(\s*$)/;
	   var reg=/^[0-9a-zA-Z_\-\.]{2}\*+[0-9a-zA-Z_\-\.]{2}@\w+([-.]\w+)*\.\w+([-.]\w+)*(\s*$)/;
	   if(!myReg.test(value) && !reg.test(value) && !my.test(value)){
		   errorFlag=true;
		   errorMessage = "邮箱格式不正确";
	   }
	}

	var el = $("#emailNote");
	if(errorFlag){
		el.text(errorMessage);
		el.show();
	}else{
		 el.hide();
	}
	return errorFlag;
}

function checkMobile(){
	var el = $("#consigneeMobileNote");
	var errorFlag = false;
	var errorMessage ="";
	var value = $("#consigneeMobile").val();
	if (isEmpty(value)) {
		errorFlag = true;
		errorMessage = "请您填写收货人手机号码";
	} else {
		var regu = /^\d{11}$/;
		var re = new RegExp(regu);
		if(!(re.test(value) || new RegExp(/^\d{3}\*\*\*\*\d{4}$/).test(value))){
			errorFlag = true;
			errorMessage = "手机号码格式不正确";
		}
	}
	if (!errorFlag) {
		value = $("#consigneePhone").val();
		if($("#consigneeMobile").val() == $("#consigneePhone").val()){
			el.hide();
		    return false;
		 }
		if (!isEmpty(value)) {
			if (!is_forbid(value)) {
				errorFlag = true;
				errorMessage = "固定电话号码中含有非法字符";
			}
			if(value.length > 20){
				errorFlag = true;
				errorMessage = "固定电话号码过长";
			}
			var strlength=value.length;
			var patternStr = "(0123456789-*)";
		    for(var i=0;i<strlength;i++){
		        var tempchar=value.substring(i,i+1);
				if(patternStr.indexOf(tempchar)<0){
					errorFlag = true;
					errorMessage = "固定电话号码格式不正确";
					break;
				}
		    }
			if(strlength >=4 && value.indexOf("*") >-1){
				if(!((new RegExp(/.+\*\*\*\*$/).test(value) && (strlength - value.indexOf("*")) < 5) || (new RegExp(/^\d{11}$/).test(value) || new RegExp(/^\d{3}\*\*\*\*\d{4}$/).test(value)))){
					errorFlag = true;
					errorMessage = "固定电话号码格式不正确";
				}
			}
		}
	}
	
	if(errorFlag){
		 el.text(errorMessage);
		 el.show();
	}else{
		 el.hide();
	}
	return errorFlag;
}

function checkArea(){
	var errorFlag = false;
	var errorMessage="";
	var provinceId = $("#provinceDiv option:selected").val();
	var cityId = $("#cityDiv option:selected").val();
	var countyId = $("#countyDiv option:selected").val();
	var townId = $("#townDiv option:selected").val();
	// 验证地区是否正确
	if (isEmpty(provinceId) || isEmpty(cityId) || provinceId==0 || cityId==0
			|| (!isEmpty($("#townDiv").html()) &&!$("#townDiv").is(":hidden") && (isEmpty(townId) || townId==0))) {
		errorFlag = true;
		errorMessage = "请您填写完整的地区信息";
	}
	var el = $("#areaNote");
	if(errorFlag){
		 el.text(errorMessage);
		 el.show();
	}else{
		 el.hide();
	}
	return errorFlag;
}

/**
 * 检查是否含有非法字符
 * @param temp_str
 * @returns {Boolean}
 */
function is_forbid(temp_str){
    temp_str = temp_str.replace(/(^\s*)|(\s*$)/g, "");
	temp_str = temp_str.replace('--',"@");
	temp_str = temp_str.replace('/',"@");
	temp_str = temp_str.replace('+',"@");
	temp_str = temp_str.replace('\'',"@");
	temp_str = temp_str.replace('\\',"@");
	temp_str = temp_str.replace('$',"@");
	temp_str = temp_str.replace('^',"@");
	temp_str = temp_str.replace('.',"@");
	temp_str = temp_str.replace(';',"@");
	temp_str = temp_str.replace('<',"@");
	temp_str = temp_str.replace('>',"@");
	temp_str = temp_str.replace('"',"@");
	temp_str = temp_str.replace('=',"@");
	temp_str = temp_str.replace('{',"@");
	temp_str = temp_str.replace('}',"@");
	var forbid_str = new String('@,%,~,&');
	var forbid_array = new Array();
	forbid_array = forbid_str.split(',');
	for(i=0;i<forbid_array.length;i++){
		if(temp_str.search(new RegExp(forbid_array[i])) != -1)
		return false;
	}
	return true;
}

/**
 * 判断是否是空
 * @param value
 */
function isEmpty(value){
	if(value == null || value == "" || value == "undefined" || value == undefined || value == "null"){
		return true;
	}
	else{
		value = (value+"").replace(/\s/g,'');
		if(value == ""){
			return true;
		}
		return false;
	}
}

/**
 * 提交订单
 */
function submitEasybuyOrder(){
	var skuId = $("#skuId").val();
	var num = $("#num").val();
	var addressId =$("input:radio[name='addressIdName']:checked").val();
	var param = "skuId="+skuId+"&num="+num+"&addressId="+addressId;
	var actionUrl = "http://easybuy.jd.com/skuDetail/submitEasybuyOrder.action";
	$("#submitButton").attr("disabled","disabled");
	jQuery.ajax( {
	    type : "POST",
	    dataType : "json",
	    url : actionUrl,
	    data :param,
	    cache : false,
	    success : function(dataResult) {
			if(isUserNotLogin(dataResult)){
				parent.location.href = "http://passport.jd.com/new/login.aspx";
				return;
			}
			if(isEmpty(dataResult) || !dataResult.success){
				parent.document.getElementById('title').innerHTML = "<span>提示：</span>";
				var errorMessage = dataResult.message;
				if(isEmpty(errorMessage)){
					errorMessage = "非常抱歉，系统累了，休息一下，稍后再试";
				}else{
					try{
						if(errorMessage.indexOf("商品无货")!=-1){
							errorMessage = "抱歉，此商品暂时售完，请后续关注";
						}
						if(errorMessage.indexOf("大件商品")!=-1 || errorMessage.indexOf("大家电")!=-1){
							errorMessage = "抱歉，轻松购暂时不支持购买大家电，请后续关注";
						}
					}catch(e){
					}
				}
				var content = "<div class='tip-box remind-box'><h3 class='font-blue'>"+errorMessage+"</h3><div class='op-btns'>"
						+ "<a href='#none' onclick='parent.jdThickBoxclose();' class='gray-btn del-btn'>关闭</a></div></div>";
				$("#easybuyDiv").html(content);
				return;
			}
			if(dataResult.success){
			    parent.location.href = "http://s.trade.jd.com/success/success.action?orderId="+dataResult.orderId+"&rid="+Math.random();
				return;
			}
		},
		error : function(XMLHttpResponse) {
		}
	});
}
function loadSkuDetailAddress(){
	var url = appDomain + "skuDetail/loadSkuDetailAddress.action";
	jQuery.ajax( {
	    type : "POST",
	    dataType : "text",
	    url : url,
	    data : "",
	    cache : false,
	    success : function(dataResult) {
			if(isUserNotLogin(dataResult)){
				parent.location.href = "http://passport.jd.com/new/login.aspx";
				return;
			}
			if(dataResult){
				$("#easybuyDiv").html(dataResult);
			}
		},
		error : function(XMLHttpResponse) {
		}
	});
}

function guideNext(){
	var oMove = $('.guide ul');
	var num = parseInt(oMove.css("left")) - 370;
	oMove.css({ left: num });
}

// 判断用户是否登录【此方法别动】
function isUserNotLogin(data) {
	if (data.error == "NotLogin") {
		return true;
	} else {
		try {
			var obj = eval("(" + data + ")");
			if (obj && obj.error && obj.error == "NotLogin") {
				return true;
			}
		} catch (e) {
		}
	}
	return false;
}

function goToLogin() {
	window.location.href = "http://passport.jd.com/new/login.aspx?ReturnUrl=" + appDomain + "address/getEasyBuyList.action?rid=" + Math.random();
}

function checkMobileAndPhone(){
	var errorFlag = false;
	var errorMessage ="";
	var value = $("#consigneeMobile").val();
	if (isEmpty(value)) {
		errorFlag = true;
		errorMessage = "请您填写收货人手机号码";
	} else {
		var regu = /^\d{11}$/;
		var re = new RegExp(regu);
		if(!re.test(value)){
			errorFlag = true;
			errorMessage = "手机号码格式不正确";
		}
	}
	if (!errorFlag) {
		value = $("#consigneePhone").val();
		if ((!isEmpty(value)) || value.indexOf("*") > -1) {
			if (!is_forbid(value)) {
				errorFlag = true;
				errorMessage = "固定电话号码中含有非法字符";
			}
			if(value.length > 20){
				errorFlag = true;
				errorMessage = "固定电话号码过长";
			}
		   var patternStr = "(0123456789-)";
		   var  strlength=value.length;
		   for(var i=0;i<strlength;i++){
		        var tempchar=value.substring(i,i+1);
				if(patternStr.indexOf(tempchar)<0){
					errorFlag = true;
					errorMessage = "固定电话号码格式不正确";
					break;
				}
		    }
		}
	}
	var el = $("#consigneeMobileNote");
	if(errorFlag){
		 el.text(errorMessage);
		 el.show();
	}else{
		 el.hide();
	}
	return errorFlag;
}

//保存别名
function saveAddessAlias(addressId,event){
	var actionUrl = appDomain+"address/setAddressAlias.action";
	var addressAlias=$("#ipt-text-"+addressId).val();
	var error = checkConsigneeAlias("ipt-text-"+addressId);
	if(error){
		stopPropagation(event);
		return;
	}
	var params = {
			'id'            : addressId,
			'addressAlias'  : addressAlias
		};
	var actionUrl = appDomain+"address/setAddressAlias.action";
	saveAddress(params, actionUrl, addressId);
	//修改地址别名埋点
	log('changgouy', 'click',"shdzydizhibieming");
}