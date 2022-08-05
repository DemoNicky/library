package com.dobudobu.perpustakaan.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "anggota")
public class Anggota extends AbstractBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_anggota", length = 100)
    private String namaAnggota;

    @Enumerated(EnumType.STRING)
    private Jk jenisKel;

    @Column(name = "jurusan_anggota", length = 20)
    private String jurusanAnggota;

    @Column(name = "no_telp_anggota")
    private String noTelp;

    @Column(name = "alamat_anggota")
    private String alamat;

    @OneToOne
    private AppUser appUser;
}
