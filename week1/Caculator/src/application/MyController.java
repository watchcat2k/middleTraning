package application;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.xml.transform.Templates;

import com.sun.prism.shader.FillCircle_LinearGradient_REFLECT_AlphaTest_Loader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import lib.*;

public class MyController implements Initializable {

   @FXML
   private Button mulButton;
   @FXML
   private Button num0Button;
   @FXML
   private Button divideButton;
   @FXML
   private Button okButton;
   @FXML
   private Button num1Button;
   @FXML
   private Button num2Button;
   @FXML
   private Button num3Button;
   @FXML
   private Button delButton;
   @FXML
   private Button num4Button;
   @FXML
   private Button num5Button;
   @FXML
   private Button num6Button;
   @FXML
   private Button subButton;
   @FXML
   private Button num7Button;
   @FXML
   private Button num8Button;
   @FXML
   private Button num9Button;
   @FXML
   private Button addButton;
   @FXML
   private TextField result;
   
   public void initialize(URL location, ResourceBundle resources) {
       // TODO (don't really need to do anything here).

   }

   // When user click on myButton
   // this method will be called.
   public void mulButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "*");
   }
   public void num0ButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "0");
   }
   public void divideButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "/");
   }
   public void okButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().length() != 0) {
		   if (result.getText().length() == 1 &&
			   result.getText().compareTo("+") != 0 &&
			   result.getText().compareTo("-") != 0 &&
			   result.getText().compareTo("*") != 0 &&
			   result.getText().compareTo("/") != 0) return;
		   result.setText(Calculator.conversion(result.getText()) + "");
	   }
   }
   public void num1ButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "1");
   }
   public void num2ButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "2");
   }
   public void num3ButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "3");
   }
   public void delButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   if (result.getText().length() != 0) {
		   result.setText(result.getText().substring(0, result.getText().length()-1));
	   }
   }
   public void num4ButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "4");
   }
   public void num5ButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "5");
   }
   public void num6ButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "6");
   }
   public void subButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "-");
   }
   public void num7ButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "7");
   }
   public void num8ButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "8");
   }
   public void num9ButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "9");
   }
   public void addButtonClick(javafx.scene.input.MouseEvent event) {
	   if (result.getText().compareTo("NaN") == 0) {	
		   result.setText("");
	   }
	   result.setText(result.getText() + "+");
   }
   

}