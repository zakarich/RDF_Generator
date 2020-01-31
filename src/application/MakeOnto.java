package application;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFWriter;

public class MakeOnto {
	
	
	public MakeOnto() {
		
	}
	
	//creer les classe du TP
	public OntModel CreateClass(OntModel model,File file,String NameClass) throws FileNotFoundException{
		
		String exns="http://www.exemple.com/vocabulary#";
		model.setNsPrefix("ns", exns);
		OntClass OntClass = model.createClass(exns+NameClass);
		
		
		//save in file
		
		PrintWriter w = new PrintWriter(file);
		   
		RDFWriter wr = model.getWriter("RDF/XML-ABBREV");
		   
		wr.setProperty("showXmlDeclaration", true);
		wr.write(model, w, null);
		wr.write(model, System.out, "");
		return model;
	}
    
	//creer Super and sub classes
	
	public OntModel CreateSuperClass(OntModel model,File file,String mere,String fille) throws FileNotFoundException {
		
		String exns="http://www.exemple.com/vocabulary#";
		model.setNsPrefix("ns", exns);
		
		OntClass ClassMere = model.createClass(exns+mere);
		OntClass ClassFille = model.createClass(exns+fille);
		ClassFille.addSuperClass(ClassMere);
		//mere.addSubCLass(fille);
		
		//save in file
		
				PrintWriter w = new PrintWriter(file);
				   
				RDFWriter wr = model.getWriter("RDF/XML-ABBREV");
				   
				wr.setProperty("showXmlDeclaration", true);
				wr.write(model, w, null);
				wr.write(model, System.out, "");
				
				return model;
		
	}
	
	//Create Comments
	public OntModel CreateComment(OntModel model,File file,String StrClass,String string) throws FileNotFoundException {
		
		String exns="http://www.exemple.com/vocabulary#";
		model.setNsPrefix("ns", exns);
		
		//AddClass
		OntClass myClass = model.createClass(exns+StrClass);
		myClass.addComment(model.createLiteral(string));
		
		//save in file
		
		PrintWriter w = new PrintWriter(file);
		   
		RDFWriter wr = model.getWriter("RDF/XML-ABBREV");
		   
		wr.setProperty("showXmlDeclaration", true);
		wr.write(model, w, null);
		wr.write(model, System.out, "");
		return model;
	}
	
	//CreateProperty
	public OntModel CreateProperty(OntModel model,File file,String Property,String Domain,String Range) throws FileNotFoundException {
		
		String exns="http://www.exemple.com/vocabulary#";
		model.setNsPrefix("ns", exns);
		
		OntProperty OntProperty=model.createOntProperty(exns+Property);
		OntClass ClassDomain=model.createClass(exns+Domain);
		OntClass ClassRange = model.createClass(exns+Range);
		OntProperty.addDomain(ClassDomain);
		OntProperty.addRange(ClassRange);
		
		//save in file
		
				PrintWriter w = new PrintWriter(file);
				   
				RDFWriter wr = model.getWriter("RDF/XML-ABBREV");
				   
				wr.setProperty("showXmlDeclaration", true);
				wr.write(model, w, null);
				wr.write(model, System.out, "");
				return model;
		
	}
	//CreateInstance
    public OntModel CreateInstance(OntModel model,File file,String Class1,String Class2,String Instance1,String Instance2,String Property) throws FileNotFoundException {
		
		String exns="http://www.exemple.com/vocabulary#";
		model.setNsPrefix("ns", exns);
		
		OntClass class1 = model.createClass(exns+Class1);
		OntClass class2 = model.createClass(exns+Class2);
		
		OntProperty Property1 = model.createOntProperty(Property);
		
		Property1.addDomain(class1);
		Property1.addRange(class2);
		
		Individual individu1 = class1.createIndividual(exns+Instance1);
		Individual individu2 = class2.createIndividual(exns+Instance2);
		individu1.addProperty(Property1,individu2);
		
		//save in file
		
				PrintWriter w = new PrintWriter(file);
				   
				RDFWriter wr = model.getWriter("RDF/XML-ABBREV");
				   
				wr.setProperty("showXmlDeclaration", true);
				wr.write(model, w, null);
				wr.write(model, System.out, "");
				return model;
		
	}
}