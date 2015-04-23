public class Node3 implements Node{
	private int nodes = 4;
	private int[][] distance_table = new int[nodes][nodes];
	private int my_id = 3;
	private final int INFINITY = 2147483647;
	private int nodeId = 0;
	private boolean[] neighbors = new boolean[nodes];
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
		for(int q = 0; q < nodes; q++){
			if(distance_table[nodeId][q] == INFINITY){
				//if not connected cost is infinity
				neighbors[q] = false;
			} else {
				neighbors[q] = true;
			}
		}
	}
	public void rtupdate(Packet rcvdpkt){
		int distCost;
		//get the sourceid as the id of the node sending the packet
		int srcId = rcvdpkt.sourceId;
		int minCost[] = rcvdpkt.minCost;
		//get the cost of this node to the target - srcId
		int cost = distance_table[nodeId][srcId];
		for (int i = 0; i < nodes; i++){
			//get the distance cost from target node to each node
			distCost = minCost[i];
			//check if the cost from node to target to another node is less than the cost of node to another node
			if((distCost + cost) < distance_table[nodeId][i]){
				//update our distance_table
				distance_table[nodeId][i] = distCost + cost;
				//notify neighbor nodes
				for(int y = 0; y < nodes; y++){
					//don't send to yourself and send to your neighbors
					if((y != nodeId) && neighbors[y]){
						//prepare our packet
						int[] newMinCosts = {distance_table[nodeId][0], distance_table[nodeId][1], distance_table[nodeId][2], distance_table[nodeId][3]};
						Packet update = new Packet(nodeId, y, newMinCosts);
						//send packet here
					}
				}
				
			}
		}
	}
}
