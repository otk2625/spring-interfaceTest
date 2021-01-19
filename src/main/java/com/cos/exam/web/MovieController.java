package com.cos.exam.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.exam.domain.CommonDto;
import com.cos.exam.domain.Movie;
import com.cos.exam.domain.MovieRepDto;
import com.cos.exam.domain.MovieRepository;

@RestController
public class MovieController {
	MovieRepository movieRepository;

	// 의존성 주입
	public MovieController(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;

	}

	// http://localhost:8080/movie
	// 전체영화 가져오기
	@GetMapping("/movie")
	public List<Movie> findAll() {
		System.out.println("영화 전체 찾기");

		return movieRepository.findAll();
	}

	// http://localhost:8080/movie/id
	// 영화 1건 가져오기(숫자로 구분 = 숫자는 영화의 id값)
	@GetMapping("/movie/{id}")
	public Movie findMovieById(@PathVariable int id) {
		System.out.println("id로 영화 찾기");

		return movieRepository.findMovieById(id);
	}

	// http://localhost:8080/movie/id
	// 영화 등록하기
	// application/json => @ResponseBody 어노테이션 + 오브젝트로 받기
	@CrossOrigin
	@PostMapping("/movie")
	public CommonDto<?> enrollmentMovie(@Valid @RequestBody MovieRepDto movie, BindingResult bindingResult) {
		System.out.println("영화 등록하기");

		if (bindingResult.hasErrors()) {

			System.out.println("실패");
			return new CommonDto<>(500, "fail");
		}

		movieRepository.enrollmentMovie(movie);

		movieRepository.seeList();
		return new CommonDto<>(200, "ok");
	}

	// http://localhost:8080/movie/id
	// 영화 삭제하기
	// application/json => @ResponseBody 어노테이션 + 오브젝트로 받기
	@DeleteMapping("/movie/{id}")
	public CommonDto<?> delete(@PathVariable int id) {
		System.out.println("영화 삭제하기");

		if (movieRepository.deleteMovie(id) == 0) {

			System.out.println("실패");
			return new CommonDto<>(500, "fail");
		}

		movieRepository.seeList();
		return new CommonDto<>(200, "ok");
	}

	// http://localhost:8080/movie/id
	// 영화 수정하기
	// application/json => @ResponseBody 어노테이션 + 오브젝트로 받기
	@PutMapping("/movie/{id}")
	public CommonDto<?> modified(@PathVariable int id, @Valid @RequestBody MovieRepDto movie,
			BindingResult bindingResult) {
		System.out.println("영화 수정하기");

		if (bindingResult.hasErrors()) {

			System.out.println("실패");
			return new CommonDto<>(500, "fail");
		}

		movieRepository.modifiedMovie(id, movie);
		movieRepository.seeList();
		return new CommonDto<>(200, "ok");
	}

}
