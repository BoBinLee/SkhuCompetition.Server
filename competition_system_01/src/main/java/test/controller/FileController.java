package test.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {
	// property 에서 PATH 속성을 불러온다.
	final static String PROPERTIES_FILE = "test/TestProperties.properties";
	String UPLOAD_PATH = null;
	
	@RequestMapping("/filetest.do")
	public ModelAndView page1(HttpServletRequest request) throws Exception {
		ModelAndView result = new ModelAndView("/test/file");
		try {
			InputStream inputstream = FileController.class.getClass().getClassLoader()
					.getResourceAsStream(PROPERTIES_FILE);
			Properties properties = new Properties();
			properties.load(inputstream);
			UPLOAD_PATH = properties.getProperty("UPLOAD_PATH");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 
		boolean isMultipartContent = ServletFileUpload
				.isMultipartContent(request);
		if (!isMultipartContent) {
			throw new Exception("enctype is not 'multipart/form-data'");
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1 * 1024 * 1024);
		factory.setRepository(new File(UPLOAD_PATH));
		List<FileItem> fileItemList = null;

		fileItemList = new ServletFileUpload(factory).parseRequest(request);

		List<FileItem> resultList = new ArrayList<FileItem>();

		for (FileItem fileItem : fileItemList) {
			if (!fileItem.isFormField()) {
				File diskFile = new File(UPLOAD_PATH, fileItem.getFieldName());
			
				fileItem.write(diskFile);
				resultList.add(fileItem);
				fileItem.delete();
			}
		} 
		result.addObject("resultList", resultList); 
		
		return result;
	}
	
	String getFileName(String pathName){
		String result = "";
		
		
		
		return null;
	}
}
