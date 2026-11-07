package modelo;

public class Mecanico extends Persona {
    private String especialidad;
    private boolean disponible; // Tipo primitivo que luego usaremos con Wrappers si es necesario

    public Mecanico(String id, String nombre, String telefono, String especialidad) {
        super(id, nombre, telefono);
        this.especialidad = especialidad;
        this.disponible = true; // Por defecto está disponible al registrarse
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    
    @Override
    public String obtenerTipo() { 
        return "Mecánico"; 
    }
}