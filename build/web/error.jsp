<%-- 
    Document   : error
    Created on : 1 dic 2024, 7:00:46 p.m.
    Author     : edreh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
                rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet">
    </head>
    <body class="container">
        <div class="container">
            <div class="row">
                <div class="col">
                    <h1 id="error">Error</h1>
                    <p>
                    <% 
                        String mensaje = (String) request.getAttribute("mensaje"); 
                        out.print("<span class='error'>"+ mensaje+"</span>"); 
                    %>                    
                    </p>
                    <input type="button" class="btn btn-info" onclick="history.back()" value="Regresar">
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>    
</html>
