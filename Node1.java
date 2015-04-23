public class Node1 implements Node{
	private int nodes = 4;
	private int[][] distance_table = new int[nodes][nodes];
	private final int INFINITY = 2147483647;
	public Node1(){
		rtinit();
	}
	public void rtinit(){
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
