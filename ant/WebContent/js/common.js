function formateBRForTextArea()
{
	var textAreaValue=$("textarea").text();
	if(textAreaValue.indexOf("<br>") !=-1){
		textAreaValue.replace(/<br>/, "\n\t");		
	}
	else if(textAreaValue.indexOf("\n\t") !=-1){
		textAreaValue.replace(/\n\t/, "<br>");
	}
	$("textarea").text(textAreaValue);
}

function customizeDialog(){
	$(".ui-dialog-titlebar button").remove();
	$(".ui-dialog-titlebar").html("<img src='../images/icon/dialog_close.png'/>");
	$(".ui-dialog-titlebar img").css("position","absolute");
	$(".ui-dialog-titlebar img").css("right","2px");
	$(".ui-dialog-titlebar img").css("height","17px");
	$(".ui-dialog-titlebar img").css("width","17px");
	$(".ui-dialog-titlebar img").css("cursor","pointer");
	$(".ui-dialog-titlebar img").live('click',function(){
		DIALOG.dialog('close');
    });
}

function initialCurrentMenuItem(id)
{
	$("#"+id).addClass("selected");
	if("menu_item_kanban" == id)
		$("#menu_item_kanban").find("img").attr("src","../images/icon/kanban_selected.png");
	if("menu_item_story" == id)
		$("#menu_item_story").find("img").attr("src","../images/icon/story_selected.png");
	if("menu_item_plan" == id)
		$("#menu_item_plan").find("img").attr("src","../images/icon/plan_selected.png");
	if("menu_item_impediment" == id)
		$("#menu_item_impediment").find("img").attr("src","../images/icon/impediment_selected.png");
	if("menu_item_project" == id)
		$("#menu_item_project").find("img").attr("src","../images/icon/project_selected.png");
	if("menu_item_setting" == id)
		$("#menu_item_setting").find("img").attr("src","../images/icon/setting_selected.png");
}


function showTooltip(){
	$(".left_menu").tooltip({
	    position: {
	        my: "right center",
	        at: "left center",
	        using: function( position, feedback ) {
	          $( this ).css( position );
	          $( "<div>" )
	            .addClass( "arrow" )
	            .addClass( feedback.vertical )
	            .addClass( feedback.horizontal )
	            .appendTo( this );
	        }
	      }
	    });
	
	
}

function showToolTipForContent()
{
	$(".content").tooltip({
	    position: {
	        my: "center center",
	        at: "center bottom+20",
	        using: function( position, feedback ) {
	          $( this ).css( position );
	          $( this ).css("background","black").css("color","white").css("border-radius","10px").css("box-shadow", "0 0 2px black");	         
	        }
	      }
	    });
}
