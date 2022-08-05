package com.dobudobu.perpustakaan.Model.Repository;

import com.dobudobu.perpustakaan.Model.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Query(value = "SELECT p FROM AppUser p WHERE p.email =:email")
    AppUser findUserByEmail(@PathParam("email") String email);

}
