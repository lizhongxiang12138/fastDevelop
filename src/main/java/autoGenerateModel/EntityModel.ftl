package ${packageName};


public class ${className} {
	<#list properties as prop>
	/**
	 * 角色
	 */
	private ${prop.entity} ${prop.entityName};
	</#list>
	
	<#list properties as prop>
	public ${prop.entity} get${prop.entityName?cap_first}() {
		return ${prop.entityName};
	}

	public void set${prop.entityName?cap_first}(${prop.entity} ${prop.entityName}) {
		this.${prop.entityName} = ${prop.entityName};
	}
	</#list>
	
}
