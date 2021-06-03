package com.pearl.paging;

import lombok.Data;

@Data
public class PaginationInfo {

	/** 페이징 계산에 필요한 파라미터들이 담긴 클래스 */
	private Criteria criteria;

	/** 전체 데이터 개수 */
	private int totalCount;

	/** 전체 페이지 개수 */
	private int totalPageCount;

	/** 페이지 리스트의 첫 페이지 번호 */
	private int firstPage;

	/** 페이지 리스트의 마지막 페이지 번호 */
	private int lastPage;

	/** SQL의 조건절에 사용되는 첫 RNUM */
	private int firstRecordIndex;

	/** SQL의 조건절에 사용되는 마지막 RNUM */
	private int lastRecordIndex;

	/** 이전 페이지 존재 여부 */
	private boolean hasPreviousPage;

	/** 다음 페이지 존재 여부 */
	private boolean hasNextPage;

	public PaginationInfo(Criteria criteria) {
		if (criteria.getCurrentPage() < 1) {
			criteria.setCurrentPage(1);
		}
		if (criteria.getAmount() < 1 || criteria.getAmount() > 100) {
			criteria.setAmount(10);
		}
		if (criteria.getPageAmount() < 5 || criteria.getPageAmount() > 20) {
			criteria.setPageAmount(10);
		}
		this.criteria = criteria;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;

		if (totalCount > 0) {
			calculation();
		}
	}

	private void calculation() {

		/* 전체 페이지 수 (현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를 저장) */
		totalPageCount = ((totalCount - 1) / criteria.getAmount()) + 1;
		if (criteria.getCurrentPage() > totalPageCount) {
			criteria.setCurrentPage(totalPageCount);
		}

		/* 페이지 리스트의 첫 페이지 번호 */
		firstPage = ((criteria.getCurrentPage() - 1) / criteria.getPageAmount()) * criteria.getPageAmount() + 1;

		/* 페이지 리스트의 마지막 페이지 번호 (마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지에 전체 페이지 수를 저장) */
		lastPage = firstPage + criteria.getPageAmount() - 1;
		if (lastPage > totalPageCount) {
			lastPage = totalPageCount;
		}

		/* SQL의 조건절에 사용되는 첫 RNUM */
		firstRecordIndex = (criteria.getCurrentPage() - 1) * criteria.getAmount();

		/* SQL의 조건절에 사용되는 마지막 RNUM */
		lastRecordIndex = criteria.getCurrentPage() * criteria.getAmount();

		/* 이전 페이지 존재 여부 */
		hasPreviousPage = firstPage != 1;

		/* 다음 페이지 존재 여부 */
		hasNextPage = (lastPage * criteria.getAmount()) < totalCount;
	}

}
