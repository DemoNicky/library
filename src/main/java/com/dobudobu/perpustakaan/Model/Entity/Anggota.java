package com.dobudobu.perpustakaan.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

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

    //@ColumnTransformer digunakan unutk enkripsi dan dekripsi, menggunakan enkripsi yang di miliki database
    @ColumnTransformer(
            read = "AES_DECRYPT(UNHEX(no_telp_anggota), 'iWiLlSaVeyOurDaY')",
            write = "HEX(AES_ENCRYPT(?,'iWiLlSaVeyOurDaY'))"
    )
    @Column(name = "no_telp_anggota", unique = true)
    private String noTelp;

    @Column(name = "alamat_anggota")
    private String alamat;

    private boolean meminjam = false;

    @OneToOne
    private AppUser appUser;
}
