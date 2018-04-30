package dao.hibernate;

import dao.NotebookDAO;
import entity.Notebook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class NotebookDAOImpl implements NotebookDAO {

    private SessionFactory factory;

    public NotebookDAOImpl(){
        factory = HibernateUtil.getFactory();
    }

    @Override
    public boolean create(Notebook notebook) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.save(notebook);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public Notebook read(Integer id) {
        Session session = factory.openSession();
        try {
            Notebook notebook = session.get(Notebook.class, id);
            return notebook;
        }catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean update(Notebook notebook) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(notebook);
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Notebook notebook) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(notebook);
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public ObservableList<Notebook> getAll() {
        Session session = factory.openSession();
        try {
//            session.beginTransaction();
            ObservableList<Notebook> list = FXCollections.observableArrayList();
            list.addAll( session.createQuery("FROM Notebook").list());

            return list;
        }catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return null;
    }
}
