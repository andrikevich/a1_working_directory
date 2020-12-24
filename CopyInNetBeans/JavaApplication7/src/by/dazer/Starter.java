package by.dazer;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Starter extends JFrame implements 
                                         PropertyChangeListener {

    Set<String> lstOfFolders = new TreeSet();
    String filename;
    int count = 0;
    private JButton btnClearAll;
    private JButton btnStartCopy;
    private JPanel deviceChooser;
    private JButton driver2;
    private JButton forderForSave;
    private JPanel jPanel2;
    private JButton karpekoCar;
    private JCheckBox rtu_190A;
    private JCheckBox rtu_CD5E;
    private JCheckBox rtu_CE48;
    private JCheckBox rtu_CE64;
    private JCheckBox rtu_CE68;
    private JCheckBox rtu_CEA0;
    private JCheckBox rtu_DD2A;
    private JCheckBox rtu_FE0A;
    private JProgressBar progressBar;

    private JTextField txtBoxFolderForSaving;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      //clearProgressBar();
    }

   
    
        class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
           
             if (Starter.this.lstOfFolders.size() > 0) {

            Starter.this.progressBar.setIndeterminate(true);
            btnStartCopy.setEnabled(false);

            Starter.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            Iterator var2 = Starter.this.lstOfFolders.iterator();

            while (var2.hasNext()) {
                String item = (String) var2.next();

                try {
                    FromFTPtoLocalDisk.ftpConn("172.16.128.11", "Tems1", "1qaz@WSX", "/LogFiles/" + item, Starter.this.filename);
                    //FromFTPtoLocalDisk.ftpConn("77.74.36.238", "tems", "mkSmet_k6", "/TemsAutomatic/LogFiles/" + item, this.filename);
                } catch (IOException var5) {
                    Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, (String) null, var5);
                }
            }
        }
            return null;
        }
 
       
        
        @Override
        public void done() {
            btnStartCopy.setEnabled(true);
            Starter.this.progressBar.setIndeterminate(false);
            Starter.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            Toolkit.getDefaultToolkit().beep();

            Starter.this.rtu_DD2A.setSelected(false);
            Starter.this.rtu_CE64.setSelected(false);
            Starter.this.rtu_CE68.setSelected(false);
            Starter.this.rtu_190A.setSelected(false);
            Starter.this.rtu_CD5E.setSelected(false);
            Starter. this.rtu_CE48.setSelected(false);
            Starter. this.rtu_CEA0.setSelected(false);
            Starter. this.rtu_FE0A.setSelected(false);
            Starter.this.lstOfFolders.removeAll(Starter.this.lstOfFolders);
            Starter.this.txtBoxFolderForSaving.setText("");
            Starter.this.progressBar.setStringPainted(true);
            Starter.this.progressBar.setString("Its Done");
            
        }
        }
    

    public Starter() {
        this.initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(600, 300);

    }

    private void initComponents() {
        this.deviceChooser = new JPanel();
        this.karpekoCar = new JButton();
        this.rtu_CD5E = new JCheckBox();
        this.rtu_CE48 = new JCheckBox();
        this.rtu_CEA0 = new JCheckBox();
        this.rtu_FE0A = new JCheckBox();
        this.driver2 = new JButton();
        this.rtu_DD2A = new JCheckBox();
        this.rtu_CE64 = new JCheckBox();
        this.rtu_CE68 = new JCheckBox();
        this.rtu_190A = new JCheckBox();
        this.btnClearAll = new JButton();
        this.jPanel2 = new JPanel();
        this.txtBoxFolderForSaving = new JTextField();
        this.forderForSave = new JButton();
        this.btnStartCopy = new JButton();

        this.setTitle("-= FTP Kulakovskij internal =-");
        //deviceChooser.setBackground(Color.gray);

        this.progressBar = new JProgressBar();
        this.setDefaultCloseOperation(3);
        this.karpekoCar.setText("KarpekoCar");
        this.karpekoCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Starter.this.karpekoCarActionPerformed(evt);
            }
        });
        this.rtu_CD5E.setText("CD5E");
        this.rtu_CD5E.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                Starter.this.rtu_CD5EStateChanged(evt);
            }
        });
        this.rtu_CD5E.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Starter.this.rtu_CD5EMouseClicked(evt);
            }
        });
        this.rtu_CD5E.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Starter.this.rtu_CD5EActionPerformed(evt);
            }
        });
        this.rtu_CE48.setText("CE48");
        this.rtu_CE48.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                Starter.this.rtu_CE48StateChanged(evt);
            }
        });
        this.rtu_CE48.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Starter.this.rtu_CE48MouseClicked(evt);
            }
        });
        this.rtu_CEA0.setText("CEA0");
        this.rtu_CEA0.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                Starter.this.rtu_CEA0StateChanged(evt);
            }
        });
        this.rtu_CEA0.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Starter.this.rtu_CEA0MouseClicked(evt);
            }
        });
        this.rtu_FE0A.setText("FE0A");
        this.rtu_FE0A.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                Starter.this.rtu_FE0AStateChanged(evt);
            }
        });
        this.rtu_FE0A.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Starter.this.rtu_FE0AMouseClicked(evt);
            }
        });
        this.driver2.setText("2-nd driver");
        this.driver2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Starter.this.driver2ActionPerformed(evt);
            }
        });
        this.rtu_DD2A.setText("DD2A");
        this.rtu_DD2A.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Starter.this.rtu_DD2AMouseClicked(evt);
            }
        });
        this.rtu_CE64.setText("CE64");
        this.rtu_CE64.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Starter.this.rtu_CE64MouseClicked(evt);
            }
        });
        this.rtu_CE68.setText("CE68");
        this.rtu_CE68.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Starter.this.rtu_CE68MouseClicked(evt);
            }
        });
        this.rtu_190A.setText("190A");
        this.rtu_190A.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Starter.this.rtu_190AMouseClicked(evt);
            }
        });
        this.btnClearAll.setText("Clear All");
        this.btnClearAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Starter.this.btnClearAllActionPerformed(evt);
            }
        });
        GroupLayout deviceChooserLayout = new GroupLayout(this.deviceChooser);
        this.deviceChooser.setLayout(deviceChooserLayout);
        deviceChooserLayout.setHorizontalGroup(deviceChooserLayout.createParallelGroup(Alignment.LEADING).addGroup(deviceChooserLayout.createSequentialGroup().addGroup(deviceChooserLayout.createParallelGroup(Alignment.LEADING).addGroup(deviceChooserLayout.createSequentialGroup().addGap(28, 28, 28).addGroup(deviceChooserLayout.createParallelGroup(Alignment.LEADING).addComponent(this.rtu_CE48).addComponent(this.rtu_CD5E).addComponent(this.rtu_FE0A).addComponent(this.rtu_CEA0))).addGroup(deviceChooserLayout.createSequentialGroup().addGap(44, 44, 44).addComponent(this.karpekoCar).addGap(67, 67, 67).addGroup(deviceChooserLayout.createParallelGroup(Alignment.LEADING).addGroup(deviceChooserLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, deviceChooserLayout.createParallelGroup(Alignment.LEADING).addComponent(this.rtu_DD2A).addComponent(this.rtu_CE64)).addGroup(deviceChooserLayout.createSequentialGroup().addGap(2, 2, 2).addGroup(deviceChooserLayout.createParallelGroup(Alignment.LEADING).addComponent(this.rtu_190A).addComponent(this.rtu_CE68)))).addComponent(this.driver2)))).addPreferredGap(ComponentPlacement.RELATED, 39, 32767).addComponent(this.btnClearAll)));
        deviceChooserLayout.setVerticalGroup(deviceChooserLayout.createParallelGroup(Alignment.LEADING).addGroup(deviceChooserLayout.createSequentialGroup().addContainerGap().addGroup(deviceChooserLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.karpekoCar).addComponent(this.driver2)).addGap(18, 18, 18).addGroup(deviceChooserLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.rtu_CD5E).addComponent(this.rtu_DD2A)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(deviceChooserLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.rtu_CE48).addComponent(this.rtu_CE64).addComponent(this.btnClearAll)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(deviceChooserLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.rtu_CEA0).addComponent(this.rtu_CE68)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(deviceChooserLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.rtu_FE0A).addComponent(this.rtu_190A)).addContainerGap(24, 32767)));
        this.karpekoCar.getAccessibleContext().setAccessibleName("karpekoCar");
        this.txtBoxFolderForSaving.setText("...folder for saving files");
        this.txtBoxFolderForSaving.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Starter.this.txtBoxFolderForSavingActionPerformed(evt);
            }
        });
        this.forderForSave.setText("...");
        this.forderForSave.setActionCommand("Save");
        this.forderForSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Starter.this.forderForSaveActionPerformed(evt);
            }
        });
        GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.txtBoxFolderForSaving, -1, 249, 32767).addGap(18, 18, 18).addComponent(this.forderForSave).addGap(31, 31, 31)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.txtBoxFolderForSaving, -2, -1, -2).addComponent(this.forderForSave)).addContainerGap(-1, 32767)));
        this.btnStartCopy.setBackground(new Color(0, 255, 255));
        this.btnStartCopy.setText("Run");
        this.setResizable(false);

        this.btnStartCopy.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Starter.this.btnStartCopyMouseClicked(evt);
            }
        });
        this.btnStartCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Starter.this.btnStartCopyActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.btnStartCopy, -2, 63, -2).addGap(184, 184, 184)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(43, 43, 43).addComponent(this.deviceChooser, -2, -1, -2)).addGroup(layout.createSequentialGroup().addGap(72, 72, 72).addComponent(this.jPanel2, -2, -1, -2)).addGroup(layout.createSequentialGroup().addGap(135, 135, 135).addComponent(this.progressBar, -2, 166, -2))).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.deviceChooser, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.btnStartCopy, -2, 31, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.progressBar, -2, -1, -2).addContainerGap(14, 32767)));
        this.pack();

    }

    private void karpekoCarActionPerformed(ActionEvent evt) {
        clearProgressBar();

        this.rtu_CD5E.setSelected(true);
        this.lstOfFolders.add("RTU0010F346CD5E");
        this.rtu_CE48.setSelected(true);
        this.lstOfFolders.add("RTU0010F346CE48");
        this.rtu_CEA0.setSelected(true);
        this.lstOfFolders.add("RTU0010F346CEA0");
        this.rtu_FE0A.setSelected(true);
        this.lstOfFolders.add("RTU0010F364FE0A");
    }

    private void forderForSaveActionPerformed(ActionEvent evt) {
        clearProgressBar();
        JFileChooser fileopen = new JFileChooser("\\\\srv-logsblr-002\\e$\\");
        fileopen.setFileSelectionMode(1);
        int ret = fileopen.showDialog((Component) null, "Choose folder for saving files");
        if (ret == 0) {
            this.filename = fileopen.getSelectedFile().getAbsolutePath();
            this.txtBoxFolderForSaving.setText(this.filename);
        }

    }

    private void txtBoxFolderForSavingActionPerformed(ActionEvent evt) {
        clearProgressBar();
    }

    private void driver2ActionPerformed(ActionEvent evt) {
        clearProgressBar();
        this.rtu_DD2A.setSelected(true);
        this.lstOfFolders.add("RTU0010F33FDD2A");
        this.rtu_CE64.setSelected(true);
        this.lstOfFolders.add("RTU0010F346CE64");
        this.rtu_CE68.setSelected(true);
        this.lstOfFolders.add("RTU0010F346CE68");
        this.rtu_190A.setSelected(true);
        this.lstOfFolders.add("RTU0010F329190A");
    }

    private void rtu_CD5EStateChanged(ChangeEvent evt) {
        clearProgressBar();
    }

    private void rtu_CE48StateChanged(ChangeEvent evt) {
        clearProgressBar();
    }

    private void rtu_CEA0StateChanged(ChangeEvent evt) {
       clearProgressBar();
    }

    private void rtu_FE0AStateChanged(ChangeEvent evt) {
        clearProgressBar();
    }

    private void rtu_CD5EActionPerformed(ActionEvent evt) {
        clearProgressBar();
    }

    private void rtu_CD5EMouseClicked(MouseEvent evt) {
        clearProgressBar();
        if (this.rtu_CD5E.isSelected()) {
            this.lstOfFolders.add("RTU0010F346CD5E");
        }

        if (!this.rtu_CD5E.isSelected() && this.lstOfFolders.contains("RTU0010F346CD5E")) {
            this.lstOfFolders.remove("RTU0010F346CD5E");
        }

    }

    private void rtu_FE0AMouseClicked(MouseEvent evt) {
        clearProgressBar();
        if (this.rtu_FE0A.isSelected()) {
            this.lstOfFolders.add("RTU0010F364FE0A");
        }

        if (!this.rtu_FE0A.isSelected() && this.lstOfFolders.contains("RTU0010F364FE0A")) {
            this.lstOfFolders.remove("RTU0010F364FE0A");
        }

    }

    private void rtu_CE48MouseClicked(MouseEvent evt) {
        clearProgressBar();
        if (this.rtu_CE48.isSelected()) {
            this.lstOfFolders.add("RTU0010F346CE48");
        }

        if (!this.rtu_CE48.isSelected() && this.lstOfFolders.contains("RTU0010F346CE48")) {
            this.lstOfFolders.remove("RTU0010F346CE48");
        }

    }

    private void rtu_CEA0MouseClicked(MouseEvent evt) {
        clearProgressBar();
        if (this.rtu_CEA0.isSelected()) {
            this.lstOfFolders.add("RTU0010F346CEA0");
        }

        if (!this.rtu_CEA0.isSelected() && this.lstOfFolders.contains("RTU0010F346CEA0")) {
            this.lstOfFolders.remove("RTU0010F346CEA0");
        }

    }

    private void btnClearAllActionPerformed(ActionEvent evt) {
        clearProgressBar();
        this.rtu_DD2A.setSelected(false);
        this.rtu_CE64.setSelected(false);
        this.rtu_CE68.setSelected(false);
        this.rtu_190A.setSelected(false);
        this.rtu_CD5E.setSelected(false);
        this.rtu_CE48.setSelected(false);
        this.rtu_CEA0.setSelected(false);
        this.rtu_FE0A.setSelected(false);
        this.lstOfFolders.removeAll(this.lstOfFolders);
        this.txtBoxFolderForSaving.setText("");
    }

    private void rtu_DD2AMouseClicked(MouseEvent evt) {
        clearProgressBar();
        if (this.rtu_DD2A.isSelected()) {
            this.lstOfFolders.add("RTU0010F33FDD2A");
        }

        if (!this.rtu_DD2A.isSelected() && this.lstOfFolders.contains("RTU0010F33FDD2A")) {
            this.lstOfFolders.remove("RTU0010F33FDD2A");
        }

    }

    private void btnStartCopyMouseClicked(MouseEvent evt) {
        

    }

    private void btnStartCopyActionPerformed(ActionEvent evt) {
         Task task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
        
    }

    private void rtu_CE64MouseClicked(MouseEvent evt) {
        clearProgressBar();
        if (this.rtu_CE64.isSelected()) {
            this.lstOfFolders.add("RTU0010F346CE64");
        }

        if (!this.rtu_CE64.isSelected() && this.lstOfFolders.contains("RTU0010F346CE64")) {
            this.lstOfFolders.remove("RTU0010F346CE64");
        }

    }

    private void rtu_CE68MouseClicked(MouseEvent evt) {
        clearProgressBar();
        if (this.rtu_CE68.isSelected()) {
            this.lstOfFolders.add("RTU0010F346CE68");
        }

        if (!this.rtu_CE68.isSelected() && this.lstOfFolders.contains("RTU0010F346CE68")) {
            this.lstOfFolders.remove("RTU0010F346CE68");
        }

    }

    private void rtu_190AMouseClicked(MouseEvent evt) {
        clearProgressBar();
        if (this.rtu_190A.isSelected()) {
            this.lstOfFolders.add("RTU0010F329190A");
        }

        if (!this.rtu_190A.isSelected() && this.lstOfFolders.contains("RTU0010F329190A")) {
            this.lstOfFolders.remove("RTU0010F329190A");
        }

    }

    public static void main(String[] args) {
        try {
            LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                LookAndFeelInfo info = var1[var3];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException var5) {
            Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, (String) null, var5);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new Starter()).setVisible(true);
            }
        });
    }

    private void clearProgressBar() {
        progressBar.setString("...");

    }
}
