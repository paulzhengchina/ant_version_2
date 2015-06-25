function setBoxWidth() {
	var menu=$(".left_menu");
	var kanban_area_width = document.body.clientWidth - menu.outerWidth() -40;
	$(".kanban_area").css("width", kanban_area_width);
}
function initializeGroupsHeight(){
	   $(".story_detail").each(function(){
		   var parentHeight=$(this).height();
		   $(this).children("div").each(function(i,val){
			   if(i<4)
			   $(this).height(parentHeight);
		   });
	   });
}
function oninit() {
	
	initializeGroupsHeight();
	
  
}

function prepareTooltip(){
	$( ".left_menu" ).tooltip({      
		position: {  my: "right top",
			         at: "left-2 top+3", 
			         using: function( position, feedback ) 
			                     { $( this ).css( position );
			                       $(this).css("color","white").css("background","black");
			                      }      
	              }   
	
	});
}