package com.pearl.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pearl.domain.PicDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileUtils {
	public List<PicDTO> parseFileInfo(Long fundNum, MultipartHttpServletRequest mt) throws Exception {
		if (ObjectUtils.isEmpty(mt)) {
			return null;
		}
		List<PicDTO> fileList = new ArrayList<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/" + current.format(format);
		File file = new File(path);
		log.info(">>>>>1. file: "+ file);
		if (file.exists() == false) {
			file.mkdirs();
		}
		
		Iterator<String> iterator = mt.getFileNames();
		log.info(">>>>>1.2. iterator: " + iterator);
		String newFileName, originalFileExtension, contentType;

		while (iterator.hasNext()) {
			List<MultipartFile> list = mt.getFiles(iterator.next());
			for (MultipartFile mtF : list) {
				if (mtF.isEmpty() == false) {
					log.info(">>>>>1.3. mtf.isempty " );
					contentType = mtF.getContentType();
					if(ObjectUtils.isEmpty(contentType)) {
						break;
						
					}else {
						if(contentType.contains("image/jpg")) {
							originalFileExtension = ".jpg";
						}
						else if(contentType.contains("image/png")) {
							originalFileExtension = ".png";
						}
						else if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpeg";
						}
						else if(contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						}
						else {
							log.info(">>>>>1.4. break");
							break;
						}
					}
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					PicDTO pic = new PicDTO();
					pic.setFundNum(fundNum);
					log.info(">>>>>2. fundNum: "+ fundNum);
					log.info(">>>>>3. picfundNum: "+ pic.getFundNum());
					pic.setFileSize(mtF.getSize());
					log.info(">>>>>4. filesize: "+ pic.getFileSize());
					pic.setOriginalFileName(mtF.getOriginalFilename());
					log.info(">>>>>5. getOriginalFilename: "+ pic.getOriginalFileName());
					pic.setStoredFilePath(path+"/" +newFileName);
					log.info(">>>>>6. StoredFilePath: "+ pic.getStoredFilePath());
					fileList.add(pic);
					
					file = new File(path+"/" + newFileName);
					mtF.transferTo(file);
					
				}
			}
		}
		return fileList;
	}
}
