<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<sql:setDataSource
driver="com.mysql.cj.jdbc.Driver"
url="jdbc:mysql://localhost:3306/wea?useTimezone=true&serverTimezone=UTC"
user="root"
password=""
var="cn"/>

<% int idProducto = Integer.parseInt(request.getParameter("idProducto")); %>

<sql:query var="pro" dataSource="${ cn }">
    select id,nombre,precio
    from   productos 
    where  id = <%= idProducto %>                
</sql:query>  

<%--
<table>
    <thead>
    </thead>
    <tbody>
--%>
        <c:forEach var="pro" items="${ pro.rowsByIndex }">
        <tr id="row_${pro[0]}">
            <td class="d-none">
                <input type="hidden" name="item_id[]" value="${pro[0]}"></input>
            </td>
            <td>${pro[1]}</td>
            <td>${pro[2]}</td>
            <td class="col-3">
		<input class="form-control" id="cantidad_${pro[0]}" type="number" name="cantidad[]" value="1" min="1" onchange="calcularImporte(${pro[0]}, ${pro[2]}, this.value);"></input>
            </td>  
            <td>
		<span id="totalImporte_${pro[0]}" name="precio[]">${pro[2]}</span>
            </td>
            <td><button type="button" class="btn btn-sm btn-danger" onclick="eliminar( ${pro[0]} );">Eliminar</button></td>
        </tr>
        </c:forEach>
<%-- 
    </tbody>
</table>
--%>
