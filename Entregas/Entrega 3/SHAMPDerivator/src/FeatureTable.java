import java.util.Hashtable;

public class FeatureTable {

	private Hashtable<String, Boolean> optionals;

	public FeatureTable() {
		optionals = new Hashtable<>();
	}

	public Hashtable<String, Boolean> getTable() {
		return optionals;
	}

	public void addFeature(String name, boolean state) {
		optionals.put(name, state);
	}

	public void addFeature(String name) {
		optionals.put(name, false);
	}

	public void changeState(String name, boolean state) {
		optionals.replace(name, state);
	}

	public void changeName(String nameOld, String newName) {
		boolean state = false;
		state = optionals.get(nameOld);
		optionals.remove(nameOld);
		optionals.put(newName, state);
	}

	public Feature getOptional(String name) {
		Feature answer;
		answer = new Feature(name, optionals.get(name));
		return answer;
	}

	public String optionalToString(String name) {
		String answer = "";
		Feature feature = getOptional(name);
		answer = feature.getMessage();
		return answer;
	}

	public int size() {
		return optionals.size();
	}

	@Override
	public String toString() {
		String answer = "";

		for (String name : optionals.keySet()) {
			answer += optionalToString(name) + "\n";
		}

		return answer;
	}
}
