package com.uan.optica.service;

import com.uan.optica.entities.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@AllArgsConstructor
public class MyUserDetails implements UserDetails {
    private Usuario user;
    private int idpaciente;
    private int idoptometra;

    private  int idadmin;


    /**Obtener los roles de un usuario*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority((user.getRol()));
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getCorreo();
    }

    public String getRol() {
        return user.getRol();
    }

    public String getNombre() {
        return user.getNombre();
    }

    public String getApellido() {
        return user.getApellido();
    }
    public Long getTelefono() {
        return user.getTelefono();
    }
    public int getIdpaciente() {
        return idpaciente;
    }
    public int getIdoptometra() {
        return idoptometra;
    }
    public int getIdadmin() {
        return idadmin;
    }


    public Long getCedula() {
        return user.getCedula();
    }











    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}