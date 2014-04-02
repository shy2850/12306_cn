(function(){

var 
uiModule = "jqueryui/jquery.ui.{{module}}.min",
paths = {
	alerts: "jqueryui/jquery.alerts",
	raphael: "raphael.min",
	template: "template/handlebars.min",
	
	cloudMap: "cloudspectrum/cloudspectrum.min",
	timeline: "timeline/storyjs-embed.min",

	css: "css.min",
	underscore: "underscore/underscore.min",
	zTree: "zTree/jquery.ztree.all-3.5.min",
	WdatePicker: "My97DatePicker/WdatePicker",
	morris:"morris/morris.min",
	
	"form-style": "jquery-form/form-style.min",
	formValid: "jquery-form/form-valid.min",
	"idCard-valid": "jquery-form/idcard-valid.min",
	selectors: "jquery-form/selectors",
	area: "data/area.min",

	imageAreaSelector: "jquery.imgareaselect/js/index.min",

	iealert : "module/iealert/index",
	requestAFrame : "module/requestAFrame",
	"template-init": "module/template-init.min",
	queryparam: "module/queryparam",
	dateUtil: "module/date-util",
	numberUtil: "module/number-util",
	formUtil: "module/form-util",
	cookie: "module/cookie",
	qrcode: "module/qrcode/qrcode.min",
	frameUpload : "module/frame-upload/index"
},
ui = [
"accordion",
"autocomplete",
"button",
"core",
"datepicker",
"dialog",
"draggable",
"droppable",
"effect-blind",
"effect-bounce",
"effect-clip",
"effect-drop",
"effect-explode",
"effect-fade",
"effect-fold",
"effect-highlight",
"effect-pulsate",
"effect-scale",
"effect-shake",
"effect-slide",
"effect-transfer",
"effect",
"menu",
"mouse",
"position",
"progressbar",
"resizable",
"selectable",
"slider",
"sortable",
"spinner",
"tabs",
"tooltip", 
"widget"
];

for (var i = 0; i < ui.length; i++) {
	paths[ui[i]] = uiModule.replace( "{{module}}",ui[i] );
};
var scripts = document.getElementsByTagName('script') , _src = scripts[scripts.length-1].src;
require.config({
	baseUrl: _src.replace( /\/[^\/]*$/,"" ),
	paths: paths,
	shim :{
		widget:{
			deps:["core"]
		},
		dialog:{
			deps: ["widget"]
		},
		tabs:{
			deps: ["widget"]
		},
		menu:{
			deps: ["widget"]
		},
		mouse:{
			deps: ["widget"]
		},
		position:{
			deps: ["widget"]
		},
		accordion:{
			deps: ["widget"]
		},
		datepicker:{
			deps: ["widget"]
		},

		draggable:{
			deps: ["mouse"]
		},
		sortable:{
			deps: ["draggable"]
		},
		alerts:{
			deps: ["dialog"]
		},
		autocomplete : {
			deps: ["menu","position"]
		},
		cloudMap:{
			deps: ["raphael"]
		},
		zTree:{
			deps: ["css!../js/zTree/zTreeStyle/zTreeStyle.css","css!../css/new-tree.css"]
		},
		iealert:{
			deps: ["css!module/iealert/css/style.css"]
		},
		"template-init":{
			deps: ["template","form-style"]
		},
		morris :{
			deps: ["raphael","css!../js/morris/morris.css"]
		},
		"idCard-valid":{
			deps: ["formValid"]
		},
		imageAreaSelector : {
			deps: ["css!jquery.imgareaselect/css/animated.css"]
		}

	}
});

})();


/**



**/