import java.util.concurrent.ArrayBlockingQueue;
public class RunSimulation{
	public static ArrayBlockingQueue[] mailbox = new ArrayBlockingQueue[4];
	
	public static void main(String[] args){
		init_mailbox();
		
	}

	public static void init_mailbox(){
		for(int i=0; i<4; i++){		
			mailbox[i]=new ArrayBlockingQueue<Packet>(20);
		}	
	}

	public static void send_message(Packet p){
		int dest = p.destId;
		mailbox[dest].add(p);
	}
	public static Packet rcv_message(int id){
		return mailbox[id].take();
	}
}
