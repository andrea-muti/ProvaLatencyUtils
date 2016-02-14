package provapack;


import org.LatencyUtils.LatencyStats;
import org.HdrHistogram.Histogram;

public class ProvaLatency {
	public static void main(String[] args){
		
		int n_ops  = 10;
		
		LatencyStats myOpStats = new LatencyStats();
		
		// During normal operation, record all operation latencies into a LatencyStats instance:

		 for(int i = 0; i < n_ops; i++){
			 
			 // take start time
			 long startTime = System.nanoTime();
			 
			 // Perform operation:
			 perform_operation_random_duration();
			 
			 // Record operation latency:
			 myOpStats.recordLatency(System.nanoTime() - startTime);
		
		 }
		 
		 // Later, report on stats collected:
		 Histogram intervalHistogram = myOpStats.getIntervalHistogram();
		 
		 System.out.println(" max : "+intervalHistogram.getMaxValue()/1000000);
		 System.out.println(" Min : "+intervalHistogram.getMinValue()/1000000);
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
	
	public static void perform_operation_random_duration(){
		long minimum = 500;
		long maximum = 5000;
		long durationMsec = minimum + (int)(Math.random() * (maximum-minimum));
		try {
			System.out.println(" - performing operation [ theoretical duration : "+durationMsec+" ms ]");
			Thread.sleep(durationMsec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
