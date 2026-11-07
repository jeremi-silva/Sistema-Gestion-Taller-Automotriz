package modelo;

public class Cliente extends Persona {
    private String direccion;

    public Cliente(String id, String nombre, String telefono, String direccion) {
        super(id, nombre, telefono); // Llama al constructor de la superclase Persona
        this.direccion = direccion;
    }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    
    @Override
    public String obtenerTipo() { 
        return "Cliente"; 
    }
}
