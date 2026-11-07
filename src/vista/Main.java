package vista;

import modelo.*;
import servicio.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService();
        TallerService taller = new TallerService();

        System.out.println("=== SISTEMA DE TALLER AUTOMOTRIZ ===");
        System.out.print("Usuario: "); 
        String u = sc.nextLine();
        System.out.print("Clave: "); 
        String p = sc.nextLine();

        if (!auth.iniciarSesion(u, p)) {
            System.out.println("Credenciales incorrectas. El sistema se cerrará.");
            return;
        }

        String opcion = "";
        while (!opcion.equals("8")) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Registrar Vehículo");
            System.out.println("3. Consultar Inventario de Repuestos"); // NUEVA OPCIÓN
            System.out.println("4. Crear Orden de Trabajo (con Repuestos)"); // MEJORADA
            System.out.println("5. Ver Vehículos");
            System.out.println("6. Reporte de Mecánicos");
            System.out.println("7. Ver Total Órdenes");
            System.out.println("8. Cerrar Sesión y Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextLine();

            if (opcion.equals("1")) {
                System.out.print("ID: "); String id = sc.nextLine();
                System.out.print("Nombre: "); String nom = sc.nextLine();
                System.out.print("Dirección: "); String dir = sc.nextLine();
                taller.registrarCliente(new Cliente(id, nom, "0990000000", dir));
                System.out.println("Cliente registrado exitosamente.");
            } 
            else if (opcion.equals("2")) {
                System.out.print("Placa: "); String pl = sc.nextLine();
                System.out.print("Marca: "); String mc = sc.nextLine();
                System.out.print("Tipo (1=Auto, 2=Moto): "); String tipo = sc.nextLine();
                
                Vehiculo v = null;
                if (tipo.equals("1")) {
                    v = new Auto(pl, mc, "Estándar", 4);
                } else if (tipo.equals("2")) {
                    v = new Moto(pl, mc, "Estándar", 150);
                }
                
                if (v != null) { 
                    taller.registrarVehiculo(v); 
                    System.out.println("Vehículo registrado exitosamente."); 
                } else {
                    System.out.println("Opción de vehículo no válida.");
                }
            }
            else if (opcion.equals("3")) {
                System.out.println("\n--- INVENTARIO DE REPUESTOS ---");
                System.out.println("Código | Nombre | Precio | Stock Disponible");
                for (Repuesto r : taller.getRepuestos()) {
                    System.out.println(r.getCodigo() + "    | " + r.getNombre() + " | $" + r.getPrecio() + " | " + r.getStock() + " unidades");
                }
            }
            else if (opcion.equals("4")) {
                System.out.print("ID del Cliente registrado: "); 
                Cliente c = taller.buscarCliente(sc.nextLine());
                
                System.out.print("Placa del Vehículo registrado: "); 
                Vehiculo v = taller.buscarVehiculo(sc.nextLine());
                
                if (c != null && v != null) {
                    Mecanico m = taller.getMecanico(0); 
                    taller.registrarOrden(c, v, m);
                    
                    // Lógica agregada para usar repuestos
                    System.out.println("Orden creada. Mecánico " + m.getNombre() + " asignado.");
                    System.out.print("¿Desea agregar el repuesto 'R01' (Filtro)? (S/N): ");
                    String usaRepuesto = sc.nextLine();
                    
                    if(usaRepuesto.equalsIgnoreCase("S")) {
                        Repuesto rep = taller.buscarRepuesto("R01");
                        if(rep != null && rep.getStock() > 0) {
                            // Obtenemos la última orden creada
                            OrdenTrabajo ultimaOrden = taller.getOrdenes().get(taller.getOrdenes().size() - 1);
                            ultimaOrden.agregarRepuesto(rep);
                            rep.reducirStock(1); // Baja el stock (Unboxing)
                            System.out.println("Repuesto agregado. El stock de '" + rep.getNombre() + "' bajó a " + rep.getStock() + ".");
                        } else {
                            System.out.println("No hay stock suficiente del repuesto.");
                        }
                    }
                } else { 
                    System.out.println("Error: Cliente o Vehículo no encontrados."); 
                }
            }
            else if (opcion.equals("5")) {
                System.out.println("\n--- LISTA DE VEHÍCULOS ---");
                for (Vehiculo v : taller.getVehiculos()) {
                    if (v instanceof Auto) {
                        Auto a = (Auto) v;
                        System.out.println("Auto -> Placa: " + a.getPlaca() + " | Puertas: " + a.getNumPuertas());
                    } else if (v instanceof Moto) {
                        Moto m = (Moto) v;
                        System.out.println("Moto -> Placa: " + m.getPlaca() + " | Cilindraje: " + m.getCilindraje() + "cc");
                    }
                }
            }
            else if (opcion.equals("6")) {
                System.out.println("\n--- REPORTE DE MECÁNICOS ---");
                String[][] reporte = taller.generarReporteMecanicos();
                System.out.println("Nombre       | Especialidad | Disponible");
                System.out.println("----------------------------------------");
                for (int i = 0; i < reporte.length; i++) {
                    System.out.println(reporte[i][0] + " | " + reporte[i][1] + "        | " + reporte[i][2]);
                }
            }
            
            else if (opcion.equals("7")) {
                System.out.println("\n--- ÓRDENES DE TRABAJO ---");
                for (OrdenTrabajo ot : taller.getOrdenes()) {
                    System.out.println("Orden #" + ot.getId() + " | Vehículo: " + ot.getVehiculo().getPlaca() + " | Total a pagar: $" + ot.calcularSubtotal());
                }
            }
        }
        System.out.println("Sesión finalizada correctamente.");
    }
}