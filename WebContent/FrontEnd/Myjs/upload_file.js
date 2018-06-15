
alert("upload");
var upload_image = function(){
		alert("ddd");
		$.post(
			"/user/image/upload",
			{
				$("#image-form").serialize()
			},
			function(result){
				if(result.STATUS == "SUCESS"){
					alert("修改成功");
				}else{
					alert("删除失败");
				}
			}
		);				
}