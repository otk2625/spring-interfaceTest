package com.cos.exam.domain;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MovieRepDto {

	@NotNull
	@NotBlank
	private String title;
	
	@NotNull
	private double rating;

}
