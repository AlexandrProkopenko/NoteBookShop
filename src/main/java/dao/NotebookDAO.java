package dao;

import entity.Notebook;

import java.util.List;

public interface NotebookDAO {

    Integer create(Notebook notebook);

    Notebook read(Integer id);

    boolean update(Notebook notebook);

    boolean delete(Notebook notebook);

    List<Notebook> getAll();
}
