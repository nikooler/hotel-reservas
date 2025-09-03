<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Resultados de Búsqueda</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Hoteles en ${param.ciudad}</h2>
        
        <c:forEach var="hotel" items="${hoteles}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${hotel.nombre} ⭐${hotel.estrellas}</h5>
                    <p class="card-text">${hotel.direccion}</p>
                    
                    <h6>Habitaciones Disponibles:</h6>
                    <c:forEach var="habitacion" items="${hotel.habitaciones}">
                        <c:if test="${habitacion.disponibilidad}">
                            <div class="d-flex justify-content-between align-items-center mb-2">
                                <div>
                                    <strong>${habitacion.tipo}</strong> - 
                                    Capacidad: ${habitacion.capacidad} personas
                                </div>
                                <div>
                                    <span class="text-success">$${habitacion.precio}/noche</span>
                                    
                                    <!-- ✅ Formulario POST para reservar -->
                                   <form action="reservar.do" method="POST" class="d-inline">
                                      <input type="hidden" name="habitacionId" value="${habitacion.id}" />
                                      <input type="hidden" name="fechaEntrada" value="${param.fechaEntrada}" />
                                      <input type="hidden" name="fechaSalida" value="${param.fechaSalida}" />
                                      <button type="submit" class="btn btn-sm btn-primary ms-2">Reservar</button>
                                   </form>

                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
