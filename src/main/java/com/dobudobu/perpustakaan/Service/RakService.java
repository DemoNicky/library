package com.dobudobu.perpustakaan.Service;

import com.dobudobu.perpustakaan.DTO.SearchDataDTO;
import com.dobudobu.perpustakaan.Model.Entity.Rak;

import java.util.List;

public interface RakService {
    List<Rak> getDataRak();

    Rak insertDataRak(Rak rak);

    Rak findRakByCode(String searchKey);
}
