package com.example.demo.controller;

import java.util.Optional;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Posts;
import com.example.demo.repository.PostsRepository;
import com.example.demo.request.PostsDeleteRequestDto;
import com.example.demo.request.PostsSaveRequestDto;
import com.example.demo.request.PostsUpdateRequestDto;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
	
	final static String APIKey = "RGAPI-fcc6aa73-8bc4-4b51-90cc-2d301515c904";
	
	
	private PostsRepository postsRepository;
	/**
	 * riot api checking / validator from riot developer api checker
	 * @return
	 */
	@RequestMapping("/riot.txt")
	@ResponseBody
	public String riot() {
		//return "5271dbcd-d91f-44a5-a47a-117c1de87770";
		return APIKey;
	}
	/*
	@PostMapping("/postSummonerId")
	public void searchSummonerId(@RequestBody PostsSearchRequestDto dto) {
		//https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-name/%EB%8D%B0%EC%9D%B4%EB%A3%A8%EC%83%A4?api_key=RGAPI-824f5210-2018-4391-a074-94908c55fe1c
		StringBuilder sb = new StringBuilder();
		sb.append("https://");
		sb.append(dto.getRegion());
		sb.append(".api.riotgames.com/tft/summoner/v1/summoners/by-name/");
		sb.append(dto.getId());
		sb.append("?api_key=");
		sb.append(APIKey);
		System.out.println(sb.toString());
		JSONObject json = null;
		try {
			json = JSONReader.readJsonFromUrl(sb.toString());
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	    System.out.println(json.toString());
	    String name = json.get("name").toString();
	    String profileIconId = json.get("profileIconId").toString();
	    String revisionDate = json.get("revisionDate").toString();
	    String summonerLevel = json.get("summonerLevel").toString();
	    String puuid = json.get("puuid").toString();
	    System.out.println(name  + " : " + puuid + " : " + profileIconId + " : " + revisionDate + " : " + summonerLevel);
	}
*/
	@GetMapping("/hello")
	public String hello() {
		return "hello, World!";
	}
	
	@PostMapping("/posts")
	public void savePosts(@RequestBody PostsSaveRequestDto dto) {
		System.out.println("WebRestController savePosts activated");
		postsRepository.save(dto.toEntity());
		System.out.println(dto.getAuthor() + dto.getContent() + dto.getTitle());
	}
	
	@PostMapping("/delete")
	public void deletePosts(@RequestBody PostsDeleteRequestDto dto) {
		System.out.println("WebRestController deletePosts activated");
		try {
			Optional<Posts> posts = postsRepository.findById(dto.getId());
			System.out.println(posts.get().getId());
			postsRepository.deleteById(posts.get().getId());
		}catch(EmptyResultDataAccessException e){
			System.out.println(dto.getId() + " is not exist");
		}
	}
	
	@PostMapping("/update")
	public void updatePosts(@RequestBody PostsUpdateRequestDto dto) {
		System.out.println("WebRestController deletePosts activated");
		System.out.println("request data : " + dto.getId() + "," +dto.getAuthor() + "," +  dto.getContent() + dto.getTitle());
		try{
			Optional<Posts> posts = postsRepository.findById(dto.getId());
			System.out.println(dto.getId() + " -> "+posts.get());
			Long id = posts.get().getId();
			String author = dto.getAuthor();
			String content = dto.getContent();
			String title = dto.getTitle();
			postsRepository.update(id,author,content,title);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(dto.getId() + " -> not exist");
		}
	}
	
}
