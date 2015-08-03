/*!
 * @author yh.zeng
 * @descript 菜单
 * @date  2013/11/27
 */
var CheckBoxs = (function(){
	
	var wrapEls = {};         //静态变量，包含所有选择框的DOM节点对象
	
	
	return function(){
		
	    var selectedAllCheckBoxEl; //全选选择框节点对象
	    var theCheckBoxEls = [];  //存放所有选择框节点对象
	    
	    var wrapEl;               //包含所有选择框的DOM节点对象
	    
	    if(arguments.length > 0){
	    	
	        if( typeof arguments[0] == "object" && arguments[0].tagName){  //DOM节点
	        	wrapEl = arguments[0];
	        }else{
	        	wrapEl = document.getElementById(arguments[0]);
	        }
	    	
	    }else{
	    	wrapEl = document.body;
	    }
	    
	    
	    init();
	    
	    function init(){
	    	
	    	if(wrapEls[wrapEl.id]){
	    		selectedAllCheckBoxEl = wrapEls[wrapEl.id]["selectedAllCheckBoxEl"];
	    		theCheckBoxEls = wrapEls[wrapEl.id]["theCheckBoxEls"];
	    	}
	    	
	    }
	    
	    
	    function setWrapEls(){
	    	
	    	if(wrapEl.id && !wrapEls[wrapEl.id]){
	    		wrapEls[wrapEl.id] = {};
	    		wrapEls[wrapEl.id]["selectedAllCheckBoxEl"] = selectedAllCheckBoxEl;
	    	    wrapEls[wrapEl.id]["theCheckBoxEls"] = theCheckBoxEls;
	    	}
	    }
	    
	    //获取所有选择框节点对象
	    function getAllCheckBoxEls(){
	    	
	        if(theCheckBoxEls.length == 0){
	              var inputEls = wrapEl.getElementsByTagName("input");
	              for(var i = 0; i < inputEls.length; i++){
	                 if(inputEls[i].type == "checkbox" && inputEls[i].value != "全选"){
	                    theCheckBoxEls.push(inputEls[i]);
	                 }else if(inputEls[i].type == "checkbox" && inputEls[i].value == "全选"){
	                    selectedAllCheckBoxEl = inputEls[i];
	                 }
	              }
	        }
	         
	        setWrapEls();
	        
	        return theCheckBoxEls;
	    }
	    
	    
	    //获取全选选择框节点对象
	    function getSelectAllCheckBoxEl(){
	        if(!selectedAllCheckBoxEl){
	              var inputEls = wrapEl.getElementsByTagName("input");
	              for(var i = 0; i < inputEls.length; i++){
	                 if(inputEls[i].type == "checkbox" && inputEls[i].value == "全选"){
	                    selectedAllCheckBoxEl = inputEls[i];
	                    break;
	                 }
	              }
	        }
	         
	        return selectedAllCheckBoxEl;
	    }
	    
	    //获取所有选择的选择框节点对象
	    function getAllSelectedCheckBoxEls(){
	       var selectedEls = [];
	       var els = getAllCheckBoxEls(wrapEl); //获取所有选择框节点对象
	       for(var i = 0; i < els.length; i++){
		       if(els[i].checked){
		           selectedEls.push(els[i]);
		       }
		   }
		   return selectedEls;
	    }
	    
	    //全选
	    function selectALL(){
	        var els = getAllCheckBoxEls(wrapEl);
	        if(getSelectAllCheckBoxEl(wrapEl).checked){
	           for(var i = 0; i < els.length; i++){
	              els[i].checked = true;
	           }
	        }else{
	           clearSelected();
	        }

	    };
	    
	    //反选
	    function inverseSelected(){
	        var els = getAllCheckBoxEls(wrapEl);
	        for(var i = 0; i < els.length; i++){
	            if(els[i].checked){
	               els[i].checked = false;
	            }else{
	               els[i].checked = true;
	            }
	        }
	        getSelectAllCheckBoxEl(wrapEl).checked = false;
	    };
	    
	    //重置
	    function clearSelected(){
	        var els = getAllCheckBoxEls(wrapEl);
	        for(var i = 0; i < els.length; i++){
	            els[i].checked = false;
	        }
	        getSelectAllCheckBoxEl(wrapEl).checked = false;
	    };
	    
	    
	    //将所有传递进来的DOM节点或id标识对应的节点标志为选择状态
	    function select(id){
	    	
	    	clearSelected();
	    	
	    	for(var i = 0; i < arguments.length; i++){
	    		
	    		if( typeof arguments[i] == "string" || typeof arguments[i] == "number"){
	    			
	    			for(var j = 0; j < theCheckBoxEls.length; j++){
	    				
	    				if(theCheckBoxEls[j].getAttribute("tid") == arguments[i]+""){
	    					theCheckBoxEls[j].checked = true;
	    					break;
	    				}
	    				
	    			}
	    			
	    		}else if(typeof arguments[i] == "object" && arguments[i].length){
                    for(var k = 0; k <  arguments[i].length; k++){
                        for (var j = 0; j < theCheckBoxEls.length; j++) {

                            if (theCheckBoxEls[j].getAttribute("tid") == arguments[i][k] + "") {
                                theCheckBoxEls[j].checked = true;
                                break;
                            }

                        }
                    }
                }else{
	    			
	    			for(var j = 0; j < theCheckBoxEls.length; j++){
	    				
	    				if(theCheckBoxEls[j] == arguments[i]){
                            theCheckBoxEls[j].checked = true;
	    					break;
	    				}
	    				
	    			}
	    			
	    		}
	    	}
	    	
	    }
	    
	    return { 
	    	     getAllSelectedCheckBoxEls: getAllSelectedCheckBoxEls,
	    	     getSelectAllCheckBoxEl: getSelectAllCheckBoxEl,
	    	     selectALL: selectALL,
	    	     inverseSelected: inverseSelected,
	    	     clearSelected: clearSelected,
	    	     select: select
	    	   };
	};

})();