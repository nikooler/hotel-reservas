<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro - Hotel Reservas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h2 class="text-center">Registro de Usuario</h2>
                
                <form action="registro.do" method="post">
                    <div class="mb-3">
                        <label class="form-label">Nombre completo</label>
                        <input type="text" name="nombre" class="form-control" required>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Correo electrónico</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Contraseña</label>
                        <input type="password" name="password" class="form-control" required>
                    </div>
                    
                    <button type="submit" class="btn btn-success w-100">Registrarse</button>
                </form>
                
                <div class="text-center mt-3">
                    <a href="login.jsp">¿Ya tienes cuenta? Inicia sesión</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
