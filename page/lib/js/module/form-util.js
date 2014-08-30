define(function(require, exports, module) {
	var $ = jQuery;

	return {
		checkedVal : function(name,form){
			var result = [];
			$( ':checkbox[name="'+name+'"]', form ).filter(":checked").each(function(){
				result.push( this.value );
			});
			return result;
		},
		selectAll : function(opt){
			var o = $.extend({
				handle : null,			//必须是页面已有标签
				checkList : null,		//可以使用预定义选择器,但是必须定义checkListHolder
				checkListHolder : null	//必须是页面已有标签
			},opt);
			var handle = $(o.handle), 
				_handle = handle[0], 
				checkList = $(o.checkList),
				checkListHolder = o.checkListHolder ? $(o.checkListHolder) : checkList.parent();
			handle.on('click',function(){ 
				var checked = this.checked;
				$(o.checkList).each(function(){
					if(this.checked != checked){
						this.click();
					}
				}); 
			});
			checkListHolder.on('click',function(e){
				if( !_handle.checked && !$(o.checkList).not(":checked").length){
					_handle.checked = true;
					handle.trigger('checked');
				}else if( _handle.checked && $(o.checkList).not(":checked").length){
					_handle.checked = false;
					handle.trigger('checked');
				}
			});
		}
	};

});