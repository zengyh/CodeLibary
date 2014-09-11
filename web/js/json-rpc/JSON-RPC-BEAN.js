/*!
 * @author yh.zeng
 * @descript AjaxBean的Js对象映射JAVA对象的工具类
 * @date  2013/10/8
 */
var JSONRPC = {};

/**
 * 新建一个和java的JavaClass类映射的对象，该对象有getObect()方法返回
 */
JSONRPC.Bean = function(javaClass){
	
	var bean = {"javaClass": javaClass};

	var set = function(property,value){
		
		bean[property] = value;
	};
	
	var get = function(property){
		
		return bean[property];
	};
	
	var getObject = function(){
		
		return bean;
	};
	
	return{
		set: set,
		get: get,
		getObject: getObject
	};
	
};


/**
 * 新建一个和java的ArrayList类映射的对象，该对象有getObect()方法返回
 */
JSONRPC.ArrayList = function(){
	
    var list = { 
            "javaClass": "java.util.ArrayList",
            "list": []
    };

	var add = function(value){
		
		list["list"].push(value);
	};
	
	var get = function(i){
		
		return list["list"][i];
	};
	
	var getObject = function(){
		
		return list;
	};
	
	
	return{
		add: add,
		get: get,
		getObject: getObject
	};
	
	
};

/**
 * 新建一个和java的HashMap类映射的对象，该对象有getObect()方法返回
 */
JSONRPC.HashMap = function(){
	
	var map = {
		 "javaClass": "java.util.HashMap",
		 "map": {}
    };


	var put = function(property,value){
		
		map["map"][property] = value;
	};
	
	var get = function(property){
		
		return map["map"][property];
	};
	
	var getObject = function(){
		return map;
	};
	
	return{
		
		put: put,
		get: get,
		getObject: getObject
		
	};
	
};
	