package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.ItemDTO;
import com.pearl.domain.PayDTO;
import com.pearl.mapper.PayMapper;

import lombok.Setter;

@Service
public class PayServiceImpl implements PayService{

	@Setter(onMethod_ = @Autowired)
	private PayMapper mapper;
	
	@Override
	public PayDTO get(Long payNum) {
		return null;
	}

	@Override
	public void insert(PayDTO dto) {
		mapper.insert(dto);
		List<ItemDTO> itemList = dto.getItem();
		for(int i=0;i<itemList.size();i++) {
			ItemDTO item = itemList.get(i);
			item.setPayNum(dto.getPayNum());
			mapper.insertItem(item);
		}
	}

	@Override
	public int delete(Long payNum) {
		// TODO Auto-generated method stub
		return 0;
	}

}
