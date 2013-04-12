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
import Visualize.*;
import org.semanticweb.owl.align.Alignment;
import org.semanticweb.owl.align.AlignmentProcess;
import org.semanticweb.owl.align.AlignmentException;
import GUI.*;
// Alignment API implementation classes
import fr.inrialpes.exmo.align.impl.ObjectAlignment;
import fr.inrialpes.exmo.ontowrap.OntowrapException;

import java.net.URI;
import java.util.Properties;

/**
 * The Skeleton of code for extending the alignment API
 */

public class StringEquality extends ObjectAlignment implements AlignmentProcess{


    public StringEquality() {
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
	    if (s1 == null || s2 == null) return 0;
	    if (s1.toLowerCase().equals(s2.toLowerCase())) { 
                s1=s1+" ";
                Global.matchArr.add(new MatchedObject(s1,s2));
                System.out.println("Size of array is "+Global.matchArr.size());
		return 1.0;
	    } else { 
		return 0;
	    }
	} catch ( OntowrapException owex ) {
	    throw new AlignmentException( "Error getting entity name", owex );
	}
    }
}