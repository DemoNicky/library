package com.dobudobu.perpustakaan.Service.Impl;

import com.dobudobu.perpustakaan.Model.Entity.Anggota;
import com.dobudobu.perpustakaan.Model.Entity.AppUser;
import com.dobudobu.perpustakaan.Model.Repository.AppUserRepo;
import com.dobudobu.perpustakaan.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    @Autowired
    private AppUserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("user with email " + email + "not found")));
    }

    @Override
    public AppUser register(AppUser appUser) {
        boolean user = userRepo.findByEmail(appUser.getEmail()).isPresent();
        if (user){
            throw new RuntimeException(String.format("user with email " +appUser.getEmail() + "already exists"));
        }

        String passwordencoder = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(passwordencoder);

        return userRepo.save(appUser);
    }

}
