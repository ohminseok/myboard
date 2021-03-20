package com.myboard.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

	private Long number;
	
	private String identify;
	
	private String password;
	
	private String email;
	
	private String resign;
	
	private LocalDateTime insertTime;
}
