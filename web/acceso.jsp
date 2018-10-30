<%-- 
    Document   : index
    Created on : 22/09/2018, 05:45:04 PM
    Author     : Jorge Baez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Spring Boot: MVC</title>
    <jsp:include page="layout/head.jsp"/>
</head>
<body>
    <jsp:include page="layout/header.jsp"/>
        
    <div class="container">
        <div class="row justify-content-center mt-3 pt-2"> <!-- mt-5 pt5 -->
            <div class="col-md-12"> <!-- col-md-7 -->

                <div class="card border-primary text-center">
                    <div class="card-header">Por favor Sign In!</div>
                    <div class="card-body">

                        <form action="controladorAcceso" method="post" id="formulario"> <!-- novalidate -->
                            <div class="form-group col-sm-6">
                                <input type="text" name="username" value="" id="username" class="form-control" placeholder="Username" autofocus required> <!-- required -->
                            </div>
                            <div class="form-group col-sm-6">
                                <input type="password" name="password" value="" id="password" class="form-control" placeholder="Password" required> <!-- required -->
                            </div>
                            <h4 class="form-group col-sm-6">
                                <button type="submit" name="action" value="signIn" class="btn btn-lg btn-primary btn-block">Sign In</button>
                            </h4>
                            
                            <input type="hidden" name="id" value="" id="id" class="form-control">
                        </form>

                    </div>
                </div>

                <jsp:include page="layout/footer.jsp"/>

            </div><!-- .col -->
        </div><!-- .row -->	
    </div><!-- .container -->     
    
    <jsp:include page="layout/cdn.jsp"/>
</body>
</html>
