package com.pearl.service;

import com.pearl.domain.PayDTO;

public interface PayService {
	PayDTO get(Long payNum);
	void insert(PayDTO dto);
	int delete(Long payNum);
}
