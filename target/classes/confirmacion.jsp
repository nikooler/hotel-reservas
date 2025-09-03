<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirmación de Reserva</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header bg-success text-white">
                <h2>¡Reserva Confirmada!</h2>
            </div>
            <div class="card-body">
                <c:if test="${not empty reserva}">
                    <h4>Detalles de la Reserva:</h4>
                    <p><strong>Hotel:</strong> ${reserva.habitacion.hotel.nombre}</p>
                    <p><strong>Habitación:</strong> ${reserva.habitacion.tipo}</p>
                    <p><strong>Check-in:</strong> <fmt:formatDate value="${reserva.fechaEntrada}" pattern="dd/MM/yyyy"/></p>
                    <p><strong>Check-out:</strong> <fmt:formatDate value="${reserva.fechaSalida}" pattern="dd/MM/yyyy"/></p>
                    <p><strong>Total:</strong> $<fmt:formatNumber value="${reserva.total}" pattern="#,##0.00"/></p>
                    <p><strong>Estado:</strong> ${reserva.estado}</p>
                </c:if>
                
                <a href="index.jsp" class="btn btn-primary">Volver al Inicio</a>
            </div>
        </div>
    </div>
</body>
</html>