package com.dobudobu.perpustakaan.Service.Impl;

import com.dobudobu.perpustakaan.Model.Entity.AppUser;
import com.dobudobu.perpustakaan.Model.Entity.Petugas;
import com.dobudobu.perpustakaan.Model.Repository.AppUserRepo;
import com.dobudobu.perpustakaan.Model.Repository.PetugasRepository;
import com.dobudobu.perpustakaan.Service.PetugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetugasServiceImpl implements PetugasService {

    @Autowired
    private PetugasRepository petugasRepository;

    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public Petugas saveRegister(Petugas petugas) {
        Petugas petugas1 = new Petugas();
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<AppUser> appUser = this.appUserRepo.findByEmail(user);

        petugas1.setNamaPetugas(petugas.getNamaPetugas());
        petugas1.setJabatanPetugas(petugas.getJabatanPetugas());
        petugas1.setNoTelp(petugas.getNoTelp());
        petugas1.setAlamatPetugas(petugas.getAlamatPetugas());
        petugas1.setAppUser(appUser.get());

        return petugasRepository.save(petugas1);
    }
}
