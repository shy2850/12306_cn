(function($){
	/*鼠标移动上去
	$(".menu").on("mouseover",function(){
		$(this).addClass("hover");
	}).on("mouseout",function(){
		$(this).removeClass("hover");
	});*/
	$.fn.hoverClass = function(cls){
		this.hover(function(){
			$(this).addClass(cls);
		},function(){
			$(this).removeClass(cls);
		});
	}

	$(".menu").hoverClass("hover");
	$(".nav-guide").hoverClass("current");

})(jQuery);