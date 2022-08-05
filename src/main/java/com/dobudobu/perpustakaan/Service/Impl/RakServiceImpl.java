package com.dobudobu.perpustakaan.Service.Impl;

import com.dobudobu.perpustakaan.DTO.SearchDataDTO;
import com.dobudobu.perpustakaan.Model.Entity.Rak;
import com.dobudobu.perpustakaan.Model.Repository.RakRepository;
import com.dobudobu.perpustakaan.Service.RakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RakServiceImpl implements RakService {

    @Autowired
    private RakRepository rakRepository;

    @Override
    public List<Rak> getDataRak() {
        return rakRepository.findAll();
    }

    @Override
    public Rak insertDataRak(Rak rak) {
        return rakRepository.save(rak);
    }

    @Override
    public Rak findRakByCode(String searchKey) {
        return rakRepository.findByKodeRak(searchKey)
                .orElseThrow(() -> new UsernameNotFoundException("kode not found"));
    }


}
