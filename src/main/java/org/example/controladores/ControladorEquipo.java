package org.example.controladores;

import org.example.mapeo.Equipo;
import org.example.util.HibernateUtil.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class ControladorEquipo {
    public static void guardarEquipo(Scanner scanner)throws HibernateException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            System.out.println("Ingrese el nombre del equipo: ");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el país del equipo: ");
            String pais = scanner.nextLine();
            Equipo equipo = new Equipo(nombre, pais);
            session.save(equipo);
            transaction.commit();
            System.out.println("El equipo se ha guardado correctamente: " + equipo);
        }catch (HibernateException e){
            throw new HibernateException("Error al guardar equipo : " + e.getMessage());
        }
    }
    public static void buscarNombreEquipo(Scanner scanner) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Introduzca el id del equipo: ");
            int id = scanner.nextInt();
            Equipo equipo = session.get(Equipo.class,id);
            if (equipo != null){
                System.out.println("Equipo encontrado: " + equipo.getNombre() + ", Pais " + equipo.getPais());
            }else{
                System.out.println("Equipo no encontrado con el id: " + id);
            }
        } catch (HibernateException e) {
            throw new HibernateException("Error al buscar equipo por nombre", e);
        }
    }
    public static void eliminarEquipo(Scanner scanner) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Introduzca el id del equipo que desea eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Equipo equipo = session.get(Equipo.class,id);
            if (equipo != null){
                session.delete(equipo);
                transaction.commit();
                System.out.println("Equipo eliminado con exito: " + equipo);
            }else {
                System.out.println("Equipo no encontrado con el id: " + id);
            }
        }catch (HibernateException e){
            throw new HibernateException("Error al eliminar equipo por nombre", e);
        }
    }
    public static void editarEquipo(Scanner scanner) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Introduzca el id del equipo que desea editar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Equipo equipo = session.get(Equipo.class,id);
            if (equipo != null){
                System.out.println("Nuevo nombre del equipo: ");
                String nuevoNombre = scanner.nextLine();
                System.out.println("Nuevo pais del equipo: ");
                String nuevoPais = scanner.nextLine();
                equipo.setNombre(nuevoNombre);
                equipo.setPais(nuevoPais);
                session.update(equipo);
                transaction.commit();
                System.out.println("Equipo editado con exito: " + equipo);
            }else {
                System.out.println("Equipo no encontrado con el id: " + id);
            }
        }catch (HibernateException e){
            throw new HibernateException("Error al editar equipo por nombre", e);
        }
    }
}
