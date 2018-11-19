<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header th:fragment="header">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Spring Boot</a>    
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">                
                <li class="nav-item active">
                    <a class="nav-link active" href="acceso">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item"><a class="nav-link" href="venta">Ventas</a></li>
                <li class="nav-item"><a class="nav-link" href="about">About</a></li>
                <li class="nav-item">
                    <a class="nav-link disabled d-none" href="#">Disabled</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
    
    <c:if test="${ not empty mensajeExito }">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            ${ mensajeExito }
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>
    <c:if test="${ not empty mensajeError }">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${ mensajeError }
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>    
    </c:if>
    <c:if test="${ not empty SQLException }">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${ SQLException }
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>    
    </c:if>
    <% session.setAttribute("mensajeExito", null); %>
    <% session.setAttribute("mensajeError", null); %>
    <% session.setAttribute("SQLException", null); %>
    <% session.setAttribute("mensajeError2", null); %>
    <% session.setAttribute("Datos", null); %>
    
    <%--
    ${ mensajeExito }
    ${ mensajeError }
    ${ SQLException }
    ${ mensajeError2 }
    ${ Datos }
    <% session.setAttribute("mensajeExito", null); %>
    <% session.setAttribute("mensajeError", null); %>
    <% session.setAttribute("SQLException", null); %>
    <% session.setAttribute("mensajeError2", null); %>
    <% session.setAttribute("Datos", null); %>
    --%>        
    
    <!--
    <div class="alert alert-success" th:if="${success != null}" th:text="${success}" role="alert"></div>
    <div class="alert alert-danger" th:if="${error != null}" th:text="${error}" role="alert"></div>
    <div class="alert alert-warning" th:if="${warning != null}" th:text="${warning}" role="alert"></div>
    <div class="alert alert-info" th:if="${info != null}" th:text="${info}" role="alert"></div>
    -->
</header>