
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sam
 */
public class Home extends javax.swing.JFrame {

    private byte[] finali = null;
    Connection conn = Dbconfig.getCon();
    ResultSet rs = null;
    PreparedStatement pst = null;
    String Path = null;

    public Home() {
        initComponents();
        this.setLocationRelativeTo(null);
        StudentsTableQuery();
        shortStudDetails();
        setDateAndTime();

        shortStudDoc();
    }

    private void StudentsTableQuery() {
        try {

            String sql = "select Student_Id,FirstName,LastName,Department,Series,Age,Height, Weight,Gender,Blood From stundent_info ";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            studentInfotable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void shortStudDetails() {
        String sql = "select Student_Id,FirstName from stundent_info";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            ShortStudDetails.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }

    private void shortStudDoc() {
        String sql = "select * from Doc_table";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tblDocument.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }

    public void getValues() {
        try {
            txtStudentId.setText(rs.getString("Student_Id"));
            txtStudentFname.setText(rs.getString("FirstName"));
            txtStudentLname.setText(rs.getString("LastName"));
            txtStudentDepart.setText(rs.getString("Department"));
            txtStudentSeries.setText(rs.getString("Series"));
            txtStudentAge.setText(rs.getString("Age"));
            txtStudentHeight.setText(rs.getString("Height"));
            txtStudentweight.setText(rs.getString("Weight"));
            txtStudentgender.setSelectedItem(rs.getString("Gender"));
            txtStudentblood.setText(rs.getString("Blood"));

            byte[] bin = rs.getBytes("Photo");
            ImageIcon finalf = new ImageIcon(Customizeimg(bin, ImageJlable.getWidth(), ImageJlable.getHeight()));
            ImageJlable.setIcon(finalf);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "no resuts ");
        }

    }

    public Image Customizeimg(byte[] img, int w, int h) {
        BufferedImage toBeRetuned = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        //Draw Image
        try {
            Graphics2D g2 = toBeRetuned.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            //convert byte array into baffered image
            ByteArrayInputStream convertedimg = new ByteArrayInputStream(img);
            BufferedImage bfimage = ImageIO.read(convertedimg);
            //Draw Image
            g2.drawImage(bfimage, 0, 0, w, h, null);
            g2.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return toBeRetuned;
    }

    public void setDateAndTime() {
        Calendar cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        DateJMi.setText("logged in at: " + month + "/" + day + "/" + year);
        DateJMi.setForeground(Color.BLUE);

        int sec = cal.get(Calendar.SECOND);
        int min = cal.get(Calendar.MINUTE);
        int hr = cal.get(Calendar.HOUR_OF_DAY);
        TimeJMi.setText(" Time: " + hr + ":" + min + ":" + sec);
        TimeJMi.setForeground(Color.red);

    }

    public void clearFields() {
        txtStudentId.setText(null);
        txtStudentFname.setText(null);
        txtStudentLname.setText(null);
        txtStudentDepart.setText(null);
        txtStudentSeries.setText(null);
        txtStudentAge.setText(null);
        txtStudentHeight.setText(null);
        txtStudentweight.setText(null);
        txtStudentgender.setSelectedItem("Male");
        txtStudentblood.setText(null);
        ImageJlable.setIcon(null);
        textImageUpload.setText(null);
      
        
    }

    public static BufferedImage getScreenshot(Component com) {
        BufferedImage image = new BufferedImage(com.getWidth(), com.getHeight(), BufferedImage.TYPE_INT_RGB);
        com.paint(image.getGraphics());
        return image;
    }

    private static void saveSreenshot(Component com, String Filename) throws IOException {
        BufferedImage img = getScreenshot(com);
        ImageIO.write(img, "png", new File(Filename));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        SingOutbtn = new javax.swing.JButton();
        OnlineHelpToolbarIt = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentInfotable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDocument = new javax.swing.JTable();
        DocumentAttach = new javax.swing.JButton();
        DocumentAdd = new javax.swing.JButton();
        DocumentDelete = new javax.swing.JButton();
        DocumentClr = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtDocId = new javax.swing.JTextField();
        txtDocStudId = new javax.swing.JTextField();
        txtDocName = new javax.swing.JTextField();
        txtDocAttach = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSubject = new javax.swing.JTextField();
        txtTo = new javax.swing.JTextField();
        txtFrom = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaMessagebody = new javax.swing.JTextArea();
        txtattachFile = new javax.swing.JTextField();
        txtAttachName = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnAttach = new javax.swing.JButton();
        btnSendMail = new javax.swing.JButton();
        txtPasswordFiedl = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        HelpSearchBtn = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        AddToDb = new javax.swing.JButton();
        DeleteDbsBtn = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        editdb = new javax.swing.JButton();
        PanelStudentInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtStudentId = new javax.swing.JTextField();
        txtStudentFname = new javax.swing.JTextField();
        txtStudentLname = new javax.swing.JTextField();
        txtStudentDepart = new javax.swing.JTextField();
        txtStudentSeries = new javax.swing.JTextField();
        txtStudentblood = new javax.swing.JTextField();
        txtStudentAge = new javax.swing.JTextField();
        txtStudentHeight = new javax.swing.JTextField();
        txtStudentweight = new javax.swing.JTextField();
        txtStudentgender = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ShortStudDetails = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        imagePanel = new javax.swing.JPanel();
        ImageJlable = new javax.swing.JLabel();
        textImageUpload = new javax.swing.JTextField();
        JbnUploadImage = new javax.swing.JButton();
        saveImagebutton = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        CloseItm = new javax.swing.JMenuItem();
        exitItem = new javax.swing.JMenuItem();
        SniperJMI = new javax.swing.JMenu();
        JmenuSnipper = new javax.swing.JMenuItem();
        JhelpMenu = new javax.swing.JMenu();
        OfflineHelpIt = new javax.swing.JMenuItem();
        mitWebHelp = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        DateJMi = new javax.swing.JMenu();
        TimeJMi = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        SingOutbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/if_40_Close_Switch_Off_Power_Switcher_Button_2142692.png"))); // NOI18N
        SingOutbtn.setText("SignOut");
        SingOutbtn.setFocusable(false);
        SingOutbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SingOutbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        SingOutbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SingOutbtnActionPerformed(evt);
            }
        });
        jToolBar1.add(SingOutbtn);

