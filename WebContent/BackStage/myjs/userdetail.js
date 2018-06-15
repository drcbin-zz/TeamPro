var user_id = window.location.href.split("=")[1];
var url = "/TeamPro/web/user/"+ user_id +"/detail";
function add_user_detail(user_id){
	$.get(	
			url,
			function(result){
				if(result.STATUS == "SUCCESS"){
					add_user_to_html(result.object);
				} // end if			
			} // end success function
		); // end get
} // end function add_users


function add_user_to_html(user){
	$("#user_detail_name").removeAttr("id").text(user.name);
	$("#user_detail_email").removeAttr("id").text(user.email);
	$("#user_detail_gender").removeAttr("id").text(user.gender);
	$("#user_detail_birthday").removeAttr("id").text(user.birthday);
	$("#user_detail_qq").removeAttr("id").text(user.qq);
	$("#user_detail_phone").removeAttr("id").text(user.phone);
	$("#user_detail_address").removeAttr("id").text(user.address);
	$("#user_detail_jby_count").removeAttr("id").text(user.jby_count);
	$("#user_detail_lyjy_count").removeAttr("id").text(user.lyjy_count);
	$("#user_detail_recent_time").removeAttr("id").text(user.lastTime);
}
add_user_detail(user_id);