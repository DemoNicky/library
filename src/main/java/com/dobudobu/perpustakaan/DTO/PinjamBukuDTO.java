package com.dobudobu.perpustakaan.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PinjamBukuDTO {

    @NotNull(message = "id Anggota belum di isi")
    private String anggotaId;

    @NotNull(message = "id Buku belum di isi")
    private String bookId;
}
