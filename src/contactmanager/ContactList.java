package contactmanager;


import java.awt.BorderLayout;
import java.awt.Dimension;
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
import java.util.Collections;
import java.util.Comparator;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;
 

public class ContactList extends JPanel{
    
    private static JLabel searchText;
    private JTextField searchBar;
            int selectedIndex;
            static TableRowSorter<TableModel> sorter;
            static JTable table;
            static DefaultTableModel tableModel;
    static ArrayList<Person> contactList = new ArrayList<>();
Person ContactRow;
    public ContactList() throws IOException {

        super(new BorderLayout());
        setPreferredSize(new Dimension(250,300));

        //File data handeling
        //filtering items
        searchBar = new JTextField();
        try{
            String nameNumberString;
            String FirstName;
            String LastName;
            String city;
            String phoneNumbers;

        File file = new File("contacts.txt");
        if (!file.exists()) {
 
            // Create a new file if not exists.
            file.createNewFile();
        }
        //openning file in REad buffer
                                  try (
     
        BufferedReader br = new BufferedReader(new FileReader(file))) {
       
            while ((nameNumberString = br.readLine()) != null) {

                // reading line from the file.
                // splitting the string to get name and
                // number

               String[] lineSplit = nameNumberString.split(" ");
               int Id = Integer.parseInt(lineSplit[0]);
               FirstName = lineSplit[1];
               LastName = lineSplit[2];
               city = lineSplit[3];
               //getting phone numbers
               phoneNumbers =lineSplit[4];
   

                // Print the contact data

                contactList.add(new Person(Id,FirstName, LastName, city, phoneNumbers) );

                 
            }System.out.println(contactList);
                    

        }
    }catch(IOException ioe){
        System.out.println(ioe);
    }


    //create table
    String[] header = {"Contacts"};
    Person[][] tableData = new Person[contactList.size()][1];

    
    tableModel = new DefaultTableModel();
    table = new JTable(tableModel){//overriding the cell is editable
        @Override
        public boolean isCellEditable(int row, int column){
             return false;
        } };
    tableModel.addColumn(header);
    tableModel.addRow(tableData);
    table.setShowGrid(false);
    table.setTableHeader(null);
    table.setFillsViewportHeight(true);
    add(new JScrollPane(table));
    sorter = new TableRowSorter<>(table.getModel());
    table.setRowSorter(sorter);
    Document doc = searchBar.getDocument();
    
     tableModel.setRowCount(0);//clearing Table

    
    DocumentListener listener = new DocumentListener() {


        @Override
        public void insertUpdate(DocumentEvent e) {
            newFilter();            
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            newFilter();            
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            newFilter();            
        }
    };
    doc.addDocumentListener(listener);

    //insert values in table
    for(int x =0 ; x<contactList.size();x++){

        tableModel.insertRow(0,new Person[]{contactList.get(x)} );
    }

//table mouse listener
table.addMouseListener(new MouseListener(){

    @Override
    public void mouseClicked(MouseEvent e) {
        int rowIndex = table.getSelectedRow();
        int columnIndex = table.getSelectedColumn();
        Person ValueRC= (Person) table.getValueAt(rowIndex, columnIndex);//value of group name.
     ContactRow = ValueRC;
     System.out.println(ValueRC);
     int selectedIndex = ContactRow.getId();
        System.out.println(selectedIndex);
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
    
//Buttons
        JButton view = new JButton("View");
        view.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
             int selectedIndex =contactList.size() -  ContactRow.getId()-1;
                  System.out.println(selectedIndex);  
                         
                    new View(ContactRow.getId(),ContactRow.getFirstname(), ContactRow.getLastname()
                    ,ContactRow.getCity(), ContactRow.getPhonenumber());
  
            }
            
        });
        
        JButton update = new JButton("Update");
        update.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                new Update(ContactRow.getId(),ContactRow.getFirstname(), ContactRow.getLastname()
                ,ContactRow.getCity(), ContactRow.getPhonenumber());
                     contactList.clear();  
                Contact.frame().dispose();
                
            }
            
        });
 
        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            int a =JOptionPane.showConfirmDialog( Contact.mainFrameContacts,"Are you sure you want to Delete?", "EXIT", JOptionPane.YES_NO_OPTION);

           
 if(a == JOptionPane.YES_OPTION){
                try {
                    // Open the file that is the first
                    // command line parameter
                    FileInputStream fstream = new FileInputStream("contacts.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                    String strLine;
                    StringBuilder fileContent = new StringBuilder();

                    //Read File Line By Line
                            int selectedIndex = ContactRow.getId();

                    while ((strLine = br.readLine()) != null) {
                        // Print the content on the console
                        System.out.println(strLine);
                        String tokens[] = strLine.split(" ");
                        if (tokens.length > 0) {
                            // Here tokens[0] will have value of ID
                            if ( Integer.parseInt(tokens[0]) == selectedIndex) {
                             
                                String newLine = "";
                                fileContent.append(newLine);
          
                            } else {
                                // update content as it is
                                fileContent.append(strLine);
                                fileContent.append("\n");
                            }
                        }
                    }

                    // Now fileContent will have updated content , which we override into file
                    FileWriter fstreamWrite = new FileWriter("contacts.txt");
                    BufferedWriter out = new BufferedWriter(fstreamWrite);
                    out.write(fileContent.toString());
                    out.close();
                    //Close the input stream
                    br.close();
                   
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
                tableModel.removeRow(table.getSelectedRow());

 }
            }
            
        });





        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(view);
        buttonPane.add(update);
        buttonPane.add(delete);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        JPanel searchPane = new JPanel();
        searchPane.setLayout(new BoxLayout(searchPane, BoxLayout.LINE_AXIS));

        
        searchText = new JLabel("Search :  ");
        searchText.setLabelFor(searchBar);
        searchPane.add(searchText);
        searchPane.add(searchBar);



        add(searchPane,BorderLayout.PAGE_START);
        add(buttonPane, BorderLayout.PAGE_END);

    }
 


    
    private void newFilter() {
        RowFilter rf = null;
    
        try {
            rf = RowFilter.regexFilter("(?i)"+searchBar.getText(),0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

public static void SortByFirstName() {
    Collections.sort(contactList, new Comparator<Person>() {
               public int compare(Person s1, Person s2) {
                      if(s1.getFirstname() != null && s2.getFirstname() != null && s1.getFirstname().compareTo(s1.getFirstname()) != 0) {
                          return s1.getFirstname().compareTo(s2.getFirstname());
                      }
                    return s2.getFirstname().compareToIgnoreCase(s1.getFirstname());
                 }
        
         
        });
          tableModel.setRowCount(0);
        
        for(int x =0 ; x<contactList.size();x++){
        
            tableModel.insertRow(0,new Person[]{contactList.get(x)} );
        }
    
}

  public static void SortByLastName() {
      
    Collections.sort(contactList, new Comparator<Person>() {
        public int compare(Person s1, Person s2) {
               if(s1.getLastname() != null && s2.getLastname() != null && s1.getLastname().compareTo(s1.getLastname()) != 0) {
                   return s1.getLastname().compareTo(s2.getLastname());
               }
             return s2.getLastname().compareToIgnoreCase(s1.getLastname());
          }
 
  
 });
   tableModel.setRowCount(0);
 
 for(int x =0 ; x<contactList.size();x++){
 
     tableModel.insertRow(0,new Person[]{contactList.get(x)} );
 }
  }
  
  
  public static void SortByCity() {
    Collections.sort(contactList, new Comparator<Person>() {
        public int compare(Person s1, Person s2) {
               if(s1.getCity() != null && s2.getCity() != null && s1.getCity().compareTo(s1.getCity()) != 0) {
                   return s1.getFirstname().compareTo(s2.getCity());
               }
             return s2.getCity().compareToIgnoreCase(s1.getCity());
          }
 
  
 });
   tableModel.setRowCount(0);
 
 for(int x =0 ; x<contactList.size();x++){
 
     tableModel.insertRow(0,new Person[]{contactList.get(x)} );
 }
  }

}