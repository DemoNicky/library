package com.dobudobu.perpustakaan.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "Book")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Book extends AbstractBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "judul_buku", nullable = false)
    private String judulBuku;

    @Column(name = "penulis_buku")
    private String penulisBuku;

    @Column(name = "penerbit_buku")
    private String penerbitBuku;

    @Column(name = "tahun_terbit", length = 4)
    private String tahunTerbit;

    @Column(name = "stok", length = 11)
    private Integer stok;

    @ManyToOne(targetEntity = Rak.class)
    @JoinColumn(name = "rak_id")
//    @JsonManagedReference
    private Rak rak;


}
