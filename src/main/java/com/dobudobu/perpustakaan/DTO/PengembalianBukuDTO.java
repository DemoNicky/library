package com.dobudobu.perpustakaan.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PengembalianBukuDTO {

    @NotNull(message = "id peminjam belum di isi")
    private String idPeminjaman;

}
