package com.dobudobu.perpustakaan.Controller;

import com.dobudobu.perpustakaan.DTO.InsertPetugasDTO;
import com.dobudobu.perpustakaan.DTO.ResponseData;
import com.dobudobu.perpustakaan.Model.Entity.Petugas;
import com.dobudobu.perpustakaan.Service.PetugasService;
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
public class PetugasController {

    @Autowired
    private PetugasService petugasService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("v1/admin/Register")
    public ResponseEntity<ResponseData<Petugas>> registerPetugas(@RequestBody @Valid InsertPetugasDTO insertPetugasDTO, Errors errors){
        ResponseData<Petugas> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Petugas petugas = modelMapper.map(insertPetugasDTO, Petugas.class);
        responseData.setPayload(petugasService.saveRegister(petugas));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }
}
