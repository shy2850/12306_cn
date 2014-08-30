define(function(require, exports, module) {

	//两位整数格式化，小于10高位补零
	var fmt_num = function(n){
		return n < 10 ? "0" + n : n;
	};

	
	var _ = {
		reg : /([yMdhms\$]{1,2})/g,
		months : ["01","02","03","04","05","06","07","08","09","10","11","12"],
		days :["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],
		yy : function(d){return d.getFullYear()},
		MM : function(d){return this.months[d.getMonth()]},
		M  : function(d){return this.months[d.getMonth()]},
		d  : function(d){return d.getDate()},
		dd : function(d){return fmt_num(d.getDate())},
		h  : function(d){return d.getHours()},
		hh : function(d){return fmt_num(d.getHours())},
		m  : function(d){return d.getMinutes()},
		mm : function(d){return fmt_num(d.getMinutes())},
		s  : function(d){return d.getSeconds()},
		ss : function(d){return fmt_num(d.getSeconds())},
		$  : function(d){return this.days[d.getDay()]},
		$$ : function(d){return this.days[d.getDay()]}
	};
		
	exports.format = function(date,format,rule){
		if( window.jQuery ){
			rule = jQuery.extend({},_,rule); //如果引入jQuery了, 支持修改对应规则
		}
		return format.replace(_.reg,function(match,key){
			return _[key](date);
		});
	};

	exports.parse = function(str,format){
		format = format || "yy/MM/dd hh:mm:ss"; 	//没有定义格式的话, 使用默认的格式

		var map = {}, nums = str.match( /\d{1,4}/g ), fmts = format.match( _.reg );
		for (var i = 0; i < fmts.length; i++) {
			map[ fmts[i] ] = nums[i];
		}; //for循环完成格式和数据的对应关系。

		//完成替换并且返回创建的Date结果。
		return new Date( "yy/MM/dd hh:mm:ss".replace(_.reg,function(match,key){
			return map[key] || 0;
		}) );
	};	

});