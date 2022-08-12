package com.dobudobu.perpustakaan.Controller;

import com.dobudobu.perpustakaan.DTO.PengembalianBukuDTO;
import com.dobudobu.perpustakaan.DTO.ResponseData;
import com.dobudobu.perpustakaan.Model.Entity.Pengembalian;
import com.dobudobu.perpustakaan.Service.PengembalianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class PenggembalianController {

    @Autowired
    private PengembalianService pengembalianService;

    @PostMapping("admin/pengembalian")
    public ResponseEntity<Void> pengembalianBuku(@RequestBody @Valid PengembalianBukuDTO pengembalianBukuDTO){

        pengembalianService.pengembalianBuku(pengembalianBukuDTO);
        return ResponseEntity.created(URI.create("admin/pengembalian")).build();
    }

}
