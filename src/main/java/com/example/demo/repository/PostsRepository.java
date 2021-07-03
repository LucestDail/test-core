package com.example.demo.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long>{
	@Query("SELECT p FROM Posts p ORDER BY p.id DESC")
	Stream<Posts> findAllDesc();
	// 영속성 컨텍스트 관련 내용 확인 필요
	@Transactional //와 이거 찾느라 죽을뻔했
	@Modifying //DML 에러뜨면 이거 추가하
	@Query("UPDATE FROM Posts p SET p.author=:author, p.content=:content, p.title=:title WHERE p.id=:id")
	void update(@Param("id")Long id, @Param("author")String author, @Param("content")String content, @Param("title")String title);
}
