package org.example.controladores;

import org.example.mapeo.Equipo;
import org.example.mapeo.Jugador;
import org.example.util.HibernateUtil.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class ControladorJugador {
    public static void guardarJugador(Scanner scanner)throws HibernateException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
           Transaction transaction = session.beginTransaction();
            System.out.println("Ingrese el nombre del jugador: ");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese la posicion del jugador: ");
            int posicion = scanner.nextInt();
            System.out.println("Ingrese el ID del equipo al que pertenece: ");
            int idEquipo = scanner.nextInt();
            scanner.nextLine();

            Equipo equipo = session.get(Equipo.class, idEquipo);
            if (equipo == null) {
                throw new HibernateException("El equipo no existe");
            }
            Jugador jugador = new Jugador(nombre, posicion, idEquipo);
            session.save(jugador);
            transaction.commit();
            System.out.println("Jugador guardada con exito: " + jugador);
        }catch (HibernateException e){
            throw new HibernateException("Error al guardar Jugador : " + e.getMessage());
        }
    }
    public static void buscarJugador(Scanner scanner) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Introduzca el id del jugador: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Jugador jugador = session.get(Jugador.class,id);
            if (jugador != null){
                System.out.println("Jugador encontrado: " + jugador.getNombre() + ", Posicion " + jugador.getPosicion() + ", Equipo " + jugador.getEquipo());
            }else {
                System.out.println("Jugador no encontrado con el id: " + id);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new HibernateException("Error al buscar jugadores por ID", e);
        }
    }
    public static void eliminarJugador(Scanner scanner) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Introduzca el id del jugador que desea eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Jugador jugador = session.get(Jugador.class,id);
            if (jugador != null){
                session.delete(jugador);
                transaction.commit();
                System.out.println("Jugador eliminado con exito: " + jugador);
            }else {
                System.out.println("Jugador no encontrado con el id: " + id);
            }
        }catch (HibernateException e){
            throw new HibernateException("Error al eliminar jugadores por ID", e);
        }
    }
    public static void editarJugador(Scanner scanner) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Introduzca el ID del jugador que desea editar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Jugador jugador = session.get(Jugador.class,id);
            if (jugador != null){
                System.out.println("Nuevo nombre del jugador: ");
                String nuevoNombre = scanner.nextLine();
                System.out.println("Nueva posicion del jugador: ");
                String nuevoPosicion = scanner.nextLine();
                System.out.println("Nuevo ID de equipo: ");
                int nuevoEquipoId = scanner.nextInt();
                scanner.nextLine();

                Equipo nuevoEquipo = session.get(Equipo.class,id);
                if (nuevoEquipo != null){
                    jugador.setNombre(nuevoNombre);
                    jugador.setPosicion(nuevoPosicion);
                    jugador.setEquipo(nuevoEquipo);
                    session.update(jugador);
                    transaction.commit();
                    System.out.println("Jugador editado con exito: " + jugador);
                }else {
                    System.out.println("Equipo no encontrado con el id: " + id);
                }
            }else {
                System.out.println("Jugador no encontrado con el id: " + id);
            }
        }catch (HibernateException e){
            throw new HibernateException("Error al editar jugadores por ID", e);
        }
    }

}
