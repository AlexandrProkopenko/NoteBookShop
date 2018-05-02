package service;

import entity.Notebook;
import javafx.collections.ObservableList;


public interface NotebookService {

    boolean save(Notebook notebook);

    Notebook getById(Integer id);

    boolean update(Notebook notebook);

    boolean delete(Notebook notebook);

    ObservableList<Notebook> getAll();
}
