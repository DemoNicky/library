package com.dobudobu.perpustakaan.Controller;

import com.dobudobu.perpustakaan.DTO.RegisterAppUserDTO;
import com.dobudobu.perpustakaan.DTO.ResponseData;
import com.dobudobu.perpustakaan.Model.Entity.AppUser;
import com.dobudobu.perpustakaan.Service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("v1/user/register")
    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody @Valid RegisterAppUserDTO registerAppUserDTO){
        ResponseData<AppUser> responseData = new ResponseData<>();
        AppUser appUser = modelMapper.map(registerAppUserDTO, AppUser.class);
        responseData.setPayload(appUserService.register(appUser));
        responseData.setStatus(true);
        responseData.getMessage().add("user saved");
        return ResponseEntity.ok(responseData);
    }

}
