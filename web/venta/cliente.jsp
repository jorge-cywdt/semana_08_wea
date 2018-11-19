<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<sql:setDataSource
    driver="com.mysql.cj.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/wea?useTimezone=true&serverTimezone=UTC"
    user="root"
    password=""
    var="cn"/>
<sql:query var="cli" dataSource="${ cn }">
    select id,nombre 
    from   clientes;
</sql:query>
            
<div class="row form-group">
    <label for="cliente" class="col-form-label col-md-4">Cliente:</label> <!-- col-md-2 -->
    <div class="col-md-7"> <!-- col-md-5 -->
        <select name="cliente" id="cliente" class="form-control">
            <c:forEach var="cli" items="${ cli.rows }">
                <option value="${ cli.id }" id="${ cli.id }">${ cli.nombre }</option>
            </c:forEach>
        </select>        
    </div>		                        
</div>
