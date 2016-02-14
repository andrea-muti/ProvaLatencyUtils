package provapack;


import org.LatencyUtils.LatencyStats;
import org.HdrHistogram.Histogram;

public class ProvaLatency {
	public static void main(String[] args){
		
		LatencyStats myOpStats = new LatencyStats();
		
		// During normal operation, record all operation latencies into a LatencyStats instance:

		 for(int i = 0; i < 10; i++){
			 
			 // take start time
			 long startTime = System.nanoTime();
			 
			 // Perform operation:
			 perform_operation(2000);
			 
			 // Record operation latency:
			 myOpStats.recordLatency(System.nanoTime() - startTime);
		
		 }
		 
		 // Later, report on stats collected:
		 Histogram intervalHistogram = myOpStats.getIntervalHistogram();
		 
		 System.out.println(" max : "+intervalHistogram.getMaxValue());
		 System.out.println(" Min : "+intervalHistogram.getMinValue());
		 System.out.println(" mean : "+intervalHistogram.getMean());
		
	}
	
	public static void perform_operation(long durationMsec){
		try {
			System.out.println(" - performing operation");
			Thread.sleep(durationMsec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
