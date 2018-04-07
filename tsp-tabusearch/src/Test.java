import java.io.IOException;
import java.util.Scanner;


public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		int size =100;
		int[][] data;
		Data.random("data", size);
		data=Data.input("data", size);
		TSP tsp = new TSP(data,size);
		
	}
	
}
