package domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "proiecte")
public class Proiect {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_inceput")
    private LocalDate dataInceput;

    @Column(name = "deadline")
    private LocalDate deadline;

//    @ManyToMany(mappedBy = "proiecte")
//    Set<Angajat> angajati;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(LocalDate dataInceput) {
        this.dataInceput = dataInceput;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proiect proiect = (Proiect) o;
        return Objects.equals(id, proiect.id) && Objects.equals(dataInceput, proiect.dataInceput) && Objects.equals(deadline, proiect.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataInceput, deadline);
    }

    @Override
    public String toString() {
        return "Proiect{" +
                "id=" + id +
                ", dataInceput=" + dataInceput +
                ", deadline=" + deadline +
                '}';
    }
}
