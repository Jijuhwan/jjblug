package com.jjapps.jjblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="members")
public class Member implements UserDetails {    // UserDetails를 상속받아 인증 객체로 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="nickname", unique = true)
    private String nickname;

    @Builder
    public Member(String email, String password, String nickname){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
    
//    @Column(nullable = false)
//    private String created_at;
//
//    @Column(nullable = false)
//    private String updated_at;

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    /* 사용자의 id를 반환(고유한 값)
     * 사용자를 식별할 수 있는 사용자 이름을 반환
     * 이때 사용되는 사용자 이름은 반드시 고유해야 한다.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /* 사용자의 패스워드를 반환
     * 저장되어 있는 비밀번호는 암호화해서 저장해야 한다.
     */
    @Override
    public String getPassword(){
        return password;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true;    // true -> 만료되지 않았음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // 잠금되었는지 확인하는 로직
        return true;   // true -> 잠금되지 않았음
    }

    // 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        return true;    // true -> 만료되지 않았음
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 사용 가능한지 확인하는 로직
        return true;    // true -> 사용 가능
    }

    // 사용자 이름 변경
    public Member update(String nickname){
        this.nickname = nickname;

        return this;
    }
}
