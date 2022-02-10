package contactmanager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Group extends JFrame{

    private static JFrame mainFrameGroups;
    private JPanel topPanelC,leftPanelC,rightPanelC;
    private JButton addNew;
    private JLabel title,title2;
    private String Fname,Lname,Cit,PhoneNum;
    private Object gName;
    int rowIndex ;
    static DefaultTableModel groupsTableModel;
    public static Map<String, ArrayList<Person>> personByGroups;
    public Group() throws IOException{

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

        title2 = new JLabel("Groups :");
        title2.setFont(new Font("serif",Font.BOLD,15));
        title2.setForeground(Color.RED);


        addNew = new JButton("Add New Group");
        addNew.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameGroups.setVisible(false);
                 mainFrameGroups.dispose();
                try {
                
                    new NewGroup();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
            }

        });


        leftPanelC.add(title2);
        leftPanelC.add(addNew);
        //rightpanel
        rightPanelC = new JPanel();
        rightPanelC.setLayout(new BorderLayout());
        rightPanelC.setBackground(Color.CYAN);


        //contacts Panel components
        JPanel groupDisplay = new JPanel();
        groupDisplay.setLayout(new BoxLayout(groupDisplay,BoxLayout.PAGE_AXIS));
        groupDisplay.setPreferredSize(new Dimension(300,300));
        rightPanelC.add(groupDisplay,BorderLayout.EAST);

                //table of lists
                JLabel listTitle = new JLabel("List of Groups");listTitle.setHorizontalAlignment(JLabel.CENTER);
                groupDisplay.add(listTitle); groupDisplay.add(Box.createRigidArea(new Dimension(50,25))); //table lable + invisible component for margin and spacing
                  
                
                //first table

                        groupsTableModel = new DefaultTableModel();
                        
                        JTable groupsTable = new JTable(groupsTableModel){//overriding the cell is editable
                            public boolean isCellEditable(int row, int column){
                                 return false;
                            } }; 
                            groupsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
  
                            groupsTableModel.addColumn("Group name");
                            groupsTableModel.addColumn("Nb.of contacts");



                            
                        groupDisplay.add(groupsTable.getTableHeader(), BorderLayout.PAGE_START);
                        groupDisplay.add(groupsTable, BorderLayout.CENTER);
                        groupDisplay.add(Box.createRigidArea(new Dimension(50,25))); //invis comp for spacing


                        //second table

                        DefaultTableModel scndTableModel = new DefaultTableModel();
                        JTable groupsContactsTable = new JTable(scndTableModel){//overriding the cell is editable
                            public boolean isCellEditable(int row, int column){
                                 return false;
                            } }; 
                            scndTableModel.addColumn("Group name");
                            scndTableModel.addColumn("Nb.of contacts");
                        groupsContactsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        groupDisplay.add(groupsContactsTable.getTableHeader(), BorderLayout.PAGE_START);
                        groupDisplay.add(groupsContactsTable, BorderLayout.CENTER);
   
                        
