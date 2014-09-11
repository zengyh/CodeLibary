/*!
 * @author yh.zeng
 * @descript 表格各行换色设置
 * @date  2013/11/13
 */
function changeColor(e, _color) {
	if(typeof($) != "undefined"){
		$(e).find("td").each(function() {
			$(this).css("background-color", _color);
		});
	}else{
		var tds = e.getElementsByTagName("td");
		for(var i = 0 ; i < tds.length; i++){
			tds[i].style.backgroundColor = _color;
		}
	}
}


function changeColorSample1(trs,tr, normalColor, targetColor, selectedColor, isMouseOver) {

	var selectedTr;
	for ( var i = 0; i < trs.length; i++) {
		if (trs[i].getAttribute("selected")
				&& (trs[i].getAttribute("selected") == true || trs[i].getAttribute("selected") == "true") ){
			selectedTr = trs[i];
		}
		var tds = trs[i].getElementsByTagName("td");
		for ( var j = 0; j < tds.length; j++) {
			if ((i + 1) % 2 != 0) {
				tds[j].style.backgroundColor = "#ffffff";
			} else {
				tds[j].style.backgroundColor = normalColor;
			}
		}
	}


	if (selectedTr) {
		var tds = selectedTr.getElementsByTagName("td");
		for ( var i = 0; i < tds.length; i++) {
			tds[i].style.backgroundColor = selectedColor;
		}
	}

	if(isMouseOver){
		var tds = tr.getElementsByTagName("td");
		for ( var i = 0; i < tds.length; i++) {
			tds[i].style.backgroundColor = targetColor;
		}
	}
}

function changeColorSample2(trs,tr, normalColor, _color) {
	if(typeof($) != "undefined"){
		$(trs).each(function() {
			$(this).find("td").each(function() {
				$(this).css("background-color", normalColor);
			});
		});
		
		$(tr).find("td").each(function() {
			$(this).css("background-color", _color);
		});
	}else{
		
		for(var i = 0 ; i < trs.length; i++){
			var tds = trs[i].getElementsByTagName("td");
			for(var j = 0 ; j < tds.length; j++){
				tds[j].style.backgroundColor = normalColor;
			}
		}
		
		var tds = tr.getElementsByTagName("td");
		for(var i = 0 ; i < tds.length; i++){
			tds[i].style.backgroundColor = _color;
		}
	}
}

function changeTableColorSample1(e, normalColor, targetColor, selectedColor){
	var target;
	if(typeof(e) == "string"){
		target = document.getElementById(e);
	}else if(typeof(e) == "object"){
		target = e;
	}else{
		throw new error("changeTableColorSample1方法传递的参数不正确!!");
	}

    normalColor = typeof(normalColor) == "undefined" ? '#eeeeee' : normalColor;
    targetColor = typeof(targetColor) == "undefined" ? '#c6e0fa' : targetColor;
    selectedColor = typeof(selectedColor) == "undefined" ? '#CFB8B8' : selectedColor;

	var head = target.getElementsByTagName("thead");
	var trs ;
	if(!head){
		trs = target.getElementsByTagName("tr");
	}else{
		var body = target.getElementsByTagName("tbody")[0];
		trs = body.getElementsByTagName("tr");
	}
	for(var i = 0; i < trs.length; i++){
		
		(function(trs,tr){

            changeColorSample1(trs, tr, normalColor, targetColor, selectedColor);

			tr.onclick = function(){
				for(var j = 0; j < trs.length; j++){
					trs[j].setAttribute("selected",false);
				}
				tr.setAttribute("selected",true);
				changeColorSample1(trs, tr, normalColor, targetColor, selectedColor);
			};
			tr.onmouseout = function(){ changeColorSample1(trs, tr, normalColor, targetColor, selectedColor, false);};
			tr.onmouseover = function(){ changeColorSample1(trs, tr, normalColor, targetColor, selectedColor, true);};
		})(trs,trs[i]);
		
	}
	
}