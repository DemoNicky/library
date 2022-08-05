package com.dobudobu.perpustakaan.DTO;

import com.dobudobu.perpustakaan.Model.Entity.Rak;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class InsertDataBookWithRakDTO {

    @NotNull(message = "judul buku belum di isi")
    private String judulBuku;

    @NotNull(message = "penulis buku belum di isi")
    private String penulisBuku;

    @NotNull(message = "penerbit buku belum di isi")
    private String penerbitBuku;

    @NotNull(message = "tahun terbit buku belum di isi")
    private String tahunTerbit;

    @NotNull(message = "stok buku belum di isi")
    private Integer stok;

    @NonNull
    private Rak rak;
}
