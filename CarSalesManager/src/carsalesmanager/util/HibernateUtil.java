/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.util;

/**
 *
 * @author george
 */
import java.util.List;
//import gestaodeveiculos.model.Acessorio;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Rodrigo
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    
    
    
//    public static void persistPerson(Acessorio p) 
//            throws Exception
//    {
//         SessionFactory session = getSessionFactory();
//         Session ss = session.openSession();
//         Transaction trans = ss.beginTransaction();
//         try
//         {  //persiste na tabela Endereço
//           // ss.save(p.getEndereco());
//            //persiste na tabela Pessoa
//          //  ss.save(p);
//
//            //efetiva a transação
//            trans.commit();
//         }catch(Exception ex)
//         {
//             trans.rollback();
//             throw ex;
//         }
//         finally
//         {
//            trans = null;
//            ss.close();
//         }
//         
//    }
//    public static List<Acessorio> listarPessoas()
//    {
//        SessionFactory sf = getSessionFactory();
//        
//        Session ss = sf.openSession();
//        Criteria crit = ss.createCriteria(Acessorio.class);
//        
//        return (List<Acessorio>)crit.list();
//        
//    }
//    public static List<Acessorio> listarPessoasNome()
//    {
//        SessionFactory sf = getSessionFactory();
//        
//        Session ss = sf.openSession();
//        Criteria crit = ss.createCriteria(Acessorio.class);
//        crit.add(Restrictions.like("nome", "Rodrigo%"));
//        //crit.add(Restrictions.eq("nome", "Rodrigo Figueira"));
//        
//        return (List<Acessorio>)crit.list();
//        
//    }
    
}
