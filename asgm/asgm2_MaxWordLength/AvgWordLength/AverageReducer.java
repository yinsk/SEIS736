package stubs;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {

	DoubleWritable doubleWritable = new DoubleWritable();
	
  @Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException {

    /*
     * This reducer is to calculate the average of input records
     */
	  
	  double wordLength = 0;
	  double wordNo = 0;
	  
	  for (IntWritable value : values) {
		  wordLength += value.get();
		  wordNo +=1;
	  }
	  	  
	  doubleWritable.set(wordLength/wordNo);
	  
	  context.write(key, doubleWritable);
	  
  }
}