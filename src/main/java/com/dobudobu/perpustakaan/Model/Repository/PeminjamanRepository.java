package com.dobudobu.perpustakaan.Model.Repository;

import com.dobudobu.perpustakaan.Model.Entity.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {

    Optional<Peminjaman> findBySecureId(String secureId);

}
