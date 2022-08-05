package com.dobudobu.perpustakaan.Controller;

import com.dobudobu.perpustakaan.DTO.PeminjamanBukuDTO;
import com.dobudobu.perpustakaan.DTO.ResponseData;
import com.dobudobu.perpustakaan.Model.Entity.Peminjaman;
import com.dobudobu.perpustakaan.Service.PeminjamanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PeminjamanController {

    @Autowired
    private PeminjamanService peminjamanService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Iterable<Peminjaman>>> minjamBuku(@RequestBody Peminjaman minjam){
        ResponseData<Iterable<Peminjaman>> responseData = new ResponseData<>();

        responseData.setPayload(peminjamanService.minjamBuku(minjam));
        return null;
    }
}
