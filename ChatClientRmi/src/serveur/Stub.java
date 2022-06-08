package serveur;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Stub extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ChatInterface st = (ChatInterface)Naming.lookup("rmi://localhost:8005/chat");
		
		primaryStage.setTitle("client");
		
		TextArea mess = new TextArea();
		mess.setPrefHeight(200);
		
		Label label = new Label("votre nom:");
		label.setId("label");
		TextField input = new TextField();
		Label label2 = new Label("votre Message:");
		label2.setId("label2");
		TextField input2 = new TextField();
		
		Label label3 = new Label("veillez recharger pour consulter vos messages avant d'envoyer un message");
		label3.setId("label3");
		
		Button recharge = new Button("recharger");
		Button button = new Button("envoyer");
		
		VBox root = new VBox(20);
		HBox mesBoutons = new HBox(10);
	    mesBoutons.getChildren().addAll(recharge,button);
	    
	    HBox meslab = new HBox(10);
	    meslab.getChildren().addAll(label, input, label2, input2);
	    meslab.setAlignment(Pos.CENTER);
		mesBoutons.setAlignment(Pos.CENTER);
		root.getChildren().addAll(mess, meslab, label3, mesBoutons);
		root.setPrefSize(500, 400);
		root.setAlignment(Pos.CENTER);
		root.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));
		
		recharge.setOnAction(Event ->{
			 try {
					 mess.appendText(st.EnvoiMessage("",""));
		             System.out.println("Message recharger");
		        } catch (Exception e) {
		           System.out.println("erreure: "+e.getMessage().toString());
		        }
			 
		});
		
		button.setOnAction(Event ->{
			 try {
					mess.appendText(st.EnvoiMessage(input.getText(), input2.getText()));
		             System.out.println("Message envoiyer");
		        } catch (Exception e) {
		           System.out.println("erreure: "+e.getMessage().toString());
		        }
			 
		});
		
       /* button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				 try {
					 	ChatInterface st = (ChatInterface)Naming.lookup("rmi://localhost:1001/chat");
					 	
								message.appendText(st.EnvoiMessage(input.getText(), input2.getText()));
			             System.out.println("Message envoiyer");
			        } catch (Exception e) {
			           System.out.println("erreure: "+e.getMessage().toString());
			        }
				 
				
			}
		});*/
		
        
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Ressource/CSS/App.css").toString());
		primaryStage.setScene(scene);
		primaryStage.show();
		

	}

	public static void main(String[] args) {
		
		launch(args);

	}

}
