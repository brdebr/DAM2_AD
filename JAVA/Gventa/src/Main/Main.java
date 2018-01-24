/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GUI.V_Principal;
import Hibernate.Classes.Pedido;
import Hibernate.HibernateUtil;
import java.util.List;
import java.util.logging.Level;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author brybre
 */
public class Main {

    public static void main(String[] args) {

//        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
//        
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//             
//        session.beginTransaction();
//        
//        Query query = session.createQuery("from Pedido");
//        
//        List<Pedido> lista =query.list();
//        
//        for (int i = 0; i < lista.size(); i++) {
//            Pedido pedido = lista.get(i);
//            System.out.println(pedido);
//        }
//        
//        session.close();
//        HibernateUtil.getSessionFactory().close();
        V_Principal principal = new V_Principal();
        principal.setVisible(true);

    }
}
