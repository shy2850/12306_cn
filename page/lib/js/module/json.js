define(function(require, exports, module) {
	function isArray(obj) { 
		return ({}).toString.call(obj) === '[object Array]'; 
	}
	function expend(r) {
		var getRef = function(o,$ref){	//获取$ref索引对象
			var t = $ref.split(".");
			if(t.length <= 1){
				return o
			}else{
				var m = $ref.match(/^(.*?)\.([^.]+)$/);
				return arguments.callee(o,m[1])[m[2]];
			}
		};

		(function(o){
			for(var k in o){
				if(o[k] && typeof o[k].$ref === 'string'){
					o[k] = getRef(r,o[k].$ref.replace(/\[(\d+)\]/g,'.$1')); //有数组格式的数据要转化一下。
				}else if(typeof o[k] === 'object'){
					arguments.callee(o[k]);
				}
			}
		})(r);
		return r;
	}
	
	var objs = [];

	var JSON = window.JSON || {
		parse : function(jsonStr){
			return new Function("return ("+jsonStr+")").call(this);
		},
		stringify : function(o){
			var tp = (null === o) ? 'undefined' : typeof o,
				callee = arguments.callee;
			
			if( this.stringify === callee ){	//如果是第一次的非递归调用, 初始化objs
				objs = [];
			}

			switch(tp){
				case 'undefined':
				case 'number':
				case 'boolean': return o; 
				case 'string': return '"' + o.replace(/\\/g,'\\\\').replace(/"/g,'\"') + '"'; 
				case 'function': return '"[object Function]"';
				case 'object':
					// 跟原生的JSON.stringify一样,对于循环调用的JSON抛出异常
					for (var i = 0; i < objs.length; i++) {		
						if( objs[i] === o ) throw new Error("Converting circular structure to JSON");
					}
					objs.push(o);
				default : 
					if( isArray(o) ){
						return (function(o){
							var res = [];
							for(var k = 0; k < o.length; k++){
								res.push( callee(o[k]) )
							}
							return '['+res.join(',')+']'
						})(o);
					}else{
						return (function(o){
							var res = [];
							for(var k in o){
								if( ({}).hasOwnProperty.call(o,k) ){
									res.push( k + ":" + callee(o[k]) )
								}
							}
							return '{'+res.join(',')+'}'
						})(o);
					}
						
			}
				
		}
	};

	return {
		parse : JSON.parse,
		stringify:JSON.stringify,
		/**
		 * 展开携带$ref的restful-json对象
		 */
		expend : function(arg){
			if(typeof arg === 'string' && /\$ref/.test(arg) ){
				return expend( JSON.parse(arg) );
			}else{
				return expend(arg);
			}
		}
	};
});