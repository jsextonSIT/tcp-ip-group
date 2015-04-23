public class Node0 implements Node{
	private int nodes = 4;
	private int[][] distance_table = new int[nodes][nodes];
	private int my_id = 0;
	private final int INFINITY = 2147483647;
	public Node0(){
		rtinit();
	}
	public void rtinit(){
		int nodes = 4;
		distance_table = new int[nodes][nodes];
		//Initialize all of table to INFINITY, i.e. largest java int
		for(int i = 0; i < nodes; i++){
			for (int j = 0; j < nodes; j++){
				distance_table[i][j] = INFINITY;
			}		
		}

		//Initialize the table for known values
		distance_table[0][0] = 0;
		distance_table[0][1] = 1;
		distance_table[0][2] = 3;
		distance_table[0][3] = 7;

		Packet packet;
		//create packets for other ones
		for(int i = 0; i < nodes; i++){
			if( i != my_id){
				packet = new Packet(my_id, i, distance_table[0]);
				
			}
		}
	}
	public void rtupdate(){
	}

	
}
