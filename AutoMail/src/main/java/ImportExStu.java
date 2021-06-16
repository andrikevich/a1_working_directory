import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ImportExStu {


    public static String exStuStr() {
        String exStu = null;
        try (FileInputStream fis = new FileInputStream("c:\\Install\\ExStu.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(fis)))

        {

            String s;
            while ((s = br.readLine()) != null) {

                exStu = s;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exStu;
    }
}