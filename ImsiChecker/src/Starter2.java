public class Starter2 {


    public static void main(String[] args) {

        //WO
      GetterListOfParameters listOfPar = new GetterListOfParameters("i:\\Download\\1.txt");
      HttpClient httpClient = new HttpClient("http://10.131.8.210:8080/webota/",  listOfPar.getListOfParameters());

//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(640,480);
//        JPanel panel = new JPanel();
//        frame.add(panel).setLocation(635,475);
//
//        JCheckBox chkBxLocalSource = new JCheckBox();
//        JCheckBox chkBxResultFile = new JCheckBox();
//        chkBxLocalSource.setSize(20,20);
//        panel.add(chkBxLocalSource).setLocation(40,460);
//        panel.add(chkBxResultFile).setLocation(40,400);
//
//        frame.setVisible(true);
    }
}
