package com.dobudobu.perpustakaan.Service;

import com.dobudobu.perpustakaan.DTO.PinjamBukuDTO;
import com.dobudobu.perpustakaan.Model.Entity.Peminjaman;

public interface PeminjamanService {


    Peminjaman peminjamanBuku(PinjamBukuDTO pinjamBukuDTO);
}
