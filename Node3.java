public class Node3 implements Node{
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
		distance_table[3][0] = 7;
		distance_table[3][2] = 2;
		distance_table[3][3] = 0;		
	}
	public void rtupdate(){
	}
}
