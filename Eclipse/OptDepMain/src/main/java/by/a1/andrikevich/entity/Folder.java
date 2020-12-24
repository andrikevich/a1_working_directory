package by.a1.andrikevich.entity;



import java.util.List;
import java.util.Map;



import org.springframework.stereotype.Component;

@Component
public class Folder {
	
	//source folder name (in our case FTP's folder name)
	private String folderName;
	
	//Destination folder name (in our case local/network disk's folder name)
	private String folderNameForSaving;
	
	//Subfolders (List of folders) in folder (in our case subfolder of "folderName" on FTP server)
	private List <String> folders;
	
	// Folders are source to be downloaded from. They should be checked by checkbox on jsp page
	private List <String> checkedFolders;
	
	// Map<FolderName,NumberOfFilesInThisFolder> (in our case represent number in each folder on FTP server)
	private Map <String,Integer> quatityOfFiles;
	
	public Folder() {
		
	}

	
	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public List <String> getFolders() {
		return folders;
	}

	public void setFolders(List<String> folders) {
		this.folders = folders;
	}

	public List<String> getCheckedFolders() {
		return checkedFolders;
	}

	public void setCheckedFolders(List<String> checkedFolders) {
		this.checkedFolders = checkedFolders;
	}

	public String getFolderNameForSaving() {
		return folderNameForSaving;
	}

	public void setFolderNameForSaving(String folderNameForSaving) {
		this.folderNameForSaving = folderNameForSaving;
	}

	public Map<String, Integer> getQuatityOfFiles() {
		return quatityOfFiles;
	}

	public void setQuatityOfFiles(Map<String, Integer> quatityOfFiles) {
		this.quatityOfFiles = quatityOfFiles;
	}

	


	}
	
	


