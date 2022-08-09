package com.dobudobu.perpustakaan.Model.Repository;

import com.dobudobu.perpustakaan.Model.Entity.AppUser;
import com.dobudobu.perpustakaan.Model.Entity.Petugas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetugasRepository extends JpaRepository<Petugas, Long> {

    Optional<Petugas> findByAppUser(AppUser appUser);
}
