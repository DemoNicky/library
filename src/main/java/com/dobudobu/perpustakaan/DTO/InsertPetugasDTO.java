package com.dobudobu.perpustakaan.DTO;

import com.dobudobu.perpustakaan.Model.Entity.AppUser;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class InsertPetugasDTO {

    @NotNull(message = "nama petugas belum di isi")
    private String namaPetugas;

    @NotNull(message = "jabatan petugas belum di isi")
    private String jabatanPetugas;

    @NotNull(message = "no telp petugas belum di isi")
    private String noTelp;

    @NotNull(message = "alamat petugas belum di isi")
    private String alamatPetugas;

}
