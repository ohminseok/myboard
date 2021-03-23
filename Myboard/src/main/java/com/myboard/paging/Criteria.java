package com.myboard.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {

	private int currentPageNo;
	
	private int recordsPerPage;
	
	private int pagaSize;
	
	private String searchKeyword;
	
	private String searchType;
	
	public Criteria() {
		this.currentPageNo = 1;
		this.recordsPerPage = 10;
		this.pagaSize = 10;
	}
	
	public int getStartPage() {
		return (currentPageNo - 1) * recordsPerPage;
	}

}
