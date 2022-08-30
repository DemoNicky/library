package com.dobudobu.perpustakaan.Security.config;

import com.dobudobu.perpustakaan.Model.Entity.AppRole;
import com.dobudobu.perpustakaan.Service.Impl.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/user/register", "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/book/**").hasAnyAuthority(AppRole.ANGGOTA.name(), AppRole.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/v1/book/**").hasAuthority(AppRole.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/v1/book/**").hasAuthority(AppRole.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/api/v1/book/**").hasAuthority(AppRole.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/v1/user/anggota").hasAuthority(AppRole.ANGGOTA.name())
                .antMatchers(HttpMethod.POST, "/api/admin/peminjaman").hasAuthority(AppRole.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/admin/pengembalian").hasAuthority(AppRole.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/v1/admin/register").hasAuthority(AppRole.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api/v1/rak").hasAnyAuthority(AppRole.ANGGOTA.name(), AppRole.ADMIN.name())
                .antMatchers(HttpMethod.POST,"/api/v1/rak").hasAuthority(AppRole.ADMIN.name())
                .antMatchers(HttpMethod.POST,"/api/v1/rak/find").hasAuthority(AppRole.ADMIN.name())
                .anyRequest().fullyAuthenticated()
                .and().httpBasic();



//                .and().formLogin()
//                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/index", true)
//                .and()
//                .rememberMe()
//                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//                    .key("verySecured")
//                .and()
//                .logout()
//                    .logoutUrl("/logout")
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//                    .clearAuthentication(true).invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID", "Idea-6e9452e4")
//                    .logoutSuccessUrl("/login");
    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManager() throws Exception{
//        return super.authenticationManager();
//    }

    //method ini di gunakan unutk memberitahu ke spring tentang class service dengan userdetailservice dan encoder
    //(bcryptPasswordEncoder) menggunakan apa
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    //method di bawah digunakan untuk memberi tahu ke pada profider class apa yang di gunakan
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
