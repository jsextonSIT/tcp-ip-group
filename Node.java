public class Node{
	private int nodeId;
	private final int nodes = 4;
	private final static int INFINITY = 2147483647;

	private int[][] distance_table = new int[nodes][nodes];
	
	private boolean[] neighbors = new boolean[nodes];

	public Node(int id, int[] distances){
		nodeId = id;
		//Initialize all of table to INFINITY, i.e. largest java int
		for(int i = 0; i < nodes; i++){
			for (int j = 0; j < nodes; j++){
				distance_table[i][j] = INFINITY;
			}		
		}

		for(int i=0; i<nodes; i++){
			if(distances[i] >= 0){	
				distance_table[nodeId][i] = distances[i];
			}else{
				distance_table[nodeId][i] = INFINITY;
			}
		}
	}

	public void run(){
		rtinit();
		try{
			while(true){
				Packet p = RunSimulation.rcv_message(nodeId);
				if(p != null){					
					rtupdate(p);
				}else{
					break;
				}
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

	public void rtinit(){
		
		//check if you are directly connected with the other nodes
		for(int q = 0; q < nodes; q++){
			if(distance_table[nodeId][q] == INFINITY){
				//if not connected cost is infinity
				neighbors[q] = false;
			} else {
				neighbors[q] = true;
			}
		}

		//temporary packet
		Packet packet;
		for(int i = 0; i < nodes; i++){
			if(neighbors[i] && i != nodeId){
				packet = new Packet(nodeId, i, distance_table[nodeId]);
				RunSimulation.send_message(packet);
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
		Packet update;
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
						update = new Packet(nodeId, y, newMinCosts);
						RunSimulation.send_message(update);
					}
				}
				
			}
		}
	}
	public static void printTable(int srcId, int[][] distTable){
		String row1, row2, row3;
		int[] otherNodes = new int[3];
		String[][] printArr = new String[3][3];
		int num = 0, num2;
		for(int i = 0; i < 4; i++){
			if(i != srcId){
				otherNodes[num] = i;
				num2 = 0;
				for(int y = 0; y < 4; y++){
					if(y != srcId){
						printArr[num][num2] = (distTable[i][y] == INFINITY) ? "I" : Integer.toString(distTable[i][y]);
						num2++;
					}
				}
				num++;
			}
		}
		row1 = "         "+otherNodes[0]+"|    "+printArr[0][0]+"    "+printArr[0][1]+"    "+printArr[0][2];
		row2 = "         "+otherNodes[1]+"|    "+printArr[1][0]+"    "+printArr[1][1]+"    "+printArr[1][2];
		row3 = "         "+otherNodes[2]+"|    "+printArr[2][0]+"    "+printArr[2][1]+"    "+printArr[2][2];
		System.out.println("");
		System.out.println("   Node "+srcId+" |    "+otherNodes[0]+"    "+otherNodes[1]+"    "+otherNodes[2]+" ");
		System.out.println("__________|________________");
		System.out.println(row1);
		System.out.println(row2);
		System.out.println(row3);
	}

	
}
