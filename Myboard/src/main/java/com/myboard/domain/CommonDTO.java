package com.myboard.domain;

import java.time.LocalDateTime;

import com.myboard.paging.Criteria;
import com.myboard.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO extends Criteria {
	
	private PaginationInfo paginationInfo;
	
	private String deleteYn;
	
	private LocalDateTime insertTime;
	
	private LocalDateTime updateTime;
	
	private LocalDateTime deleteTime;
	
}