        OnlineHelpToolbarIt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Help-Support-icon.png"))); // NOI18N
        OnlineHelpToolbarIt.setText("Help");
        OnlineHelpToolbarIt.setFocusable(false);
        OnlineHelpToolbarIt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        OnlineHelpToolbarIt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        OnlineHelpToolbarIt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnlineHelpToolbarItActionPerformed(evt);
            }
        });
        jToolBar1.add(OnlineHelpToolbarIt);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action Pane", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 12), new java.awt.Color(102, 255, 255))); // NOI18N

        studentInfotable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        studentInfotable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        studentInfotable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentInfotableMouseClicked(evt);
            }
        });
        studentInfotable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                studentInfotableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(studentInfotable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 576, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Data Table", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1443, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Chart", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1443, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Statistics", jPanel5);

        jPanel12.setBackground(new java.awt.Color(204, 204, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblDocument.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDocument.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDocumentMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblDocument);

        DocumentAttach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Editing-Attach-icon.png"))); // NOI18N
        DocumentAttach.setText("Attach");
        DocumentAttach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocumentAttachActionPerformed(evt);
            }
        });

        DocumentAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Add-group-icon.png"))); // NOI18N
        DocumentAdd.setText("Add");
        DocumentAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocumentAddActionPerformed(evt);
            }
        });

        DocumentDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/document-delete-icon.png"))); // NOI18N
        DocumentDelete.setText("Delete");
        DocumentDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocumentDeleteActionPerformed(evt);
            }
        });

        DocumentClr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Clear-icon.png"))); // NOI18N
        DocumentClr.setText("Clear");
        DocumentClr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocumentClrActionPerformed(evt);
            }
        });

        jLabel17.setText("Doc Name:");

        jLabel18.setText("Doc Id:");

        jLabel19.setText("Student Id:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDocAttach, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDocName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDocId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDocStudId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DocumentAttach)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(DocumentAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DocumentClr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DocumentDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DocumentAttach)
                            .addComponent(txtDocAttach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DocumentAdd)
                            .addComponent(txtDocId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DocumentDelete)
                            .addComponent(txtDocStudId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DocumentClr)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDocName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Documents", jPanel7);

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 51));
        jLabel12.setText("FROM:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 51));
        jLabel13.setText("PASSWORD:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 0));
        jLabel14.setText("TO:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 0));
        jLabel15.setText("SUBJECT:");

        txtAreaMessagebody.setColumns(20);
        txtAreaMessagebody.setRows(5);
        jScrollPane3.setViewportView(txtAreaMessagebody);

        jLabel16.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 204));
        jLabel16.setText("Attachment Name");

        btnAttach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Editing-Attach-icon.png"))); // NOI18N
        btnAttach.setText("Attach");
        btnAttach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachActionPerformed(evt);
            }
        });

        btnSendMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/blue-mail-send-icon.png"))); // NOI18N
        btnSendMail.setText("Send Mail");
        btnSendMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel14))
                            .addComponent(jLabel12))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                            .addComponent(txtSubject)
                            .addComponent(txtPasswordFiedl)
                            .addComponent(txtTo)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel15)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(94, 94, 94)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnSendMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtAttachName)
                                .addComponent(txtattachFile, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(32, 32, 32)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAttach, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(124, 124, 124))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtPasswordFiedl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtattachFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAttach))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAttachName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnSendMail, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(313, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Email", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 153));
        jLabel11.setText("     powered by:  @gatheru innovations");

        txtSearch.setText("search......");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        HelpSearchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/folder-search-icon.png"))); // NOI18N
        HelpSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpSearchBtnActionPerformed(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Commands", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 14), new java.awt.Color(0, 51, 204))); // NOI18N

        AddToDb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Add-group-icon.png"))); // NOI18N
        AddToDb.setText("Add");
        AddToDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToDbActionPerformed(evt);
            }
        });

        DeleteDbsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/document-delete-icon.png"))); // NOI18N
        DeleteDbsBtn.setText("Delete");
        DeleteDbsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteDbsBtnActionPerformed(evt);
            }
        });

        ClearBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Clear-icon.png"))); // NOI18N
        ClearBtn.setText("Clear");
        ClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBtnActionPerformed(evt);
            }
        });

        editdb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Edit-validated-icon.png"))); // NOI18N
        editdb.setText("Edit");
        editdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editdbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DeleteDbsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editdb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddToDb, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddToDb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editdb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DeleteDbsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HelpSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HelpSearchBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        PanelStudentInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Student Info", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 14), new java.awt.Color(0, 51, 153))); // NOI18N
        PanelStudentInfo.setForeground(new java.awt.Color(0, 51, 153));
        PanelStudentInfo.setFont(new java.awt.Font("Agency FB", 0, 11)); // NOI18N

        jLabel1.setText("Student Id:");

        jLabel2.setText("First Name:");

        jLabel3.setText("Last Name:");

        jLabel4.setText("Department:");

        jLabel5.setText("Series:");

        jLabel6.setText("Blood:");

        jLabel7.setText("Gender:");

        jLabel8.setText("Weight:");

        jLabel9.setText("Height:");

        jLabel10.setText("Age:");

        txtStudentgender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        javax.swing.GroupLayout PanelStudentInfoLayout = new javax.swing.GroupLayout(PanelStudentInfo);
        PanelStudentInfo.setLayout(PanelStudentInfoLayout);
        PanelStudentInfoLayout.setHorizontalGroup(
            PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStudentInfoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtStudentId)
                    .addComponent(txtStudentFname)
                    .addComponent(txtStudentLname)
                    .addComponent(txtStudentDepart)
                    .addComponent(txtStudentSeries, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(26, 26, 26)
                .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtStudentAge)
                    .addComponent(txtStudentHeight)
                    .addComponent(txtStudentweight)
                    .addComponent(txtStudentblood)
                    .addComponent(txtStudentgender, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        PanelStudentInfoLayout.setVerticalGroup(
            PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStudentInfoLayout.createSequentialGroup()
                .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelStudentInfoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStudentAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelStudentInfoLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(17, 17, 17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStudentInfoLayout.createSequentialGroup()
                                .addComponent(txtStudentHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStudentweight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStudentgender, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStudentblood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(PanelStudentInfoLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(txtStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStudentFname)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txtStudentLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStudentDepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelStudentInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStudentSeries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(59, 59, 59))
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ShortStudDetails.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ShortStudDetails.setForeground(new java.awt.Color(153, 0, 153));
        ShortStudDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        ShortStudDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShortStudDetailsMouseClicked(evt);
            }
        });
        ShortStudDetails.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ShortStudDetailsKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(ShortStudDetails);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ImageJlable, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addComponent(ImageJlable, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.add(imagePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));
        jPanel10.add(textImageUpload, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 190, 120, 30));

        JbnUploadImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/attachment-icon.png"))); // NOI18N
        JbnUploadImage.setText("Upload");
        JbnUploadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbnUploadImageActionPerformed(evt);
            }
        });
        jPanel10.add(JbnUploadImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 110, -1));

        saveImagebutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Floppy-Small-icon.png"))); // NOI18N
        saveImagebutton.setText("Save");
        saveImagebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveImagebuttonActionPerformed(evt);
            }
        });
        jPanel10.add(saveImagebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 206, -1));

        jMenu3.setText(" File ");

        CloseItm.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        CloseItm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/if_Close_53010.png"))); // NOI18N
        CloseItm.setText("Close");
        CloseItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseItmActionPerformed(evt);
            }
        });
        jMenu3.add(CloseItm);

        exitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        exitItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/if_exit_3226.png"))); // NOI18N
        exitItem.setText("Exit");
        exitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitItemActionPerformed(evt);
            }
        });
        jMenu3.add(exitItem);

        jMenuBar2.add(jMenu3);

        SniperJMI.setText(" Edit ");
        SniperJMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SniperJMIActionPerformed(evt);
            }
        });

        JmenuSnipper.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        JmenuSnipper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/04-Tin-snips-icon.png"))); // NOI18N
        JmenuSnipper.setText("Snip");
        JmenuSnipper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmenuSnipperActionPerformed(evt);
            }
        });
        SniperJMI.add(JmenuSnipper);

        jMenuBar2.add(SniperJMI);

        JhelpMenu.setText(" Help ");

        OfflineHelpIt.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        OfflineHelpIt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/if_Help_Circle_Blue_34225.png"))); // NOI18N
        OfflineHelpIt.setText("Ofline Help");
        OfflineHelpIt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OfflineHelpItActionPerformed(evt);
            }
        });
        JhelpMenu.add(OfflineHelpIt);

        mitWebHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        mitWebHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/if_browser_1646006.png"))); // NOI18N
        mitWebHelp.setText("Web Help");
        mitWebHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitWebHelpActionPerformed(evt);
            }
        });
        JhelpMenu.add(mitWebHelp);

        jMenuBar2.add(JhelpMenu);

        jMenu2.setText(" About ");
        jMenuBar2.add(jMenu2);

        DateJMi.setText(" Date ");
        jMenuBar2.add(DateJMi);

        TimeJMi.setText(" Time ");
        jMenuBar2.add(TimeJMi);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PanelStudentInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelStudentInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseItmActionPerformed
        logIn l = new logIn();
        l.setVisible(true);
        this.dispose();
        if (conn != null) {
            try {
                pst.close();
                rs.close();
                conn.close();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_CloseItmActionPerformed

    private void exitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitItemActionPerformed

    private void SingOutbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SingOutbtnActionPerformed
        logIn l = new logIn();
        l.setVisible(true);
        this.dispose();
        if (conn != null) {
            try {
                rs.close();
                pst.close();
                conn.close();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_SingOutbtnActionPerformed

    private void ShortStudDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShortStudDetailsMouseClicked
        try {
            int index = ShortStudDetails.getSelectedRow();

            String Id = ShortStudDetails.getValueAt(index, 0).toString();

            String sql = "Select * from stundent_info where Student_Id='" + Id + "' ";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                getValues();
            } else {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        } finally {
            try {
                rs.close();
                pst.close();
                //conn.close();
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_ShortStudDetailsMouseClicked

    private void ShortStudDetailsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ShortStudDetailsKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                int index = ShortStudDetails.getSelectedRow();

                String Id = ShortStudDetails.getValueAt(index, 0).toString();

                String sql = "Select * from stundent_info where Student_Id='" + Id + "' ";

                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    getValues();
                } else {

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            } finally {
                try {
                    rs.close();
                    pst.close();
                    //conn.close();
                } catch (Exception e) {
                }

            }
        }
    }//GEN-LAST:event_ShortStudDetailsKeyReleased

    private void studentInfotableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentInfotableMouseClicked
        // TODO add your handling code here:
        //   txtStudentId.setEditable(false);
        try {
            int index = studentInfotable.getSelectedRow();

            String Id = studentInfotable.getValueAt(index, 0).toString();

            String sql = "Select * from stundent_info where Student_Id='" + Id + "' ";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                getValues();
            } else {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        } finally {
            try {
                rs.close();
                pst.close();
                //conn.close();
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_studentInfotableMouseClicked

    private void studentInfotableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentInfotableKeyReleased
//       txtStudentId.setEditable(false);
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                int index = studentInfotable.getSelectedRow();

                String Id = studentInfotable.getValueAt(index, 0).toString();

                String sql = "Select * from stundent_info where Student_Id='" + Id + "' ";

                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    getValues();
                } else {

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            } finally {
                try {
                    rs.close();
                    pst.close();
                    //conn.close();
                } catch (Exception e) {
                }

            }
        }
    }//GEN-LAST:event_studentInfotableKeyReleased

    private void OfflineHelpItActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OfflineHelpItActionPerformed
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "src\\Files\\help.pdf");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_OfflineHelpItActionPerformed

    private void OnlineHelpToolbarItActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnlineHelpToolbarItActionPerformed
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "src\\Files\\help.pdf");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_OnlineHelpToolbarItActionPerformed

    private void mitWebHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitWebHelpActionPerformed
        try {
            String url = "https://www.google.com/search?q=icon+archive&oq=i&aqs=chrome.4.69i59j69i61j69i60l2j69i59j69i60.2676j0j7&sourceid=chrome&ie=UTF-8";
            Desktop.getDesktop().browse(URI.create(url));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_mitWebHelpActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        try {
            String sql = "select * from stundent_info where FirstName=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtSearch.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                getValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        try {
            String sql = "select * from stundent_info where Student_Id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtSearch.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                getValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_txtSearchKeyReleased

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        clearFields();
        // TODO add your handling code here:
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void HelpSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelpSearchBtnActionPerformed
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "src\\Files\\help.pdf");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_HelpSearchBtnActionPerformed

    private void AddToDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToDbActionPerformed
        
        
        if (txtStudentId.getText().equals("")) {
             JOptionPane.showMessageDialog(null, "No record to added","empty Fields", JOptionPane.ERROR_MESSAGE);
          
        }else{
       try {
            String sql = "insert into stundent_info(Student_Id,FirstName,LastName,Department,Series,Age,Height,Weight,Gender,Blood)values(?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtStudentId.getText());
            pst.setString(2, txtStudentFname.getText());
            pst.setString(3, txtStudentLname.getText());
            pst.setString(4, txtStudentDepart.getText());
            pst.setString(5, txtStudentSeries.getText());
            pst.setString(6, txtStudentAge.getText());
            pst.setString(7, txtStudentHeight.getText());
            pst.setString(8, txtStudentweight.getText());
            pst.setString(9, txtStudentgender.getSelectedItem().toString());
            pst.setString(10, txtStudentblood.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Success");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        StudentsTableQuery();
        shortStudDetails();  
        }
       
    }//GEN-LAST:event_AddToDbActionPerformed

    private void DeleteDbsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteDbsBtnActionPerformed

        if (!txtStudentId.getText().equals("")) {
             JOptionPane.showMessageDialog(null, "RECORD WILL BE DELETED","WARNING",JOptionPane.WARNING_MESSAGE);
            int P = JOptionPane.showConfirmDialog(null, "Do you really Want to DELETE?", "delete!!!", JOptionPane.YES_NO_OPTION);
            if (P == JOptionPane.YES_OPTION) {
                try {
                    String sql = "delete from stundent_info where Student_Id=?";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, txtStudentId.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Deleted");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                StudentsTableQuery();
                shortStudDetails();
                clearFields();
            }
        } else {

          JOptionPane.showMessageDialog(null, "No record to Delete","empty Fields", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_DeleteDbsBtnActionPerformed

    private void editdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editdbActionPerformed

        if (!txtStudentId.getText().equals(null)) {
            String sql = " update stundent_info set  FirstName=?,LastName=?, Department=?,Series=?,Age=?,Height=?,Weight=?,Gender=?,Blood=? where Student_Id='" + txtStudentId.getText() + "'";
        try {
            pst = conn.prepareStatement(sql);
            //pst.setString(1,txtStudentId.getText() );
            pst.setString(1, txtStudentFname.getText());
            pst.setString(2, txtStudentLname.getText());
            pst.setString(3, txtStudentDepart.getText());
            pst.setString(4, txtStudentSeries.getText());
            pst.setString(5, txtStudentAge.getText());
            pst.setString(6, txtStudentHeight.getText());
            pst.setString(7, txtStudentweight.getText());
            pst.setString(8, txtStudentgender.getSelectedItem().toString());
            pst.setString(9, txtStudentblood.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Success");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        StudentsTableQuery();
        shortStudDetails();
//        clearFields();
        }else{
       JOptionPane.showMessageDialog(null, "0 records to edit","No Records", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editdbActionPerformed

    private void SniperJMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SniperJMIActionPerformed

    }//GEN-LAST:event_SniperJMIActionPerformed

    private void JmenuSnipperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmenuSnipperActionPerformed
        // TODO add your handling code here:
        try {
            saveSreenshot(PanelStudentInfo, "Panel img.png");
            JOptionPane.showMessageDialog(null, "capure saved");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_JmenuSnipperActionPerformed

    private void JbnUploadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbnUploadImageActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String path = f.getAbsolutePath();
        textImageUpload.setText(path);
//        textImageUpload.setPreferredSize(new Dimension(10, 23));
        try {
            FileInputStream fi = new FileInputStream(f);
            ByteArrayOutputStream baOs = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fi.read(buf)) != -1;) {
                baOs.write(buf, 0, readNum);
            }
            finali = baOs.toByteArray();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_JbnUploadImageActionPerformed

    private void saveImagebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveImagebuttonActionPerformed
        try {
            String sql = "UPDATE  stundent_info set Photo=? where Student_id=?";

            pst = conn.prepareStatement(sql);
            pst.setBytes(1, finali);
            pst.setString(2, txtStudentId.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "image set");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_saveImagebuttonActionPerformed

    private void btnSendMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMailActionPerformed
        final String From = txtFrom.getText();
        String password = txtPasswordFiedl.getText();
        String To = txtTo.getText();
        String Subject = txtSubject.getText();
        String Message = txtAreaMessagebody.getText();
        //SETTING UP EMAIL PROPERTIES
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.socketFactory.ports", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");

        //CREATING A SESSSION FOR AUTHENTICATING USER
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(From, password);
            }
        });
        try {
            //creating the message header
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(From));
            //message.setRecipients(Message.RecipientType.To, InternetAddress.parse(To));
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(To));
            message.setSubject(Subject);
            // message.setText(Message);

            //code to set the maessage text
            MimeBodyPart messagebodyPart = new MimeBodyPart();
            messagebodyPart.setText(Message);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messagebodyPart);
            //code for Attchment
            messagebodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(Path);
            messagebodyPart.setDataHandler(new DataHandler(source));
            messagebodyPart.setFileName(txtAttachName.getText());
            multipart.addBodyPart(messagebodyPart);

            //how to send file
            message.setContent(multipart);
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "mail sent");
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "bad credentials");
            txtFrom.setText(null);
            txtPasswordFiedl.setText(null);
            txtTo.setText(null);
            txtSubject.setText(null);
            txtAttachName.setText(null);
            txtattachFile.setText(null);

        }
    }//GEN-LAST:event_btnSendMailActionPerformed

    private void btnAttachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        Path = f.getAbsolutePath();
        txtattachFile.setText(Path);
        txtAttachName.setText(Path);

    }//GEN-LAST:event_btnAttachActionPerformed

    private void DocumentAttachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocumentAttachActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        Path = f.getAbsolutePath();
        txtDocAttach.setText(Path);
        //txtAttachName.setText(Path);
    }//GEN-LAST:event_DocumentAttachActionPerformed

    private void DocumentAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocumentAddActionPerformed
         try {
            String sql = "insert into Doc_table(Doc_Id,Student_id,Dac_name,Path)values(?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtDocId.getText());
            pst.setString(2, txtDocStudId.getText());
            pst.setString(3, txtDocName.getText());
            pst.setString(4, txtDocAttach.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Success");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        shortStudDoc();
    }//GEN-LAST:event_DocumentAddActionPerformed

    private void tblDocumentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentMouseClicked
         try {
            int index = tblDocument.getSelectedRow();

            String Id = tblDocument.getValueAt(index, 0).toString();
            String Id2 = tblDocument.getValueAt(index, 3).toString();

            String sql = "Select * from Doc_Table where Doc_Id='" + Id + "' ";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtDocId.setText(rs.getString("Doc_Id"));
                txtDocStudId.setText(rs.getString("Student_Id"));
                txtDocName.setText(rs.getString("Dac_name"));
                txtDocAttach.setText(rs.getString("Path"));
                
                try {
            int p =JOptionPane.showConfirmDialog(null, "do you want to open the file??","Open file",JOptionPane.YES_NO_OPTION);
                    if (p==JOptionPane.YES_OPTION) {
                      Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + Id2);  
                    }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
            } else {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        } finally {
            try {
                rs.close();
                pst.close();
                //conn.close();
            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_tblDocumentMouseClicked

    private void DocumentDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocumentDeleteActionPerformed
         if (!txtDocId.getText().equals("")) {

            int P = JOptionPane.showConfirmDialog(null, "Do you really Want to DELETE?", "delete!!!", JOptionPane.YES_NO_OPTION);
            if (P == JOptionPane.YES_OPTION) {
                try {
                    String sql = "delete from Doc_Table where Doc_Id=?";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, txtDocId.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Deleted");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                shortStudDoc();
                clearFields();
            }
        } else {

            JOptionPane.showMessageDialog(null, "0 fileds selected");
        }
    }//GEN-LAST:event_DocumentDeleteActionPerformed

    private void DocumentClrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocumentClrActionPerformed
           txtDocId.setText(null);
           txtDocAttach.setText(null);
           txtDocName.setText(null);
           txtDocStudId.setText(null);
    }//GEN-LAST:event_DocumentClrActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddToDb;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JMenuItem CloseItm;
    private javax.swing.JMenu DateJMi;
    private javax.swing.JButton DeleteDbsBtn;
    private javax.swing.JButton DocumentAdd;
    private javax.swing.JButton DocumentAttach;
    private javax.swing.JButton DocumentClr;
    private javax.swing.JButton DocumentDelete;
    private javax.swing.JButton HelpSearchBtn;
    private javax.swing.JLabel ImageJlable;
    private javax.swing.JButton JbnUploadImage;
    private javax.swing.JMenu JhelpMenu;
    private javax.swing.JMenuItem JmenuSnipper;
    private javax.swing.JMenuItem OfflineHelpIt;
    private javax.swing.JButton OnlineHelpToolbarIt;
    private javax.swing.JPanel PanelStudentInfo;
    private javax.swing.JTable ShortStudDetails;
    private javax.swing.JButton SingOutbtn;
    private javax.swing.JMenu SniperJMI;
    private javax.swing.JMenu TimeJMi;
    private javax.swing.JButton btnAttach;
    private javax.swing.JButton btnSendMail;
    private javax.swing.JButton editdb;
    private javax.swing.JMenuItem exitItem;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem mitWebHelp;
    private javax.swing.JButton saveImagebutton;
    private javax.swing.JTable studentInfotable;
    private javax.swing.JTable tblDocument;
    private javax.swing.JTextField textImageUpload;
    private javax.swing.JTextArea txtAreaMessagebody;
    private javax.swing.JTextField txtAttachName;
    private javax.swing.JTextField txtDocAttach;
    private javax.swing.JTextField txtDocId;
    private javax.swing.JTextField txtDocName;
    private javax.swing.JTextField txtDocStudId;
    private javax.swing.JTextField txtFrom;
    private javax.swing.JPasswordField txtPasswordFiedl;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStudentAge;
    private javax.swing.JTextField txtStudentDepart;
    private javax.swing.JTextField txtStudentFname;
    private javax.swing.JTextField txtStudentHeight;
    private javax.swing.JTextField txtStudentId;
    private javax.swing.JTextField txtStudentLname;
    private javax.swing.JTextField txtStudentSeries;
    private javax.swing.JTextField txtStudentblood;
    private javax.swing.JComboBox<String> txtStudentgender;
    private javax.swing.JTextField txtStudentweight;
    private javax.swing.JTextField txtSubject;
    private javax.swing.JTextField txtTo;
    private javax.swing.JTextField txtattachFile;
    // End of variables declaration//GEN-END:variables
}
