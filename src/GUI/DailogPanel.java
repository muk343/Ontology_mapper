/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author rishab
 */
public class DailogPanel extends JPanel{
     JTextField v1=new JTextField(15);
    JTextField v2=new JTextField(15);
    JLabel l1=new JLabel("Vertex 1");
    JLabel l2=new JLabel("Vertex 2");
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    public DailogPanel()
    {
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            add(p1);
            add(p2);
            p1.add(l1);
            p1.add(v1);
            p2.add(l2);
            p2.add(v2);
    }
}
