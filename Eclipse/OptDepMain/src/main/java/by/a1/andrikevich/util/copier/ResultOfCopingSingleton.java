package by.a1.andrikevich.util.copier;

import java.util.HashMap;
import java.util.Map;

public final class ResultOfCopingSingleton {

	
	   // Creation of SINGLETONE
        private static volatile ResultOfCopingSingleton instance;
	
        public static ResultOfCopingSingleton getInstance() {
        	ResultOfCopingSingleton localInstance = instance;
		if (localInstance == null) {
			synchronized (ResultOfCopingSingleton.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new ResultOfCopingSingleton();
				}
			}
		}
		return localInstance;
	}
        
        // SAVING coping results
        
        private Map <String, Integer> mapStatOfFolderFiles = new HashMap<>();

		public Map<String, Integer> getMapStatOfFolderFiles() {
			return mapStatOfFolderFiles;
		}

		  public synchronized void  putToMap (String folderName, int countOfFiles) {
			  this.mapStatOfFolderFiles.put(folderName,countOfFiles);
			  
		  }
		  
		  public void clearResultMap() {
			  mapStatOfFolderFiles = new HashMap<>();  
		  }

        
}
