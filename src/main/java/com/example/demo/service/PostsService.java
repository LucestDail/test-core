package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.PostsRepository;
import com.example.demo.request.PostsSaveRequestDto;
import com.example.demo.request.PostsUpdateRequestDto;
import com.example.demo.response.PostsMainResponseDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsService {
	private PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto dto) {
		System.out.println("PostsService.save activated");
		return postsRepository.save(dto.toEntity()).getId();
	}	
	
	@Transactional
	public void update(PostsUpdateRequestDto dto) {
		System.out.println("PostsService update activated -> " + dto);
		postsRepository.update(dto.getId(), dto.getAuthor(), dto.getContent(), dto.getTitle());
	}
	
	@Transactional(readOnly = true)
	public List<PostsMainResponseDto> findAllDesc(){
		System.out.println("PostsService findAllDesc activated");
		return postsRepository.findAllDesc().map(PostsMainResponseDto::new).collect(Collectors.toList());
	}
}
