/**
 * 自定义数据验证
 */
$
		.extend(
				$.fn.validatebox.defaults.rules,
				{
					email_data : {
						validator : function(value) {
							var reg = /^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z]+[-.])+[A-Za-z]{2,5}$/;
							return reg.test(value);
						},
						message : '邮箱格式不正确'
					},
					data_baseType : {
						validator : function(value) {
							var reg = /^([\s\S]*)$/;
							return reg.test(value);
						},
						message : '数据格式不正确'
					},
				})
