

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class RouteReader {

	/**
	 * Evalua unos parametros para cambiar el archivo de rutas
	 * 
	 * @param nameFeature
	 * @param lineEnter
	 * @param start
	 * @param end
	 * @return
	 */
	private static String evaluateRoute(String nameFeature, String lineEnter,
			String feature, FeatureTable optional, String file) {
		String lineExit = lineEnter;
		if (!optional.getOptional(nameFeature).getState()) {
			if (lineExit.equals(Derivator.END_ROUTE + "" + feature)) {
				Derivator.flagRoute = false;
				Derivator.actualFeature = "";
			}

			if (Derivator.flagRoute && Derivator.actualFeature.equals(feature)) {
				if (!lineExit.startsWith("# "))
					lineExit = "# " + lineExit;
			}

			if (lineExit.equals(Derivator.START_ROUTE.concat(feature))) {
				if(nameFeature.equals("ChangePassword")){
					FilesReader.exincludeViews("D:\\play-java\\build.sbt", nameFeature, optional);
					System.out.println("Encontro cambio de password y elimino vista " + "D:\\play-java\\build.sbt");
				}
				Derivator.flagRoute = true;
				Derivator.actualFeature = feature;
			}
		} else {
			if (lineExit.equals(Derivator.END_ROUTE.concat(feature))) {
				Derivator.flagRoute = false;
			}

			if (Derivator.flagRoute && Derivator.actualFeature.equals(feature)) {
				if (lineExit.startsWith("# "))
					lineExit = lineExit.substring(2, lineExit.length());
			}

			if (lineExit.equals(Derivator.START_ROUTE.concat(feature))) {
				Derivator.flagRoute = true;
				Derivator.actualFeature = feature;
			}
		}

		return lineExit;
	}

	/**
	 * Lee el archivo routes.conf del proyecto e inhabilita algunos rest
	 * dependiendo de la configuración
	 * 
	 * @param file
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public static void readRoutes(String file, FeatureTable optional)
			throws IOException, NoSuchAlgorithmException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuffer sb = new StringBuffer();

		try {
			String line = br.readLine();

			while (line != null) {
				line = evaluateRoute("Notification", line, Derivator.MESSAGE,
						optional, file);
				line = evaluateRoute("Reports", line, Derivator.REPORT,
						optional, file);
				line = evaluateRoute("ChangePassword", line,
						Derivator.CHANGE_PASSWORD, optional, file);
				
				line = evaluateRoute("Rating", line, Derivator.RATE, optional, file);

				// Mostrar estado del estado actual del feature
				System.out.println("ActualFeature: " + Derivator.actualFeature);

				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}

			BufferedWriter fw = new BufferedWriter(new FileWriter(file, false));
			fw.write(sb.toString());
			fw.close();

		} finally {
			br.close();
		}
	}
}
