package com.jjapps.jjblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id     // id 필드를 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 기본키 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    @Builder        // 빌더 패턴으로 객체 생성
    public Article(Long id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
