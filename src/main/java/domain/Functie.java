package domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "functii")
public class Functie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "salar_de_baza")
    private float salarDeBaza;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getSalarDeBaza() {
        return salarDeBaza;
    }

    public void setSalarDeBaza(float salarDeBaza) {
        this.salarDeBaza = salarDeBaza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Functie functie = (Functie) o;
        return Float.compare(functie.salarDeBaza, salarDeBaza) == 0 && Objects.equals(id, functie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salarDeBaza);
    }

    @Override
    public String toString() {
        return "Functie{" +
                "idFunctie='" + id + '\'' +
                ", salarDeBaza=" + salarDeBaza +
                '}';
    }
}
