module ClientRmi {
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires java.rmi;
	requires javafx.base;
	exports serveur;
}