package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

public class SampleController {
	
	@FXML
    public MenuItem rdfButton;
	@FXML
    public MenuItem ClassButton;
	@FXML
    public MenuItem SubClassButton;
	@FXML
    public MenuItem CommentButton;
	@FXML
    public MenuItem PropertyButton;
	@FXML
    public TextField uriQuery;
	@FXML
    public Button uriButton;
	@FXML
	public MenuItem SaveButton;
	@FXML
	public MenuItem InstanceButton;
	
	@FXML
	 public WebView MyWebView;
	
	
}
