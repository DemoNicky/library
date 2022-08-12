package com.dobudobu.perpustakaan.Model.Entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "peminjaman")
@SQLDelete(sql = "UPDATE peminjaman SET deleted = true WHERE id =?")
@Where(clause = "deleted = false")
public class Peminjaman extends AbstractBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tanggal_pinjam", nullable = false)
    private LocalDate tanggalPinjam;

    @Column(name = "tanggal_kembali", nullable = false)
    private LocalDate tanggal_kembali;

    @OneToOne
    @JoinColumn(name = "id_anggota", nullable = false)
    private Anggota anggota;

    @ManyToOne
    @JoinColumn(name = "id_petugas", nullable = false)
    private Petugas petugas;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name = "peminjaman_book", joinColumns = {
            @JoinColumn(name = "id_peminjam", referencedColumnName = "id")},
        inverseJoinColumns = {
            @JoinColumn(name = "id_book", referencedColumnName = "id")
        })
    private Set<Book> books;


}
