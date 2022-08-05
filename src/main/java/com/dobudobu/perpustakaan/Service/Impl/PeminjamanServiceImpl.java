package com.dobudobu.perpustakaan.Service.Impl;

import com.dobudobu.perpustakaan.Model.Entity.Peminjaman;
import com.dobudobu.perpustakaan.Model.Repository.PeminjamanRepository;
import com.dobudobu.perpustakaan.Service.PeminjamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeminjamanServiceImpl implements PeminjamanService {

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Override
    public Iterable<Peminjaman> minjamBuku(Peminjaman minjam) {
        Peminjaman peminjaman = new Peminjaman();

        return null;

    }
}
