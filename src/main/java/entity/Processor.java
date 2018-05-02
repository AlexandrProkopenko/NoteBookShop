package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "processors")
public class Processor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "fraquency")
    private Integer fraquency;

    @OneToMany(mappedBy = "processor")
    private List<Notebook> owners;

    public Processor() {
    }

    public Processor(String title, Integer fraquency) {
        this.title = title;
        this.fraquency = fraquency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFraquency() {
        return fraquency;
    }

    public void setFraquency(Integer fraquency) {
        this.fraquency = fraquency;
    }

    public List<Notebook> getOwners() {
        return owners;
    }

    public void setOwners(List<Notebook> owners) {
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fraquency=" + fraquency +
                '}';
    }
}
