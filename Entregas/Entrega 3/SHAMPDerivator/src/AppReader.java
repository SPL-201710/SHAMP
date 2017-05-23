

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AppReader {

	/**
	 * Lee el aplication conf del proyecto, y cambia las lineas según la
	 * configuración realizada en feature IDE
	 * 
	 * @param file
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 *             Ejemplo: line = evaluateRoute("Notification", line, MESSAGE,
	 *             actualFeature);
	 */
	public static void readApplicationConf(String file, FeatureTable optional)
			throws IOException, NoSuchAlgorithmException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuffer sb = new StringBuffer();
		String begin = "";
		begin = Derivator.FEATURE_CONF;

		try {
			String line = br.readLine();

			while (line != null) {
				if (line.startsWith(begin)) {
					String nameFeature = ModelReader.separateName(line, begin);
					line = reviewState(nameFeature, optional);
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
	 * Ingresa la linea a analizar, evalua el feature con el mismo nombre y
	 * returna el estado para el aplication conf
	 * 
	 * @param line
	 * @return new line
	 */
	private static String reviewState(String line, FeatureTable optional) {
		String answer = Derivator.FEATURE_CONF;
		boolean state = false;

		switch (line) {
		case "advancesearch":
			state = optional.getOptional("AdvancedSearch").getState();
			break;
		case "productrating":
			state = optional.getOptional("RateProducts").getState();
			break;
		case "shirttext":
			state = optional.getOptional("Text").getState();
			break;
		case "messages":
			state = optional.getOptional("Notification").getState();
			break;
		case "filters":
			state = optional.getOptional("StampEffect").getState();
			break;
		case "privatestamp":
			state = optional.getOptional("Notification").getState();
			break;
		case "loginsocialnetwork":
			if (optional.getOptional("AuthTwitter").getState()
					|| optional.getOptional("AuthFacebook").getState())
				state = true;
			else
				state = false;
		case "sharesocialnetwork":
			state = optional.getOptional("ShareOnSocialNetwork").getState();
			break;
		case "changepassword":
			state = optional.getOptional("ChangePassword").getState();
			break;
		case "changeaddress":
			state = optional.getOptional("ChangeAddress").getState();
			break;
		case "ratingsreports":
			state = optional.getOptional("Rating").getState();
			break;
		case "sellreports":
			state = optional.getOptional("Sales").getState();
			break;
		default:
			state = false;
			break;
		}

		answer += line + "=\"" + String.valueOf(state) + "\"";
		return answer;
	}

}
