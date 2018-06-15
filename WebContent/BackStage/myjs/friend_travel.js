var friend_table_body_id = "friends_travel_table_body";
var friend_item_template_id = "friends_item_template";

var author=null;


// 发请求查找结伴游列表
function add_travels(page, count){
	$.get(
			"/TeamPro/web/friends_travel/list?page=" + page + "&count=" + count,
			function(result){
				if(result.STATUS == "SUCCESS"){
					add_friends_travel_to_html(result.object);
				} // end if			
			} // end success function
		); // end get
} // end function add_users


//通过id查找作者
function add_user_to_html(user){	
	author = user.name;
}

// 发请求查找作者
function find_friends_travel_author(authId){
	
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
function add_friends_travel_to_html(travels){
	var status = null;
	for(var  travel of travels ){
		if(travel.status == 1){
			status = "active";
		}else if(travel.status == 2){
			status = "pending";
		}else{
			status = "banned";
		}
		find_friends_travel_author(travel.userId);
		
		var friend_table_body = $("#" + friend_table_body_id);//.empty();
		var template_element = $("#" + friend_item_template_id).clone(true).removeAttr("style").attr("id", travel.id);
		template_element.find("#friends_item_title").removeAttr("id").text(travel.title);
		template_element.find("#friends_item_createtime").removeAttr("id").text(travel.releaseTime);
		template_element.find("#friends_item_author").removeAttr("id").text(author);
		template_element.find("#friends_item_status").removeAttr("id").text(status);
		template_element.find("#click_to_detail").attr("href","/TeamPro/BackStage/pt_detail.html?id="+travel.id);
		template_element.find("#delete_travel").attr("onclick","delete_travel(" + travel.id + ")");
		friend_table_body.append(template_element);
	}

}

add_travels(1,10);


function delete_travel(id){
	var url = "/TeamPro/web/friendstravel/" + id + "/delete";
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


















