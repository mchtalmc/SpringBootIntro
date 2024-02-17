package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Kullanmak zorunlu degil . Jpa extend ettigim icin
public interface StudentRepository extends JpaRepository<Student, Long> {

    //Sık kullanilan methodlarin orneklerini olusturmus fakat degiskenleri degistererek bu methodlari overloading yapabiliyorum
    //Harfler ayni olmali sadce field'lari degisterek JpaRepository'nin hazir methodlarini turetebiliyorum.
    Boolean existsByEmail(String email); //Jpa Repository icinde hazir olan methodlar mevcut.

 //   @Query("FROM Student s WHERE s.grade=:pGrade")
 //   List<Student> findAllGradeEquals(@Param("grade") Integer grade);

    @Query(value = "SELECT * FROM Student s WHERE s.grade=:pGrade", nativeQuery = true)
    List<Student> findAllGradeEquals(@Param("grade") Integer grade);


}
