package com.example.demo.entity;

import com.example.demo.entity.Tamplate.AbsUUIDEntity;
import com.example.demo.entity.Tamplate.Position;
import com.example.demo.entity.enums.Huquq;
import com.example.demo.entity.enums.SystemRoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User extends AbsUUIDEntity implements UserDetails{

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String username;


    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Position position;

    @Enumerated(EnumType.STRING)
    private SystemRoleName systemRoleName;


    private boolean enabled;

    @Column(nullable = false)
    private String email;

    private String emailCode;

    @Column(nullable = false)
    private String color;

    private String initialLetter;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment avatar;


       @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Huquq> huquqList = this.position.getHuquqList();
        List<GrantedAuthority>grantedAuthorities=new ArrayList<>();
        for (Huquq huquq :huquqList ) {
            grantedAuthorities.add(new SimpleGrantedAuthority(huquq.name()));
    }
    return grantedAuthorities;
        }
        //   @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority>grantedAuthorities=new ArrayList<>();
//        List<Huquq> huquqList = this.position.getHuquqList();
//        for (Huquq huquq :huquqList ) {
//            grantedAuthorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return huquq.name();
//                }
//            });
//        }
//
//        return grantedAuthorities;
//    }

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

}
