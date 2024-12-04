<%-- 
    Document   : index
    Created on : 30 nov 2024, 8:42:01 p.m.
    Author     : edreh
--%>

<%@page import="com.proyecto.agencia.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenido</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
                rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet">
    </head>
    <body class="container">    
        <%
            if(session==null || session.getAttribute("isLogged")==null){
                out.println("<div class='card' style='width': 18rem;align-items: center;'>");
                out.println("<div class='card-body'>");
                out.println("<h5 class='card-title'>Bienvenido al sistema</h5>");
                out.println("<p class='card-text'>Accede para continuar</p>");
                out.println("<a href='SvClientes?instruccion=login' class='btn btn-primary'>Acceder</a>");
                out.println("</div></div>");
            }else{
                ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("CLIENTES");
                if(!clientes.isEmpty()){
        %>
        <div class="d-flex justify-content-between align-items-center">
            <h1 class="mb-0" id="welcome">Bienvenido, <%= session.getAttribute("username") %>!</h1>
            <a href="SvClientes?instruccion=logout" class="btn btn-danger">Cerrar sesión</a>
        </div>
                <h2 class="text-center" id="enc-clientes">Clientes</h2>
                <a href="SvClientes?instruccion=new" 
                       class="btn btn-info float-left mb-3">Añadir Cliente</a>
                <div class="container" id="clientes">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Teléfono</th>
                            <th>Agente</th>
                            <th>Actualizar</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                           <% for(Cliente c : clientes){
                            out.print("<tr>");
                                out.print("<td>"+c.getId()+"</td>");
                                out.print("<td>"+c.getNombre()+"</td>");
                                out.print("<td>"+c.getApellido()+"</td>");
                                out.print("<td>"+c.getTel()+"</td>");
                                out.print("<td>"+
                                        c.getAgente().getNombre()+" "+c.getAgente().getApellido()+"</td>");
                                out.print("<td><a class='btn btn-info' href='SvClientes?id="
                                        +c.getId()+"&instruccion=modify'>Modificar</a></td>");
                                out.print("<td><a class='btn btn-warning' href='SvClientes?id="
                                        +c.getId()+"&instruccion=delete'>Eliminar</a></td>");
                            out.print("</tr>");
                          }
                       %>                  
                  </tbody>
                </table>
            </div>
        <%
                }else{
                    out.print("<div class='container superior'>");
                    out.print("<div class='row'>");
                    out.print("<div class='col'>");
                    out.print("<h3>No hay clientes en la base de datos</h3>");
                    out.print("</div></div></div>");
                }
            }
        %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
