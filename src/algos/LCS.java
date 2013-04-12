/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algos;

/**
 *
 * @author N Hari Prasad
 */
// Alignment API classes
import org.semanticweb.owl.align.Alignment;
import org.semanticweb.owl.align.AlignmentProcess;
import org.semanticweb.owl.align.AlignmentException;

// Alignment API implementation classes
import fr.inrialpes.exmo.align.impl.ObjectAlignment;
import fr.inrialpes.exmo.ontowrap.OntowrapException;

import java.net.URI;
import java.util.Properties;

/**
 * The Skeleton of code for extending the alignment API
 */

public class LCS extends ObjectAlignment implements AlignmentProcess{


    public LCS() {
    }

    /**
     * The only method to implement is align.
     * All the resources for reading the ontologies and rendering the alignment are from ObjectAlignment and its superclasses:
     * - ontology1() and ontology2() returns objects LoadedOntology
     * - addAlignCell adds a new mapping in the alignment object  
     */
    @Override
    public void align( Alignment alignment, Properties param ) throws AlignmentException {
	try {
	    // Match classes
	    for ( Object cl2: ontology2().getClasses() ){
		for ( Object cl1: ontology1().getClasses() ){
		    // add mapping into alignment object 
		    addAlignCell(cl1,cl2,"=",match(cl1,cl2));    
		}
	    }
	    // Match dataProperties
	    for ( Object p2: ontology2().getDataProperties() ){
		for ( Object p1: ontology1().getDataProperties() ){
		    // add mapping into alignment object 
		    addAlignCell(p1,p2,"=",match(p1,p2));    
		}
	    }
	    // Match objectProperties
	    for ( Object p2: ontology2().getObjectProperties() ){
		for ( Object p1: ontology1().getObjectProperties() ){
		    // add mapping into alignment object 
		    addAlignCell(p1,p2,"=",match(p1,p2));   
		}
	    }
	} catch (Exception e) { e.printStackTrace(); }
    }

    /*
    * *Very* simple matcher, based on equality of names (in the example, only classes and properties)
    */
    public double match(Object o1, Object o2) throws AlignmentException {
	try {
	    String s1 = ontology1().getEntityName(o1);
	    String s2 = ontology2().getEntityName(o2);
            int M = s1.length(),N=s2.length();
            int[] dp[] = new int[M+1][N+1];
	    if (s1 == null || s2 == null) return 0;
	    for(int i=M;i>=0;i--)
                for(int j=N;j>=0;j--)
                {
                    if(i==M || j==N) { dp[i][j] = 0; continue;}
                    if(s1.charAt(i) == s2.charAt(j))
                        dp[i][j] = 1 + dp[i+1][j+1];
                    else
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            return (double)dp[0][0]/Math.min(s1.length(), s2.length());
	} catch ( OntowrapException owex ) {
	    throw new AlignmentException( "Error getting entity name", owex );
	}
    }
}