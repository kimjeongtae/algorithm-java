import java.util.ArrayList;

public class Sudoku {
	int MAP_SIZE = 9;
	int BLOCK_SIZE = 3;
	int[][] sudoku;
	/**
	 * 위치를 나타내는 클래스
	 * @author DI
	 *
	 */
	public class Position{
		int x;
		int y;
		public Position(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	
	public Sudoku() {
		this.sudoku = new int[MAP_SIZE][MAP_SIZE];
		System.out.println("문제");
		print();
		solve(nextPosition(new Position(0,0)));
		System.out.println(" 답");
		print();
	}
	
	public Sudoku(int[][] sudoku) {
		this.sudoku = sudoku;
		System.out.println("문제");
		print();
		solve(nextPosition(new Position(0,0)));
		System.out.println(" 답");
		print();
	}

	/**
	 * 백트레킹을 이용하여 스토쿠를 푸는 메소드
	 * @param current
	 * @return
	 */
	public boolean solve(Position current) {
		ArrayList<Integer> ps=findPartSolution(current);
			for(int i=0;i<ps.size();i++){
				sudoku[current.x][current.y]=ps.get(i);
				if(current.x==MAP_SIZE-1&&current.y==MAP_SIZE-1)
					return true;
				if(solve(nextPosition(current))){
					return true;
				}
			}
			sudoku[current.x][current.y]=0;
			return false;
		}
	


	
	/**
	 * 다음위치로 이동하는 메소드
	 * @param current
	 * @return
	 */
	public Position nextPosition(Position current){
		Position next=new Position(current.x, current.y);
		while(sudoku[next.x][next.y]!=0){
			if(next.y==MAP_SIZE-1){
				if(next.x==MAP_SIZE-1) {
					return next;
				}
				next.x+=1;
				next.y=0;
			}else
				next.y+=1;
		}
		return next;
	}
	
	/**
	 * 가능해를 찾는 메소드
	 * @param current
	 * @return
	 */
	public ArrayList<Integer> findPartSolution(Position current) {
		int[] ps = new int[MAP_SIZE];
		ArrayList<Integer> p=new ArrayList<Integer>();
		for (int i = 0; i < MAP_SIZE; i++) {
			ps[i] = i+1;
		}
		for (int i = 0; i < MAP_SIZE; i++) {
			if (sudoku[current.x][i] != 0)
				ps[sudoku[current.x][i]-1]=0;
			if (sudoku[i][current.y] != 0)
				ps[sudoku[i][current.y]-1]=0;
		}
		int a = BLOCK_SIZE * (current.x / BLOCK_SIZE);
		int b = BLOCK_SIZE * (current.y / BLOCK_SIZE);
		for (int i = a; i < a + BLOCK_SIZE; i++) {
			for (int j = b; j < b + BLOCK_SIZE; j++) {
				if (sudoku[i][j] != 0)
					ps[sudoku[i][j] - 1] = 0;
			}
		}
		for(int i=0;i<MAP_SIZE;i++){
			if(ps[i]!=0)
				p.add(ps[i]);
		}
		return p;
	}
	
	/**
	 * 프린트하는 메소드
	 */
	public void print() {
		for (int i = 0; i < MAP_SIZE; i++) {
			for (int j = 0; j < MAP_SIZE; j++) {
				System.out.printf(" %2d",sudoku[i][j]);
				//System.out.print(" " +sudoku[i][j]);
				if ((j + 1) % BLOCK_SIZE == 0)
					System.out.print(" ");
			}
			System.out.println();
			if ((i + 1) % BLOCK_SIZE == 0)
				System.out.println();
		}
	}
}
