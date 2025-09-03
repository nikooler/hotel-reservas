package com.hotel.controller;

import com.hotel.model.Hotel;
import com.hotel.dao.HotelDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/buscar.do")
public class BuscarServlet extends HttpServlet {
    private HotelDAO hotelDAO;
    
    @Override
    public void init() throws ServletException {
        hotelDAO = new HotelDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String ciudad = request.getParameter("ciudad");
        String fechaEntrada = request.getParameter("fechaEntrada");
        String fechaSalida = request.getParameter("fechaSalida");
        
        List<Hotel> hoteles = hotelDAO.buscarHoteles(ciudad);
        
        request.setAttribute("hoteles", hoteles);
        request.setAttribute("fechaEntrada", fechaEntrada);
        request.setAttribute("fechaSalida", fechaSalida);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("resultados.jsp");
        dispatcher.forward(request, response);
    }
}