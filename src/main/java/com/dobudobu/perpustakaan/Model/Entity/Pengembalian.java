package com.dobudobu.perpustakaan.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "pengembalian")
public class Pengembalian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tgl_pengembalian")
    private LocalDate tanggalPengembalianBuku = LocalDate.now();

    @Column(name = "denda")
    private Integer denda;

    @OneToOne
    @JoinColumn(name = "peminjaman_id")
    private Peminjaman peminjaman;

    @OneToOne
    @JoinColumn(name = "anggota_id")
    private Anggota anggota;

    @OneToOne
    @JoinColumn(name = "petugas_id")
    private Petugas petugas;

}
