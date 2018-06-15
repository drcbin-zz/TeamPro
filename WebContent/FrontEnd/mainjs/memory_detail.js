var author = null;

//赋值
function add_author_to_html(user){
	author = user.name;
}

//发请求查找作者
function find_author(authId){
	var url = "/TeamPro/web/user/"+ authId +"/detail";
	var auth = null;
	$.ajax({
		type:"post",
		url:url ,	
		async:false,
		success:function(result){
			if(result.STATUS == "SUCCESS"){
				auth = result.object;
			}else{
				alert("请求失败");
			} // end if		
		}	
	});
	return auth;
}
//发请求查找详情
function add_travel_detail(travel_id){
	
	var url = "/TeamPro/web/travel_memoary/"+travel_id+"/detail";
	$.post(	
			url,
			function(result){
				if(result.STATUS == "SUCCESS"){
					add_travel_to_html(result.object);
				} // end if			
			} // end success function
		); // end get
} // end function add_users

//赋值
function add_travel_to_html(travel){
	$("#title").removeAttr("id").text(travel.title);
	$("#type_name").removeAttr("id").text(travel.type_name);
	$("#createTime").removeAttr("id").text(travel.createTime);
	$("#memory_content").removeAttr("id").text(travel.content);
	$("#memory_img").removeAttr("id").attr("src",travel.imagePath);	
	$("#author").removeAttr("id").text(find_author(travel.authId).name);
}







	
