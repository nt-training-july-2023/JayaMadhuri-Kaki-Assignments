package property;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
public class SystemProperty {
	public static void main(String args[]) {
		Properties property = System.getProperties();
		Set<Object> prop;
		
		prop = property.keySet();
		Iterator<Object> it = prop.iterator();
		while(it.hasNext()) {
			String str = (String)it.next();
			System.out.println("property: "+str+" value: "+property.getProperty(str));
		}
	}
}