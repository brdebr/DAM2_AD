/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Hibernate.Clases.Articulo;
import Hibernate.Clases.Categoria;
import Hibernate.NewHibernateUtil;
import java.math.BigDecimal;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author brybre
 */
public class Principal {
    public static void main(String[] args) {
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        
        //session.save(new Articulo("ADAD", new BigDecimal(23.4), new Long(5)));
        //session.save(new Categoria("cat faaaaa"));
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Intruduce nombre categoria : ");
        String cate = scanner.nextLine();
        session.save(new Categoria(cate));
        
        transaction.commit();
        
        NewHibernateUtil.getSessionFactory().close();
        //session.close();
        
    }
}
