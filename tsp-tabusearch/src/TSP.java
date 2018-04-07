import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class TSP {
	private int[][] data;
	private LinkedHashSet<String> tabuList;
	Solution bestSolution;
	private Solution initialSolution;
	private int size;

	public TSP(int[][] data, int size) {
		initialSolution = new Solution();
		tabuList = new LinkedHashSet<String>();
		this.size = size;
		this.data = data;
		
		init();
		System.out.println("init");
		print(initialSolution);
		tabuSearch();
		System.out.println("best");
		print(bestSolution);
	}
	
	private void init() {
		if (generateGIS(0)) {
			calculateCost(initialSolution);
			bestSolution = (Solution) initialSolution.clone();
			tabuList.add(transform(initialSolution));
		} else {
			System.err.println("고립점이있습니다.");
			System.exit(1);
		}
	}

	private void tabuSearch() {
	
		int fail = 0;
		Solution a = null;
		LinkedList<Solution> b = new LinkedList<Solution>();
		while (fail < 100) {
			b.clear();
			for (int i = 0; i < size - 1; i++) {
				for (int j = i + 1; j < size; j++) {
					if (isSolution(i, j)) {
						a = swap(i, j);
						if (b.size() < 100) {
							b.add(a);
						} else {
							if (b.peekLast().cost > a.cost) {
								b.pollLast();
								b.add(a);
							}
						}
					}
					Collections.sort(b);
				}
			}

			int i = 0;
			for (i = 0; i < b.size(); i++) {
				String s = transform(b.get(i));

				if (!tabuList.contains(s)) {
					if (tabuList.size() > size)
						tabuList.remove(tabuList.iterator().next());
					tabuList.add(s);
					initialSolution = (Solution) b.get(i).clone();
					if (bestSolution.cost > initialSolution.cost) {
						bestSolution = (Solution) initialSolution.clone();
						fail = 0;
					}
					break;
				}
			}

			//if (i == b.size()) {
			//	System.out.println("?????");
			//	break;
			//}
			System.out.println("fail ->" +  fail++);
		}
	}

	private String transform(Solution x) {
		StringBuffer s = new StringBuffer();
		int start = 0;
		for (int i = 0; i < size; i++)
			if (x.way.get(i) == 0) {
				start = i;
				break;
			}
		for (int j = start; ((j % size != start) || ((j % size == start) && (j
				/ x.way.size() == 0))); j++) {
			s.append(x.way.get(j % size) + " ");
		}
		return s.toString();
	}

	private Solution swap(int i, int j) {
		Solution p = (Solution) initialSolution.clone();
		Integer t = p.way.get(i);
		p.way.set(i, p.way.get(j));
		p.way.set(j, t);
		calculateCost(p);
		return p;
	}

	private boolean isSolution(int i, int j) {
		int i_current = initialSolution.way.get(i);
		int i_front = initialSolution.way.get((i + size - 1) % size);
		int i_rear = initialSolution.way.get((i + 1) % size);
		int j_current = initialSolution.way.get(j);
		int j_front = initialSolution.way.get((j + size - 1) % size);
		int j_rear = initialSolution.way.get((j + 1) % size);

		if (data[i_front][j_current] == 0 || data[j_current][i_rear] == 0
				|| data[j_front][i_current] == 0
				|| data[i_current][j_rear] == 0)
			return false;
		else
			return true;
	}

	private boolean generateRIS(int row) {
		initialSolution.way.add(row);
		ArrayList<Integer> ps = findPartWay(row);
		if (initialSolution.way.size() == size && data[row][0] != 0) {
			return true;
		}
		if (ps.size() != 0) {
			int x = (int) (Math.random() * ps.size());
			for (int j = x; ((j % ps.size() != x) || ((j % ps.size() == x) && (j
					/ ps.size() == 0))); j++) {
				if (generateGIS(ps.get(j % ps.size())))
					return true;
			}
		}
		initialSolution.way.remove(initialSolution.way.size() - 1);
		return false;
	}

	private boolean generateGIS(int row) {
		initialSolution.way.add(row);
		ArrayList<Integer> ps = findPartWay(row);
		Qsort(ps, 0, ps.size()-1, row);
		//for(Integer e:ps)
		//	System.out.print(data[row][e]+ "  ");
		//System.out.println();
		if (initialSolution.way.size() == size && data[row][0] != 0) {
			return true;
		}
		for (int i = 0; i < ps.size(); i++) {
			if (generateRIS(ps.get(i % ps.size())))
				return true;

		}
		initialSolution.way.remove(initialSolution.way.size() - 1);
		return false;
	}

	void swap(ArrayList<Integer> ps, int i, int j) {
		int temp;
		temp = ps.get(i);
		ps.set(i,ps.get(j));
		ps.set(j, temp);
	}

	public void Qsort(ArrayList<Integer> ps, int left, int right,int row) {
		int pe;
		int i, last;

		if (left >= right)
			return;
		pe = (left + right) / 2;
		swap(ps, left, pe);
		last = left;
		for (i = left + 1; i <= right; i++)
			if (data[row][ps.get(i)] < data[row][ps.get(left)])
				swap(ps, ++last, i);
		swap(ps, left, last);
		Qsort(ps, left, last - 1,row);
		Qsort(ps, left + 1, right,row);
	}

	private ArrayList<Integer> findPartWay(int row) {
		ArrayList<Integer> ps = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			if (data[row][i] != 0) {
				int j;
				for (j = 0; j < initialSolution.way.size(); j++) {
					if (initialSolution.way.get(j) == i)
						break;
				}
				if (j == initialSolution.way.size())
					ps.add(i);
			}
		}
		return ps;
	}

	private void calculateCost(Solution x) {
		int cost = 0;
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				cost += data[x.way.get(i)][x.way.get(0)];
				break;
			}
			cost += data[x.way.get(i)][x.way.get(i + 1)];
		}
		x.cost = cost;
	}

	public void print(Solution x) {
		System.out.println("cost:" + x.cost);
		for (Integer e : x.way)
			System.out.print(e + " ");
		System.out.println();

	}
}
