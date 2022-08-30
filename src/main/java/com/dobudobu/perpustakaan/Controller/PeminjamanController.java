package com.dobudobu.perpustakaan.Controller;

import com.dobudobu.perpustakaan.DTO.PeminjamanBukuDTO;
import com.dobudobu.perpustakaan.DTO.PinjamBukuDTO;
import com.dobudobu.perpustakaan.DTO.ResponseData;
import com.dobudobu.perpustakaan.Model.Entity.Peminjaman;
import com.dobudobu.perpustakaan.Service.PeminjamanService;
import org.modelmapper.ModelMapper;
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

@RestController
@RequestMapping("/api")
public class PeminjamanController {

    @Autowired
    private PeminjamanService peminjamanService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("admin/peminjaman")
    public ResponseEntity<ResponseData<Peminjaman>> minjamBuku(@RequestBody @Valid PinjamBukuDTO pinjamBukuDTO, Errors errors){
        ResponseData<Peminjaman> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setPayload(peminjamanService.peminjamanBuku(pinjamBukuDTO));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }

}
