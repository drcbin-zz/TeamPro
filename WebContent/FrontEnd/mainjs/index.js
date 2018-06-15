var memory_hot_ul_id = "memory_hot_ul";
var memory_hot_li_id = "memory_hot_li";

//发起请求
function find_memory(page,count){
	$.post(
			"/TeamPro/web/travel_memoary/list?page=" + page + "&count=" + count,
			function(result){
				if(result.STATUS == "SUCCESS"){
					add_memory_to_html(result.object);
				}else{
					alert("false");
				}
			}
	);
}

function add_memory_to_html(memorys){
	for(var memory of memorys){	
		var memory_hot_ul = $("#" + memory_hot_ul_id );
		var template_element = $("#"+ memory_hot_li_id).clone(true).removeAttr("style id");
		template_element.find("#link_to").removeAttr("id").attr("onclick","addfile('memory/memory_detail.htm', null, " + memory.id +");");
		template_element.find("#spot_img").removeAttr("id").attr("src",memory.imagePath);
		template_element.find("#title").removeAttr("id").text(memory.title).attr("onclick","addfile('memory/memory_detail.htm', null, " + memory.id +");");
		memory_hot_ul.append(template_element);
	}
}


