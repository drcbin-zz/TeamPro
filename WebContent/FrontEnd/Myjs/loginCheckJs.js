var login_form_id = "login-form";

var user_info_id = "user-info";

var login_button_id = "sure";

var logout_button_id = "logout";

function do_logined(user){

	var userstring = JSON.stringify(user);
	// 影藏登陆框
	$("#" + login_form_id).hide();
	// 显示用户信息
	$("#" + user_info_id).show();
	
	// 存cookie
	$.cookie("userstring",userstring);
	
	// 设置用户名
	$("#user-info-username").html(user.name);
}

function do_logout(){
	// 隐藏用户信息
	$("#" + user_info_id).hide();
	// 显示登陆框
	$("#" + login_form_id).show();
	
	// 删除cookie
	$.cookie("userstring",'', { expires: -1 });
	window.location.reload(); 
}

function is_login(){
	var user = null;
	if($.cookie("userstring")){
		user = JSON.parse($.cookie("userstring"));
	}
	return user;
}



function init(){
	var user = is_login();
	if(user){
		do_logined(user);
	}else{
//		do_logout();
	}
}



$(document).ready(function(){
	
	// 初始化
	init();
	
	// 注册登陆事件
	$("#" + login_button_id).on("click", function(){
		var username = $("#username").val();
		var password = $("#password").val();
		$.post(
				"/TeamPro/web/user/login",
				{
					username:username,
					password:password
				},
				function(data){
					if(data.STATUS=="SUCCESS"){
						$("#myLoginModal").modal("hide");
						var user = data.user;
						do_logined(user);
					}else{
						alert("账号或密码错误");
					} // end if else 
				} // end success function
			);	// end $.post			
	});	 // end onclick	
	
	
	// 注册注销事件
	$("#" + logout_button_id).on("click", function(){
		$.get(
			"/TeamPro/web/user/login",
			{},
			function(data){
//				if(data.STATUS == "SUCCESS") {
//					do_logout();
//				}
				do_logout();
			} // end success function
		); // end $ get
	}); // end on click 
}); // end domready
