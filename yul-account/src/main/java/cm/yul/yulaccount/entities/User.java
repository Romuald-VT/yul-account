package cm.yul.yulaccount.entities;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phoneNumber;

    public User(Integer id,String firstname,String lastname,String email,String password,String phoneNumber)
    {
        this.Id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email= email;
        this.password= password;
        this.phoneNumber= phoneNumber;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;
    private boolean isActive=false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+this.role.getUserRole() ));
    }
    @Override
    public String getUsername() {
        return this.getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        
        return this.isActive();
    }
    @Override
    public boolean isAccountNonLocked() {
       
        return this.isActive();
    }
    @Override
    public boolean isCredentialsNonExpired() {
         
        return this.isActive();
    }
    @Override
    public boolean isEnabled() {
        return this.isActive();
    }
}