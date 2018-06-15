$(document).ready(function(){
		$("#delcookie").on("click",function(){
			$.cookie("userstring",'', { expires: -1 });
			window.location.reload(); 
		});
	});