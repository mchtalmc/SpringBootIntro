package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Kullanmak zorunlu degil . Jpa extend ettigim icin
public interface StudentRepository extends JpaRepository<Student, Long> {

    //Sık kullanilan methodlarin orneklerini olusturmus fakat degiskenleri degistererek bu methodlari overloading yapabiliyorum
    //Harfler ayni olmali sadce field'lari degisterek JpaRepository'nin hazir methodlarini turetebiliyorum.
    Boolean existsByEmail(String email); //Jpa Repository icinde hazir olan methodlar mevcut.


    //JPQL
    @Query("SELECT s FROM Student s WHERE s.grade=:pGrade")
    //   @Query("FROM Student s WHERE s.grade=:pGrade")
    List<Student> findAllGradeEquals(@Param("pGrade") Integer grade);


    //SQL
//    @Query(value = "SELECT * FROM student s WHERE s.grade=:pGrade",nativeQuery = true)//Student
//    List<Student> findAllGradeEquals(@Param("pGrade") Integer grade);




    //DB den gelen studentı DTO ya çevirerek gönderiyor
    @Query("SELECT new com.tpe.dto.StudentDTO(s) FROM Student s WHERE s.id=:pId")
    Optional<StudentDTO> findStudentDtoById(@Param("pId") Long id);
}
