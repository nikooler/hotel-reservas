
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Hotel Reservas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Búsqueda de Hoteles</h1>
        
        <form action="buscar.do" method="get" class="row g-3">
            <div class="col-md-4">
                <label class="form-label">Ciudad</label>
                <input type="text" name="ciudad" class="form-control" required>
            </div>
            <div class="col-md-3">
                <label class="form-label">Fecha Entrada</label>
                <input type="date" name="fechaEntrada" class="form-control" required>
            </div>
            <div class="col-md-3">
                <label class="form-label">Fecha Salida</label>
                <input type="date" name="fechaSalida" class="form-control" required>
            </div>
            <div class="col-md-2">
                <label class="form-label">&nbsp;</label>
                <button type="submit" class="btn btn-primary w-100">Buscar</button>
            </div>
        </form>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
    </div>
</body>
</html>