<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<sql:setDataSource
    driver="com.mysql.cj.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/wea?useTimezone=true&serverTimezone=UTC"
    user="root"
    password=""
    var="cn"/>
<sql:query var="cat" dataSource="${cn}">
    select id,nombre 
    from   categorias;
</sql:query>
            
<div class="row form-group">
    <label for="categoria" class="col-form-label col-md-4">Categoría:</label> <!-- col-md-2 -->
    <div class="col-md-7"> <!-- col-md-5 -->
        <select name="categoria" id="categoria" class="form-control">
            <c:forEach var="cat" items="${cat.rows}">
                <option value="${ cat.id }" id="${ cat.id }">${ cat.nombre }</option>
            </c:forEach>
        </select>        
    </div>		                        
</div>
