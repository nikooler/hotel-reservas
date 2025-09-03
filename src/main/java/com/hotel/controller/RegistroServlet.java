package com.hotel.controller;

import com.hotel.dao.UsuarioDAO;
import com.hotel.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;

@WebServlet("/registro.do")
public class RegistroServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validar si el email ya existe
        if (usuarioDAO.buscarPorEmail(email) != null) {
            request.setAttribute("error", "El correo ya está registrado.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        // Cifrar la contraseña antes de guardarla
        String hashedPassword = hashPassword(password);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(hashedPassword);
        usuario.setTelefono(""); // Puedes agregar campo si lo tienes en el formulario

        try {
            usuarioDAO.guardarUsuario(usuario);
            request.setAttribute("mensaje", "Registro exitoso. Ahora puedes iniciar sesión.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar el usuario.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }

    // Método para cifrar la contraseña con SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
