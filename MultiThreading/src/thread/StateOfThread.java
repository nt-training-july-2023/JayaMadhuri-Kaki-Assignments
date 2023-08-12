package thread;

class Thread1 extends Thread{
	public void run() {
		try {
			Thread.sleep(300);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
class Thread2 extends Thread{
	public void run() {
		try {
			Thread.sleep(200);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
public class StateOfThread {
	public static void main(String args[]) throws InterruptedException {
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();
		System.out.println(t1.getState());
		t1.start();
		t2.start();
		System.out.println(t1.getState());
		try {
			Thread.sleep(250);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		System.out.println(t1.getState());
		try {
			t1.join();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		System.out.println(t1.getState());
		t2.getState();
	}
}
