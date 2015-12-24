package stubs;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	IntWritable intWritable = new IntWritable();
	
  @Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException {

    /*
     * The reducer is to find the max record(word length) for each input
     */
	  
	  int wordLength = 0;
	  int max = 0;
	  
	  for (IntWritable value : values) {
		  wordLength = value.get();
		  if (wordLength > max ){
			  max = wordLength;
		  }
	  }
	  	  
	  intWritable.set(max);
	  
	  context.write(key, intWritable);
	  
  }
}