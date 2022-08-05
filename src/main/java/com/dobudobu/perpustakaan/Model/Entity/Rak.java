package com.dobudobu.perpustakaan.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "rak_buku")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Rak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kode_rak", length = 3)
    private String kodeRak;

    @Column(name = "lokasi_rak", length = 5)
    private String lokasiRak; //misal LT 3

    @OneToMany(mappedBy = "rak", targetEntity = Book.class)
//    @JsonBackReference
    private List<Book> books;
}
