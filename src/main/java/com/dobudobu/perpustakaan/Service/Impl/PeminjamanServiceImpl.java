package com.dobudobu.perpustakaan.Service.Impl;

import com.dobudobu.perpustakaan.DTO.PinjamBukuDTO;
import com.dobudobu.perpustakaan.Model.Entity.*;
import com.dobudobu.perpustakaan.Model.Repository.*;
import com.dobudobu.perpustakaan.Service.PeminjamanService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class PeminjamanServiceImpl implements PeminjamanService {

    @Autowired
    private final PeminjamanRepository peminjamanRepository;

    @Autowired
    private final AnggotaRepository anggotaRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final PetugasRepository petugasRepository;

    @Autowired
    private final AppUserRepo appUserRepo;

    @Autowired
    private final EmailSendService senderService;

    @Override
    public Peminjaman peminjamanBuku(PinjamBukuDTO pinjamBukuDTO) {
        Peminjaman peminjaman = new Peminjaman();
        Anggota anggota = anggotaRepository.findBySecureId(pinjamBukuDTO.getAnggotaId())
                .orElseThrow(() -> new RuntimeException(String.format("Data Anggota tidak di temukan")));

        Book book = bookRepository.findBySecureId(pinjamBukuDTO.getBookId())
                .orElseThrow(() -> new RuntimeException(String.format("Data Buku tidak di temukan")));

        Integer bookss = book.getStok() - 1;
//        Integer x = bookss - 1;
        book.setStok(bookss);

        Set<Book> books = new HashSet<>(Set.of(book));

        String dataPetugas = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = appUserRepo.findByEmail(dataPetugas).get();
        Petugas petugas = petugasRepository.findByAppUser(appUser).get();

        peminjaman.setTanggalPinjam(LocalDate.now());
        peminjaman.setTanggal_kembali(LocalDate.now().plus(10, ChronoUnit.DAYS));
        peminjaman.setAnggota(anggota);
        peminjaman.setBooks(books);
        peminjaman.setPetugas(petugas);
        sendEmail(anggota.getAppUser().getEmail(), book.getJudulBuku(), peminjaman.getTanggal_kembali(), appUser.getFullName());
        return peminjamanRepository.save(peminjaman);
    }

    private void sendEmail(String email, String  book, LocalDate tanggalKembali, String nama){

        String subject = "Pemberitahuan Peminjaman Buku Perpustakaan Online";

        String body = "Kepada Yth. " + nama + " Kami informasikan bahwa kamu telah meminjam buku " + book +
                " dari perpustakaan online, dengan tanggal pengembailan buku : " +tanggalKembali.toString()+
                " \n\nUntuk informasi lebih lanjut silahkan datangi kantor cabang perpustakaan online terdekat\n\n\n" +
                "UWU";

        senderService.sendEmail(email, subject, body );
    }

    @Override
    public void deletedPeminjaman(String securedId) {
        Peminjaman peminjaman = peminjamanRepository.findBySecureId(securedId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("id tidak di temukan")));

        peminjamanRepository.deleteById(peminjaman.getId());
    }

//    @Override
//    public Peminjaman findPeminjamBySecuredId(String secureId) {
//        return peminjamanRepository.findBySecureId(secureId).get();
//
//    }


}
