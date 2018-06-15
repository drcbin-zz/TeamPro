
//发请求查找详情
function add_spot_detail(spot_id){
	var url = "/TeamPro/web/spot/"+spot_id+"/detail";
	$.post(	
			url,
			function(result){
				if(result.STATUS == "SUCCESS"){
					add_spot_to_html(result.object);
				} // end if			
			} // end success function
		); // end get
} // end function add_users

//赋值
function add_spot_to_html(spot){
	var type=null;
	if(spot.spot_type == 1){
		type = "主题游";
	}else{
		type = "时令游";
	}
	$("#spot_name").removeAttr("id").text(spot.spot_name);
	$("#spot_type").removeAttr("id").text(type);
	$("#suit_people").removeAttr("id").text(spot.suit_people);
	$("#suit_time").removeAttr("id").text(spot.suit_time);
	$("#need_things").removeAttr("id").text(spot.need_things);
	$("#attentions").removeAttr("id").text(spot.attentions);
	$("#spot_detail").removeAttr("id").text(spot.detail);
	$("#like_to").removeAttr("id").text(spot.likeCount);
	$("#spot_content").removeAttr("id").text(spot.content);
	$("#spot_imgpath").removeAttr("id").attr("src",spot.image_path);
}






	
