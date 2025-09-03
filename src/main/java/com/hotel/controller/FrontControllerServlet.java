package com.hotel.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        switch (path) {
            case "/buscar.do":
                request.getRequestDispatcher("/BuscarServlet").forward(request, response);
                break;
            case "/reservar.do":
                request.getRequestDispatcher("/ReservaServlet").forward(request, response);
                break;
            case "/login.do":
                request.getRequestDispatcher("/LoginServlet").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
