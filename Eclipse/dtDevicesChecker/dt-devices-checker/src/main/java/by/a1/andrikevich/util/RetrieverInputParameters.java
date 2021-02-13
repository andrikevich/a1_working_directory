package by.a1.andrikevich.util;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class RetrieverInputParameters {
	
	private Properties prop = PropertiesUtil.getProperty(InitialData.propertyFile);
	
	public final String ipAdress = prop.getProperty("ftp.ip");
	public final String loginName = prop.getProperty("ftp.user");
	public final String loginPwd = prop.getProperty("ftp.password");
	public final String ftpUrl = prop.getProperty("ftp.url");
	public final List<String> foldersToCheck = 
								(List<String>) Arrays.asList(
										prop.getProperty("checked.folders").split(",")
										);
	public final List<String> scanFolders = 
			(List<String>) Arrays.asList(
					prop.getProperty("scan.folders").split(",")
					);
	public final List<String> volteFolders = 
			(List<String>) Arrays.asList(
					prop.getProperty("volte.folders").split(",")
					);						

}
