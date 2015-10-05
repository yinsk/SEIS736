package stubs;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class StockPartitioner<K2, V2> extends Partitioner<Text, FloatWritable> {



  public int getPartition(Text key, FloatWritable value, int numReduceTasks) {
    /*
     * TODO implement
     *
     */
	 String s = key.toString();
	 char c = (char) s.charAt(0);
	 int n = (int) c -65;
	 
     return n;
  }
}

