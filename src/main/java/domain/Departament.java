package domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "departamente")
public class Departament {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_director")
    private Integer idDirector;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Integer idDirector) {
        this.idDirector = idDirector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departament that = (Departament) o;
        return Objects.equals(id, that.id) && Objects.equals(idDirector, that.idDirector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idDirector);
    }

    @Override
    public String toString() {
        return "Departament{" +
                "id=" + id +
                ", idDirector=" + idDirector +
                '}';
    }
}
