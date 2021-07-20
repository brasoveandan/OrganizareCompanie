package service;

import domain.Angajat;
import domain.Departament;
import domain.Proiect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.AngajatRepository;
import repository.DepartamentRepository;
import repository.ProiectRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ServiceTest {
    AngajatRepository angajatRepository;
    DepartamentRepository departamentRepository;
    ProiectRepository proiectRepository;
    Service service;

    @BeforeEach
    void setUp() {
        angajatRepository = new AngajatRepository();
        departamentRepository = new DepartamentRepository();
        proiectRepository = new ProiectRepository();
        service = new Service();
    }

    @Test
    void calculareSalariuAngajat() {
        Angajat angajat = angajatRepository.findOne(1);
        assertEquals(angajat.getDataAngajare().getYear(), LocalDate.now().getYear());
        assertEquals(2000, service.calculareSalariuAngajat(angajat));
    }

    @Test
    void asignareProiect() {
        Proiect proiect = new Proiect();
        proiect.setId(0);
        proiect.setDataInceput(LocalDate.of(2021, 6, 15));
        proiect.setDeadline(LocalDate.of(2021, 6, 30));
        List<Angajat> angajati = new ArrayList<>();
        angajati.add(angajatRepository.findOne(2));
        int nrProiecte = angajatRepository.findOne(2).getProiecte().size();
        assertEquals(0, angajatRepository.findOne(2).getConcedii().size());
        assertEquals(0, service.asignareProiect(angajati, proiect).size());
        assertEquals(nrProiecte + 1, angajatRepository.findOne(2).getProiecte().size());
    }

    @Test
    void angajatiDepartament() {
        Departament departament = departamentRepository.findOne(1);
        assertEquals(1, service.angajatiDepartament(departament).size());
    }

    @Test
    void proiectPerDepartament() {
        Proiect proiect = proiectRepository.findOne(1);
        assertEquals(1, service.proiectPerDepartament(proiect).size());
    }

    @Test
    void interschimbareAngajati() {
        Angajat angajatNou = new Angajat();
        angajatNou.setDataAngajare(LocalDate.of(2021, 6, 1));
        Angajat angajatVechi = angajatRepository.findOne(1);
        service.interschimbareAngajati(angajatVechi, angajatNou);
        assertEquals(LocalDate.of(2021, 6, 1), angajatRepository.findOne(1).getDataAngajare());
    }
}