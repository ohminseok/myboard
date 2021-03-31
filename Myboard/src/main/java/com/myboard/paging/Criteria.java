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
	
	private int pageSize;
	
	private int searchKeyword;
	
	private int searchType;
	
	public Criteria() {
		this.currentPageNo = 1;
		this.recordsPerPage = 10;
		this.pageSize = 10;
	}
	
	public String makeQueryString(int pageNo) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
							.queryParam("currentPageNo", pageNo)
							.queryParam("recordsPerPage", recordsPerPage)
							.queryParam("pageSize", pageSize)
							.queryParam("searchType", searchType)
							.queryParam("searchKeword", searchKeyword)
							.build()
							.encode();
		
		return uriComponents.toString();
	}
}
