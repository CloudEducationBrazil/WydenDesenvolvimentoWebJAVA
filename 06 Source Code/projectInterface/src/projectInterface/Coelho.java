package projectInterface;

import projectInterface.interfaces.IVegetariano;

public class Coelho extends Animal implements IVegetariano{

	public Coelho() {}; 
	
	private String sobre;
	
	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public String buscarVegetariano() {
		return "oi";
	};
}