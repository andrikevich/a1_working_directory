package by.a1.andrikevich.controller;



import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import by.a1.andrikevich.entity.Folder;
import by.a1.andrikevich.util.FtpUtil;

@Controller
@PropertySource("classpath:config.properties")
@RequestMapping("/dtgroup")
public class FtpController {
	
	@Value("${local.folder.url}")
	private String startOfUrlForLocalFolder;
	
	FtpUtil ftpUtil = new FtpUtil();
	
	@GetMapping("/copierFromFtp")
	public String doChoosingFtpFolders (Model model) {

	Folder theFolder = new Folder();
	theFolder.setFolders (ftpUtil.retrieveListOfFolderNames());
	model.addAttribute("folder", theFolder);
	return "copier-from-ftp";
	}
	

	@PostMapping("/copyResult")
	public String doCopierResult (@ModelAttribute ("folder") Folder theFolder, Model model, HttpServletResponse response) {
		String  tmpRetrieveFldrName;

		try {
			tmpRetrieveFldrName = theFolder.getFolderNameForSaving().trim();
			byte[] byteArrTmp = tmpRetrieveFldrName.getBytes("ISO-8859-1");
			String folderForSavTmp = new String (byteArrTmp,"UTF-8");
			theFolder.setFolderNameForSaving(startOfUrlForLocalFolder + folderForSavTmp);
			ftpUtil.doMovingFilesFromFtpToLocalDisk(theFolder);

			Cookie cookie = new Cookie("fld4Sav",folderForSavTmp);
			response.addCookie(cookie); 
			
			
			//TODO Create statistics of folder's files to result.page
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("folder", theFolder);
		model.addAttribute("countOfDownloadedFiles", ftpUtil.retrieveNumberOfDownloadedFiles(theFolder.getQuatityOfFiles()));
		return "copier-result";
	}
}
