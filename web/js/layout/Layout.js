/*!
 * @author yh.zeng
 * @descript 基于JQUERY的布局工具类
 * @date  2013/11/27
 */
var Layout = {
    /**
     * 基于parent父节点居中target
     * @param parent  父节点，必须是一个
     * @param target  要基于父节点居中的子节点对象，可以是一个也可以是多个
     */
    horizontal: function (parent,target){

    	var parentEl =  $(parent);
    	var targetEls = $(target);

    	for(var i = 0 ; i < targetEls.length; i++){
    		var targetEl = $(targetEls[i]);

    		targetEl.css("float","left");

    		var parentWidth = parentEl.get(0).offsetWidth;
    		var parentHeight = parentEl.get(0).offsetHeight;
    		var targetWidth = targetEl.get(0).offsetWidth;
    		var targetHeight = targetEl.get(0).offsetHeight;

    		targetEl.css("position","relative");
    		targetEl.css("left",(parentWidth - targetWidth)/2);
    	}

    }
}