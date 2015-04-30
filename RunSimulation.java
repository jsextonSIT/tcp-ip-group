import java.util.concurrent.*;


public class RunSimulation{
	public static ArrayBlockingQueue[] mailbox = new ArrayBlockingQueue[4];
	public static final int timeout = 6; //number of seconds to wait before deciding that its over
	public static void main(String[] args){
		init_mailbox();
		int[] arr1 = {0,1,3,7};
		int[] arr2 = {1,0,1,-1};
		int[] arr3 = {3,1,0,2};
		int[] arr4 = {7,-1,2,0};
		try{			
			ExecutorService pool = Executors.newCachedThreadPool();
			pool.submit(new Thread(new Node(0, arr1)));
			pool.submit(new Thread(new Node(1, arr2)));
			pool.submit(new Thread(new Node(2, arr3)));
			pool.submit(new Thread(new Node(3, arr4)));
			pool.shutdown();
			pool.awaitTermination(1, TimeUnit.SECONDS);
		}catch(Exception e){

		}
	}

	public static void init_mailbox(){
		for(int i=0; i<4; i++){		
			mailbox[i]=new ArrayBlockingQueue<Packet>(20);
		}	
	}

	public static void send_message(Packet p){
		int dest = p.destId;
		System.out.println(p.sourceId + " sending to " + p.destId);
 		mailbox[dest].add((Object) p);
	}
	public static Packet rcv_message(int id) throws Exception{
		System.out.println(id + " reading from mailbox.");
		return (Packet) mailbox[id].poll(timeout, TimeUnit.SECONDS);
	}
}
