var memoary_container_id = "memoary-container";
var memoary_item_container_id = "memoary-item-container";

var container_template_id = "container-template";
var memoary_item_template_id = "memoary-item-template";


var get_keys = function(objects){
	var keys = [];
	for(var key in objects){
		keys.push(key);
	}
	return keys;
};


function get_memorys(){
	$.get(
			"/TeamPro/web/travel_memoary/list",
			{
				type:1,
				count:50
			},
			function(result){
				if (result.STATUS == "SUCCESS"){
					var types = result.types;
					var type_names = get_keys(types);
					for(var type_name of type_names){
						
						
						// create memoarys with title
						var memoarys = $("#" + container_template_id).clone(true).removeAttr("id style");
						memoarys.find("#type_title").removeAttr("id").text(type_name);
						
						var ul = memoarys.find("#" + memoary_item_container_id).removeAttr("id");
						
						var count = 0;
						for(var memoary of types[type_name]) {
							if(count<4){
								count++;
								var item = $("#" + memoary_item_template_id).clone(true)
									.removeAttr("id style")
									.attr("onclick","addfile('memory/memory_detail.htm', null, " + memoary.id +");");
								item.find("#title").removeAttr("id").text(memoary.title);
								item.find("#image").removeAttr("id").attr("src", memoary.imagePath);
								item.find("#content").removeAttr("id").text(memoary.content);
								ul.append(item);								
							}	
						}
						

						// add memoary to memoaty container
						$("#" + memoary_container_id).append(memoarys);
	
						
					} //for end
				}  // if end
				
			}  // function end
		);  // get end
}