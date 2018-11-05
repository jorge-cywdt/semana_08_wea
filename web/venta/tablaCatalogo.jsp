<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<sql:setDataSource
    driver="com.mysql.cj.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/wea?useTimezone=true&serverTimezone=UTC"
    user="root"
    password=""
    var="cn"/>

<%
    int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));                
    int idFabricante = Integer.parseInt(request.getParameter("idFabricante"));
%>

<sql:query var="pro" dataSource="${cn}">
    select     p.id,p.nombre,c.nombre,f.nombre,p.stock,p.precio
    from       productos p
    inner join categorias c on p.id_categoria = c.id
    inner join fabricantes f on p.id_fabricante = f.id
    where      id_categoria = <%= idCategoria %>
    and        id_fabricante = <%= idFabricante %>
</sql:query>     

<table>
    <thead>
    </thead>
    <tbody>
        <c:forEach var="pro" items="${pro.rowsByIndex}">
        <tr>                        
            <td id="desProd">${ pro[1] }</td>                        
            <td id="stoProd">${ pro[4] }</td>
            <td id="preProd">${ pro[5] }</td>
            <td><button type="button" class="btn btn-sm btn-primary" onclick="agregar( ${pro[0]}, ${pro[5]} )">Agregar</button></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
