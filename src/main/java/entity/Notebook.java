package entity;

import java.sql.Date;

public class Notebook {

    private Integer id;
    private String serial;
    private String vendor;
    private String model;
    private Date date;
    private Double price;

    public Notebook(Integer id, String serial, String vendor, String model, Date date, Double price) {
        this.id = id;
        this.serial = serial;
        this.vendor = vendor;
        this.model = model;
        this.date = date;
        this.price = price;
    }

    public Notebook(String serial, String vendor, String model, Date date, Double price) {
        this.serial = serial;
        this.vendor = vendor;
        this.model = model;
        this.date = date;
        this.price = price;
    }

    public Notebook() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id=" + id +
                ", serial='" + serial + '\'' +
                ", vendor='" + vendor + '\'' +
                ", model='" + model + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
