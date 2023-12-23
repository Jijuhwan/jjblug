package com.jjapps.jjblog.service;

import com.jjapps.jjblog.domain.Article;
import com.jjapps.jjblog.dto.AddArticleRequest;
import com.jjapps.jjblog.dto.UpdateArticleRequest;
import com.jjapps.jjblog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service                    // 빈으로 등록
@RequiredArgsConstructor    // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
public class BlogService {
    private final BlogRepository blogRepository;

    // 아이디 기준 블로그 글 조회 메서드
    public Article findById(Long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:: " + id));
    }

    // 블로그 모든 글 조회 메서드
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request, String memberName){
        return blogRepository.save(request.toEntity(memberName));
    }

    // 블로그 글 삭제 메서드
    public void delete(Long id){
        Article article = blogRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        authorizeArticleAuthor(article);
        blogRepository.deleteById(id);
    }

    // 블로그 글 수정 메서드
    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());
        return article;
    }

    // 게시글을 작성한 유저인지 확인
    private static void authorizeArticleAuthor(Article article){
        String memberName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!article.getAuthor().equals(memberName)) throw new IllegalArgumentException("not authorized");
    }
}
