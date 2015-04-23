public interface Node extends Runnable{
	void rtinit();
	void rtupdate(Packet rcvdpkt);
}
