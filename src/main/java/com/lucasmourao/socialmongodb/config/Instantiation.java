package com.lucasmourao.socialmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lucasmourao.socialmongodb.domain.Post;
import com.lucasmourao.socialmongodb.domain.User;
import com.lucasmourao.socialmongodb.repositories.PostRepository;
import com.lucasmourao.socialmongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		repository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");		
	
		Post post1 = new Post(null, sdf.parse("21/03/2018"),"Partiu viagem!","Vou viajar pessoal!!",maria);
		
		postRepository.save(post1);
		repository.saveAll(Arrays.asList(maria,alex,bob));
	}

}
