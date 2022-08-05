package com.dobudobu.perpustakaan.DTO;

import com.dobudobu.perpustakaan.Model.Entity.Jk;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AnggotaSaveDTO {

    @NotNull(message = "nama anggota kosong")
    private String namaAnggota;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Jk jenisKel;

    @NotNull(message = "jurusan anggota kosong")
    private String jurusanAnggota;

    @NotNull(message = "no telp anggota kosong")
    @Size(min = 9, max = 20)
    private String noTelp;

    @NotNull(message = "alamat anggota kosong")
    private String alamat;
}
