package playerRace;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class racePartitioner<K2, V2> extends Partitioner<Text, FloatWritable> {

  public int getPartition(Text key, FloatWritable value, int numReduceTasks) {
 
	 String s = key.toString();
	 
	 String [] line = s.split("-");
	 
	 int numberReudce = 5;
	 
	 if (line[0].equals("A")){
		 numberReudce = 0;
	 }else if (line[0].equals("H")){
		 numberReudce = 1;
	 }else{
		 numberReudce = 3;
	 }

	return numberReudce;
   
  }
}

