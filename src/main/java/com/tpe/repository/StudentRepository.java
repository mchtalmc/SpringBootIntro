package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Kullanmak zorunlu degil . Jpa extend ettigim icin
public interface StudentRepository extends JpaRepository<Student, Long> {


    Boolean existsByEmail(String email); //Jpa Repository icinde hazir olan methodlar mevcut.
    //Sik kullanilan methodlarin orneklerini olusturmus fakat degiskenleri degistererek bu methodlari overloading yapabiliyorum
    //Harfler ayni olmali sadce field'lari degisterek JpaRepository'nin hazir methodlarini turetebiliyorum.
}
