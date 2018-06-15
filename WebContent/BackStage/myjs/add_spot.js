//清空form表单
function reset_form(){
	$("#spot_reset").trigger("click");	
	var ele = $("#spot_content").cleditor()[0];
	$("#spot_content").val('');
	ele.updateFrame();
	
}

// 将form转为ajax提交

var form_id = "addspot_form";

function ajaxSubmit(frm){
	$.post(
			"/TeamPro/web/spot/insert",
			$("#" + form_id).serialize(),
			function(result){
				if(result.STATUS == "SUCCESS") {					
					reset_form();
					alert("提交成功");
				}else{
					alert(result.MSG);
				}
			}
		);
}
// 调用
$(document).ready(function(){
	$('#submit_spot').on("click", function(){
		ajaxSubmit();
	});
});