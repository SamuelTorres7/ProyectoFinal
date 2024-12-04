/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto.agencia;

import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@WebServlet(name="SvClientes")
public class SvClientes extends HttpServlet{
    private ModeloClientes mc; // Modelo para manejar operaciones con clientes
    private ModeloAgentes ma; // Modelo para manejar operaciones con agentes

    @Resource(name = "jdbc/Agencia") // Conexión a la base de datos
    private DataSource pool;

    public SvClientes(){
        super();
    }

    /**
     * Método de inicialización del servlet. Se utiliza para configurar los modelos
     * necesarios para manejar clientes y agentes.
     *
     * @throws ServletException si ocurre algún error durante la inicialización.
     */
    @Override
    public void init()throws ServletException{
        super.init();
        try{
            mc = new ModeloClientes(pool);
            ma = new ModeloAgentes(pool);
        }catch (Exception ex){
            throw new ServletException(ex);
        }
    }

    /**
     * Maneja las solicitudes GET y ejecuta acciones según el parámetro "instruccion".
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error de servlet.
     * @throws IOException      si ocurre un error de E/S.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String b = request.getParameter("instruccion");
        if(b == null){
            b = "login";
        }
        switch(b){
            case "home":
                goHome(request, response);
                break;
            case "login":
                login(request, response);
                break;
            case "session":
                iniciarSesion(request, response);
                break;
            case "add":
                addCliente(request, response);
                break;
            case "new":
                newCliente(request, response);
                break;
            case "modify":
                modifyCliente(request, response);
                break;
            case "update":
                updateCliente(request, response);
                break;
            case "delete":
                deleteCliente(request, response);
                break;
            case "logout":
                logout(request,response);
                break;
            default:
                login(request, response);
        }
    }

    /**
     * Redirige a la página principal si el usuario está autenticado.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error de servlet.
     * @throws IOException      si ocurre un error de E/S.
     */
    private void goHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null && Boolean.TRUE.equals(session.getAttribute("isLogged"))){
            try{
                ArrayList<Cliente> clientes = mc.getClientes();
                request.setAttribute("CLIENTES", clientes);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }catch (Exception e){
                mensajeError(e.getMessage(), "error.jsp", request, response);
            }
        }else{
            response.sendRedirect("login.jsp");
        }
    }

    /**
     * Redirige al formulario para editar un cliente específico.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error de servlet.
     * @throws IOException      si ocurre un error de E/S.
     */
    private void modifyCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String idS = request.getParameter("id");
        try{
            int id = Integer.parseInt(idS);
            Cliente c = mc.getCliente(id);
            ArrayList<Agente> agentes = ma.getAgentes();
            request.setAttribute("CLIENTE", c);
            request.setAttribute("AGENTES", agentes);
            request.getRequestDispatcher("update.jsp").forward(request, response);
        }catch(SQLException ex){
            mensajeError(ex.getMessage(), "error.jsp", request, response);
        }
    }

    /**
     * Actualiza la información de un cliente en la base de datos.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error de servlet.
     * @throws IOException      si ocurre un error de E/S.
     */
    private void updateCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        if(session != null && Boolean.TRUE.equals(session.getAttribute("isLogged"))){
            try{
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String tel = request.getParameter("tel");
                int agenteId = Integer.parseInt(request.getParameter("agente"));

                Agente a = ma.getAgente(agenteId);

                Cliente c = new Cliente();
                c.setId(id);
                c.setNombre(nombre);
                c.setApellido(apellido);
                c.setTel(tel);
                c.setAgente(a);

                mc.updateCliente(c);
                goHome(request, response);
            }catch (IllegalArgumentException | SQLException e){
                mensajeError(e.getMessage(), "error.jsp", request, response);
            }
        }else{
            response.sendRedirect("login.jsp");
        }
    }

    /**
     * Redirige al formulario de login.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error de servlet.
     * @throws IOException      si ocurre un error de E/S.
     */
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect("login.jsp");
    }

    /**
     * Redirige al formulario para agregar un nuevo cliente.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error de servlet.
     * @throws IOException      si ocurre un error de E/S.
     */
    private void newCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try{
            ArrayList<Agente> agentes = ma.getAgentes();
            request.setAttribute("AGENTES", agentes);
            request.getRequestDispatcher("newcliente.jsp").forward(request, response);
        }catch(SQLException ex){
            mensajeError(ex.getMessage(), "error.jsp", request, response);
        }catch(Exception ex){
            mensajeError(ex.getMessage(), "error.jsp", request, response);
        }
    }

    /**
     * Agrega un nuevo cliente a la base de datos.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error de servlet.
     * @throws IOException      si ocurre un error de E/S.
     */
    private void addCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        if(session != null && Boolean.TRUE.equals(session.getAttribute("isLogged"))){
            try{
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String tel = request.getParameter("tel");
                int agenteId = Integer.parseInt(request.getParameter("agente"));

                Agente a = ma.getAgente(agenteId);

                Cliente c = new Cliente();
                c.setId(id);
                c.setNombre(nombre);
                c.setApellido(apellido);
                c.setTel(tel);
                c.setAgente(a);

                mc.addCliente(c);
                goHome(request, response);
            }catch (IllegalArgumentException | SQLException e){
                mensajeError(e.getMessage(), "error.jsp", request, response);
            }
        }else{
            response.sendRedirect("login.jsp");
        }
    }

    /**
     * Inicia sesión para el usuario si las credenciales son válidas.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error de servlet.
     * @throws IOException      si ocurre un error de E/S.
     */
    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        ModeloUsuario mu = new ModeloUsuario(pool);
        try{
            if(mu.validateUser(user, password)){
                HttpSession session = request.getSession();
                session.setAttribute("username", user);
                session.setAttribute("isLogged", true);
                response.sendRedirect("SvClientes?instruccion=home");
            }else{
                request.setAttribute("ERROR","error");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }catch(Exception e){
            mensajeError(e.getMessage(), "error.jsp", request, response);
        }
    }

    /**
     * Elimina un cliente de la base de datos.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error de servlet.
     * @throws IOException      si ocurre un error de E/S.
     */
    private void deleteCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String idS = request.getParameter("id");
        try{
            int id = Integer.parseInt(idS);
            mc.deleteCliente(id);
            goHome(request, response);
        }catch (SQLException ex){
            mensajeError(ex.getMessage(), "error.jsp", request, response);
        }
    }
    private void logout(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        session.invalidate();
        response.sendRedirect("login.jsp");
    }

    /**
     * Muestra un mensaje de error y redirige a una página de error.
     *
     * @param mensaje  el mensaje de error.
     * @param destino  la página de destino.
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error de servlet.
     * @throws IOException      si ocurre un error de E/S.
     */
    private void mensajeError(String mensaje, String destino, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("ERROR", mensaje);
        request.getRequestDispatcher(destino).forward(request, response);
    }
    @Override
    public String getServletInfo(){
        return "Short description";
    }

}
