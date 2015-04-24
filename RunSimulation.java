import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;


public class RunSimulation{
	public static ArrayBlockingQueue[] mailbox = new ArrayBlockingQueue[4];
	public static final int timeout = 8; //number of seconds to wait before deciding that its over
	public static void main(String[] args){
		init_mailbox();
		(new Node(0, {0,1,3,7})).start();
		(new Node(1, {1,0,1,-1})).start();
		(new Node(2, {3,1,0,2})).start();
		(new Node(3, {7,-1,2,0})).start();
	}

	public static void init_mailbox(){
		for(int i=0; i<4; i++){		
			mailbox[i]=new ArrayBlockingQueue<Packet>(20);
		}	
	}

	public static void send_message(Packet p){
		int dest = p.destId;
		mailbox[dest].add((Object) p);
	}
	public static Packet rcv_message(int id) throws Exception{
		return (Packet) mailbox[id].poll(timeout, TimeUnit.SECONDS);
	}
}
