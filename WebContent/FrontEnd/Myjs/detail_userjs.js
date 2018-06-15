var infomation_user = function(){
					user = JSON.parse($.cookie("userstring"));
					
					$.post("../web/user/"+user.id+"/detail",{},function(data){
						if(data.STATUS=="SUCCESS"){
							
							var user_infomation = data.object;
							if(user_infomation.gender==1){
								$("#man").prop("checked","true");
								
							}else{
								$("#woman").prop("checked","true");
							}
							$("#detail_nickname").val(user_infomation.name);
							$("#detail_birthday").val(user_infomation.birthday);
							
							$("#detail_address").val(user_infomation.address);
							$("#detail_qq").val(user_infomation.qq);
							$("#detail_phone").val(user_infomation.phone);
							$("#detail_email").val(user_infomation.email);
							$("#detail_temp").val(user_infomation.temp);
						}
					},"json");
				}