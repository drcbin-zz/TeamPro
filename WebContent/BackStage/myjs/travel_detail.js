var travel_id = window.location.href.split("=")[1];


var url = "/TeamPro/web/travel_memoary/"+travel_id+"/detail";

function add_travel_detail(travel_id){
	$.get(	
			url,
			function(result){
				if(result.STATUS == "SUCCESS"){
					add_travel_to_html(result.object);
				}else{
					alert("false");
				} // end if			
			} // end success function
		); // end get
} // end function add_users


function add_travel_to_html(travel){
	$("#travel_detail_title").removeAttr("id").text(travel.title);
	$("#travel_detail_send_time").removeAttr("id").text(travel.createTime);
	$("#travel_detail_content").removeAttr("id").text(travel.content);
}
add_travel_detail(travel_id);