package entity;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "notebooks")
public class Notebook {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "serial")
    private String serial;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "model")
    private String model;

//    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "processor_id")
    private Processor processor;

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

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
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
