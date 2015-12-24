package stubs;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StockReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {

	FloatWritable floatwritable = new FloatWritable ();
	
  @Override
  public void reduce(Text key, Iterable<FloatWritable> values, Context context)
      throws IOException, InterruptedException {
    
	  /*
	   * This is the reducer for stockChange
	   * Find max record form input 
	   */
	  
	  Float maxChange = Float.MIN_VALUE;
	  
	  for (FloatWritable value:values){
		   if(value.get() > maxChange){
			   maxChange = value.get();
		   }
	  }
	  
	  floatwritable.set(maxChange);
	  
	  context.write(key, floatwritable);
	  
  }
}
