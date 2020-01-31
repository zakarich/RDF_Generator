package application;

import org.apache.jena.rdf.model.Model;

import org.apache.jena.rdf.model.RDFNode;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.RenderContext;

/**
 *
 * @author 
 */
public class AffichageGraphe {
 
    /*public AffichageGraphe(File file) {
    	
    	Model model = FileManager.get().loadModel(file.toString());
    	Graph<RDFNode, Statement> g = new JenaJungGraph(model);
    	Layout<RDFNode, Statement> layout = new FRLayout(g);
    	layout.setSize(new Dimension(300, 300));
    	BasicVisualizationServer<RDFNode, Statement> viz = new BasicVisualizationServer<RDFNode, Statement>(layout);
    	RenderContext context = viz.getRenderContext();
    	context.setEdgeLabelTransformer(Transformers.EDGE); // property label
    	context.setVertexLabelTransformer(Transformers.NODE); // node label
    	viz.setPreferredSize(new Dimension(350, 350));
    	JFrame frame = new JFrame("Jena Graph");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.getContentPane().add(viz);
    	frame.pack();
    	frame.setVisible(true);
    	
    }*/
	
 
}