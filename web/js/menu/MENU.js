/*!
 * @author yh.zeng
 * @descript 菜单
 * @date  2013/11/27
 */
var MENU = function() {

	var targetDOM = []; // 点击触发显示菜单的对象,数组对象
	var ulDOM; // 菜单对象
	var targetDOMClicked; // 最近一次点击的targetDOM，只能是其中的一个对象
	
	/**
	 * 创建菜单ID，格式为 RMenu_JS_1  
	 */
	function createMenuId(){
		
		var _id = 1; 
		
		if(!window.top.rMenuIDsArray){ 
			window.top.rMenuIDsArray = []; //浏览器窗口的下拉菜单ID标识都是存放在了window.top.rMenuIDsArrays数组中
		}else{
			
			for( var i = 0; i < window.top.rMenuIDsArray.length; i++ ){
				
				var t_id = parseInt(window.top.rMenuIDsArray[i].substring(9));
				if(_id < t_id){
					_id = t_id;
				}
				
			}
			
			_id++;
		}
		
		_id = "RMenu_JS_"+_id;
		
		window.top.rMenuIDsArray.push(_id);
		return _id;
	}
	
	/**
	 * 获取浏览器窗口创建的所有的菜单ID，返回的是数组
	 */
	function getAllRMenuIDs(){
		return window.top.rMenuIDsArray ? window.top.rMenuIDsArray : [];
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
			e.attachEvent("on" + event, function() {
				func.apply(e, arguments);
			});
		}

	}
	
	/**
	 * 判断obj对象是否为数组
	 * @param obj
	 * @return
	 */
	function isArray(obj) {   
		  return Object.prototype.toString.call(obj) === '[object Array]';    
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
	 * 去除左右两边的空格
	 * @param str
	 */
	function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g, '');
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
	 * 阻止浏览器的默认事件行为
	 * @param e 事件对象event
	 */
	function stopEventDefault(e) {  
	     if (e && e.preventDefault) {//如果是FF下执行这个 
	         e.preventDefault(); 
	     }else{  
	         window.event.returnValue = false;//如果是IE下执行这个 
	     } 
	     return false; 
	} 
	
	
	/**
	 * 初始化菜单方式一
	 * 
	 * @param target
	 *            为某事件源触发显示菜单的对象，可以是数组对象和字符串对象,
	 *            数组对象里的元素可以是DOM节点，也可以是字符串，字符串必须是ID。
	 *            单个对象可以是DOM节点，也可以是字符串ID，多个字符串ID必须以逗号隔开。
	 * @param eventType
	 *            事件类型
	 * @return
	 */
	function init(target,eventType) {
		
		if(typeof(target) == "object"){
			if(isArray(target)){
				forEach(target,function(aTarget){
					switch(typeof(aTarget)){
					   case "object":
						    targetDOM.push(aTarget);
						    break;
					   case "string":
						    var aTargets = getDOM(aTarget);
						    if(aTargets && aTargets.length > 0){
						    	targetDOM.push(aTargets[0]);
						    }
						    break;
					   default:
						    break;
					}
				});
			}else{
				targetDOM.push(target);
			}
		}else if(typeof(target) == "string"){
			targetDOM = getDOM(target);
		}else{
			return;
		}
		
		ulDOM = document.createElement("ul");
		ulDOM.className = "nav";
		ulDOM.id = createMenuId();
		document.body.appendChild(ulDOM);

		// 设置样式
		ulDOM.style.float = "left";
		ulDOM.style.position = "absolute";
		ulDOM.style.zIndex = "9999";
		hide();

		// 定位事件
		forEach(targetDOM,function(dom){
			addEvent(dom,eventType,function(e){
/*				targetDOMClicked = this;
				ulDOM.style.left = (e.x || e.pageX)+"px";
				ulDOM.style.top = (e.y || e.pageY)+"px";
				show();*/
				
				show();
				var left = e.x || e.pageX || 0;
				var top = e.y || e.pageY || 0;
				if(ulDOM.offsetWidth + left > document.body.offsetWidth){
					left = document.body.offsetWidth - ulDOM.offsetWidth -10;
					left = left < 0 ? 0 : left;
				}
				if(ulDOM.offsetHeight + top > document.body.offsetHeight){
					top = document.body.offsetHeight - ulDOM.offsetHeight -10;
					top = top < 0 ? 0 : top;
				}
				ulDOM.style.left = left+"px";
				ulDOM.style.top = top+"px";
				
				stopEventDefault(e);
			});
		});

		// 其他事件源事件
		forEach(getAllWindow(),function(win){
			addEvent(win.document,"click",function(e){  //鼠标左键点击
				if(eventType != "click"){
					hide();
				}
				for(var i = 0; i< targetDOM.length; i++){
					if ((e.target || e.srcElement) == targetDOM[i]) {
						return;
					}
				}
				hide();
			});
			addEvent(win.document,"contextmenu",function(e){  //鼠标右键点击
				if(eventType != "contextmenu"){
					hide();
				}
				for(var i = 0; i< targetDOM.length; i++){
					if ((e.target || e.srcElement) == targetDOM[i]) {
						return;
					}
				}
				hide();
			});
		});
/*		addEvent(document,"click",function(e){  //鼠标左键点击
			if(eventType != "click"){
				//hide();
				hideAllRMenus();
			}
			for(var i = 0; i< targetDOM.length; i++){
				if ((e.target || e.srcElement) == targetDOM[i]) { 
					return;
				}
			}
			//hide();
			hideAllRMenus();
		});
		addEvent(document,"contextmenu",function(e){  //鼠标右键点击
			if(eventType != "contextmenu"){
				//hide();
				hideAllRMenus();
			}
			for(var i = 0; i< targetDOM.length; i++){
				if ((e.target || e.srcElement) == targetDOM[i]) { 
					return;
				}
			}
			//hide();
			hideAllRMenus();
		});*/
		
	}
	
	/**
	 * 初始化菜单的方式二，直接创建菜单
	 */
	function createMenu(){
		ulDOM = document.createElement("ul");
		ulDOM.className = "nav";
		ulDOM.id = createMenuId();
		document.body.appendChild(ulDOM);

		// 设置样式
		ulDOM.style.float = "left";
		ulDOM.style.position = "absolute";
		ulDOM.style.zIndex = "9999";
		hide();
		
	}
	

	/**
	 * 添加菜单项
	 * @param _option 格式为{id:...,text:...,img:...,click:...,enable:...}字面量对象,
	 *        id为菜单项标识,text菜单项显示的文本,img为菜单项图标链接,
	 *        click菜单项的点击事件处理方法必须是FUNCTION,
	 *        enable为false则菜单项不可以点击
	 */
	function addItem(_option) {

		var option = {
			id : "", // 菜单项ID
			txt : "测试", // 菜单项文本
			img : "/images/menu/shortcut_add.png",  //图片链接
			click : function() { // 菜单项点击事件
				hide();
			},
			enable: false
		};

		for ( var pro in _option) {
			option[pro] = _option[pro];
		}
		
		var liDOM = document.createElement("li");
		liDOM.innerHTML = "<img src=\""+option.img+"\" width=\"0\" height=\"0\" style=\"display:inline-block;position:absolute;left:2px;\"></img>"
			            + "<a href=\"javascript:void(0)\"  tid=\"" + option.id + "\" enable=\""+option.enable+"\" style=\"outline:none;\">"
                        + option.txt + "</a>";
		ulDOM.appendChild(liDOM);
		
		if(!option.enable){
			liDOM.getElementsByTagName("a")[0].style.color = "gray";
		}
		
		//图标的放大和缩小操作
		var childNodes = liDOM.childNodes;
		forEach(childNodes,function(childNode){
			if(childNode.tagName){
				if(trim(childNode.tagName.toLowerCase()) == "img"){
					addEvent(childNode,"load",function(){
					    var img = new Image(); //图片预加载
					    img.src = this.src; 
					    var width = img.width;
					    var height = img.height;
					    this.width = 16;     //宽度设定为16px
					    this.height = height*16/width;  //这里的img.width和img.height为原图大小
					
					    liDOM.style.height = height*16/width+"px";  //根据图片高度重新设定li的高度
					});
				}
			}
		});

		if(option.enable){
			addEvent(liDOM,"click",function(){
				hide();
				if(targetDOMClicked){
					targetDOMClicked.focus();
				}
				option.click.apply(this,arguments);
			});
			addEvent(liDOM,"contextmenu",function(){
				hide();
				if(targetDOMClicked){
					targetDOMClicked.focus();
				}
				option.click.apply(this,arguments);
			});
			
			addEvent(liDOM,"mouseover",function(){
				this.style.backgroundColor="#FFEEC2";
				liDOM.style.border="1px solid orange";
			});
			
			addEvent(liDOM,"mouseout",function(){
				this.style.backgroundColor="";
				this.style.border="";
			});
		}
		
	}
	
	/**
	 * 隐藏浏览器窗口的所有的右键下拉菜单或其他行为的下拉菜单
	 */
	function hideAllRMenus(){
		if(document.getElementById(ulDOM.id)){
			 var ids = getAllRMenuIDs();
			 var allWindows = getAllWindow();
			 forEach(allWindows,function(_window){
					var _doc = _window.document;
					forEach(ids,function(id){
						if(_doc.getElementById(id)){
							_doc.getElementById(id).style.display = "none";
						}
					});
			 });
		}
	}
	
	
	/**
	 * 获取该网页的所有窗口对象
	 */
	function getAllWindow(){
		var allWIN = [window.top];
		
		_getAllWindow(window.top,allWIN);
		
		return allWIN;
	}
	
	function _getAllWindow(tWindow,winArray){
		var frames = tWindow.frames;
		if(frames){
			forEach(frames,function(frame){
				winArray.push(frame);
				_getAllWindow(frame,winArray);
			});
		}
	}

	/**
	 * 显示菜单
	 * @param posX     菜单相对于窗口的X坐标轴的位置
	 * @param posY     菜单相对于窗口的Y坐标轴的位置
	 * @param width    菜单的宽度
	 * @param height   菜单的高度
	 */
	function show(posX, posY, width, height) {
		
		hideAllRMenus();
		
		if(posX && posY){  
			ulDOM.style.left = posX;
			ulDOM.style.top = posY;
		}if(width && height){
			ulDOM.style.width = width;
			ulDOM.style.height = height;
		}
		
		ulDOM.style.display = "block";
		//设置所有菜单项的宽度一致
		var maxItemWidth = 0;
		var allItemLinks = getAllItemLink();
		forEach(allItemLinks,function(itemLink){
			maxItemWidth = maxItemWidth < itemLink.offsetWidth ? itemLink.offsetWidth : maxItemWidth;
		});
		forEach(allItemLinks,function(itemLink){
			itemLink.style.width = maxItemWidth + "px";
		});

		
		if(ulDOM.style.left && ulDOM.offsetWidth + parseInt(ulDOM.style.left.replace(/\D*/g,'')) > document.body.offsetWidth){
			if(document.body.offsetWidth - ulDOM.offsetWidth -10 < 0){
				ulDOM.style.left = "0px";
			}else{
				ulDOM.style.left = (document.body.offsetWidth - ulDOM.offsetWidth -10)+"px";
			}
		}
		if(ulDOM.style.top && ulDOM.offsetHeight + parseInt(ulDOM.style.top.replace(/\D*/g,'')) > document.body.offsetHeight){
			if(document.body.offsetHeight - ulDOM.offsetHeight -10 < 0){
				ulDOM.style.top = 0;
			}else{
				ulDOM.style.top = (document.body.offsetHeight - ulDOM.offsetHeight -10)+"px";
			}
		}
	}

	/**
	 * 隐藏菜单
	 */
	function hide() {
		ulDOM.style.display = "none";
	}

	/**
	 * 设置菜单项的点击事件
	 * @param id为菜单项的ID, 必须为字符串
	 * @param func 方法对象
	 */
	function setClickFunc(id, func) {
		var linkDOM = getItemLink(id);
		if(linkDOM.getAttribute("enable") == "true"){
		    addEvent(linkDOM.parentNode, "click", function() {
				hide();
				if(targetDOMClicked){
					targetDOMClicked.focus();
				}
				func.apply(linkDOM, arguments);
			});
		    addEvent(linkDOM.parentNode, "contextmenu", function() {
				hide();
				if(targetDOMClicked){
					targetDOMClicked.focus();
				}
				func.apply(linkDOM, arguments);
			});
		}
	}
	
	/**
	 * 获取菜单项中标识为ID的链接对象,若是有多个该ID标识的菜单项链接,
	 * 那么只返回找到的第一个
	 * @param id  标识符
	 */
	function getItemLink(id){
		var linkDOMS = ulDOM.getElementsByTagName("a");
		var tLinkDOMS;
		for(var i = 0; i < linkDOMS.length; i++){
			if(trim(linkDOMS[i].getAttribute("tid")) == trim(id)){
				tLinkDOMS =  linkDOMS[i];
				break;
			}
		}
		
		return tLinkDOMS;
	}
	
	/**
	 * 获取所有菜单项
	 */
	function getAllItemLink(){
		var linkDOMS = ulDOM.getElementsByTagName("a");
		
		return linkDOMS;
	}

	/**
	 * 设置菜单项文本
	 * id为菜单项的ID
	 * text为菜单项的文本
	 */
	function setText(id, text) {
		var linkDOM = getItemLink(id);
		if(linkDOM){
			linkDOM.text(text);
		}
	}
	
	/**
	 * 获取菜单项文本
	 * id为菜单项的ID
	 */
	function getText(id){
		var _text;
		var linkDOM = getItemLink(id);
		if(linkDOM){
			_text = linkDOM.text();
		}

		return  _text;
	}
	
	/**
	 * 设置宽度
	 * @param width
	 */
	function setWidth(width){
        forEach(ulDOM.getElementsByTagName("li"),function(li){
            li.style.width = width;
        });
	}
	
	/**
	 * 设置高度
	 * @param height
	 */
	function setHeight(height){
		ulDOM.style.height = height;
	}

	return {
		init : init,
		createMenu : createMenu,
		addItem : addItem,
		show : show,
		hide : hide,
		hideAllRMenus : hideAllRMenus,
		setClickFunc : setClickFunc,
		setText : setText,
		getText : getText,
		getAllWindow : getAllWindow,
		setWidth : setWidth,
		setHeight : setHeight
	};

};
