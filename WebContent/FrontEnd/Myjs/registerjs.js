$(document).ready(function(){
	
		$("#checkPassword").on("blur",function(){
			if($("#checkPassword").val().length<20&&$("#checkPassword").val().length>5
					&&$("#checkPassword").val().indexOf(" ",1)==-1){
					$("#password-error").html("√").css("color","green");
					$("#password-error").attr("title","密码可以使用");
			}else{
			$("#password-error").html("！！！").css("color","red");
			$("#password-error").attr("title","密码不合法");
				}
			});
		
		$("#checkrePassword").on("blur",function(){
			if($("#checkrePassword").val()==$("#checkPassword").val()){
				$("#repassword-error").html("√").css("color","green");
				$("#repassword-error").attr("title","成功");
			}else{
				$("#repassword-error").html("！！！").css("color","red");
				$("#repassword-error").attr("title","两次密码不同");
			}
			
		});
		
		
		$("#checkEmail").on("blur",function(){
			if($("#checkEmail").val().indexOf("@",1)==-1){
				$("#email-error").html("！！！").css("color","red");
				$("#email-error").attr("title","请输入正确邮箱");
			}else{
				$("#email-error").html("√").css("color","green");
				$("#email-error").attr("title","邮箱可以使用");
			}
		});
		
		
		$("#checkUsername").on("blur",function(){
			if($("#checkUsername").val()==""){
				$("#username-error").html("！！！").css("color","red");
				$("#username-error").attr("title","用户名不能为空");
			}else{
			$.get("/TeamPro/web/user/register",
					{username:$("#checkUsername").val()},function(data){
					if(data.STATUS=="FALSE"){
						$("#username-error").html("！！！").css("color","red");
						$("#username-error").attr("title","用户名重复");
					}else{
						$("#username-error").html("√").css("color","green");
						$("#username-error").attr("title","用户名可以使用");
					}
				},"json");
			}
		});	
		
		
		$("#register-submit").on("click",function(){
			alert("dd");
			if($("#username-error").html()=="√"&&$("#password-error").html()=="√"
					&&$("#repassword-error").html()=="√"&&$("#email-error").html()=="√"){
				$.post(
						 	"/TeamPro/web/user/register",
							
				 				{
									username:$("#checkUsername").val(),
									password:$("#checkPassword").val(),
									email:$("#checkEmail").val()
								},
							function(data){
									$("#myRegisterModal").modal("hide");
									alert("注册成功，请登录");
								} // end success callback
							
						); // end ajax
					}else{
				alert("请正确填写");
			}
			 
		});
});
