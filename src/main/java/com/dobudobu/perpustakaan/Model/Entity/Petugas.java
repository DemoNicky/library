package com.dobudobu.perpustakaan.Model.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "petugas")
public class Petugas extends AbstractBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_petugas", length = 100)
    private String namaPetugas;

    @Column(name = "jabatan_petugas", length = 50)
    private String jabatanPetugas;

    @Column(name = "no_telp_petugas")
    private String noTelp;

    @Column(name = "alamat_petugas")
    private String alamatPetugas;

    @OneToOne
    private AppUser appUser;
}
