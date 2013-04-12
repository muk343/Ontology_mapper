/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Visualize;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jgraph.JGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

/**
 *
 * @author mukul
 * 
 */
public class addvertex extends JFrame{
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );
      private JGraphModelAdapter m_jgAdapter;
      public JButton b;
      ListenableGraph g;
       JGraph jgraph;
    public addvertex()
    {
        g = new ListenableDirectedGraph( DefaultEdge.class );

        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter( g );
        
        jgraph = new JGraph( m_jgAdapter );

        jgraph.setPreferredSize( DEFAULT_SIZE );
        getContentPane().add(jgraph);
        resize( DEFAULT_SIZE );
        setVisible(true);
        b=new JButton("ADD Vertex");
       getContentPane().add(b);
       
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              String str = JOptionPane.showInputDialog(null, "Enter name of vertex you want to add");
        g.addVertex(str);
        System.out.println("In Input ");
       
        
            repaint();
            }
        });
        pack();
                }
       
    
    
    
    
}



