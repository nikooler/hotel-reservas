package com.hotel.controller;

import com.hotel.model.Usuario;
import com.hotel.dao.UsuarioDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.MessageDigest;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;
    
    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = hashPassword(request.getParameter("password"));
        
        Usuario usuario = usuarioDAO.validarUsuario(email, password);
        
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("error", "Credenciales inválidas");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
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
