<%-- 
    Document   : update
    Created on : 2 dic 2024, 2:31:48 p.m.
    Author     : edreh
--%>

<%@page import="com.proyecto.agencia.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar</title>
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
                //obtenemos los datos del libro
                Cliente c = (Cliente)request.getAttribute("CLIENTE");
        %>
        <div class="container mt-5">
            <h2>Editar Cliente</h2>
            <form method="get" action="SvClientes">
                <input type="hidden" name="instruccion" value="update">
                <div class="mb-3">
                    <label for="id" class="form-label">ID</label>
                    <input type="number" class="form-control" id="id" name="id" 
                           value="<% out.print(c.getId()); %>" readonly>
                </div>
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre"
                           value="<% out.print(c.getNombre()); %>" required>
                </div>
                <div class="mb-3">
                    <label for="apellido" class="form-label">Apellido</label>
                    <input type="text" class="form-control" id="apellido" name="apellido"
                           value="<% out.print(c.getApellido()); %>" required>
                </div>
                <div class="mb-3">
                    <label for="telefono" class="form-label">Teléfono</label>
                    <input type="tel" class="form-control" id="telefono" name="tel"
                           value="<% out.print(c.getTel()); %>" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required>
                </div>
                 <div class="form-group">
                    <label for="agente">Agente</label>
                    <SELECT name="agente" class="custom-select">
                        <option value="<% out.print(c.getAgente().getId()); %>">
                                <% out.print(c.getAgente().getNombre()+" "+c.getAgente().getApellido());%></option>
                        <%
                        ArrayList<Agente> agentes = 
                                (ArrayList<Agente>) request.getAttribute("AGENTES");
                        
                        for(Agente a : agentes){
                             out.print("<option value='"
                                     +a.getId()+"'>"+a.getNombre()+" "+a.getApellido()+
                                              "</option>");    
                        }
                            }
                    %>
                    </SELECT>
                </div>
                <br>
                <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
