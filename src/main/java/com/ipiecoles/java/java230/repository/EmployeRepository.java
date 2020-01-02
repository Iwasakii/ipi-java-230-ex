package com.ipiecoles.java.java230.repository;

import com.ipiecoles.java.java230.model.Employe;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeRepository extends PagingAndSortingRepository<Employe, Long> {

    Employe findByMatricule(String Matricule);
    List<Employe> findByNomAndPrenom(String nom, String prenom);
    List<Employe> findByNomIgnoreCase(String nom);
    List<Employe> findByDateEmbaucheBefore(LocalDate DateEmbauche);
    List<Employe> findByDateEmbaucheAfter(LocalDate DateEmbauche);
    List<Employe> findBySalaireGreaterThanOrderBySalaireDesc(Double salaire);
    Page<Employe> findByNomIgnoreCase(String nom, Pageable pageable);

    @Query("select e from Employe e where lower(e.prenom) = lower(:nomOuPrenom) or lower(e.nom) = lower(:nomOuPrenom)")
    List<Employe> findByNomOrPrenomAllIgnoreCase(@Param("nomOuPrenom") String nomOrPrenom);

   // @Query("select e from Employe e where lower(e.prenom) = lower(:marque1)")
    //List<Employe> findByNomOrPrenomAllIgnoreCase(@Param("marque1") String nom, @Param("marque1") String prenom);

    @Query(value = "select * from Employe e where salaire > (select avg(e2.salaire) from employe e2)", nativeQuery = true)
    List<Employe> findEmployePlusRiches();
}
