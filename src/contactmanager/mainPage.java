package contactmanager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class mainPage extends JFrame{

    private JFrame mainFrame;
    private JPanel topPanel,leftPanel,rightPanel;
    private JButton contacts,groups;
    private JLabel title;
        
    public mainPage(){

        mainFrame = new JFrame("Contact");
        mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500,500);
        mainFrame.setLayout(new BorderLayout(8,6));

        //Top Panel
        topPanel = new JPanel();  
        title = new JLabel("Gestion De Contact");
        title.setFont(new Font("serif",Font.PLAIN,21));
        title.setForeground(Color.BLUE);
        topPanel.add(title);

        //left PAnel
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5,1,5,55)); //Rows Columns hgap Vgap
        leftPanel.setBorder(new EmptyBorder(50, 10, 0, 0));
        contacts= new JButton("Contacts"); 
        groups = new JButton("Groups");
        leftPanel.add(contacts);
        leftPanel.add(groups);

        //rightpanel
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.CYAN);

        //add componants
        mainFrame.add(topPanel,BorderLayout.NORTH);
        mainFrame.add(leftPanel,BorderLayout.WEST); 
        mainFrame.add(rightPanel,BorderLayout.CENTER);       
        mainFrame.setVisible(true);

        //actionListeners
        contacts.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
              try {
                new Contact();
            } catch (IOException e1) {
             
                e1.printStackTrace();
            }
            }

        });
      
groups.addActionListener(new ActionListener(){

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.dispose();
        try {
          new Group();
          
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
    }
    
});


}


public static void main(String[] args) throws Exception {

       mainPage giu = new mainPage();
      

}

    }