var friends_travel_id = window.location.href.split("=")[1];


var url = "/TeamPro/web/friends_travel/"+friends_travel_id+"/detail";

function add_friends_travel_detail(){
	$.get(	
			url,
			function(result){
				if(result.STATUS == "SUCCESS"){
					add_friends_travel_to_html(result.object);
				} // end if			
			} // end success function
		); // end get
} // end function add_users


function add_friends_travel_to_html(friend_travel){
	$("#friends_travel_detail_title").removeAttr("id").text(friend_travel.title);
	$("#friends_travel_detail_time").removeAttr("id").text(friend_travel.releaseTime + " ---> " + friend_travel.fromTime);
	$("#friends_travel_detail_content").removeAttr("id").text(friend_travel.content);
}
add_friends_travel_detail();