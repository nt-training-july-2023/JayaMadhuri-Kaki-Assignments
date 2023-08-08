package encapsulation;
public class EncapsulationExample {
	public static void main(String args[]) {
		Encapsulation en = new Encapsulation();
		en.setFirstName("Jaya");
		en.setLastName("Madhuri");
		en.setAge(22);
		System.out.println("first name: "+en.getFirstName());
		System.out.println("last name: "+en.getLastName());
		System.out.println("age: "+en.getAge());
	}
}