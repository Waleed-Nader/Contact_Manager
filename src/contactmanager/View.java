
package contactmanager;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class View {
    private JFrame mainFrame;
    private JPanel topPanelC,leftPanelC,rightPanelC,rightPanel;
    private JTextField FN,LN,city;
    JLabel phoneTitle;
    private JLabel title,title2;
    Map<String, ArrayList<Person>> groups=  Group.getGroupMap();

    public View(int Id,String FirName,String LasName,String CiTy,String PhoneNum){

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

        title2 = new JLabel("View Contacts :");
        title2.setFont(new Font("serif",Font.BOLD,15));
        title2.setForeground(Color.RED);
leftPanelC.add(title2);

//firstname
        FN= new JTextField(); FN.setMaximumSize(new Dimension(300, 20));
        FN.setText(FirName);
        FN.setEnabled(false);
           JLabel FNtext =  new JLabel("First Name :");    
        FNtext.setLabelFor(FN);
 //lastname       
        LN = new JTextField(); LN.setMaximumSize(new Dimension(300, 20));
        LN.setEnabled(false);
        LN.setText(LasName);
           JLabel LNtext =new JLabel("Last Name :");
        LNtext.setLabelFor(LN);
//city
        city = new JTextField(); city.setMaximumSize(new Dimension(300, 20));
        city.setEnabled(false);
        city.setText(CiTy);
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
// getting phone numbers data & if phone is greater than 2 digits for area code and 6 digits for number it only shows the 2 and 6 and if lesser 00 0000
Object[][] Data = {
    {"",""},
    {"",""},
    {" "," "},
    {" "," "},
};

String[] PhoneNumArr = PhoneNum.split(";");
   for(int i = 0; i<PhoneNumArr.length;i++){
      String[] codeAndNum = PhoneNumArr[i].split("/");
String areacode=codeAndNum[0] ;
String number= codeAndNum[1];

Data[i][0]=areacode;
Data[i][1]=number;  
        
        
   }

   
//setting columns and table
String[] columnNames = {"Region Code","Phone Number"};

JTable phoneTable = new JTable(Data,columnNames);

phoneTable.setEnabled(false);

tablePanel.add(phoneTable.getTableHeader(), BorderLayout.PAGE_START);
tablePanel.add(phoneTable, BorderLayout.CENTER);

rightPanel.add(tablePanel);

                       //contact groups
//    JLabel groupLabel = new JLabel("Contact Groups");
//    groupLabel.setHorizontalAlignment(JLabel.CENTER);
//    rightPanel.add(groupLabel);
//    
//check Boxes
//Person cont = new Person(Id, FirName, LasName, CiTy, PhoneNum);
//
//// Iterator
//        Iterator<Entry<String, ArrayList<Person>>> new_Iterator
//            = groups.entrySet().iterator();
// 
//        // Iterating every set of entry in the HashMap
//        while (new_Iterator.hasNext()) {
//            Map.Entry<String, ArrayList<Person>> new_Map
//                = (Map.Entry<String, ArrayList<Person>>)
//                      new_Iterator.next();
// 
//JCheckBox ch = new JCheckBox(new_Map.getKey());
//rightPanel.add(ch);
//         ArrayList<Person> arr = new_Map.getValue();
//             
//                       if(arr.contains(cont) ){
//                                System.out.println(new_Map.getKey());
//
//                           }
//               
//                              
//        }
//
//System.out.println(groups);


//adding to east border
rightPanelC.add(rightPanel,BorderLayout.EAST);
//

        //add componants
        mainFrame.add(topPanelC,BorderLayout.NORTH);
        mainFrame.add(leftPanelC,BorderLayout.WEST); 
        mainFrame.add(rightPanelC,BorderLayout.CENTER);       
        mainFrame.setVisible(true);

        //actionListeners
        }
}
