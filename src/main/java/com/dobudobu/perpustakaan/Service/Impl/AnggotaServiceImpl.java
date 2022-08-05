package com.dobudobu.perpustakaan.Service.Impl;

import com.dobudobu.perpustakaan.Model.Entity.Anggota;
import com.dobudobu.perpustakaan.Model.Entity.AppUser;
import com.dobudobu.perpustakaan.Model.Repository.AnggotaRepository;
import com.dobudobu.perpustakaan.Model.Repository.AppUserRepo;
import com.dobudobu.perpustakaan.Service.AnggotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnggotaServiceImpl implements AnggotaService {

    @Autowired
    private AnggotaRepository anggotaRepository;

    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public Anggota saveAnggota(Anggota anggota) {
        Anggota anggotaUser = new Anggota();
        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<AppUser> appUser = this.appUserRepo.findByEmail(user);

        anggotaUser.setNamaAnggota(anggota.getNamaAnggota());
        anggotaUser.setJenisKel(anggota.getJenisKel());
        anggotaUser.setJurusanAnggota(anggota.getJurusanAnggota());
        anggotaUser.setNoTelp(anggota.getNoTelp());
        anggotaUser.setAlamat(anggota.getAlamat());
        anggotaUser.setAppUser(appUser.get());

        return anggotaRepository.save(anggotaUser);
    }
}
