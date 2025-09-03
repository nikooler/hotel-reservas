package com.hotel.controller;

import com.hotel.model.Reserva;
import com.hotel.model.Habitacion;
import com.hotel.model.Usuario;
import com.hotel.dao.ReservaDAO;
import com.hotel.dao.HabitacionDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet("/reservar.do")
public class ReservaServlet extends HttpServlet {
    private ReservaDAO reservaDAO;
    private HabitacionDAO habitacionDAO;
    
    @Override
    public void init() {
        reservaDAO = new ReservaDAO();
        habitacionDAO = new HabitacionDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            int habitacionId = Integer.parseInt(request.getParameter("habitacionId"));
            String fechaEntradaStr = request.getParameter("fechaEntrada");
            String fechaSalidaStr = request.getParameter("fechaSalida");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaEntrada = sdf.parse(fechaEntradaStr);
            Date fechaSalida = sdf.parse(fechaSalidaStr);
            
            Habitacion habitacion = habitacionDAO.buscarPorId(habitacionId);
            
            if (habitacion != null && habitacion.isDisponibilidad()) {
                Reserva reserva = new Reserva();
                reserva.setUsuario(usuario);
                reserva.setHabitacion(habitacion);
                reserva.setFechaEntrada(fechaEntrada);
                reserva.setFechaSalida(fechaSalida);
                
                // Calcular total
                long dias = (fechaSalida.getTime() - fechaEntrada.getTime()) / (1000 * 60 * 60 * 24);
                double total = dias * habitacion.getPrecio();
                reserva.setTotal(total);
                reserva.setEstado("Confirmada");
                
                reservaDAO.guardarReserva(reserva);
                
                // Actualizar disponibilidad de la habitación
                habitacion.setDisponibilidad(false);
                habitacionDAO.actualizarHabitacion(habitacion);
                
                request.setAttribute("reserva", reserva);
                request.getRequestDispatcher("confirmacion.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Habitación no disponible");
                request.getRequestDispatcher("resultados.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar la reserva");
            request.getRequestDispatcher("resultados.jsp").forward(request, response);
        }
    }
}
