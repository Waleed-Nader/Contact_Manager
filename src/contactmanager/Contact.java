package contactmanager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Contact extends JFrame{
    public static JFrame mainFrameContacts;
    private JPanel topPanelC,leftPanelC,rightPanelC;
    private JButton sortFN,sortLN,sortCity,addNew;
    private JLabel title,title2;
        
    public Contact() throws IOException{

        mainFrameContacts = new JFrame("Contacts");
        mainFrameContacts.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        mainFrameContacts.setSize(500,500);
        mainFrameContacts.setLayout(new BorderLayout(8,6));

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

        title2 = new JLabel("Contacts :");
        title2.setFont(new Font("serif",Font.BOLD,15));
        title2.setForeground(Color.RED);

        sortFN= new JButton("Sort by First Name"); 
        sortLN = new JButton("Sort by Last Name");
        sortCity = new JButton("Sort by City");

        
//add new contact
        addNew = new JButton("Add New Contact");
        addNew.addActionListener(new ActionListener(){


            @Override
            public void actionPerformed(ActionEvent e) {
         mainFrameContacts.dispose();
              ContactList.contactList.clear();      
         new NewContact();
                
                
            }
            
        });


        leftPanelC.add(title2);
        leftPanelC.add(sortFN);
        leftPanelC.add(sortLN);
        leftPanelC.add(sortCity);
        leftPanelC.add(addNew);
      
        rightPanelC = new JPanel();
        rightPanelC.setLayout(new BorderLayout());
        rightPanelC.setBackground(Color.CYAN);
        rightPanelC.add(new ContactList(),BorderLayout.EAST); //add the contact Frame + Border EAST



        //add componants
        mainFrameContacts.add(topPanelC,BorderLayout.NORTH);
        mainFrameContacts.add(leftPanelC,BorderLayout.WEST); 
        mainFrameContacts.add(rightPanelC,BorderLayout.CENTER);       
        mainFrameContacts.setVisible(true);


        //actionListeners

        sortFN.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
              ContactList.SortByFirstName();
             
            }
            
        });

        sortLN.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               ContactList.SortByLastName();
                
            }
            
        });

        sortCity.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
               ContactList.SortByCity();

            }
            
        });
    }

public static JFrame frame() {
    return mainFrameContacts;
}
}
