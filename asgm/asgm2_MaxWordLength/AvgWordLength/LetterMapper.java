package stubs;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LetterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	IntWritable intWritable = new IntWritable();
	Text text = new Text();
	
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    /*
     * Mapper is used to find the length of word for each input record, 
     * and use the first letter of each word as output key
     */
	  
	  String line = value.toString();
	  
	  for (String word : line.split("\\W+")) {
		  
		  if (word.length() > 0) {
	    		text.set(word.substring(0,1));
	    		intWritable.set(word.length());
	    		context.write(text, intWritable);
	    	}
		  
	  
	  }
	  
	  
  }
}
