package com.dobudobu.perpustakaan.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BatchSaveDTO {

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

}
