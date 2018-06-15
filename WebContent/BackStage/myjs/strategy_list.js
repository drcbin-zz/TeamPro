var spot_table_body_id = "spot_table_body";
var spot_item_template_id = "spot_item_template";

var author=null;


// 发请求查找游记列表
function add_spots(page, count){
	
	$.get(
			"/TeamPro/web/spot/list?page=" + page + "&count=" + count,
			function(result){
				if(result.STATUS == "SUCCESS"){
					add_spots_to_html(result.object);
				} // end if			
			} // end success function
		); // end get
} // end function add_users


//赋值
function add_spots_to_html(spots){
	var status = null;
	for(var  spot of spots ){
		if(spot.status == 1){
			status = "active";
		}else if(spot.status == 2){
			status = "pending";
		}else{
			status = "banned";
		}
		var spot_table_body = $("#" + spot_table_body_id);//.empty();
		var template_element = $("#" + spot_item_template_id).clone(true).removeAttr("style").attr("id", spot.id);
		template_element.find("#spot_item_title").removeAttr("id").text(spot.spot_name);
		template_element.find("#spot_item_createTime").removeAttr("id").text(spot.createTime);
		template_element.find("#spot_item_status").removeAttr("id").text(status);
		template_element.find("#click_to_edit").attr("href","/TeamPro/BackStage/strategy_edit.html?id="+spot.id);
		template_element.find("#delete_spot").attr("onclick","delete_spot(" + spot.id + ")");
		spot_table_body.append(template_element);
	}

}


add_spots(1,10);


function delete_spot(id){
	var url = "/TeamPro/web/spot/" + id + "/delete";
	$.post(url,
		function(data){
			if("SUCCESS" == data.STATUS){
				alert("删除成功");
				$("#" + id).remove();
			}else{
				alert("删除失败");
			}
		}
	);
}


















