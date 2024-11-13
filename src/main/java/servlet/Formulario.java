package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/ingresar")
public class Formulario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Seteamos el tipo de contenido
        response.setContentType("text/html");

        // Obtenemos los valores de los parámetros
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String[] lenguajes = request.getParameterValues("lenguajes");
        String idioma = request.getParameter("idioma");
        String pais = request.getParameter("pais");
        String[] roles = request.getParameterValues("roles");
        boolean habilitar = request.getParameter("Habilitar") != null;

        //Lista

        List<String> errores = new ArrayList<>();

        if (username == null || username.isBlank()) {
            errores.add("El campo username es necesario");
        }
        if (password == null || password.isBlank()) {
            errores.add("El campo password es necesario");
        }
        if (email == null || !email.contains("@")) {
            errores.add("El campo email es necesario que contenga el arroba");
        }
        if (lenguajes == null || lenguajes.length == 0) {
            errores.add("El campo password es necesario");
        }
        if (idioma == null) {
            errores.add("El campo idioma es necesario");
        }
        if (pais == null || pais.equals("")) {
            errores.add("El campo pais es necesario");
        }


        //Validamos que todos lo campos esten llenos y no vacios
p        // Generamos el HTML de respuesta

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Formulario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Manejo de Formulario</h1>");
            if(errores.isEmpty()) {

                out.println("<ul>");
                out.println("<li>Nombre usuario: " + username + "</li>");
                out.println("<li>Password: " + password + "</li>");
                out.println("<li>Email: " + email + "</li>");

                // Mostrar los lenguajes de programación

                out.println("<ul>Lenguajes de programación:");
                Arrays.asList(lenguajes).forEach(lenguaje -> {
                    out.println("<li>" + lenguaje + "</li>");
                });
                out.println("</ul>");

                // Mostrar el idioma seleccionado
                out.println("<li>Idioma seleccionado: " + idioma + "</li>");

                // Mostrar la nacionalidad
                out.println("<li>Nacionalidad: " + pais + "</li>");

                // Mostrar los roles seleccionados

                out.println("<ul>Roles:");
                if (roles != null) {
                    Arrays.asList(roles).forEach(role -> {
                        out.println("<li>" + role + "</li>");
                    });
                }
                out.println("</ul>");

                // Mostrar si está habilitado
                out.println("<li>Habilitar: " + (habilitar ? "Sí" : "No") + "</li>");
                out.println("</ul>");
            }else {
                errores.forEach(error -> {
                    out.println("<li>" + error + "</li>");
                });
                out.println("<p><a href=\"/Formulario/index.html\"> Volver al Index</a></p>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}