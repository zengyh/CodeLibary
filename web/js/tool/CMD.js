/*!
 * @author: yh.zeng
 * @descript: 调用CMD命令
 * @time: 15-9-6 下午5:16
 */
var CMD = (function(){

    var wsh = new ActiveXObject('WScript.Shell');

    function exec(command) {

   		if (wsh){
   			wsh.Run("\"" + command + "\""); // 解决目录包含空格问题
   		}
   		window.onerror = function(err) {
   			if (err.indexOf('utomation') != -1) {
   				alert('命令' + window._command + ' 已经被用户禁止！');
   				return true;
   			} else
   				return false;
   		};
   	}

    return {exec: exec};
})();