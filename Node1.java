public class Node1 implements Node{
	private int[][] distance_table;
	private final int INFINITY = 2147483647;
	public Node0(){
		rtinit0();
	}
	public void rtinit0(){
		int nodes = 4;
		distance_table = new int[nodes][nodes];
		for(int i = 0; i < nodes; i++){
			for (int j = 0; j < nodes; j++){
				distance_table[i][j] = INFINITY;
			}		
		}
		distance_table[1][0] = 1;
		distance_table[1][1] = 0;
		distance_table[1][2] = 1;		
	}
	public void rtupdate(){
	}
}
