public class Node3 implements Node{
	private int nodes = 4;
	private int[][] distance_table = new int[nodes][nodes];
	private int my_id = 3;
	private final int INFINITY = 2147483647;
	public Node3(){
		rtinit();
	}
	public void rtinit(){
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
