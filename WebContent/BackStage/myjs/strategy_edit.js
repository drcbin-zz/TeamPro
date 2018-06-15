var strategy_id = window.location.href.split("=")[1];

var url = "/TeamPro/web/spot/" + strategy_id + "/detail";

//发请求寻找景点
function find_spot(id) {
	$.post(url, function(result) {
		if (result.STATUS == "SUCCESS") {
			add_spot_to_html(result.object);
		} else {

		}
	})
}

// 将信息添加到页面
function add_spot_to_html(spot) {
	$("#spot_title").removeAttr("id").val(spot.spot_name);
	$("#suit_time").removeAttr("id").val(spot.suit_time);
	$("#suit_people").removeAttr("id").val(spot.suit_people);
	$("#spot_img").removeAttr("id").val(spot.spot_img);
	$("#spot_type").removeAttr("id").val(spot.spot_type);

	var ele = $("#content").cleditor()[0];
	$("#content").val(spot.content);
	ele.updateFrame();
	$("#need_things").removeAttr("id").val(spot.need_things);
	$("#attentions").removeAttr("id").val(spot.attentions);
}

$(document).ready(function() {
	find_spot(strategy_id);
});

//清空form表单
function reset_form() {
	$("#spot_reset").trigger("click");
	var ele = $("#content").cleditor()[0];
	$("#content").val('');
	ele.updateFrame();
}

// 将form转为ajax提交

var form_id = "update_spot_form";

function ajaxSubmit(frm) {
	$.post("/TeamPro/web/spot/update", $("#" + form_id).serialize(), function(
			result) {
		if (result.STATUS == "SUCCESS") {
			reset_form();
			alert("提交成功");
		} else {
			alert(result.MSG);
		}
	});
}
// 调用
$(document).ready(function() {
	$('#submit_spot').on("click", function() {
		ajaxSubmit();
	});
});