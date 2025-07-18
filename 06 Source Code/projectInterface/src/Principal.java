import projectInterface.Coelho;
import projectInterface.Animal;

public class Principal {
	public static void main(String[] args) {
		Coelho coelho = new Coelho();
		Animal animal = coelho;
		//Coelho coelho02 = new Animal();
		//IVegetariano vegetariano = new IVegetariano();
		//IVegetariano vegetariano02 = coelho;
		Object animal02 = coelho;
		//IVegetariano vegetariano03 = new Animal();
		//animal = animal02;
		//Animal animal03 = vegetariano;
	}
}