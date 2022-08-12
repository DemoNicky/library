package com.dobudobu.perpustakaan.Service.Impl;

import com.dobudobu.perpustakaan.DTO.PengembalianBukuDTO;
import com.dobudobu.perpustakaan.Model.Entity.Peminjaman;
import com.dobudobu.perpustakaan.Model.Entity.Pengembalian;
import com.dobudobu.perpustakaan.Model.Repository.PeminjamanRepository;
import com.dobudobu.perpustakaan.Model.Repository.PengembalianRepository;
import com.dobudobu.perpustakaan.Service.PeminjamanService;
import com.dobudobu.perpustakaan.Service.PengembalianService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

@Service
@AllArgsConstructor
public class PengembalianServiceImpl implements PengembalianService {

    @Autowired
    private PengembalianRepository pengembalianRepository;

    @Autowired
    private final PeminjamanService peminjamanService;

    @Autowired
    private final PeminjamanRepository peminjamanRepository;

    @Override
    public void pengembalianBuku(PengembalianBukuDTO pengembalianBukuDTO) {
        Pengembalian pengembalian = new Pengembalian();
        Peminjaman peminjaman = peminjamanRepository.findBySecureId(pengembalianBukuDTO.getIdPeminjaman()).get();
        LocalDate localDate = peminjaman.getTanggal_kembali();

        int resultDate = localDate.compareTo(LocalDate.now());

        if (resultDate < 0){
            long days = ChronoUnit.DAYS.between(LocalDate.now(), localDate);
            long countResult = days * 5000;
            int ress = (int) countResult;
            pengembalian.setDenda(ress);
        }else {
            pengembalian.setDenda(0);
        }

        pengembalian.setPeminjaman(peminjaman);
        pengembalian.setAnggota(peminjaman.getAnggota());
        pengembalian.setPetugas(peminjaman.getPetugas());
        pengembalianRepository.save(pengembalian);
        peminjamanService.deletedPeminjaman(pengembalianBukuDTO.getIdPeminjaman());
    }
}
