package serveur;

import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Timer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.print.PrintColor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Sckleton extends Application {
	
	ChatImplementation chat;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ChatImplementation chat = new ChatImplementation();
		
		primaryStage.setTitle("serveur");
		
		TextArea message = new TextArea();
		message.setPrefHeight(200);
		message.setText(ChatImplementation.message +"\n");
		Label label = new Label("votre nom");
		label.setId("label");
		TextField input = new TextField();
		Label label2 = new Label("votre message");
		label2.setId("label2");
		TextField input2 = new TextField();
		
		Label label3 = new Label("veillez recharger pour consulter vos messages avant d'envoyer un message");
		label3.setId("label3");
		
		
		Button recharge = new Button("recharger");
		Button button = new Button("envoyer");
		
		recharge.setOnAction(Event ->{
			 try {
					 message.appendText(chat.EnvoiMessage("",""));
		             System.out.println("Message recharger");
		        } catch (Exception e) {
		           System.out.println("erreure: "+e.getMessage().toString());
		        }
			 
		});
		
		button.setOnAction(Event ->{
			 try {
					message.appendText(chat.EnvoiMessage(input.getText(), input2.getText()));
		             System.out.println("Message envoiyer");
		        } catch (Exception e) {
		           System.out.println("erreure: "+e.getMessage().toString());
		        }
			 
		});
		
		/*button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				 try { 
					    ChatImplementation chat = new ChatImplementation();
					    
							message.appendText(chat.EnvoiMessage(input.getText(), input2.getText()));
					    	System.out.println("Message envoiyer");
			        } catch (Exception e) {
			           System.out.println("erreure: "+e.getMessage().toString());
			        }
				
			}
		});*/
		
		VBox root = new VBox(20);
		
		HBox mesBoutons = new HBox(10);
	    mesBoutons.getChildren().addAll(recharge,button);
	    
	    HBox meslab = new HBox(10);
	    meslab.getChildren().addAll(label, input, label2, input2);
		root.getChildren().addAll(message, meslab,label3, mesBoutons);
		meslab.setAlignment(Pos.CENTER);
		mesBoutons.setAlignment(Pos.CENTER);
		root.setPrefSize(500, 400);
		root.setAlignment(Pos.CENTER);
		root.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Ressource/CSS/App.css").toString());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		
		
		try { 
			LocateRegistry.createRegistry(8005);
			 System.out.println("Serveur : Construction de l’implémentation"); 
			 
			ChatImplementation chat = new ChatImplementation();
			 
			 Naming.rebind("rmi://localhost:8005/chat", chat); 
			 System.out.println("Attente des invocations des clients "); 
			 
			 } 
			 catch (Exception e) { 
			 System.out.println("Erreur"); 
			 System.out.println(e.toString()); 
			 } 
		
		launch(args);
		
	}
}
