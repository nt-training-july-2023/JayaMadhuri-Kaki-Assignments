package priority;

class Thread1 extends Thread{
	public void run() {
		System.out.println("Thread 1 is running ");
	}
}
class Thread2 extends Thread{
	public void run() {
		System.out.println("Thread 2 is running ");
	}
}
class Thread3 extends Thread{
	public void run() {
		System.out.println("Thread 3 is running ");
	}
}
public class SetPriorityDemo {
	public static void main(String args[]) throws InterruptedException {
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();
		Thread3 t3 = new Thread3();
		t1.setPriority(1);
		t2.setPriority(3);
		t3.setPriority(5);
		t1.start();
		t2.start();
		t3.start();
	}
}
