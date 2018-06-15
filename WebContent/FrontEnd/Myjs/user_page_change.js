
var user_page_content = "user_page_content";


var change_to_page = function(page_name){
	$.get(
			page_name,
			function(result){
				$("#" + user_page_content).empty().append(result);
			}
		);
}