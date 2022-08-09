package com.dobudobu.perpustakaan.Model.Repository;

import com.dobudobu.perpustakaan.Model.Entity.Anggota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnggotaRepository extends JpaRepository<Anggota, Long> {

    Optional<Anggota> findBySecureId(String id);

}
