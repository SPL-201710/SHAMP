import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FilesReader {

	/**
	 * Incluye o excluye vistas dependiendo del valor de un feature
	 * 
	 * @param file
	 * @param feature
	 * @param optional
	 */
	public static void exincludeViews(String file, String feature,
			FeatureTable optional) {
		String directionFile = file.replace("/", "\\").replace("build.sbt", "");
		String directionFileTo = directionFile + "app\\views\\";
		if (!optional.getOptional(feature).getState()) {
			switch (feature) {
			case "Notification":
				findExcludeFiles(directionFileTo, Derivator.MENSAJE_VIEWS);
				break;
			case "Reports":
				findExcludeFiles(directionFileTo, Derivator.REPORTS_VIEWS);
				break;
			case "ChangePassword":
				System.out.println("Quitar: " + directionFileTo + Derivator.CHANGEPASSWORD_VIEWS[0]);
				findExcludeFiles(directionFileTo, Derivator.CHANGEPASSWORD_VIEWS);
				break;
			}
		} else {
			String directionFileFrom = directionFile.replace("play-java\\", "");
			try {
				switch (feature) {
				case "Notification":
					directionFileFrom += "\\Messages\\views\\";
					findIncludeFiles(directionFileFrom, directionFileTo,
							Derivator.MENSAJE_VIEWS);
					break;
				case "Reports":
					directionFileFrom += "\\Reports\\views";
					findIncludeFiles(directionFileFrom, directionFileTo,
							Derivator.REPORTS_VIEWS);
					break;
				case "ChangePassword":
					directionFileFrom += "\\ChangePassword\\views";
					findIncludeFiles(directionFileFrom, directionFileTo,
							Derivator.REPORTS_VIEWS);
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * Eliminar vistas dependiendo del valor de un feature
	 * 
	 * @param directionFile
	 * @param listViews
	 */
	private static void findExcludeFiles(String directionFile,
			String[] listViews) {
		for (String fileToDelete : listViews) {
			File f = new File(directionFile + fileToDelete);
			if (f.exists())
				f.delete();
		}
	}

	/**
	 * Copiar vistas dependiendo del valor de un feature
	 * 
	 * @param directionFileFrom
	 * @param directionFileTo
	 * @param listViews
	 * @throws IOException
	 */
	private static void findIncludeFiles(String directionFileFrom,
			String directionFileTo, String[] listViews) throws IOException {
		for (String fileToAdd : listViews) {
			File fileTo = new File(directionFileTo + fileToAdd);
			if (!fileTo.exists()) {
				File fileFrom = new File(directionFileFrom + fileToAdd);
				copyFile(fileFrom, fileTo);
			}
		}
	}

	/**
	 * Copy file to another path
	 * 
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	private static void copyFile(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());
	}
}
