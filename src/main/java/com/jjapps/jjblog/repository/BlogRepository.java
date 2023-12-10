package com.jjapps.jjblog.repository;

import com.jjapps.jjblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
