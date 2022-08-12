package com.dobudobu.perpustakaan.Service;

import com.dobudobu.perpustakaan.DTO.PengembalianBukuDTO;
import com.dobudobu.perpustakaan.Model.Entity.Pengembalian;

public interface PengembalianService {
    void pengembalianBuku(PengembalianBukuDTO pengembalianBukuDTO);
}
