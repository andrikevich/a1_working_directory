package by.a1.andrikevich.checker;

import org.apache.commons.net.ftp.FTPFile;

import by.a1.andrikevich.ftp.RetrieverFilesFromFtp;
import by.a1.andrikevich.runner.EmptyFldrCheckebaleRunner;
import by.a1.andrikevich.runner.ScanFldrCheckebaleRunner;
import by.a1.andrikevich.runner.UsualFldrCheckebaleRunner;
import by.a1.andrikevich.runner.VolteFldrCheckebaleRunner;
import by.a1.andrikevich.util.RetrieverInputParameters;

public class CheckerImpl {

	private RetrieverInputParameters param = new RetrieverInputParameters();

	public void check() {
		while (true) {

			RetrieverFilesFromFtp client = new RetrieverFilesFromFtp(param.ipAdress, param.loginName, param.loginPwd);

			for (String folder : param.foldersToCheck) {
				FTPFile[] files = client.retrieveFilesArrFromFolder(param.ftpUrl + folder + "/");

				if (files.length > 0) {
					if (param.scanFolders.contains(folder)) {

						new Thread(new ScanFldrCheckebaleRunner(files, folder)).start();
					} else if (param.volteFolders.contains(folder)) {

						new Thread(new VolteFldrCheckebaleRunner(files, folder)).start();
					} else {

						new Thread(new UsualFldrCheckebaleRunner(files, folder)).start();
					}
				} else {
					new Thread(new EmptyFldrCheckebaleRunner(folder)).start();
				}
				
				
				}
			try {
				Thread.sleep(5*60*1000);
				continue;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
