package servicio;

public class AuthService {
    // Uso de un Arreglo Estático Bidimensional (Matriz) para seguridad básica
    private String[][] usuarios = {
        {"admin", "1234"},
        {"recepcion", "recep123"}
    };
    private boolean logueado = false;

    public boolean iniciarSesion(String user, String pass) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i][0].equals(user) && usuarios[i][1].equals(pass)) {
                logueado = true;
                return true;
            }
        }
        return false;
    }

    public void cerrarSesion() { 
        logueado = false; 
    }
    
    public boolean isLogueado() { 
        return logueado; 
    }
}