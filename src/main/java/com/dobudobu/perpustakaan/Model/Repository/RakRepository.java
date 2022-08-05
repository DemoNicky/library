package com.dobudobu.perpustakaan.Model.Repository;

import com.dobudobu.perpustakaan.Model.Entity.Rak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.Optional;

@Repository
public interface RakRepository extends JpaRepository<Rak, Long> {

    Optional<Rak> findByKodeRak(String kode);
}
