package com.dobudobu.perpustakaan.Service;

import com.dobudobu.perpustakaan.Model.Entity.Peminjaman;

public interface PeminjamanService {
    Iterable<Peminjaman> minjamBuku(Peminjaman minjam);
}
