package com.tpe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor //Parametreli Constructor
@NoArgsConstructor // Parametresiz Constructor
//@RequiredArgsConstructor // Final keywordu kullanilarak veya Notnull olarak set edilen field'larin constructor'ini olusturur.
public class Student {
        // ************ @ LAR NERDEN GELDIGINE GORE FARKLARI *******************
    //Hibernate'gelen Annotation'lar DB'De kaydedilme isleminde kontrol yapar
    //Validation Kutuphanesinden gelen Annotation'lar Front-end kisminda client' veri giridligi zaman kontrol edilir.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Setter(AccessLevel.NONE)  Id'nin Setter method'una ulasmak istemedigim zaman kullaniyorum. Filed-method level olarakda
    //kullanilabilir . Lombok kutuphanesinden gelmektedir.
    private Long id;

    @Column(nullable = false,length = 25)
    @Size(min = 2, max = 25 , message = "Name '${validatedValue}' must be beetwen {min} and {max}")
    //'${validatedValue}' valid edilen degelerin okunmasi icin kullanilir.
    @NotBlank(message = "Name can not be space")
    private String name;

    @Column(nullable = false,length = 25)
    //@Colum hibernate'den gelen bir @ . Bu constrait'ler db'de kaydetme islemi sirasinda kontrol ediliyor.
    //Hibernate'den gelen bir @ Annotation'dur.
    @Size(min = 2, max = 25 , message = "Lastname '${validatedValue}' must be beetwen {min} and {max}")
    @NotBlank(message = "LastName can not be space")
    //Validation Kutuphanesinden gelen @(Annotation)lar front-end kisminda degiskenlere deger girildigi zaman client tarafinda validation
    // gerceklestiriyor.
    private String lastname;


    private Integer grade;

    @Column(nullable = false,unique = true, length = 50)
    @Email(message = "Please provide valid email")
    // aaa@bb.com formatinda olmasini saglar. Daha detayli olmasini istersek @Email(pattern="") regexlerle yapabilirim.
    private String email;

    private String phoneNumber;


    private LocalDateTime createDateTime= LocalDateTime.now();
}
