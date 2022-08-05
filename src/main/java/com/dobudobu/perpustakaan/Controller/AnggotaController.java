package com.dobudobu.perpustakaan.Controller;

import com.dobudobu.perpustakaan.DTO.AnggotaSaveDTO;
import com.dobudobu.perpustakaan.DTO.ResponseData;
import com.dobudobu.perpustakaan.Model.Entity.Anggota;
import com.dobudobu.perpustakaan.Service.AnggotaService;
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
@RequestMapping(path = "/api")
public class AnggotaController {

    @Autowired
    private AnggotaService anggotaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("v1/user/anggota")
    public ResponseEntity<ResponseData<Anggota>> saveAnggota(@RequestBody @Valid AnggotaSaveDTO anggotaSaveDTO, Errors errors){
        ResponseData<Anggota> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Anggota anggota = modelMapper.map(anggotaSaveDTO, Anggota.class);
        responseData.setPayload(anggotaService.saveAnggota(anggota));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }
}
