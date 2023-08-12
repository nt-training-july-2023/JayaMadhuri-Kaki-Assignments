package currentThread;

class Thread1 extends Thread{
	public void run() {
		System.out.println("Thread 1 is running "+Thread1.currentThread());
	}
}
class Thread2 extends Thread{
	public void run() {
		System.out.println("Thread 2 is running "+Thread2.currentThread());
	}
}
class Thread3 extends Thread{
	public void run() {
		System.out.println("Thread 3 is running "+Thread3.currentThread());
	}
}
public class Reading {
	public static void main(String args[]) {
		Thread1 t1 = new Thread1();
		t1.start();
		Thread2 t2 = new Thread2();
		t2.start();
		Thread3 t3 = new Thread3();
		t3.start();
	}
}

