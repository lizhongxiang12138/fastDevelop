/**
 * 自定义数据验证
 */
$.extend($.fn.validatebox.defaults.rules,{
	<#list fields as f>
	${f.dataType_id}:{
		validator:function(value){
			var reg = ${f.regex};
			return reg.test(value);
		},
		message:'${f.errorMess}'
	},
	</#list>
})
