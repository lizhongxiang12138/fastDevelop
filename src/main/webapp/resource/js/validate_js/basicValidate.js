/**
 * 自定义数据验证
 */
$.extend(
				$.fn.validatebox.defaults.rules,
				{
					endTime : {
						validator : function(value,param) {
							var $startTime = $('#'+param);
							var startTimeVal =$.trim( $startTime.val() );
							var endTimeVal=$.trim(value);
							if(''==startTimeVal) return true;//如果开始时间没有填写则返回则不开启验证
							if(''==endTimeVal) return false;//如果填写了开始时间没有填写结束时间返回验证不通过
							var startValArr = startTimeVal.split(':');
							var endtValArr = endTimeVal.split(':');
							if(parseInt(endtValArr[0])>parseInt(startValArr[0])) 
								return true;
							else if(parseInt(endtValArr[0])<parseInt(startValArr[0])) 
								return false;
							else {
								if(parseInt(endtValArr[1])>parseInt(startValArr[1])) 
									return true;
								else
									return false;
							}
						},
						message : '结束时间不能小于开始开始时间'
					},
					endDate:{
						validator:function(value,param){
							var $startDate = $('#'+param);
							var startDate = $.trim($startDate.parent().find("input[type='hidden']").val());
							if(''==$.trim(startDate)) return true;//如果没填写开始展出日期则不进行验证
							if(''==$.trim(value)) return false;//如果填写了开始日期没有填写结束日期则验证不通过
							startDate = startDate.replace(/-/g,'/');
							value = value.replace(/-/g,'/');
							
							if(new Date(Date.parse(value))>new Date(Date.parse(startDate))) return true;
							else return false;
						},
						message:'结束日期不能大于开始日期'
					}
				})
