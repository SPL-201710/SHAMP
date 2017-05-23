import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;

public class BuildReader {

	/**
	 * Lectura y escritura del archivo build.sbt
	 * 
	 * @param file
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public static void readBuild(String file, FeatureTable optional)
			throws IOException, NoSuchAlgorithmException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuffer sb = new StringBuffer();
		try {
			String line = br.readLine();

			while (line != null) {

				// Evaluar si el componente de notificación esta apagado
				if (line.startsWith("excludeFilter")) {
					
					// Eliminar o agregar las clases en el build.sbt
					line = excludeClass(line, "Notification",
							Derivator.MENSAJE_CLASS, optional);
					line = excludeClass(line, "Reports",
							Derivator.REPORTE_CLASS, optional);
					
					// Eliminar o agregar las vistas
					FilesReader.exincludeViews(file, "Notification", optional);
					FilesReader.exincludeViews(file, "Reports", optional);

				}
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

	/**
	 * Elimina o agrega clases en build.sbt
	 * @param line
	 * @param feature
	 * @param classes
	 * @param optional
	 * @return
	 */
	private static String excludeClass(String line, String feature,
			String classes, FeatureTable optional) {
		String answer = "";
		if (!optional.getOptional(feature).getState()) {
			if (!line.contains(classes))
				line += classes;

		} else
			line = line.replace(classes, "");
		answer = line;
		return answer;
	}

	
}
