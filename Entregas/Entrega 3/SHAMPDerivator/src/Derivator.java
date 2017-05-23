import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Derivator {

	// Contiene el estado de cada feature determinado en el modelo
	public static FeatureTable optional = new FeatureTable();
	public static boolean flagRoute = false;
	public static String actualFeature = "";

	// Constantes para determinar como sacar el nombre de un feature
	public static final String OR_ALT = "<feature mandatory";
	public static final String FEATURE = "<feature name";

	// Constantes que muestran el inicio de un archivo xml
	public static final String OR = "<or";
	public static final String ALT = "<alt";
	public static final String AND = "<and";

	// Constante que me determina que debe ir concatenado para que no sea un
	// featureObligatorio
	public static final String NOT_MANDATORY = " name";

	// Constante que me determina cuando es OR_ALT o FEATURE
	public static final String END = "</";

	// Constante que simboliza el inicio de un feature en la aplicación
	public static final String FEATURE_CONF = "feature.";

	// Constante para el inicio o fin de un feature en Route conf
	public static final String START_ROUTE = "###START";
	public static final String END_ROUTE = "###END";

	// Constante para determinar que empezo o termino un feature
	public static final String REPORT = "REPORT";
	public static final String MESSAGE = "MESSAGE";
	public static final String CHANGE_PASSWORD = "CHANGEPASSWORD";
	public static final String RATE = "RATE";

	//Constantes con las clases que dependen de un feature
	public static final String MENSAJE_CLASS = "||\"MessageController.java\"||\"MessageCreationDto.java\"||\"MessageDto.java\"||\"MessageAdmin.java\"||\"ResponseMessageForm.java\"||\"Message.java\"";
	public static final String REPORTE_CLASS = "||\"ReportAdmin.java\"||\"ReportsController.java\"||\"ReportDto.java\"||\"CreateReportForm.java\"";
	
	//Constantes con las vistas que dependen de un feature
	public static final String[] MENSAJE_VIEWS = {"adminMessages.scala.html", "response_message.scala.html", "viewResponsedMessage.scala.html", "viewUnresponseMessage.scala.html"};
	public static final String[] REPORTS_VIEWS = {"create_report.scala.html", "viewReport.scala.html"};
	public static final String[] CHANGEPASSWORD_VIEWS = {"change_password.scala.html"};
	/**
	 * Metodo principal del derivador que ejecuta los metodos en un orden
	 * determinado para cambiar algunos aspectos en la aplicaición
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			// Se lee el modelo creado en FeatureIDE
			System.err.println("Inicia la lectura del modelo de FeatureIDE...");
			optional = ModelReader.readModel(
					"D:\\Documentos\\WorkspaceEclipse\\SPLShamp\\model.xml",
					optional);
			System.err.println("Termina la lectura del modelo de FeatureIDE.");
			
			printSeparator();
			showFeatures();
			printSeparator();
			
			// Se ingresa la dirección del archivo config de FeatureIDE
			System.err.println("Inicia la lectura del archivo de configuración de featureIDE (default.config)...");
			optional = ModelReader
					.readConfig(
							"D:\\Documentos\\WorkspaceEclipse\\SPLShamp\\configs\\default.config",
							optional);
			System.err.println("Termina la lectura del archivo de configuración de featureIDE.");
			
			printSeparator();
			showFeatures();
			printSeparator();
			
			
			// Se ingresa la dirección del archivo aplication.conf
			System.err.println("Inicia lectura y edición del archivo de aplication.conf... ");
			AppReader.readApplicationConf(
					"D:\\play-java\\conf\\application.conf", optional);
			System.err.println("Termina lectura y edición del archivo de aplication.conf. ");

			
			printSeparator();
			
			//Se ingresa la dirección del archivo routes
			System.err.println("Inicia lectura y edición del archivo de Routes... ");
			RouteReader.readRoutes("D:\\play-java\\conf\\routes", optional);
			System.err.println("Termina lectura y edición del archivo de Routes. ");

			printSeparator();
			
			//Se ingresa la dirección del archivo routes
			System.err.println("Inicia lectura y edición del archivo de configuración de la tienda (build.sbt) ... ");
			BuildReader.readBuild("D:\\play-java\\build.sbt", optional);
			System.err.println("Termina lectura y edición del archivo de configuración de la tienda. ");

			
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void printSeparator(){
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println();
	}
	
	private static void showFeatures(){
		System.out.println();
		System.out.println("Estado actual de la tabla de features: ");
		System.out.println("Tamaño: " + optional.size());
		System.out.println("Contenido: ");
		System.out.println(optional.toString());
	}

	
	

}
