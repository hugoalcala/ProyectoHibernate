package org.example.main;

import org.example.controladores.ControladorEquipo;
import org.example.controladores.ControladorJugador;
import org.hibernate.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Crear equipo");
            System.out.println("2. Crear jugador");
            System.out.println("3. Buscar equipo");
            System.out.println("4. Buscar jugadores");
            System.out.println("5. Eliminar equipo");
            System.out.println("6. Eliminar jugador");
            System.out.println("7. Editar equipo");
            System.out.println("8. Editar jugador");
            System.out.println("9. Salir");
            System.out.print("Seleciona una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            try{
                switch (opcion) {
                    case 1:
                        ControladorEquipo.guardarEquipo(scanner);
                        break;
                    case 2:
                       ControladorJugador.guardarJugador(scanner);
                        break;

                    case 3:
                        ControladorEquipo.buscarNombreEquipo(scanner);
                        break;
                    case 4:
                       ControladorJugador.buscarJugador(scanner);
                        break;
                    case 5:
                        ControladorEquipo.eliminarEquipo(scanner);
                        break;
                    case 6:
                        ControladorJugador.eliminarJugador(scanner);
                        break;
                    case 7:
                        ControladorEquipo.editarEquipo(scanner);
                        break;
                    case 8:
                        ControladorJugador.editarJugador(scanner);
                        break;
                    case 9:
                        System.out.println("Saliendo");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            }catch (HibernateException e){
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }while (opcion != 5);
        scanner.close();
    }
}