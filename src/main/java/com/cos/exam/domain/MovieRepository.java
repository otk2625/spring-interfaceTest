package com.cos.exam.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {
	List<Movie> list;
	static int count = 6;
	public MovieRepository() {
		list = new ArrayList<>();

		list.add(new Movie(1,"영화1", 11.1,new Timestamp(System.currentTimeMillis())));
		list.add(new Movie(2,"영화2", 22.2,new Timestamp(System.currentTimeMillis())));
		list.add(new Movie(3,"영화3", 33.3,new Timestamp(System.currentTimeMillis())));
		list.add(new Movie(4,"영화4", 44.4,new Timestamp(System.currentTimeMillis())));
		list.add(new Movie(5,"영화5", 55.5,new Timestamp(System.currentTimeMillis())));
	}
	
	public List<Movie> findAll(){
		
		return list;
	}
	public void seeList() {
		System.out.println(this.list.toString());
	}

	public Movie findMovieById(int id) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id) {
				return list.get(i);
			}
		}
		return null;
	}

	public void enrollmentMovie(MovieRepDto movie) {
		if(movie != null) {
			list.add(new Movie(count,movie.getTitle(),movie.getRating(),new Timestamp(System.currentTimeMillis())));

		}
		count++;
	
	}

	public int deleteMovie(int id) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id) {
				list.remove(i);
				
				return 1;
			}
		}
		return 0;
	}

	public void modifiedMovie(int id, @Valid MovieRepDto movie) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id) {
				list.get(i).setTitle(movie.getTitle());
				list.get(i).setRating(movie.getRating());
			}
		}
		
	}
	
}