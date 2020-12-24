
package by.dazer;

import javax.swing.JProgressBar;


public class StatusBar implements Runnable {
    private JProgressBar progressBar = new JProgressBar ();
    private int  curStatus = 0;
    Thread thread;
    public StatusBar(JProgressBar progressBar){
        this.progressBar = progressBar;
        thread = new Thread("ProgressBarThread");
        thread.start();
    }


    @Override
    public void run() {
        progressBar.setVisible(true);
        progressBar.repaint();
        progressBar.setIndeterminate(true);


    }

}
