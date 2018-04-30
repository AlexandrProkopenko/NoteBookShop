package service.jdbc;

import dao.jdbc.DAOjdbc;
import entity.Notebook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;


public class NBService {

    private DAOjdbc daoJDBC = new DAOjdbc();

    public boolean create(Notebook notebook){
        if (notebook!=null)
            return daoJDBC.create(notebook);
        else
            return false;
    }

    public Notebook read(Integer id){
        if (id!=null)
            return daoJDBC.read(id);
        else
            return null;
    }

    public boolean update(Notebook notebook){
        if (notebook!=null)
            return daoJDBC.update(notebook);
        else
            return false;
    }

    public boolean delete(Notebook notebook){
        if (notebook!=null)
            return daoJDBC.delete(notebook);
        else
            return false;
    }



    public ObservableList<Notebook> getAll(){
        ObservableList result = (ObservableList) daoJDBC.getAll();
        if (result == null)
            return FXCollections.observableArrayList();
        return result;
    }

    public ObservableList<Notebook> findByModel(String model){

        if(model.equals(""))
            return getAll();

        return (ObservableList)daoJDBC.findByModel(model);
    }

    public ObservableList<Notebook> findByVendor(String vendor){

        if(vendor.equals(""))
            return getAll();

        return (ObservableList)daoJDBC.findByVendor(vendor);
    }

    public ObservableList<Notebook> findByPriceAndYear(Double price, Date date){
        ObservableList<Notebook> result = FXCollections.observableArrayList();

        if(price==null || date == null)
            return result;

        return (ObservableList)daoJDBC.findByPriceAndYear(price, date);
    }

    public ObservableList<Notebook> findBetweenPriceLtDateBtVendor(Double priceFrom, Double priceTo, Date date, String vendor){
        ObservableList<Notebook> result = FXCollections.observableArrayList();

        if(priceFrom==null || priceTo == null || date == null || vendor.equals(""))
            return result;

        System.out.println("inService");
        return (ObservableList)daoJDBC.findBetweenPriceLtDateBtVendor(priceFrom, priceTo, date, vendor);
    }

    public ObservableList<Notebook> findBetweenDate(Date dateFrom, Date dateTo){
        ObservableList<Notebook> result = FXCollections.observableArrayList();

        if(dateFrom==null || dateTo == null)
            return result;

        System.out.println("inService");
        return (ObservableList)daoJDBC.findBetweenDate(dateFrom, dateTo);
    }
}
