var travel_table_body_id = "travel_table_body";
var travel_item_template_id = "travel_item_template";

var author=null;


// 发请求查找游记列表
function add_travels(page, count){
	$.get(
			"/TeamPro/web/travel_memoary/list?page=" + page + "&count=" + count,
			function(result){
				if(result.STATUS == "SUCCESS"){
					add_travels_to_html(result.object);
				} // end if			
			} // end success function
		); // end get
} // end function add_users


//通过id查找作者
function add_user_to_html(user){	
	author = user.name;
}

// 发请求查找作者
function find_travel_author(authId){
	
	var url = "/TeamPro/web/user/"+ authId +"/detail";
	
	$.ajax({
			type:"get",
			url:url ,	
			async:false,
			success:function(result){
				if(result.STATUS == "SUCCESS"){
					add_user_to_html(result.object);
				} // end if		
			}
			
	});
}

//赋值
function add_travels_to_html(travels){
	for(var  travel of travels ){
		
		find_travel_author(travel.authId);
		
		var travel_table_body = $("#" + travel_table_body_id);//.empty();
		var template_element = $("#" + travel_item_template_id).clone(true).removeAttr("style").attr("id", travel.id);
		template_element.find("#travel_item_title").removeAttr("id").text(travel.title);
		template_element.find("#travel_item_createTime").removeAttr("id").text(travel.createTime);
		template_element.find("#travel_item_author").removeAttr("id").text(author);
		template_element.find("#click_to_detail").removeAttr("id").attr("href","/TeamPro/BackStage/travel_detail.html?id="+travel.id);
		template_element.find("#delete_travel").removeAttr("id").attr("onclick","delete_memoary(" + travel.id + ")");
		travel_table_body.append(template_element);
	}
}


add_travels(1,10);


function delete_memoary(id){
	var url = "/TeamPro/web/travelmemoary/" + id + "/delete";
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




















