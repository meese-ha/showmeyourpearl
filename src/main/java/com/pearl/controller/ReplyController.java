package com.pearl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pearl.domain.ReplyVO;
import com.pearl.service.ReplyService;

import lombok.Setter;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Setter(onMethod_ =@Autowired)
	private ReplyService service;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/upload")
	public ResponseEntity<String> upload(ReplyVO vo){
		int result = service.insert(vo);
		return result==1
				? new ResponseEntity<String>("success",HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/{boardNum}")
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("boardNum") int boardNum){
		List<ReplyVO> list = service.list(boardNum);
		return new ResponseEntity<List<ReplyVO>>(list,HttpStatus.OK);
	}
	
	@PreAuthorize("principal.member.memNum == #vo.memNum")
	@PostMapping("/delete")
	public ResponseEntity<String> delete(@RequestBody ReplyVO vo) {
		log.info("Delete>>>>>>>>"+vo);
		service.delete(vo.getReplyNum().intValue());
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@PreAuthorize("principal.member.memNum == #vo.memNum")
	@PostMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody ReplyVO vo) {
		log.info("Update>>>>>>>>"+vo);
		service.update(vo);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
}
