package service.hibernate;

import dao.NotebookDAO;
import dao.hibernate.NotebookDAOImpl;
import entity.Notebook;
import javafx.collections.ObservableList;
import service.NotebookService;


public class NotebookServiceImpl implements NotebookService {

    private NotebookDAO dao;

    public NotebookServiceImpl(){
        dao = new NotebookDAOImpl();
    }


    @Override
    public boolean save(Notebook notebook) {
        return dao.create(notebook);
    }

    @Override
    public Notebook getById(Integer id) {
        return dao.read(id);
    }

    @Override
    public boolean update(Notebook notebook) {
        return dao.update(notebook);
    }

    @Override
    public boolean delete(Notebook notebook) {
        return dao.delete(notebook);
    }

    @Override
    public ObservableList<Notebook> getAll() {
        return  dao.getAll();
    }
}
