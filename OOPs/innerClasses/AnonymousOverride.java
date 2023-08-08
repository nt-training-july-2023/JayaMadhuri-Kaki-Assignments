class Demo{
    public void print(){
        System.out.println("This is a method in Demo");
    }
}
public class AnonymousOverride {
    public static void main(String args[]){
        Demo d = new Demo()
        {
	    @Override
            public void print(){
                System.out.println("This is a method in anonymous class 1");
            }
        };
	d.print();
	Demo d1 = new Demo();
	d1.print();
	Demo d2 = new Demo()
        {
	    @Override
            public void print(){
                System.out.println("This is a method in anonymous class 2");
            }
        };
	d2.print();
    }
}