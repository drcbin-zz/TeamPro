$(document).ready(function(){
	if(!$.cookie("userstring")){
		$("#user-info").hide();
		$("#login-form").show();
	}else{
		$("#login-form").hide();
		$("#user-info-username").html(JSON.parse($.cookie("userstring")).name + "&nbsp;&nbsp;").css("color","blue");
		$("#user-info").show();
	}
});