

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ModelReader {

	public static FeatureTable readModel(String file, FeatureTable optional)
			throws IOException, NoSuchAlgorithmException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String begin = "";
		begin = Derivator.FEATURE;

		try {
			String line = br.readLine();

			while (line != null) {
				// Elimina tabulación
				line = line.replace("\t", "");

				/**
				 * Si comienza con: <feature name <and name <alt name Lo agrega
				 * a la lista de opcionales
				 */
				if (line.startsWith(begin)
						|| line.startsWith(Derivator.ALT
								+ Derivator.NOT_MANDATORY)
						|| line.startsWith(Derivator.AND
								+ Derivator.NOT_MANDATORY)) {
					String nameFeature = separateName(line, begin);
					optional.addFeature(nameFeature);
				}

				if (line.startsWith(Derivator.OR)
						|| line.startsWith(Derivator.ALT))
					begin = Derivator.OR_ALT;

				if (line.startsWith(Derivator.END))
					begin = Derivator.FEATURE;

				line = br.readLine();
			}
		} finally {
			br.close();
		}

		return optional;

	}

	/**
	 * Obtiene el nombre del feature opcional, que se encontro en el modelo
	 * 
	 * @param featureName
	 * @param begin
	 * @return featureName
	 */
	public static String separateName(String featureName, String begin) {
		String answer = "";
		if (begin.equals(Derivator.FEATURE))
			answer = featureName.split(" ")[1].split("\"")[1];
		else if (begin.equals(Derivator.OR_ALT))
			answer = featureName.split(" ")[2].split("\"")[1];
		else if (begin.equals(Derivator.FEATURE_CONF)) {
			answer = featureName.split("=")[0].split("\\.")[1];
		}
		return answer;
	}

	/**
	 * Lee el config de FeatureIDE, para saber que se selecciono y que no
	 * 
	 * @param file
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public static FeatureTable readConfig(String file, FeatureTable optional)
			throws IOException, NoSuchAlgorithmException {
		// Lectura del archivo de EXCEL
		BufferedReader br = new BufferedReader(new FileReader(file));

		try {
			// Guarda la última linea encontrada en el archivo file
			String line = br.readLine();

			// Mientras aún existan lineas por leer
			while (line != null) {
				// A medida que aparecen los features los cambia de estado
				optional.changeState(line, true);
				line = br.readLine();
			}

		} finally {
			br.close();
		}

		return optional;
	}

}
