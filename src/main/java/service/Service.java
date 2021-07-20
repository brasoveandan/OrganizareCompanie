package service;

import domain.Angajat;
import domain.Concediu;
import domain.Departament;
import domain.Proiect;
import repository.AngajatRepository;
import repository.DepartamentRepository;
import repository.ProiectRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Service {
    AngajatRepository angajatRepository = new AngajatRepository();
    DepartamentRepository departamentRepository = new DepartamentRepository();
    ProiectRepository proiectRepository = new ProiectRepository();

    public float calculareSalariuAngajat(Angajat angajat) {
        if (angajat != null ) {
            float salar;
            int numarAni = (LocalDate.now().getYear() - angajat.getDataAngajare().getYear() + 1);
            salar = angajat.getFunctie() != null ? numarAni * angajat.getFunctie().getSalarDeBaza() : -1;
            return salar;
        }
        else throw new NullPointerException();
    }

    public List<Angajat> asignareProiect(List<Angajat> angajati, Proiect proiect) {
        List<Angajat> angajatiIndisponibili = new ArrayList<>();
        if (proiectRepository.findOne(proiect.getId()) == null)
            proiectRepository.save(proiect);
        angajati.forEach(angajat -> {
            if (angajat.getConcedii().isEmpty()) {
                Set<Proiect> proiecte = angajat.getProiecte();
                proiecte.add(proiect);
                angajat.setProiecte(proiecte);
                angajatRepository.update(angajat);
            }
            for (Concediu concediu : angajat.getConcedii())
                if (concediu.getDataSfarsit().isBefore(proiect.getDataInceput()) || concediu.getDataInceput().isAfter(proiect.getDeadline())) {
                    angajatiIndisponibili.add(angajat);
                    break;
                }
                else {
                    Set<Proiect> proiecte = angajat.getProiecte();
                    proiecte.add(proiect);
                    angajat.setProiecte(proiecte);
                    angajatRepository.update(angajat);
                    break;
                }
        });
        return angajatiIndisponibili;
    }

    public List<Angajat> angajatiDepartament(Departament departament) {
        List<Angajat> angajati = new ArrayList<>();
        angajatRepository.findAll().forEach(angajat -> {
            if (angajat.getDepartamente().contains(departament))
                angajati.add(angajat);
        });
        return angajati;
    }

    public List<Departament> proiectPerDepartament(Proiect proiect) {
        List<Departament> departamente = new ArrayList<>();
        for (Departament departament : departamentRepository.findAll())
            for (Angajat angajat : angajatRepository.findAll())
                if (angajat.getDepartamente().contains(departament) && angajat.getProiecte().contains(proiect)) {
                    departamente.add(departament);
                    break;
                }
        return departamente;
    }

    public void interschimbareAngajati(Angajat angajatVechi, Angajat angajatNou) {
        if (angajatVechi != null) {
            angajatNou.setId(angajatVechi.getId());
            angajatNou.setIdSuperior(angajatVechi.getIdSuperior());
            angajatNou.setProiecte(angajatVechi.getProiecte());
            angajatNou.setDepartamente(angajatVechi.getDepartamente());
            angajatNou.setFunctie(angajatVechi.getFunctie());
            angajatRepository.update(angajatNou);
        }
        else throw new NullPointerException();
    }
}
