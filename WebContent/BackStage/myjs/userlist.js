var user_table_body_id = "user_table_body";
var user_item_template_id = "user_item_template";



function add_users(page, count){
	$.get(
			"/TeamPro/web/user/list?page=" + page + "&count=" + count,
			function(result){
				if(result.STATUS == "SUCCESS"){
					add_users_to_html(result.object);
				} // end if			
			} // end success function
		); // end get
} // end function add_users


function add_users_to_html(users){
	var gender=null;
	var status = null;
	for(var  user of users ){
		if(user.gender == 1){
			gender = "男";
		}else{
			gender = "女";
		}
		
		if(user.status == 1){
			status = "active";
		}else if(user.status == 2){
			status = "pending";
		}else{
			status = "banned";
		}
		var user_table_body = $("#" + user_table_body_id);//.empty();
		var template_element = $("#" + user_item_template_id).clone(true).removeAttr("style id");
		template_element.find("#user_item_name").removeAttr("id").text(user.name);
		template_element.find("#user_item_email").removeAttr("id").text(user.email);
		
		template_element.find("#gender").removeAttr("id").text(gender);
		template_element.find("#user_item_status").removeAttr("id").text(status);
		template_element.find("#click_to_detail").attr("href","/TeamPro/BackStage/auser_detail.html?id="+user.id);
		user_table_body.append(template_element);
	}

}


add_users(1,10);

