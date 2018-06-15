
			var memeory_template_id = "memeory_template";
			var memeory_container_id = "memeory-list";
			
			var load_actions2 = function(x){
				
				user = JSON.parse($.cookie("userstring"));
				var url = "/TeamPro/web/user/"+ user.id+ "/travel_memoary/all";
				$.post(
						
						url,
						{
							page : x,
							count:10000
							
						},
						function(data){
							if(data.STATUS == "SUCCESS"){
								
								for(var temps of data.objects){
									
									var action = $("#" + memeory_template_id).clone().removeAttr("id style");
									
									action.find("#memeory_title")
										.removeAttr("id")
										.html(temps.title);
									action.find("#memeory_temp").removeAttr("id").html(temps.type_name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp");
									action.find("#memeory_time").removeAttr("id").html(temps.createTime);
									action.find("#memeory_picture").attr("onclick", "addfile('memory/memory_detail.htm', null,'" + temps.id + "' );return false;")
									$("#" + memeory_container_id).append(action);
								}
								

							}
						});
				
			};
			