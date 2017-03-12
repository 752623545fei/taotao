var TT = TAOTAO = {
	checkLogin : function(){
		var _ticket = $.cookie("TT_TOKEN");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://sso.hongde508.top/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.nickname;
					var html = "<a href=\"/user/myhome.html\" class=\"link-logout\"> " + username + "</a>，欢迎来到淘淘！<a href=\"/user/logout.html\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
					$("#buyerNick").attr("value",username);
					$("#userId").attr("value",data.data.id);
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});