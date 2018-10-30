<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:setDataSource
    driver="com.mysql.cj.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/wea?useTimezone=true&serverTimezone=UTC"
    user="root"
    password=""
    var="cn"/>         
<sql:query var="fab" dataSource="${cn}">
    select id,nombre 
    from   fabricantes;
</sql:query>                      

<div class="row form-group">
    <label for="fabricante" class="col-form-label col-md-4">Fabricante:</label> <!-- col-md-2 -->
    <div class="col-md-7"> <!-- col-md-5 -->
        <select name="fabricante" id="fabricante" class="form-control">
            <c:forEach var="fab" items="${fab.rows}">
                <option value="${ fab.id }" id="${ fab.id }">${ fab.nombre }</option>
            </c:forEach>
        </select>        
    </div>		                        
</div>
