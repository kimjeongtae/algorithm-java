import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Data {
	public static int[][] input(String title, int size) {
		int data[][] = new int[size][size];
		try (BufferedReader input = new BufferedReader(new FileReader(title + ".txt"))) {
			String l;
			for (int i = 0; (l = input.readLine()) != null; i++) {
				String[] sp = l.split(" ");
				for (int j = 0; j < sp.length; j++)
					data[i][j] = Integer.parseInt(sp[j]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void random(String title, int size) {
		try (PrintWriter output = new PrintWriter(new FileWriter(title + ".txt"))) {
			int cost = 51;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (j == size - 1) {
						if (i == j) {
							output.print(0);
							continue;
						}
						output.print((int) (Math.random() * cost));
						continue;
					}

					if (i == j) {
						output.print(0 + " ");
						continue;
					}
					output.print((int) (Math.random() * cost) + " ");
				}
				output.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
