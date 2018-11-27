<%-- 
    Document   : index
    Created on : 22/09/2018, 09:22:09 PM
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

                <div class="card bg-light">
                    <div class="card-header">Ventas</div>
                    <div class="card-body">
                           
                        <form action="controladorVenta" method="post" id="formulario"> <!-- novalidate -->
                            <div class="table-responsive">
                                <table id="table_id" class="table table-bordered table-striped"> <!-- <table class="table table-sm table-bordered table-striped table-hover table-responsive"> -->
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>Buscar</th>
                                            <th>Producto</th>                 
                                            <th>Venta</th>
                                        </tr>						
                                    </thead>					
                                    <tbody>                               
                                        <tr>
                                            <td  class="col-3">
                                                <jsp:include page="venta/cliente.jsp"/>
                                                <jsp:include page="venta/categoria.jsp"/>
                                                <jsp:include page="venta/fabricante.jsp"/>                                            
                                                <h4>
                                                    <button type="button" class="btn btn-primary" onclick="buscar()">Buscar</button>
                                                </h4>
                                            </td>                                                                    
                                            <td class="col-4">
                                                <table id="table_id" class="table table-striped table-bordered"> <!-- <table class="table table-bordered table-striped table-hover table-responsive table-sm"> -->                            
                                                    <thead class="thead-dark">
                                                        <tr>                                                    
                                                            <th>Nombre</th>                                                    
                                                            <th>Stock</th>
                                                            <th>Precio</th>  
                                                            <th>Opciones</th>
                                                        </tr>        
                                                    </thead>
                                                    <tbody id="data">

                                                    </tbody>
                                                </table>
                                            </td>
                                            <td  class="col-4">
                                                <table id="table_id" class="table table-striped table-bordered"> <!-- <table class="table table-bordered table-striped table-hover table-responsive table-sm"> -->                            
                                                    <thead class="thead-dark">
                                                        <tr>
                                                            <th class="d-none">ID</th>                                				                                    
                                                            <th>Nombre</th>
                                                            <th>Precio</th>
                                                            <th>Cantidad</th>
                                                            <th>Total</th>
                                                            <th>Eliminar</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="data2">

                                                    </tbody>
                                                </table>
                                                <h5 id="margin80"> Total <span class="badge badge-dark" id="granTotal">Total</span></h5>
                                            </td>
                                        </tr>                                                   
                                    </tbody>			                            
                                </table>
                            </div>
                            <h4>
                                <button type="submit" name="action" value="saveVenta" class="btn btn-success">Crear Venta</button>
                            </h4>

                            <!-- <input type="hidden" name="id" value="" id="id" class="form-control"> -->
                        </form>

                    </div>
                </div>
                                    
                <jsp:include page="layout/footer.jsp"/>

            </div><!-- .col -->
        </div><!-- .row -->	
    </div><!-- .container -->        
            
    <jsp:include page="layout/cdn.jsp"/>
    
    <script>
        function buscar() {
            categoria = $('#categoria').val();
            fabricante = $('#fabricante').val();            
            $.ajax({
                data: {idCategoria:categoria,idFabricante:fabricante},
                method: 'post',                    
                url: 'venta/tablaProducto.jsp',
                success: function(response) {
                    $('#data').html(response);
                }
            });
        }  

        function agregar(id, precio) {            
            $.ajax({
                data: {idProducto:id}, 
                method: 'post',
                url: 'venta/tablaVenta.jsp',
                success: function(response) {                                                           
                    if (hasProducto(id)) {
			incrementaCantidad(id, precio);
			return false; // No ejecuta el siguiente método, como un else
                    }

                    $('#data2').append(response);
                    
                    var cantidad = $('#cantidad_' + id).val(); 
                    calcularImporte(id, precio, cantidad);
                }
            });            
        }  
        
        function eliminar(id) {                        
            $('#row_' + id).remove();
            calcularGranTotal();
        }  
                        
        function calcularImporte(id, precio, cantidad) {
            $('#totalImporte_' + id).text((parseFloat(precio) * parseInt(cantidad)).toFixed(2));
            calcularGranTotal();
        }
        
        function calcularGranTotal() {
            var total = 0;
            $('span[id^="totalImporte_"]').each(function() { // All elements with a title attribute value starting with "Tom"
                total += parseFloat($(this).text());
            });
            $('#granTotal').text(total.toFixed(2));
	}
        
        function hasProducto(id) {
            var resultado = false;

            $('input[name="item_id[]"]').each(function() { // Referenciamos cada input que tenga name="item_id[]"
                if( parseInt(id) == parseInt($(this).val()) ) {
                    resultado = true;
                }
            });
		
            return resultado;
	}
        
	function incrementaCantidad(id, precio) {
            var cantidad = $('#cantidad_' + id).val() ? parseInt($('#cantidad_' + id).val()) : 0;
            $('#cantidad_' + id).val(++cantidad);
            calcularImporte(id, precio, cantidad);
	}
    </script> 
</body>            
</html>
