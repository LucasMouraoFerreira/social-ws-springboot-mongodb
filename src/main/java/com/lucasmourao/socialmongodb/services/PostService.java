package com.lucasmourao.socialmongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasmourao.socialmongodb.domain.Post;
import com.lucasmourao.socialmongodb.repositories.PostRepository;
import com.lucasmourao.socialmongodb.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;	
		
	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public List<Post> findByTitle(String text){
		return repository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime()+24*60*60*1000);
		return repository.fullSearch(text, minDate, maxDate);
	}
}
