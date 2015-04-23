
public class Packet {
	public int sourceId;
	public int destId;
	public int minCost[];
	public Packet(int sourceId, int destId, int minCost[]){
		this.sourceId = sourceId;
		this.destId = destId;
		this.minCost = minCost;
	}
}
