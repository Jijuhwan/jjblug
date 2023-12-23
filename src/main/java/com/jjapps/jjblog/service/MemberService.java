package com.jjapps.jjblog.service;

import com.jjapps.jjblog.domain.Member;
import com.jjapps.jjblog.dto.AddUserRequest;
import com.jjapps.jjblog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        // 생성자를 사용해 직접 생성해서 패스워드를 암호화할 수 있게 수정
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return memberRepository.save(Member.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public Member findById(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected member"));
    }

    public Member findByEmail(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
