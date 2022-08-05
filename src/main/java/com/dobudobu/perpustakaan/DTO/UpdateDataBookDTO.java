package com.dobudobu.perpustakaan.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateDataBookDTO {

    private String judulBuku;

    private String penulisBuku;

    private String penerbitBuku;

    private String tahunTerbit;

    private Integer stok;

}
