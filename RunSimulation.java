import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;


public class RunSimulation{
	public static ArrayBlockingQueue[] mailbox = new ArrayBlockingQueue[4];
	public static final int timeout = 15; //number of seconds to wait before deciding that its over
	public static void main(String[] args){
		init_mailbox();
		int[] arr1 = {0,1,3,7};
		int[] arr2 = {1,0,1,-1};
		int[] arr3 = {3,1,0,2};
		int[] arr4 = {7,-1,2,0};
		(new Node(0, arr1)).run();
		(new Node(1, arr2)).run();
		(new Node(2, arr3)).run();
		(new Node(3, arr4)).run();
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
