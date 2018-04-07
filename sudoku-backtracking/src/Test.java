
public class Test {

	public static void main(String[] args) {
		int[][] sudoku = new int[9][9];
		sudoku[0][5] = 4;
		sudoku[0][6] = 9;
		sudoku[0][7] = 1;
		
		sudoku[1][2] = 9;
		sudoku[1][5] = 3;
		sudoku[1][6] = 5;
		sudoku[1][7] = 2;
		
		sudoku[2][1] = 5;
		sudoku[2][3] = 1;
		sudoku[2][6] = 8;
		sudoku[2][7] = 4;
		
		sudoku[3][3] = 3;
		sudoku[3][5] = 6;
		sudoku[3][7] = 8;
		
		sudoku[5][1] = 7;
		sudoku[5][3] = 2;
		sudoku[5][5] = 9;
		
		sudoku[6][1] = 3;
		sudoku[6][2] = 7;
		sudoku[6][5] = 8;
		sudoku[6][7] = 6;
		
		sudoku[7][1] = 8;
		sudoku[7][2] = 5;
		sudoku[7][3] = 4;
		sudoku[7][6] = 7;
		
		sudoku[8][1] = 1;
		sudoku[8][2] = 4;
		sudoku[8][3] = 7;
	
		new Sudoku(sudoku);
	}

}
