var travel_template_id = "travle_x";
var travel_container_id = "travel-list";

var load_actions3 = function(x){
	
	user = JSON.parse($.cookie("userstring"));
	var url = "/TeamPro/web/user/"+ user.id+ "/friends_travel/all";
	$.post(
			url,
			{
				count:10000
				
			},
			function(data){
				if(data.STATUS == "SUCCESS"){
					
					for(var temps of data.objects){
						
						var action = $("#" + travel_template_id).clone().removeAttr("id style");
						
						action.find("#travel_title").removeAttr("id").html(temps.title);
						action.find("#travel_cont").removeAttr("id").html
						(temps.content+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"+"行程："+
						temps.fromTime+"至"+temps.toTime+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布时间"+
						temps.releaseTime);
						
						$("#" +travel_container_id).append(action);
					}
					

				}
			});
	
};