package com.dobudobu.perpustakaan.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "peminjaman")
public class Peminjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tanggal_pinjam", nullable = false)
    private LocalDate tanggalPinjam = LocalDate.now();

    @Column(name = "tanggal_kembali", nullable = false)
    private LocalDate tanggal_kembali;

    @OneToOne
    @JoinColumn(name = "id_anggota", nullable = false)
    private Anggota anggota;

    @ManyToOne
    @JoinColumn(name = "id_petugas", nullable = false)
    private Petugas petugas;

    @ManyToMany
    @JoinTable(name = "peminjaman_book", joinColumns = {
            @JoinColumn(name = "id_peminjam", referencedColumnName = "id")},
        inverseJoinColumns = {
            @JoinColumn(name = "id_book", referencedColumnName = "id")
        })
    private List<Book> books;


}
