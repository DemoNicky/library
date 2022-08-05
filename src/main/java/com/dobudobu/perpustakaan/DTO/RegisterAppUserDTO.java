package com.dobudobu.perpustakaan.DTO;

import com.dobudobu.perpustakaan.Model.Entity.Anggota;
import com.dobudobu.perpustakaan.Model.Entity.AppRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class RegisterAppUserDTO {

    @NotNull(message = "fullname is empty")
    private String fullName;

    @NotNull(message = "email is empty")
    @Email
    private String email;

    @NotNull(message = "password is empty")
    private String password;

    @NotNull(message = "role is empty")
    private String roles;

}
