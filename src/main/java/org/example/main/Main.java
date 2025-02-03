package org.example.main;

import org.example.mapeo.Equipo;
import org.example.mapeo.Jugador;
import org.example.util.HibernateUtil.DatabaseException;
import org.example.util.HibernateUtil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Crear equipo");
            System.out.println("2. Crear jugador");
            System.out.println("3. Salir");
            System.out.print("Seleciona una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            try{
                switch (opcion) {
                    case 1:
                        System.out.println("Introduzca el nombre del equipo: ");
                        String nombreEquipo = scanner.nextLine();
                        System.out.println("Introduzca el pais del equipo: ");
                        String paisEquipo = scanner.nextLine();
                        guardarEquipo(nombreEquipo, paisEquipo);
                        break;
                    case 2:
                        System.out.print("Introduzca el nombre del jugador: ");
                        String nombreJugador = scanner.nextLine();
                        System.out.print("Introduzca la posición del jugador: ");
                        String posicionJugador = scanner.nextLine();
                        System.out.print("Introduzca el ID del equipo al que pertenece: ");
                        int equipoId = scanner.nextInt();
                        scanner.nextLine();
                        guardarJugador(nombreJugador, posicionJugador, equipoId);
                        break;
                    case 3:
                        System.out.println("Saliendo");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            }catch (DatabaseException e){
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }while (opcion != 3);
        scanner.close();
    }
    private static void guardarEquipo(String nombre, String paisEquipo){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Equipo equipo = new Equipo(nombre, paisEquipo);
            session.save(equipo);
            transaction.commit();
            System.out.println("Equipo guardada con exito: " + equipo);
        }catch (Exception e){
            throw new DatabaseException("Error al guardar equipo : " + e.getMessage());
        }
    }
    private static void guardarJugador(String nombre, String posicionJugador, int equipoId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Equipo equipo = session.get(Equipo.class, equipoId);
            if (equipo == null){
                throw new DatabaseException("No existe un equipo con ese id: " + equipoId);
            }
            Jugador jugador = new Jugador(nombre, posicionJugador, equipo);
            session.save(jugador);
            transaction.commit();
            System.out.println("Jugador guardada con exito: " + jugador);
        }catch (Exception e){
            throw new DatabaseException("Error al guardar Jugador : " + e.getMessage());
        }
    }
}