package application;
	
import java.io.File;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

@SuppressWarnings("restriction")
public class Main extends Application {
	
	final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
	
	public ArrayList<OntClass> OntListClass = new ArrayList<OntClass>();
	public File file = null;
	public  MakeOnto MakeOnto = new MakeOnto();
	public OntModel NewModel=ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
		Parent root = loader.load();
	    SampleController controller = loader.getController();
	    
	    
		
	    //RDF select File
		
		final FileChooser fileChooser = new FileChooser();
	    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("RDF", "*.rdf"));
	    
		controller.rdfButton.setOnAction((event) -> {
	        configureFileChooser(fileChooser);
	        this.file = fileChooser.showOpenDialog(primaryStage);
	        if (file != null) {
	    		controller.uriQuery.setText(this.file.toString());
	    		
	    		
	    		
	        } else {
	            Alert alert = new Alert(Alert.AlertType.WARNING);
	            alert.setTitle("File Selection Error");
	            alert.setHeaderText("Please select a valid RDF file..!");
	            alert.showAndWait();
	        }
	    });
		
		//addCLass
		
		controller.ClassButton.setOnAction((event) -> {
            // Create the custom dialog.
            Dialog<Class> dialog = new Dialog<>();
            dialog.setTitle("Add Class");


            // Set the button types.
            ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            // Create the labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(40, 170, 20, 20));

            TextField name = new TextField();
            name.setPromptText("Name");

            grid.add(new Label("Name:"),0,0);
            grid.add(name,0,1);


            dialog.getDialogPane().setContent(grid);
            BooleanBinding bb = new BooleanBinding() {
                {
                    super.bind(name.textProperty());
                }

                @Override
                protected boolean computeValue() {
                    return (name.getText().isEmpty());
                }
            };

            Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
            addButton.disableProperty().bind(bb); //disable button when inputs are empty

            // Request focus on the username field by default.
            Platform.runLater(() -> name.requestFocus());

            // when the department button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                    //add the department
					 try {
						this.NewModel=this.MakeOnto.CreateClass(NewModel,new File(controller.uriQuery.getText()),name.getText());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
                    return new Class(name.getText());
                }
                return null;
            });

            dialog.showAndWait();
        });
		
		//AddSuperClass
		
		controller.SubClassButton.setOnAction((event) -> {
            // Create the custom dialog.
            Dialog<SubClass> dialog = new Dialog<>();
            dialog.setTitle("Add Sub Class");


            // Set the button types.
            ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            // Create the labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(40, 170, 20, 20));

            TextField mere = new TextField();
            mere.setPromptText("ClassMere");
            TextField fille = new TextField();
            fille.setPromptText("ClassFille");

            grid.add(new Label("Mere:"), 0, 0);
            grid.add(mere, 1, 0);
            grid.add(new Label("Fille:"), 0, 1);
            grid.add(fille, 1, 1);


            dialog.getDialogPane().setContent(grid);
            BooleanBinding bb = new BooleanBinding() {
                {
                    super.bind(mere.textProperty(),
                            fille.textProperty());
                }

                @Override
                protected boolean computeValue() {
                    return (mere.getText().isEmpty()
                            || fille.getText().isEmpty());
                }
            };

            Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
            addButton.disableProperty().bind(bb); //disable button when inputs are empty

            // Request focus on the username field by default.
            Platform.runLater(() -> mere.requestFocus());

            // when the SubClass button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                	
                    //add the subClass
                	    try {
							this.NewModel=MakeOnto.CreateSuperClass(NewModel,new File(controller.uriQuery.getText()),mere.getText(), fille.getText());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		
                }
                return null;
            });

            dialog.showAndWait();
		  });
		
		//AddComment
		controller.CommentButton.setOnAction((event) -> {
            // Create the custom dialog.
            Dialog<SubClass> dialog = new Dialog<>();
            dialog.setTitle("Add Comment");


            // Set the button types.
            ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            // Create the labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(40, 170, 20, 20));

            TextField CommentClass = new TextField();
            CommentClass.setPromptText("Class");
            TextField StringComment = new TextField();
            StringComment.setPromptText("Comment");

            grid.add(new Label("Class:"), 0, 0);
            grid.add(CommentClass, 1, 0);
            grid.add(new Label("Comment:"), 0, 1);
            grid.add(StringComment, 1, 1);


            dialog.getDialogPane().setContent(grid);
            BooleanBinding bb = new BooleanBinding() {
                {
                    super.bind(CommentClass.textProperty(),
                            StringComment.textProperty());
                }

                @Override
                protected boolean computeValue() {
                    return (CommentClass.getText().isEmpty()
                            || StringComment.getText().isEmpty());
                }
            };

            Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
            addButton.disableProperty().bind(bb); //disable button when inputs are empty

            // Request focus on the username field by default.
            Platform.runLater(() -> CommentClass.requestFocus());

            // when the Comment button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                    //AddComment
                	try {
						this.NewModel=MakeOnto.CreateComment(NewModel,new File(controller.uriQuery.getText()),CommentClass.getText(),StringComment.getText());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					   
                }
                return null;
            });

            dialog.showAndWait();
		  });
		//Add Property
		controller.PropertyButton.setOnAction((event) -> {
            // Create the custom dialog.
            Dialog<SubClass> dialog = new Dialog<>();
            dialog.setTitle("Add Property");


            // Set the button types.
            ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            // Create the labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(40, 170, 20, 20));
            //Class&Property
            TextField Property = new TextField();
            Property.setPromptText("Property");
            //Range&Domain
            TextField inputRange = new TextField();
            inputRange.setPromptText("Range");
            TextField inputDomain = new TextField();
            inputDomain.setPromptText("Domain");

            grid.add(new Label("Property:"), 0, 1);
            grid.add(Property, 1, 1);
            
            grid.add(new Label("Range:"), 0, 0);
            grid.add(inputRange, 1, 0);
            grid.add(new Label("Domain:"), 2, 0);
            grid.add(inputDomain, 3, 0);

            dialog.getDialogPane().setContent(grid);
            BooleanBinding bb = new BooleanBinding() {
                {
                    super.bind(inputRange.textProperty(),
                            inputDomain.textProperty());
                }

                @Override
                protected boolean computeValue() {
                    return (inputRange.getText().isEmpty()
                            || inputDomain.getText().isEmpty());
                }
            };

            Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
            addButton.disableProperty().bind(bb); //disable button when inputs are empty

            // Request focus on the input field by default.
            Platform.runLater(() -> Property.requestFocus());

            // when the Comment button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                	//saveClass
                	
                    //AddProperty
                	
                	
                	try {
						this.NewModel=MakeOnto.CreateProperty(NewModel,new File(controller.uriQuery.getText()),Property.getText(),inputDomain.getText(),inputRange.getText());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
                   
                }
                return null;
            });

            dialog.showAndWait();
		  });
		
		//Add Instance:
		controller.InstanceButton.setOnAction((event) -> {
            // Create the custom dialog.
            Dialog<SubClass> dialog = new Dialog<>();
            dialog.setTitle("Add Instance");


            // Set the button types.
            ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            // Create the labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(40, 170, 20, 20));
            //Individual 1 & 2
            TextField Individual1 = new TextField();
            Individual1.setPromptText("Individual1");
            TextField Individual2 = new TextField();
            Individual2.setPromptText("Individual2");
            //Class1 & Class2
            TextField Class1 = new TextField();
            Class1.setPromptText("Class1");
            TextField Class2 = new TextField();
            Class2.setPromptText("Class2");
            //Property
            TextField Property1 = new TextField();
            Property1.setPromptText("Property");

            grid.add(new Label("Individual1:"), 0, 0);
            grid.add(Individual1, 1, 0);
            grid.add(new Label("Individual2:"), 2, 0);
            grid.add(Individual2, 3, 0);
            
            grid.add(new Label("Class1:"), 0, 1);
            grid.add(Class1, 1, 1);
            grid.add(new Label("Class2:"), 2, 1);
            grid.add(Class2, 3, 1);
            
            grid.add(new Label("Property:"), 0, 2);
            grid.add(Property1, 1, 2);

            dialog.getDialogPane().setContent(grid);
            BooleanBinding bb = new BooleanBinding() {
                {
                    super.bind(Class1.textProperty(),
                            Class2.textProperty());
                }

                @Override
                protected boolean computeValue() {
                    return (Class1.getText().isEmpty()
                            || Class2.getText().isEmpty());
                }
            };

            Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
            addButton.disableProperty().bind(bb); //disable button when inputs are empty

            // Request focus on the username field by default.
            Platform.runLater(() -> Class1.requestFocus());

            // when the Comment button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                	
                    //Add Instance
                	
                	
                	try {
						this.NewModel=MakeOnto.CreateInstance(NewModel,new File(controller.uriQuery.getText()),Class1.getText(),Class2.getText(),Individual1.getText(),Individual2.getText(),Property1.getText());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
                   
                }
                return null;
            });

            dialog.showAndWait();
		  });
		
		//ShowGraph:
		controller.uriButton.setOnAction((event) -> {
			
		});
		
		// Browsing WebView 
		controller.uriButton.setOnAction((event) -> {
			controller.MyWebView.getEngine().load("http://www.ldf.fi/service/rdf-grapher?rdf="+controller.uriQuery.toString()+"&from=xml&to=png");
			
		});
		
		primaryStage.setTitle("WS_Project MSID:");
        primaryStage.setScene(new Scene(root, 935, 700));
        
        primaryStage.show();
		
	}
	//interClass
	private static class Class {
        String name;

        public Class(String name) {
            this.name = name;
        }
        public String getName() {
        	return name;
        }
    }
	private static class SubClass{
		Class mere;
		Class fille;
		public SubClass(Class mere, Class fille) {
			this.mere=mere;
			this.fille=fille;
		}
	}
	//class WebView:
	private static class Browser extends Region {
		 
	    final WebView browser = new WebView();
	    final WebEngine webEngine = browser.getEngine();
	     
	    public Browser(String path) {
	        //apply the styles
	        getStyleClass().add("browser");
	        // load the web page
	        webEngine.load("https");
	        //add the web view to the scene
	        getChildren().add(browser);
	        
	   }
	}
	//ConfigureFileChooser
	private static void configureFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("View Files");
        fileChooser.setInitialDirectory(
        new File(System.getProperty("user.home") + "/Desktop")
        );
    }
	
	
	public static void main(String[] args) {
		launch(args);
	}
}