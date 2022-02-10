package contactmanager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;


public class UpdateGroup {

    ArrayList<Person> contactList = new ArrayList<Person>();


    private JFrame mainFrameGroups;
    private JPanel topPanelC,leftPanelC,rightPanelC;
    private JLabel title,title2;


    public UpdateGroup(int GiD,Object groupName,String GDesc) throws NumberFormatException, IOException {
        mainFrameGroups = new JFrame("Contacts");
        mainFrameGroups.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        mainFrameGroups.setSize(500,500);
        mainFrameGroups.setLayout(new BorderLayout(8,6));

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

        title2 = new JLabel("Update Group ");
        title2.setFont(new Font("serif",Font.BOLD,15));
        title2.setForeground(Color.RED);



        leftPanelC.add(title2);

        //rightpanel
        rightPanelC = new JPanel();
        rightPanelC.setLayout(new BorderLayout());
        rightPanelC.setBackground(Color.CYAN);


        //contacts Panel components
        JPanel groupDisplay = new JPanel();
        groupDisplay.setLayout(new BoxLayout(groupDisplay,BoxLayout.PAGE_AXIS));
        groupDisplay.setPreferredSize(new Dimension(250,300));
        rightPanelC.add(groupDisplay,BorderLayout.EAST);

               

               //groupname field
      JTextField  GN= new JTextField(); GN.setMaximumSize(new Dimension(300, 20));
        JLabel GNtext =  new JLabel("Group Name :");    
     GNtext.setLabelFor(GN);
     GN.setText(groupName.toString());
//Description field
    JTextField desc = new JTextField(); 
    desc.setMaximumSize(new Dimension(300, 20));
        JLabel desctext =new JLabel("Description :");
        desctext.setLabelFor(desc);
        desc.setText(GDesc);
  //first row
  JPanel one = new JPanel();
  one.setLayout(new BoxLayout(one,BoxLayout.LINE_AXIS));
  one.add(GNtext);
  one.add(GN);
  groupDisplay.add(one);
//scndrow
JPanel two = new JPanel();
two.setLayout(new BoxLayout(two,BoxLayout.LINE_AXIS));
two.add(desctext);
two.add(desc);
groupDisplay.add(two);

  groupDisplay.add(Box.createRigidArea(new Dimension(50,25))); //table lable + invisible component for margin and spacing
                      
  //insering contacts in table
  File file = new File("contacts.txt");
  File GroupFile = new File(groupName+".txt"); //selected group file
  ArrayList<Person> groupContacts = new ArrayList<Person>(); ///already existing contacts
  try ( //completing all contacts array
   
      BufferedReader br = new BufferedReader(new FileReader(file))) {
     
          String nameNumberString;
          while ((nameNumberString = br.readLine()) != null) {

              // reading line from the file.
              // splitting the string to get name and
              // number

             String[] lineSplit = nameNumberString.split(" ");
             int Id = Integer.parseInt(lineSplit[0]);
             String FirstName = lineSplit[1];
             String LastName = lineSplit[2];
             String city = lineSplit[3];
             //getting phone numbers
             String phoneNumbers = lineSplit[4];
 

              // Print the contact data

              contactList.add(new Person(Id,FirstName, LastName, city, phoneNumbers) );
               
          }System.out.println(contactList);
                 
      }
      try (
   
        BufferedReader br2 = new BufferedReader(new FileReader(GroupFile))) {
       
            String nameGroupString;
            while ((nameGroupString = br2.readLine()) != null) {
  
                // reading line from the file.
                // splitting the string to get name and
                // number
  
               String[] lineSplit = nameGroupString.split(" ");
               int IdG = Integer.parseInt(lineSplit[0]);
               String FirstNameG = lineSplit[1];
               String LastNameG = lineSplit[2];
               String cityG = lineSplit[3];
               //getting phone numbers
               String phoneNumbersG = lineSplit[4];
   
  
                // Print the contact data
  
                groupContacts.add(new Person(IdG,FirstNameG, LastNameG, cityG, phoneNumbersG) );
                 
            }
                   
        }
                    //first table
                     //table of lists
                     String[] colName = {"Contact Name","City","Add To Group"};
                     Object rowData[][]=new Object[contactList.size()][3];
                    
                            AbstractTableModel TableModel = new AbstractTableModel(){
                                public int getColumnCount(){
                                  return colName.length;
                                }
                                public int getRowCount(){
                                  return rowData.length;
                                }
                                public String getColumnName( int col){
                                  return colName[col];
                                }
                                public Object getValueAt( int row, int column){
                                    System.out.println(rowData[row][column]);
                                  return rowData[row][column];
                                }
                                public Class getColumnClass(int c) {
                                    return getValueAt(0, c).getClass();
                                }
                             
                                public void setValueAt(Object value, int row, int column) {
                                    rowData[row][column] = value;
                                    fireTableCellUpdated(row, column);
                                  }

                              }
                            ;
                            JTable groupsTable = new JTable(TableModel){
                                public boolean isCellEditable(int row, int column){
                                    if (column < 2) {
                                        return false;
                                    } else {
                                        return true;
                                    }
                           
                                } }; //overriding the cell is editable
                        JScrollPane scroll =new JScrollPane(groupsTable);
                            groupsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                            groupsTable.setPreferredScrollableViewportSize(new Dimension(20, 20));
                            groupsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                            groupDisplay.add(groupsTable);
                            groupDisplay.add(scroll, BorderLayout.CENTER);
                            
                            
     //ajouter les objects contacts a la list model
     for(int i = 0 ; i <contactList.size();i++){
               

            String FirstName1 = contactList.get(i).getFirstname();
            String LastName1 = contactList.get(i).getLastname();
            String city1 = contactList.get(i).getCity();

               rowData[i][0] = FirstName1+" "+LastName1;
                rowData[i][1] = city1;
                rowData[i][2] = Boolean.FALSE; 

   }

                     //overriding chechbox value if row existes in group by using ID value
                     for(int j =0;j<groupContacts.size();j++){
                      int CID= groupContacts.get(j).getId();
                  
                      rowData[CID-1][2] = Boolean.TRUE; 
                  
                  }


            //buttons panel
            JPanel buttonPane = new JPanel();
            JButton save = new JButton("Save Group");
            JButton cancel = new JButton("Cancel");
            buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
            buttonPane.add(save);buttonPane.add(Box.createRigidArea(new Dimension(10,5))); //invis comp for spacing
            buttonPane.add(cancel);
            groupDisplay.add(Box.createRigidArea(new Dimension(50,80))); //invis comp for spacing
            groupDisplay.add(buttonPane);


            
        //add componants
        mainFrameGroups.add(topPanelC,BorderLayout.NORTH);
        mainFrameGroups.add(leftPanelC,BorderLayout.WEST); 
        mainFrameGroups.add(rightPanelC,BorderLayout.CENTER);       
        mainFrameGroups.setVisible(true);


        //actionListeners
        save.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
    
    if (GN.getText() != null) {
      //creating textfile for group contacts
String GroupName=GN.getText();
String GroupDesc = desc.getText();


          ArrayList<Person> contactsToAdd = new ArrayList<Person>(); //arraylist for contacts to add
    for(int x = 0; x<rowData.length;x++){
    if((boolean) rowData[x][2]){  //adding to arraylist contacts to add

                contactsToAdd.add(contactList.get(x));
      }

} 

FileInputStream fIstream;
try {
    fIstream = new FileInputStream(GroupName+".txt");
BufferedReader bReader = new BufferedReader(new InputStreamReader(fIstream));
StringBuilder groupContent = new StringBuilder();

//overwrite content to file
for(int z = 0 ; z<contactsToAdd.size();z++){
  String gContact = contactsToAdd.get(z).getId() + " " + contactsToAdd.get(z).getFirstname() + " "+
  contactsToAdd.get(z).getLastname()+ " " + contactsToAdd.get(z).getCity()+ " " + contactsToAdd.get(z).getPhonenumber();
  groupContent.append(gContact);
  groupContent.append("\n");
}

FileWriter fWrite = new FileWriter(GroupName+".txt") ;
BufferedWriter output = new BufferedWriter(fWrite);
output.write(groupContent.toString());
output.close(); 
bReader.close();


//exiting mainframe to groups main
mainFrameGroups.dispose();
new Group();

} catch (FileNotFoundException e1) {
    e1.printStackTrace();
} catch (IOException e1) {
    e1.printStackTrace();
}

        

        //Close the input stream
       


     
    }
}
            });


    
   
            
        

        cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int a =JOptionPane.showConfirmDialog(mainFrameGroups, "Are you sure you want to exit?", "EXIT", JOptionPane.YES_NO_OPTION);
                if(a == JOptionPane.YES_OPTION){
                     mainFrameGroups.dispose();
         try {
            new Group();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
                             } //if end
            }
            
        });
    }
    
}
