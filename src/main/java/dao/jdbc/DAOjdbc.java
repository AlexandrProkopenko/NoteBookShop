package dao.jdbc;

import dao.NotebookDAO;
import entity.Notebook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class DAOjdbc implements NotebookDAO {

    private static String url = "jdbc:mysql://localhost:3306/notebookshop ?serverTimezone=UTC&useSSL=false";
    private static String login = "root";
    private static String password = "1111";
    private Logger log = LoggerFactory.getLogger(NotebookDAO.class);

    public boolean create(Notebook notebook) {
        try (Connection c = DriverManager.getConnection(
                url, login, password)){

            PreparedStatement statement = c.prepareStatement(
                    "INSERT INTO notebookshop.notebooks(serial, vendor, model, date, price)" +
                            "VALUES (?, ?, ?, ?, ?)"
            );


            statement.setString(1, notebook.getSerial());
            statement.setString(2, notebook.getVendor());
            statement.setString(3, notebook.getModel());
            statement.setDate(4, notebook.getDate());
            statement.setDouble(5, notebook.getPrice());

            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("new notebook created");
        return false;
    }

    public Notebook read(Integer id) {
        Notebook notebook = new Notebook();
        try (Connection c = DriverManager.getConnection(
                url, login, password)){

            PreparedStatement statement = c.prepareStatement(
                    "select * from notebookshop.notebooks WHERE ID = ?"
            );

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                notebook.setId(resultSet.getInt(1));
                notebook.setSerial(resultSet.getString(2));
                notebook.setVendor(resultSet.getString(3));
                notebook.setModel(resultSet.getString(4));
                notebook.setDate(resultSet.getDate(5));
                notebook.setPrice(resultSet.getDouble(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("notebook has been read");
        return notebook;
    }

    public boolean update(Notebook notebook) {
        try (Connection c = DriverManager.getConnection(
                url, login, password)){

            PreparedStatement statement = c.prepareStatement(
                    "UPDATE notebookshop.notebooks SET serial = ?, vendor = ?," +
                            " model = ?, date = ?, price = ?" +
                            " WHERE id = ?"
            );

            statement.setString(1, notebook.getSerial());
            statement.setString(2, notebook.getVendor());
            statement.setString(3, notebook.getModel());
            statement.setDate(4, notebook.getDate());
            statement.setDouble(5, notebook.getPrice());
            statement.setInt(6, notebook.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        log.info("update success");
        return true;
    }

    public boolean delete(Notebook notebook) {
        try (Connection c = DriverManager.getConnection(
                url, login, password)){
            PreparedStatement statement = c.prepareStatement(
                    "DELETE FROM notebookshop.notebooks WHERE ID = ?"
            );
            statement.setInt(1,notebook.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        log.info("delete success");
        return true;
    }

    public ObservableList<Notebook> getAll() {
        ObservableList<Notebook> list = FXCollections.observableArrayList();

        try (Connection c =
                     DriverManager.getConnection(url,login, password)){
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM notebookshop.notebooks"
            );

            while (resultSet.next()){
                list.add(new Notebook(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getDouble(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Notebook> findByModel(String model){
        ObservableList<Notebook> list = FXCollections.observableArrayList();
        try(Connection c = DriverManager.getConnection(url, login, password)) {
            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM notebookshop.notebooks WHERE model= ?");

            statement.setString(1,model);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Notebook(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getDouble(6)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Notebook> findByVendor(String vendor){
        ObservableList<Notebook> list = FXCollections.observableArrayList();
        try(Connection c = DriverManager.getConnection(url, login, password)) {
            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM notebookshop.notebooks WHERE vendor= ?");

            statement.setString(1,vendor);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Notebook(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getDouble(6)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Notebook> findByPriceAndYear(Double price, Date date){
        ObservableList<Notebook> list = FXCollections.observableArrayList();
        try(Connection c = DriverManager.getConnection(url, login, password)) {
            PreparedStatement statement = c.prepareStatement(
                    "SELECT * FROM notebookshop.notebooks WHERE price= ? AND YEAR (notebookshop.notebooks.date) = ?");

            statement.setDouble(1,price);
            statement.setInt(2,date.toLocalDate().getYear());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Notebook(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getDouble(6)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Notebook> findBetweenPriceLtDateBtVendor(Double priceFrom, Double priceTo, Date date, String vendor){
        ObservableList<Notebook> list = FXCollections.observableArrayList();
        try(Connection c = DriverManager.getConnection(url, login, password)) {
            PreparedStatement statement = c.prepareStatement(
                        "SELECT * " +
                             "FROM notebookshop.notebooks  "+
                             "WHERE  vendor = ? " +
                             "AND date <= ? " +
                             "AND price BETWEEN ? AND ?"
                        );
            statement.setString(1,vendor);
            System.out.println(vendor);
            statement.setDate(2, date);
            System.out.println(date);
            statement.setDouble(3,priceFrom);
            System.out.println(priceFrom);
            statement.setDouble(4,priceTo);
            System.out.println(priceTo);




            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Notebook(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getDouble(6)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("inDao");
        for(Notebook n: list){
            System.out.println(n);
            System.out.println("ddd");
        }
        return list;
    }

    public List<Notebook> findBetweenDate(Date dateFrom, Date dateTo){
        ObservableList<Notebook> list = FXCollections.observableArrayList();
        try(Connection c = DriverManager.getConnection(url, login, password)) {
            PreparedStatement statement = c.prepareStatement(
                    "SELECT * " +
                            "FROM notebookshop.notebooks  "+
                            "WHERE date  > ? AND  date < ?"
            );

            statement.setDate(1, dateFrom);
            statement.setDate(2, dateTo);





            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Notebook(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getDouble(6)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("inDao");
        for(Notebook n: list){
            System.out.println(n);
            System.out.println("ddd");
        }
        return list;
    }

}
