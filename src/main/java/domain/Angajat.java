package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "angajati")
public class Angajat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Functie functie;

    @Column(name = "id_superior")
    private Integer idSuperior;

    @Column(name = "data_angajare")
    private LocalDate dataAngajare;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "angajati_departamente",
            joinColumns = {@JoinColumn(name = "id_angajat")},
            inverseJoinColumns = {@JoinColumn(name = "id_departament")}
    )
    private Set<Departament> departamente = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "angajat")
    private Set<Concediu> concedii;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "angajati_proiecte",
            joinColumns = {@JoinColumn(name = "id_angajat")},
            inverseJoinColumns = {@JoinColumn(name = "id_proiect")}
    )
    private Set<Proiect> proiecte;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Functie getFunctie() {
        return functie;
    }

    public void setFunctie(Functie functie) {
        this.functie = functie;
    }

    public Integer getIdSuperior() {
        return idSuperior;
    }

    public void setIdSuperior(Integer idSuperior) {
        this.idSuperior = idSuperior;
    }

    public LocalDate getDataAngajare() {
        return dataAngajare;
    }

    public void setDataAngajare(LocalDate dataAngajare) {
        this.dataAngajare = dataAngajare;
    }

    public Set<Departament> getDepartamente() {
        return departamente;
    }

    public void setDepartamente(Set<Departament> departamente) {
        this.departamente = departamente;
    }

    public Set<Concediu> getConcedii() {
        return concedii;
    }

    public void setConcedii(Set<Concediu> concedii) {
        this.concedii = concedii;
    }

    public Set<Proiect> getProiecte() {
        return proiecte;
    }

    public void setProiecte(Set<Proiect> proiecte) {
        this.proiecte = proiecte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Angajat angajat = (Angajat) o;
        return Objects.equals(id, angajat.id) && Objects.equals(functie, angajat.functie) && Objects.equals(idSuperior, angajat.idSuperior) && Objects.equals(dataAngajare, angajat.dataAngajare) && Objects.equals(departamente, angajat.departamente) && Objects.equals(concedii, angajat.concedii) && Objects.equals(proiecte, angajat.proiecte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, functie, idSuperior, dataAngajare, departamente, concedii, proiecte);
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "id=" + id +
                ", functie=" + functie +
                ", idSuperior=" + idSuperior +
                ", dataAngajare=" + dataAngajare +
                ", departamente=" + departamente +
                ", concedii=" + concedii +
                ", proiecte=" + proiecte +
                '}';
    }
}
