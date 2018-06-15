/*
*
* auth: chenzhibing
*
* for: 旅游记忆->右上角更多
*
*/


var last_view = null;

var memoary_list_change_to_hidden = function(id){
	$("#" + last_view).hide();
	$("#" + id).show();
	last_view = id;
}

var title_container_id = "travel_memoary_title_container";
var title_template_id = "travel_memoary_title_template";

var content_container_id = "travel_memoary_content_container";
var content_template_id = "travel_memoary_content_template";


var item_template_id = "memoary_list_item_template";

var memoary_list_add_to_html = function(types){

	for(var type in types){
		// set title
		var title = $("#" + title_template_id).clone(true).removeAttr("style id");
		
		title.find("#title").removeAttr("id")
			.text(type)
			.attr("onclick", "memoary_list_change_to_hidden('" + type + "');return false;");
		$("#" + title_container_id).append(title);
		
		// get contents
		var contents = types[type];
		
		var content_container = $("#" + content_template_id).clone(true).attr("id", type);
		
		
		// load contents
		for(var content of contents){
			var item = $("#" + item_template_id).clone(true).removeAttr("style id");
			item.find("#title").removeAttr("id").text(content.title);
			item.find("#img").removeAttr("id")
				.attr("src", content.imagePath)
				.attr("onclick", "addfile('memory/memory_detail.htm', null," + content.id  + ");");
			
			item.find("#cdate").removeAttr("id").text(content.createTime);
			item.find("#auth").removeAttr("id").text(content.authId);
			item.find("#type").removeAttr("id").text(content.type_name);
			
			content_container.append(item);
		}
		$("#" + content_container_id).append(content_container);
	}
	
	// 手动点击第一个
	for(var type in types){
		memoary_list_change_to_hidden(type);
		break;
	}
};


var memoary_list = function(){
	$.get(
			"/TeamPro/web/travel_memoary/list",
			{
				type:1,
				count:500,
			},
			function(result){
				if(result.STATUS == "SUCCESS"){
					memoary_list_add_to_html(result.types);
				}else{
					alert("获取内容失败");
				}
			}
	);
}