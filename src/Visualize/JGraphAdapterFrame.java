/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Visualize;
import com.touchgraph.graphlayout.GraphListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import java.awt.*;
import java.util.Set;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.undo.UndoableEdit;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.ListenableGraph;
import org.jgrapht.event.GraphVertexChangeEvent;
import org.jgrapht.event.VertexSetListener;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.*;


/**
 *
 * @author Mukul
 */
public class JGraphAdapterFrame extends JFrame{

    
     
    private static final Color     DEFAULT_BG_COLOR = Color.white;
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );
    // 
    private JGraphModelAdapter m_jgAdapter;
  

    /**
     * @see java.applet.Applet#init().
     */
   
    public JGraphAdapterFrame ( ) {
        // create a JGraphT graph
        
//        ListenableGraph g = new ListenableDirectedGraph( DefaltEdge.class );
       // ListenableDirectedWeightedGraph<String,Double> g=new ListenableDirectedWeightedGraph<String, Double>(Double.class);
ListenableDirectedWeightedGraph<String,MyWeightedEdge>  g = new ListenableDirectedWeightedGraph<String,MyWeightedEdge>(MyWeightedEdge.class);
        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter( g );
        
        JGraph jgraph = new JGraph( m_jgAdapter );

        adjustDisplaySettings( jgraph );
        getContentPane().add(jgraph);
        resize( DEFAULT_SIZE );
        pack();
        
        

        // add some sample data (graph manipulated via JGraphT)
        
        MatchedObject obj=new MatchedObject();
        int y=0;
        for(int m=0;m<Global.matchArr.size();m++)
        {   obj=Global.matchArr.get(m);
            g.addVertex(obj.st1);
           
            g.addVertex(obj.st2);
            
            MyWeightedEdge w1=g.addEdge(obj.st1, obj.st2);
            g.setEdgeWeight(w1, 1);
          
            System.out.println(g.getEdgeWeight(w1));
            positionVertexAt(obj.st1, 150, 60+y);
            positionVertexAt(obj.st2,600,60+y);
            y+=50;
        }
        
        /*Set st = g.vertexSet();
        for(Object o: st)
            System.out.println(o);*/
        
        
     /*   g.addVertex( "v1" );
        g.addVertex( "v2" );
        g.addVertex( "v3" );
        g.addVertex( "v4" );
        g.addVertex("Mukul");
        
        g.addEdge( "v1", "v2" );
        g.addEdge( "v2", "v3" );
        g.addEdge( "v3", "v1" );
        g.addEdge( "v4", "v3" );
        g.addEdge("v4","Mukul"); */
        // position vertices nicely within JGraph component
       // positionVertexAt( obj.st1, 150, 60 );
       /* positionVertexAt( obj, 60, 200 );
        positionVertexAt( "v3", 310, 230 );
        positionVertexAt( "v4", 380, 70 );
*/
      
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        // that's all there is to it!...
    }

    public static void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
           // colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }


    private void positionVertexAt( Object vertex, int x, int y ) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        Map              attr = cell.getAttributes(  );
        //Rectangle2D b = GraphConstants.getBounds(attr);
        //int height=(int)b.getHeight();
        //int width=(int)b.getWidth();
        Rectangle2D b=new Rectangle(x, y,70,70);
GraphConstants.setBounds( attr,b );




        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        m_jgAdapter.edit(cellAttr, null, null, (UndoableEdit[])null);
    }
}
/*class driverGraph
{
    public void startGraph()
    {
        JFrame f=new JGraphAdapterFrame();
    //f.setVisible(true);
    }
}*/