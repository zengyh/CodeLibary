/*!
 * @author: yh.zeng
 * @descript: 百度输入法调用
 * @time: 15-8-7 下午4:41
 */
var BAIDU = (function() {

	//调用百度手写输入法
	function shouxie(){
		var command = "D:\\Program Files\\SogouInput\\Components\\HandInput\\1.0.0.179\\HandInput.exe";
        CMD.exec(command);
	}
	
	//调用电脑软件键盘
	function ruanjianpan(){
		var command = "osk";
		CMD.exec(command);
	}
	
	return{
		shouxie: shouxie,
		ruanjianpan: ruanjianpan
	};
	
})();
