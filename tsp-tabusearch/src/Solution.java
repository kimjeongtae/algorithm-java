import java.util.ArrayList;

public class Solution implements Comparable<Solution> {
	ArrayList<Integer> way;
	int cost;

	public Solution() {
		way = new ArrayList<Integer>();
		cost = 0;
	}

	@SuppressWarnings("unchecked")
	protected Object clone() {
		Solution v = new Solution();
		v.way=(ArrayList<Integer>)this.way.clone();
		v.cost = this.cost;
		return v;
	}

	public int compareTo(Solution o) {
		return this.cost - o.cost;
	}
}