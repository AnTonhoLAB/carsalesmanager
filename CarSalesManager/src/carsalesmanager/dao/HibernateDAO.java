/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsalesmanager.dao;


import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import carsalesmanager.util.HibernateUtil;

public abstract class HibernateDAO<T, ID extends Serializable> implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	Session s;
	Transaction t;
	Query q;
	Criteria c;
	
	T entity;
	
	public HibernateDAO(T entity){
            this.entity = entity;
	}
	
	public void save(T e)throws Exception{
		s = HibernateUtil.getSessionFactory().openSession();
		t = s.beginTransaction();
		s.save(e);
		t.commit();
		s.close();
	}
	public void update(T e)throws Exception{
		s = HibernateUtil.getSessionFactory().openSession();
		t = s.beginTransaction();
		s.update(e);
		t.commit();
		s.close();
	}
	public void delete(T e)throws Exception{
		s = HibernateUtil.getSessionFactory().openSession();
		t = s.beginTransaction();
		s.delete(e);
		t.commit();
		s.close();
	}
	public List<T> findAll(){
		s = HibernateUtil.getSessionFactory().openSession();
		List<T> lista = s.createCriteria(entity.getClass()).list();
		s.close();
		return lista;
	}
        public List<T> findAllWithoutClose(){
		s = HibernateUtil.getSessionFactory().openSession();
		List<T> lista = s.createCriteria(entity.getClass()).list();
		return lista;
	}
        public void closeSession(){
            if (s.isOpen()){
                s.close();
            }
        }
	public T findByCod(ID cod){
		s = HibernateUtil.getSessionFactory().openSession();
		T e = (T) s.get(entity.getClass(), cod);
		s.close();
		return e;
	}
	
}