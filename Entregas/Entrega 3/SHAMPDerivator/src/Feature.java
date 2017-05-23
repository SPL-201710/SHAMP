public class Feature {

	String name = "";
	boolean state = false;

	public Feature(String Name, boolean State) {
		name = Name;
		state = State;
	}

	public String getMessage() {
		return "feature." + name + "=\"" + String.valueOf(state) + "\"";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return getMessage();
	}
}
