<%-- 
    Document   : newcliente
    Created on : 1 dic 2024, 10:57:45 p.m.
    Author     : edreh
--%>

<%@ page import="java.util.*, com.proyecto.agencia.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Agregar Cliente</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
                rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
            <link href="css/mystyle.css" rel="stylesheet">

    </head>
    <body class="container">
        <div class="container mt-5 mb-5">
            <h1 id="main-header">Agregar nuevo cliente</h1>
            
            <form method="get" action="SvClientes">
              <input type="hidden" name="instruccion" value="add">
              <div class="form-group">
                <label for="id">ID:</label>
                <input type="number" class="form-control" name="id" id="cid" required>
              </div>
              <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" name="nombre" id="nombre" required>
              </div>
              <div class="form-group">
                <label for="apellido">Apellido:</label>
                <input type="text" class="form-control" name="apellido" id="apellido" required>
              </div>
              <div class="form-group">
                <label for="telefono">Teléfono:</label>
                <input type="tel" class="form-control" name="tel" id="tel"
                       pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required>
              </div>
                <!-- Lista de Agentes -->
                <div class="form-group">
                    <label for="agente">Agente:</label>
                    <SELECT name="agente" class="custom-select">
                        <option selected>Seleccione un Agente</option>
                        <%
                        ArrayList<Agente> agentes = 
                                (ArrayList<Agente>) request.getAttribute("AGENTES");
                        
                        for(Agente a : agentes){
                             out.print("<option value='"
                                     +a.getId()+"'>"+a.getNombre()+" "+a.getApellido()+
                                              "</option>");    
                        }
                    %>
                    </SELECT>
                </div>               
                    <br>
              <button type="submit" class="btn btn-info">Agregar</button>
              <button type="reset" class="btn btn-light">Reestablecer</button>
            </form>            
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