///display groups list

            File file = new File("groups.txt");
        if (!file.exists()) {
 
            // Create a new file if not exists.
            file.createNewFile();
        }
         String nameNumberString;
         String GroupName ;
       //  String GroupDesc ;

         personByGroups = new HashMap<>(); ///group as key and members a list value
         
         ArrayList<Person> groupMembers;
         ArrayList<Ogroup> aGroup = new ArrayList<Ogroup>(); //group array
        //openning file in REad buffer
                                  try (
     
        BufferedReader br = new BufferedReader(new FileReader(file))) {
       
            while ((nameNumberString = br.readLine()) != null) {

                // reading line from the file.
                // splitting the string to get values

               String[] lineSplit = nameNumberString.split(";");
               int Id = Integer.parseInt(lineSplit[0]);
               GroupName = lineSplit[1];
            String  groupDesc = lineSplit[2];

                aGroup.add(new Ogroup(Id, GroupName, groupDesc));
               groupMembers= new GroupMembers(GroupName).getMembers();
                personByGroups.put(GroupName,new GroupMembers(GroupName).getMembers()); //add themembers through an array list in group member class

                // Print the group data
                
groupsTableModel.insertRow(0, new Object[]{GroupName,groupMembers.size()});
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

 System.out.println(personByGroups);
    



///table listener for row index ana column name and showing group contacts
//initializing contact infos

groupsTable.addMouseListener(new MouseListener() {
                                                

    @Override
    public void mouseClicked(MouseEvent e) {

    rowIndex = groupsTable.getSelectedRow();
   int columnIndex = groupsTable.getSelectedColumn();
   Object ValueRC= groupsTable.getValueAt(rowIndex, columnIndex);//value of group name.
gName = ValueRC;
System.out.println(gName);
       scndTableModel.setRowCount(0);//reset table after each click.
       ArrayList<Person> contactsArray =  personByGroups.get(ValueRC);
     for(int x = 0;x <contactsArray.size();x++){
      
         Fname =contactsArray.get(x).getFirstname();
         Lname = contactsArray.get(x).getLastname();
         Cit = contactsArray.get(x).getCity();
         PhoneNum = contactsArray.get(x).getPhonenumber();
scndTableModel.insertRow(0, new Object[]{Fname+" "+Lname,Cit});


     }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
});




  
            //buttons panel + listeners
            JPanel buttonPane = new JPanel();
            JButton view = new JButton("View"); view.setEnabled(false);

view.addActionListener(new ActionListener(){


    @Override
    public void actionPerformed(ActionEvent e) {

        ArrayList<Person> selectedpersonArr = personByGroups.get(gName);
        int rowContactIndex = selectedpersonArr.size()-1 - groupsContactsTable.getSelectedRow();
        System.out.println(rowContactIndex);
        Person perCon = selectedpersonArr.get(rowContactIndex);
             new View(perCon.getId(),perCon.getFirstname(),perCon.getLastname(),perCon.getCity(),perCon.getPhonenumber());

    }

});
            JButton update = new JButton("Update Group");
            update.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                 mainFrameGroups.dispose();
                Object groupName=  groupsTable.getModel().getValueAt(groupsTable.getSelectedRow(), 0);
               int GID=1;
               String GDesc="";

               for(int s = 0 ; s<aGroup.size();s++){ //getting GID and Gdesc
                    if(groupName.toString().equals(aGroup.get(s).getGroupName())){
                            GID = aGroup.get(s).getgId();
                            GDesc = aGroup.get(s).getGroupDesc();
                    }
               }
                 try {
                    new UpdateGroup(GID,groupName,GDesc); //head to update page
                } catch (NumberFormatException | IOException e1) {
                    e1.printStackTrace();
                }
                    
                }
                
            });



            JButton delete = new JButton("Delete");
            delete.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    int a =JOptionPane.showConfirmDialog(mainFrameGroups, "Are you sure you want to Delete?", "EXIT", JOptionPane.YES_NO_OPTION);
                    if(a == JOptionPane.YES_OPTION){          
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("groups.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            StringBuilder fileContent = new StringBuilder();

            //Read File Line By Line

            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                System.out.println(strLine);
                String tokens[] = strLine.split(";");
                if (tokens.length > 0) {
                    // Here tokens[0] will have value of ID
                    if ( tokens[1].equals(gName)) {
                     
                        String newLine = "";
                        fileContent.append(newLine);
                        File del = new File(gName+".txt");
                        del.delete();
                    } else {
                        // update content as it is
                        fileContent.append(strLine);
                        fileContent.append("\n");
                    }
                }
            }

            // Now fileContent will have updated content , which we override into file
            FileWriter fstreamWrite = new FileWriter("groups.txt");
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
            out.close();
            //Close the input stream
            br.close();


         

        } catch (IOException ioe) {
            System.out.println(ioe);
        }      //reload page
          mainFrameGroups.dispose();
                try {
                    new Group();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }   




    }     
                }     
                
            });

            buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
            buttonPane.add(view);buttonPane.add(Box.createRigidArea(new Dimension(10,5)));
            buttonPane.add(update);buttonPane.add(Box.createRigidArea(new Dimension(10,5))); //invis comp for spacing
            buttonPane.add(delete);
            groupDisplay.add(Box.createRigidArea(new Dimension(40,70))); //invis comp for spacing
            groupDisplay.add(buttonPane,BorderLayout.SOUTH);


//table 2 listener for getting contact info
groupsContactsTable.addMouseListener(new MouseListener(){

    @Override
    public void mouseClicked(MouseEvent e) {
     view.setEnabled(true);

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
});


        //add componants
        mainFrameGroups.add(topPanelC,BorderLayout.NORTH);
        mainFrameGroups.add(leftPanelC,BorderLayout.WEST); 
        mainFrameGroups.add(rightPanelC,BorderLayout.CENTER);       
        mainFrameGroups.setVisible(true);

    }

public static Map<String, ArrayList<Person>> getGroupMap() {
    return personByGroups;
}


}
