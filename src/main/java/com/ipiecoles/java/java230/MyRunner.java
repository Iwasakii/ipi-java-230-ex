package com.ipiecoles.java.java230;

import com.ipiecoles.java.java230.model.Employe;
import com.ipiecoles.java.java230.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(employeRepository);
        Optional<Employe> e = employeRepository.findById(5L);
        if(e.isPresent()) {
            System.out.println((e.get()));
        } else {
            System.out.println("Employe non trouvé !");
        }


        Page<Employe> emps = employeRepository.findAll(PageRequest.of(0, 10, Sort.Direction.ASC, "matricule"));
        System.out.println(emps.getTotalElements());


        List<Employe> emp = employeRepository.findByNomOrPrenomAllIgnoreCase("Adam");
        for(Employe ee : emp) {
            System.out.println(ee);
        }
    }

    public static void print(Object t) {
        System.out.println(t);
    }
}
