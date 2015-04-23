
public class Packet {
	int sourceId;
	int destId;
	int minCost[];
	public Packet(int sourceId, int destId, int minCost[]){
		this.sourceId = sourceId;
		this.destId = destId;
		this.minCost = minCost;
	}
}
