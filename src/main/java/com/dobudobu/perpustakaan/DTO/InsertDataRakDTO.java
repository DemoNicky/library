package com.dobudobu.perpustakaan.DTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class InsertDataRakDTO {

    @NotNull(message = "kode rak belum di isi")
    private String kodeRak;

    @NotNull(message = "lokasi belum di isi")
    private String lokasiRak;

}
