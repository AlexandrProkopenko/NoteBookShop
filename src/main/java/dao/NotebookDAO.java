package dao;

import entity.Notebook;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public interface NotebookDAO {

    boolean create(Notebook notebook);

    Notebook read(Integer id);

    boolean update(Notebook notebook);

    boolean delete(Notebook notebook);

    ObservableList<Notebook> getAll();
}
