package service;

import domain.Angajat;
import domain.Concediu;
import domain.Departament;
import domain.Proiect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repository.AngajatRepository;
import repository.DepartamentRepository;
import repository.ProiectRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class RestServices {
    AngajatRepository angajatRepository = new AngajatRepository();
    DepartamentRepository departamentRepository = new DepartamentRepository();
    ProiectRepository proiectRepository = new ProiectRepository();

    @GetMapping("/salar")
    public ResponseEntity<Float> calculareSalariuAngajat(@RequestBody Angajat angajat) {
        if (angajat != null) {
            float salar;
            int numarAni = (LocalDate.now().getYear() - angajat.getDataAngajare().getYear() + 1);
            salar = angajat.getFunctie() != null ? numarAni * angajat.getFunctie().getSalarDeBaza() : -1;
            return new ResponseEntity<>(salar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @PostMapping("/asignareProiect/{idProiect}")
    public ResponseEntity<List<Angajat>> asignareProiect(@RequestBody List<Angajat> angajati, @PathVariable Integer idProiect) {
        List<Angajat> angajatiIndisponibili = new ArrayList<>();
        Proiect proiect = proiectRepository.findOne(idProiect);
        for (Angajat angajat : angajati) {
            if (angajat.getConcedii().isEmpty()) {
                Set<Proiect> proiecte = angajat.getProiecte();
                proiecte.add(proiect);
                angajat.setProiecte(proiecte);
                angajatRepository.update(angajat);
            }
            for (Concediu concediu : angajat.getConcedii())
                if (concediu.getDataSfarsit().isBefore(proiect.getDataInceput()) || concediu.getDataInceput().isAfter(proiect.getDeadline())) {
                    Set<Proiect> proiecte = angajat.getProiecte();
                    proiecte.add(proiect);
                    angajat.setProiecte(proiecte);
                    angajatRepository.update(angajat);
                    break;
                } else {
                    angajatiIndisponibili.add(angajat);
                    break;
                }
        }
        return new ResponseEntity<>(angajatiIndisponibili, HttpStatus.OK);
    }

    @GetMapping("/angajati/departament")
    public ResponseEntity<List<Angajat>> angajatiDepartament(@RequestBody Departament departament) {
        List<Angajat> angajati = new ArrayList<>();
        angajatRepository.findAll().forEach(angajat -> {
            if (angajat.getDepartamente().contains(departament))
                angajati.add(angajat);
        });
        return new ResponseEntity<>(angajati, HttpStatus.OK);
    }

    //revizuire
    @GetMapping("/proiect/departament")
    public ResponseEntity<List<Departament>> proiectPerDepartament(@RequestBody Proiect proiect) {
        List<Departament> departamente = new ArrayList<>();
        for (Departament departament : departamentRepository.findAll())
            for (Angajat angajat : angajatRepository.findAll())
                if (angajat.getDepartamente().contains(departament) && angajat.getProiecte().contains(proiect)) {
                    departamente.add(departament);
                    break;
                }
        return new ResponseEntity<>(departamente, HttpStatus.OK);
    }

    @PutMapping("/interschimbare/{idAngajat}")
    public ResponseEntity<String> interschimbareAngajati(@PathVariable Integer idAngajat, @RequestBody Angajat angajatNou) {
        Angajat angajatVechi = angajatRepository.findOne(idAngajat);
        if (angajatVechi != null) {
            angajatNou.setId(angajatVechi.getId());
            angajatNou.setIdSuperior(angajatVechi.getIdSuperior());
            angajatNou.setProiecte(angajatVechi.getProiecte());
            angajatNou.setDepartamente(angajatVechi.getDepartamente());
            angajatNou.setFunctie(angajatVechi.getFunctie());
            angajatRepository.update(angajatNou);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/angajat")
    public ResponseEntity<List<Angajat>> findAll() {
        List<Angajat> angajatList = angajatRepository.findAll();
        if (!angajatList.isEmpty())
            return new ResponseEntity<>(angajatList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/departament")
    public ResponseEntity<List<Departament>> findAll2() {
        List<Departament> departaments = departamentRepository.findAll();
        if (!departaments.isEmpty())
            return new ResponseEntity<>(departaments, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/angajat/{idAngajat}")
    public ResponseEntity<String> deleteAngajat(@PathVariable Integer idAngajat) {
        Angajat angajat = angajatRepository.delete(idAngajat);
        if (angajat == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
