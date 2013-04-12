/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Visualize;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.undo.UndoableEdit;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import javax.swing.JPanel;
/**
 *
 * @author Mukul
 */
public class JGraphAdapterPanel extends JPanel{
     
     private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );
     Color  c        = DEFAULT_BG_COLOR;
    private JGraphModelAdapter m_jgAdapter;

    /**
     * @see java.applet.Applet#init().
     */
    public JGraphAdapterPanel () {
        // create a JGraphT graph
        ListenableGraph g = new ListenableDirectedGraph( DefaultEdge.class );

        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter( g );
        setBackground(Color.red);
        JGraph jgraph = new JGraph( m_jgAdapter );

    //    adjustDisplaySettings( jgraph );
   //     getContentPane(  ).add( jgraph );
   //     resize( DEFAULT_SIZE );

        // add some sample data (graph manipulated via JGraphT)
        
        MatchedObject obj=new MatchedObject();
        for(int m=0;m<Global.matchArr.size();m++)
        {   obj=Global.matchArr.get(m);
            g.addVertex(obj.st1);
            g.addVertex(obj.st2);
            g.addEdge(obj.st1, obj.st2);
        }
        
        
        setVisible(true);
        
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
      //  positionVertexAt( obj.st1, 150, 60 );
       /* positionVertexAt( obj, 60, 200 );
        positionVertexAt( "v3", 310, 230 );
        positionVertexAt( "v4", 380, 70 );
*/
   //     pack();
   //     setVisible(true);
    //    setDefaultCloseOperation(EXIT_ON_CLOSE);
        // that's all there is to it!...
    }


  /*  private void adjustDisplaySettings( JGraph jg ) {
     //   jg.setPreferredSize( DEFAULT_SIZE );*/

       
      /*  String colorStr = null;

        try {
           // colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }
*/

    private void positionVertexAt( Object vertex, int x, int y ) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        Map              attr = cell.getAttributes(  );
        Rectangle2D b = GraphConstants.getBounds(attr);

        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        m_jgAdapter.edit(cellAttr, null, null, (UndoableEdit[])null);
    }
}
