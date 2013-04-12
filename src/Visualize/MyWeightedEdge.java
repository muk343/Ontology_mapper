/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Visualize;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 *
 * @author mukul
 */

public class MyWeightedEdge extends DefaultWeightedEdge {
   public String toString()
   {
       return Double.toString(getWeight());
   }
}
