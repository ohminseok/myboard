package com.myboard.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
	
	private Long idx;
	
	private String title;
	
	private String content;
	
	private String writer;
	
	private int viewCnt;
	
	private String delete_Yn;
	
	private LocalDateTime insertTime;
	
	private LocalDateTime updateTime;
	
	private LocalDateTime deleteTime;

}
