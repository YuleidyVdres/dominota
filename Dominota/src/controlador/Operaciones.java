/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import modelo.*;

public class Operaciones {
    public void AgregarUsuario(Jugadores jugador){
      try {
      Equipos equipo_individual=new Equipos(jugador);
      SessionFactory sesion = NewHibernateUtil.getSessionFactory();
      Session session;
      session=sesion.openSession();
      session.beginTransaction();
      session.save(jugador);
      session.save(equipo_individual);
      session.getTransaction().commit();
      session.close();
      JOptionPane.showMessageDialog(null,"Crado correctamente");
      } catch (Exception e) {
              JOptionPane.showMessageDialog(null,"El Juagador ya existe");
	      System. out. println("Usuario ya existe");          
      }
     
    }
    
    public void AgregarEquipo(Equipos equipo){
        try {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session;
            session=sesion.openSession();
            session.beginTransaction();
            session.save(equipo);
            session.getTransaction().commit();
            session.close();
            JOptionPane.showMessageDialog(null,"Equipo creado correctamente");
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null,"El equipo ya exise");
	      System. out. println(e);          
        }
    }
    
    public ArrayList<Jugadores> ConsultarJugadores(){
        ArrayList<Jugadores> jugadores = null;
        try {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session;
            session=sesion.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Jugadores");
            jugadores = (ArrayList<Jugadores>)query.list();
            for(Jugadores jugador : jugadores) {
                System.out.println(jugador.getNombre());
            }
        }
        catch(Exception e) {
            
        }
        return jugadores;
    }
    public void crearPartidaEquipos(Equipos equipo1, Equipos equipo2, int puntos){
        try {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session;
            session=sesion.openSession();
            session.beginTransaction();
            Partidas partida = new Partidas(new Date(),new BigDecimal(puntos));
            PartidosEquipos Partidoequipos1 = new PartidosEquipos(partida, equipo1);
            PartidosEquipos Partidoequipos2 = new PartidosEquipos(partida, equipo2);
            
            session.save(partida);
            session.save(Partidoequipos1);
            session.save(Partidoequipos2);
            session.getTransaction().commit();
            session.close();
            JOptionPane.showMessageDialog(null, "Partida creada con exito!");
        }
        catch(Exception e) {
            System.out.println("error");
        }
    }
    
    public void crearPartidaIndividuales(ArrayList<Equipos> equipos, int puntos){
        try {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session;
            session=sesion.openSession();
            session.beginTransaction();
            Partidas partida = new Partidas(new Date(),new BigDecimal(puntos));
            session.save(partida);
            for (int i = 0; i < equipos.size(); i++) {
                PartidosEquipos Partidoequipos1 = new PartidosEquipos(partida, equipos.get(i));
                session.save(Partidoequipos1);
            }     
            session.getTransaction().commit();
            session.close();
            JOptionPane.showMessageDialog(null, "Partida creada con exito!");
        }
        catch(Exception e) {
            System.out.println("error");
        }
    }
    
    public Jugadores InformacionJugador(String nombre){
        try {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session;
            session=sesion.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Jugadores where nombre =:nombre");
            query.setParameter("nombre", nombre);
            Jugadores jugador = (Jugadores) query.uniqueResult();
            return jugador;
        }
        catch(Exception e) {
            
        }
        return null;
    }
    
    public ArrayList<Equipos> ConsultarEquiposes(){
        ArrayList<Equipos> equipos = null;
        try {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session;
            session=sesion.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Equipos where jugadores_id1 <> null");
            equipos = (ArrayList<Equipos>)query.list();
            for(Equipos equipo : equipos) {
                System.out.println(equipo.getNombre());
            }
        }
        catch(Exception e) {
            
        }
        return equipos;
    }
    
    public ArrayList<Equipos> ConsultarIndividual(){
        ArrayList<Equipos> equipos = null;
        try {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session;
            session=sesion.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Equipos where jugadores_id1 = null");
            equipos = (ArrayList<Equipos>)query.list();
            for(Equipos equipo : equipos) {
                System.out.println(equipo.getNombre());
            }
        }
        catch(Exception e) {
            
        }
        return equipos;
    }
    
    public Equipos ObjetoEquipos(String nombre){
       
        try {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session;
            session=sesion.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Equipos where nombre =?");
            query.setString(0,nombre);
            Equipos equipo= (Equipos) query.uniqueResult();
            return equipo;
        }
        catch(Exception e) {
            System.out.println("Algo salio mal");
            return null;
        }
        
    }
    
    public List<Object[]> PartidasGanadas(){
        List<Object[]> lista = null;
        try {
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session session;
            session=sesion.openSession();
            session.beginTransaction();
            Query query = session.createQuery("select JUGADORES.NOMBRE, sum(num) from \n" +
                                        "(select EQUIPOS.JUGADORES_ID as jugador, count(*) as num FROM partidas, EQUIPOS \n" +
                                        "WHERE partidas.EQUIPOS_ID = EQUIPOS.ID group by EQUIPOS.JUGADORES_ID\n" +
                                        "UNION all\n" +
                                        "select EQUIPOS.JUGADORES_ID1, count(*) FROM partidas, EQUIPOS \n" +
                                        "WHERE partidas.EQUIPOS_ID = EQUIPOS.ID and\n" +
                                        "EQUIPOS.JUGADORES_ID1 is not null group by EQUIPOS.JUGADORES_ID1), \n" +
                                        "JUGADORES WHERE JUGADORES.ID=jugador GROUP BY JUGADORES.NOMBRE");
            lista = query.list();
            for (Object[] datos : lista) {
             System.out.println(datos[0] + "--" + datos[1]);
            }
        }
        catch(Exception e) {
            
        }
        return null;
    }
}
