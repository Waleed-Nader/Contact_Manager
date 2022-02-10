package contactmanager;


    import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewContact {

    private JFrame mainFrame;
    private JPanel topPanelC,leftPanelC,rightPanelC,rightPanel;
    private JTextField FN,LN,city;
    JLabel phoneTitle;
    private JLabel title,title2;
        
    public NewContact(){

        mainFrame = new JFrame("Contacts");
        mainFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(500,500);
        mainFrame.setLayout(new BorderLayout(8,6));

        //Top Panel
        topPanelC = new JPanel();  
        title = new JLabel("Gestion De Contact");
        title.setFont(new Font("serif",Font.PLAIN,21));
        title.setForeground(Color.BLUE);
        topPanelC.add(title);

        //left PAnel
        leftPanelC = new JPanel();
        leftPanelC.setLayout(new GridLayout(6,1,5,40)); //Rows Columns hgap Vgap
        leftPanelC.setBorder(new EmptyBorder(0, 10, 0, 5));

        title2 = new JLabel("Update Contacts :");
        title2.setFont(new Font("serif",Font.BOLD,15));
        title2.setForeground(Color.RED);
leftPanelC.add(title2);

//firstname
        FN= new JTextField(); FN.setMaximumSize(new Dimension(300, 20));
           JLabel FNtext =  new JLabel("First Name :");    
        FNtext.setLabelFor(FN);
 //lastname       
        LN = new JTextField(); LN.setMaximumSize(new Dimension(300, 20));
           JLabel LNtext =new JLabel("Last Name :");
        LNtext.setLabelFor(LN);
//city
        city = new JTextField(); city.setMaximumSize(new Dimension(300, 20));
            JLabel cityText = new JLabel("City :");
        cityText.setLabelFor(city);
//phonenumber
       phoneTitle = new JLabel("Phone Numbers");

        



        rightPanelC = new JPanel();
        rightPanelC.setBackground(Color.CYAN); 
        rightPanelC.setLayout(new BorderLayout());

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(300,300));
        //first row
      JPanel one = new JPanel();
      one.setLayout(new BoxLayout(one,BoxLayout.LINE_AXIS));
      one.add(FNtext);
      one.add(FN);
      rightPanel.add(one);
     //scndrow
     JPanel two = new JPanel();
     two.setLayout(new BoxLayout(two,BoxLayout.LINE_AXIS));
     two.add(LNtext);
     two.add(LN);
     rightPanel.add(two);
//thirdrow
JPanel three = new JPanel();
three.setLayout(new BoxLayout(three,BoxLayout.LINE_AXIS));
three.add(cityText);
three.add(city);
rightPanel.add(three);
//phone text
phoneTitle.setHorizontalAlignment(JLabel.CENTER);
rightPanel.add(phoneTitle);

//phone numbers table

JPanel tablePanel = new JPanel();
tablePanel.setLayout(new BorderLayout());
// getting phone numbers data 
Object[][] Data = {
    {"",""},
    {"",""},
    {"",""},
    {"",""},
    {"",""}
};


   
//setting columns and table

String[] columnNames = {"Region Code","Phone Number"};
JTable phoneTable = new JTable(Data,columnNames);
tablePanel.add(phoneTable.getTableHeader(), BorderLayout.PAGE_START);
tablePanel.add(phoneTable, BorderLayout.CENTER);
rightPanel.add(tablePanel);


//adding to east border
rightPanelC.add(rightPanel,BorderLayout.EAST);
//

        //add componants
        mainFrame.add(topPanelC,BorderLayout.NORTH);
        mainFrame.add(leftPanelC,BorderLayout.WEST); 
        mainFrame.add(rightPanelC,BorderLayout.CENTER);       
        mainFrame.setVisible(true);

        //actionListeners && Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        rightPanel.add(buttonPanel);

        JButton save = new JButton("Save");
        buttonPanel.add(save);

        //save action listener >>> GetNew DAta>> UPdate LIne in file >> EXIT 
        save.addActionListener(new ActionListener(){

            @Override
        public void actionPerformed(ActionEvent e) {  
          
         String newFirstname = FN.getText();      
         String newLastname = LN.getText();                    
         String newCity= city.getText(); 
         String newPhonenum="";                              //getting phone numbers string by getting values from table 
                                                    //and adding seperator to prepare the string to write into text file 
    for(int t =0; t <phoneTable.getRowCount();t++ ){
        if(phoneTable.getValueAt(t, 0).toString().length()==0 || phoneTable.getValueAt(t, 1).toString().length()==0){
            newPhonenum+="";
        }else{
        newPhonenum+= phoneTable.getValueAt(t, 0).toString()+"/"+phoneTable.getValueAt(t, 1).toString()+";";
        }       
    }
        ///insering data into text file
        
            try {
                // Open the file that is the first
                // command line parameter
                FileInputStream fstream = new FileInputStream("contacts.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String strLine;
                StringBuilder fileContent = new StringBuilder();
                //Read File Line By Line


                int maxId = 0;

                while ((strLine = br.readLine()) != null) {
                    // Print the content on the console
                    System.out.println(strLine);
                    String tokens[] = strLine.split(" ");
                    if (tokens.length > 0) {
                        // Here tokens[0] will have value of ID
                        if ( Integer.parseInt(tokens[0])>maxId) {
                            maxId = Integer.parseInt(tokens[0]);

                            fileContent.append(strLine);
                            fileContent.append("\n"); 
                           
                        }
                         
                         
                    }
                }
                //verifiying input before writing it to file
                    if(newFirstname.trim().isEmpty() || newLastname.trim().isEmpty() || newCity.trim().isEmpty() ){
                        JOptionPane.showMessageDialog(mainFrame, "A contact Must have First Name, Last Name, City, Phone number !","ERROR MESSAGE"
                        , JOptionPane.ERROR_MESSAGE);
                            
                    }else{
                if((strLine = br.readLine()) == null) {
                                   
                  maxId+=1;

                String newLineIn = maxId + " " + newFirstname + " " + newLastname + " " + newCity + " " + newPhonenum;
                fileContent.append(newLineIn);
                fileContent.append("\n");
                
            }
                // Now fileContent will have updated content , which we override into file
                FileWriter fstreamWrite = new FileWriter("contacts.txt");
                BufferedWriter out = new BufferedWriter(fstreamWrite);
                out.write(fileContent.toString());
                out.close();
                
                //Close the input stream
                br.close();
                mainFrame.dispose();
                new Contact();

               }//else end


            } catch (IOException ioe) {
                System.out.println(ioe);
            }
System.out.println(newPhonenum);
                
            }
            
        });

       // >>> return to contact page on cancel
        JButton cancel = new JButton("Cancel");
        buttonPanel.add(cancel);    
        cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            int a =JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to exit?", "EXIT", JOptionPane.YES_NO_OPTION);
            if(a == JOptionPane.YES_OPTION){
                    mainFrame.dispose();
                    try {
                        new Contact();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
              }
                
            }
            
        });    
    }
}


