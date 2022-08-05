package com.dobudobu.perpustakaan.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PeminjamanBukuDTO {

    @NotNull(message = "anggotaId is null")
    private String anggotaId;

    @NotNull(message = "petugasId is null")
    private String petugasId;
}
