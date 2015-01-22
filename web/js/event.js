/**
 * Created with IntelliJ IDEA.
 * User: yh.zeng
 * Date: 15-1-22
 * Time: 上午11:36
 * To change this template use File | Settings | File Templates.
 */
/**
 * 追加事件处理
 * @param e 要添加事件的DOM节点对象
 * @param eventType 事件类型，如"click"
 * @param callbackFunc 事件触发的方法
 */
function addEvent(e, eventType, callbackFunc) {

    if (e.addEventListener) {   //非ie 和ie9
        e.addEventListener(eventType, callbackFunc, false);

    } else if (e.attachEvent) {  //ie6到ie8
        window.top[callbackFunc.toString()] = function () {
            callbackFunc.apply(e, arguments);  //让func函数里面的this指向e
        };
        e.attachEvent("on" + eventType, window.top[callbackFunc.toString()]);
    } else{     //ie5
        e["on" + eventType] = callbackFunc;
    }

}


/**
 * 删除事件处理
 * @param e             要添加事件的DOM节点对象
 * @param eventType     eventType 事件类型，如"click"
 * @param callbackFunc  事件触发的方法
 */
function delEvent(e, eventType, callbackFunc){
    if (e.removeEventListener) { //非ie 和ie9
        e.removeEventListener(eventType, callbackFunc, false);

    } else if (e.detachEvent && window.top[callbackFunc.toString()]) { //ie6到ie8
        e.detachEvent("on" + eventType, window.top[callbackFunc.toString()]);
    } else{  //ie5
        e["on" + eventType] = null;
    }
}