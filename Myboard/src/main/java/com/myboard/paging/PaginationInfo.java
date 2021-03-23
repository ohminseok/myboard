package com.myboard.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter //화면 하단에 보이는 페이지 개수를 계산하는 용도
public class PaginationInfo {
	
	private Criteria criteria;
	//전체 데이터 개수
	private int totalRecordCount;
	//전체 페이지 개수
	private int totalPageCount;
	//페이지 리스트의 첫 페이지 번호
	private int firstPage;
	//페이지 리스트의 마지막 페이지 번호
	private int lastPage;
	//sql의 조건절에 사용되는 첫 rnum
	private int firstRecordIndex;
	//sql의 조건절에 사용되는 마지막 rnum
	private int lastRecordIndex;
	//이전 페이지 존재 여부
	private boolean hasPreviousPage;
	//다음 페이지 존재 여부
	private boolean hasNextPage;
	
	public PaginationInfo(Criteria criteria) {
		if (criteria.getCurrentPageNo() < 1) {
			criteria.setCurrentPageNo(1);
		}
		if (criteria.getRecordsPerPage() < 1 || criteria.getRecordsPerPage() > 100) {
			criteria.setRecordsPerPage(10);
		}
		
		if (criteria.getPagaSize() < 5 || criteria.getPagaSize() > 20) {
			criteria.setPagaSize(10);
		}
		
		this.criteria = criteria;
	}
	
	public void setTotalRecordCount(int totalRecordCount) {
		if (totalRecordCount > 0) {
			calculation();
		}
	}
	
	private void calculation() {
		//전체 페이지 수 (현재 페이지 번호가 전체 페이지 수보다 크면 현제 페이즈 번호에 전체 페이지 수를 저장)
		totalPageCount = ((totalRecordCount -1) / criteria.getRecordsPerPage()) +1;
	
		if (criteria.getCurrentPageNo() > totalPageCount) {
			criteria.setCurrentPageNo(totalPageCount);
		}
		
		//페이지 리스트의 첫 페이지 번호
		firstPage = ((criteria.getCurrentPageNo() -1) / criteria.getPagaSize()) * criteria.getPagaSize() + 1;
		
		//페이지 리스트의 마지막 페이지 번호(마지막페이지가 전체페이지 수보다 크면 마지막 페이지에 전체 페이지 수를 저장)
		lastPage = (firstPage + criteria.getPagaSize())-1;
		
		if (totalPageCount > lastPage) {
			lastPage = totalPageCount;
		}
		//sql의 조건절에 사용되는 첫 rnum
		firstRecordIndex = (criteria.getCurrentPageNo() - 1) * criteria.getRecordsPerPage();
		//sql의 조건절에 사용되는 마지막 rnum
		lastRecordIndex = criteria.getCurrentPageNo() * criteria.getRecordsPerPage();
		//이전 페이지 존재 여부
		hasPreviousPage = firstPage != 1;
		//다음 페이지 존재 여부
		hasNextPage = (lastPage * criteria.getRecordsPerPage()) < totalRecordCount;
	}
	
	
}
