import java.util.*;

public class HelloWorld {
	String str;
	public void hello() {
		str = "Hello World!";
	} 
	public String getStr() {
		return str;
	}
	public static void main(String args[]) {
		HelloWorld helloworld = new HelloWorld();
		helloworld.hello();
		System.out.println(helloworld.getStr());
	}
}