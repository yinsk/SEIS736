package stubs;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class StockPartitioner<K2, V2> extends Partitioner<Text, FloatWritable> {



  public int getPartition(Text key, FloatWritable value, int numReduceTasks) {
    /*
     * This partitioner is used in StockChange 
     * This partitioner will return integer between 0 and 25 base on the first letter of input 
     * ASCII codes
     */
	 String s = key.toString();
	 char c = (char) s.charAt(0);
	 int n = (int) c -65;
	 
     return n;
  }
}

