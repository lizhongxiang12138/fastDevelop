package ${packageName};

import com.my.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository(value="${beanName}")
public class ${className} extends BaseDaoImpl<${entity}> implements ${interfaceName} {

}
