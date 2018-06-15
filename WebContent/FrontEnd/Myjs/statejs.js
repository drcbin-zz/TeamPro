	var user1;
	var action_template_id = "action_template";
	var action_container_id = "actions-list";
	
	var load_actions1 = function(x){
		
		user1 = JSON.parse($.cookie("userstring"));
		var url = "/TeamPro/web/user/"+ user1.id+ "/travel_memoary/all";
		$.post(
				url,
				{
					page : x,
					count:6
				},
				function(data){
					if(data.STATUS == "SUCCESS"){
						for(var temp of data.objects){
							var action = $("#" + action_template_id).clone().removeAttr("id style");
							action.find("#time").removeAttr("id").html(temp.createTime);
							action.find("#content").removeAttr("id").html(temp.content);
							var id = temp.id;
							(function(n){
								action.find("#state_del_sub").removeAttr("id").on("click",function(){
									if(confirm("是否确认删除?")){
										
											$.post("/TeamPro/web/travelmemoary/"+n+"/delete",{},
													function(data){
														if(data.STATUS=="SUCCESS"){
															alert("删除成功");
															action.remove();
														}
											},"json");
									
										
									}
								});
								
							})(id);
							
						
							// alert("dd");
							var a = $("#" + action_container_id);
							
							$("#" + action_container_id).append(action);
						}
						

					}
				});
		
	};