/*!
 * @author yh.zeng
 * @descript DOM节点操作工具类
 * @date  2013/10/16
 */

/**
 * 隐藏元素
 * @param id 要隐藏的节点，可以使字符串也可以是DOM节点。可以传多个
 */
function notVisible(id) {
	forEach(arguments,function(arg){getDOM(arg)[0].style.display = "none";});
}

/**
 * 显示元素
 * @param id 要显示的节点，可以使字符串也可以是DOM节点。可以传多个
 */
function visible(id) {
	forEach(arguments,function(arg){getDOM(arg)[0].style.display = "block";});
}

/**
 * 遍历doms调用fuc方法
 * @param doms 数组或类似数组的字面量对象
 * @param fuc 该方法的参数为遍历doms得到的对象
 */
function forEach(doms,fuc){
	
	if(doms){
		
		if(doms.length != undefined){
			for(var i = 0 ; i < doms.length; i++){
				fuc.call(this,doms[i]);
			}
		}else{
			for(var pro in doms){
				fuc.call(this,doms[pro]);
			}
		}
		
	}

	
}

function getArgs(args){
	
	var _args = [];
	
	for(var i = 0; i < args.length; i++){
		
		_args[i] = args[i];
		
	}
	
	return _args.join(",");
}

/**
 * 获取DOM对象，参数为ID或DOM节点，参数可以传多个
 * @returns Array
 */
function getDOM() {
	var doms = [];
	for(var i = 0; i < arguments.length; i++){
		if (arguments.length == 0) {
			throw new error("请传递参数!");
		} else {
			if (typeof arguments[i] == "object" && arguments[i].tagName) { // dom节点
				doms.push(arguments[i]);
			} else if (typeof arguments[i] == "string") {
				doms.push(document.getElementById(arguments[i]));
			}
		}
	}
	return doms ;
}


/**
 * 获取node节点下class属性等于classname的所有节点
 * @param node
 * @param classname
 * @returns Array
 */
function getElementByClassName(node, classname){
    var a = [];
    var re = new RegExp('(^| )' + classname + '( |$)');
    var els = node.getElementsByTagName("*" );
    for( var i =0, j = els.length; i<j; i++){
         if(re.test(els[i].className)) a.push(els[i]);
    }
    return a;
};


/**
 * 将XML解析成DOM节点
 * @param text XML字符串
 * @returns XML文件对应的DOM节点
 */
function parseXML(text) {
    var xmlDoc;
    try //Internet Explorer
    {
	   xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	   xmlDoc.async = "false";
	   xmlDoc.loadXML(text);
	   
    }catch (e) {
	   try //Firefox, Mozilla, Opera, etc.
	   {
	       parser = new DOMParser();
	       xmlDoc = parser.parseFromString(text, "text/xml");
	       
	   } catch (e) {
	     alert(e.message);
	   }
   }
   return xmlDoc;
}


/**
 * 获取dom节点的文本内容
 * @param dom
 * @returns Boolean
 */
function getText(dom){
   var el = getDOM(dom)[0];
   if(el.innerText != undefined){
	   return el.innerText;
   }else{
	   return el.contentText;
   }
}

/**
 * 刷新浏览器页面
 */
function refreshPage(){
	window.document.execCommand( "Refresh" );  
}


/**
 * 判断字面量对象object是否为空
 * @param object
 * @returns {Boolean}
 */
function isEmpty(object){
	
    var isEmpty = true;
    
    for(var pro in object){
        isEmpty = false;
        break;
    }
    
    return isEmpty;
}

/**
 * 停顿milliSeconds毫秒
 * @param milliSeconds
 */
function sleep(milliSeconds) {
	var startTime = new Date().getTime();
	while (new Date().getTime() < startTime + milliSeconds);
}


/**
 * 数字判断函数，返回true表示是全部数字，返回false表示不全部是数字  
 * @param str
 * @return
 */
function isNumber(str){  


	if(""==str){  
		return false;  
	}  
	var reg = /\D/;  

	return str.match(reg)==null;  

}  

/**
 * 判断是否空
 * @param str
 * @return
 */
function isNull(value){
	var isN = false;
	if(typeof value == "string"){
		if(value == null || value.replace(/\s/g,"") == ""){
			isN =  true;
		}
	}else if(typeof value == "undefined"){
		isN =  true;
	}
	return isN;
}

/**
 * 去除左右两边的空格
 * @param str
 */
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, '');
}


/**
 * 追加事件处理
 * @param e 要添加事件的DOM节点对象
 * @param event 事件类型，如"click"
 * @param func 事件触发的方法
 */
function addEvent(e, event, func) {

    if (e.addEventListener) {
       e.addEventListener(event, func, false);

   } else if (e.attachEvent) {
       e.attachEvent( "on" + event, function() {
           func.apply(e, arguments);  //让func函数里面的this指向e
       });
   }

}


