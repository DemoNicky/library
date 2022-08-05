package com.dobudobu.perpustakaan.Controller;

import com.dobudobu.perpustakaan.DTO.InsertDataRakDTO;
import com.dobudobu.perpustakaan.DTO.ResponseData;
import com.dobudobu.perpustakaan.DTO.SearchDataDTO;
import com.dobudobu.perpustakaan.Model.Entity.Rak;
import com.dobudobu.perpustakaan.Service.RakService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RakController {

    @Autowired
    private RakService rakService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("v1/rak")
    public List<Rak> getBook(){
        return rakService.getDataRak();
    }

    @PostMapping("v1/rak")
    public ResponseEntity<ResponseData<Rak>> insertRakBook(@RequestBody @Valid InsertDataRakDTO insertDataRakDTO, Errors errors){
        ResponseData<Rak> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Rak rak = modelMapper.map(insertDataRakDTO, Rak.class);
        responseData.setPayload(rakService.insertDataRak(rak));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("v1/rak/find")
    public Rak findByKodeRak(@RequestBody SearchDataDTO kode){
        return rakService.findRakByCode(kode.getSearchKey());
    }
}
