$("#detail_update").on("click",function(){
			user = JSON.parse($.cookie("userstring"));
			$.post("../web/user/update",
					{
					birthday: $("#detail_birthday").val(),
					qq: $("#detail_qq").val(),
					temp: $("#detail_temp").val(),
					address: $("#detail_address").val(),
					gender: $("input[name='optionsRadios']:checked").val(),
					tel: $("#detail_phone").val(),
					name: $("#detail_nickname").val(),
					id:user.id,
					email: $("#detail_email").val(),
					
					}
			,function(data){	
					alert("修改成功");			
			},"json");
		});


alert("upload");
var upload_image = function(){
		alert("ddd");
		$.post(
			"/TeamPro/user/image/upload",
			$("#image-form").serialize(),
			function(result){
				if(result.STATUS == "SUCESS"){
					alert("修改成功");
				}else{
					alert("删除失败");
				}
			}
		);				
}