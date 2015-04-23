public class Node0 implements Node{
	private int nodes = 4;
	private int[][] distance_table = new int[nodes][nodes];
	private final int INFINITY = 2147483647;
	private int nodeId = 0;
	public Node0(){
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
		distance_table[0][0] = 0;
		distance_table[0][1] = 1;
		distance_table[0][2] = 3;
		distance_table[0][3] = 7;		
	}
	public void rtupdate(Packet rcvdpkt){
		int distCost;
		//get the sourceid as the id of the node sending the packet
		int srcId = rcvdpkt.sourceId;
		int minCost[] = rcvdpkt.minCost;
		//get the cost of this node to the target - srcId
		int cost = distance_table[nodeId][srcId];
		for (int i = 0; i < 4; i++){
			//get the distance cost from target node to each node
			distCost = minCost[i];
			//check if the cost from node to target to another node is less than the cost of node to another node
			if((distCost + cost) < distance_table[nodeId][i]){
				//update our distance_table
				distance_table[nodeId][i] = distCost + cost;
				//notify neighbor nodes
			}
		}
	}
}
