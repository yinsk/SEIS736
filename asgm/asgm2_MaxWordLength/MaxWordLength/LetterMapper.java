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
     * Mapper is used to find the length of word for each input value 
     * then change the word to lower case
     * and use the first letter as the key
     * Also ignore the words that begin with a character other than a-z
     */
	  
	  String line = value.toString();
	  
	  for (String word : line.split("\\W+")) {
		  
		  if (word.length() > 0) {
			  
			  String lowkey = word.substring(0,1).toLowerCase();
			  String normalizekey = lowkey.replaceAll("[^a-z]",""); 
			  
			  if (normalizekey.length() != 0 ){
	    		text.set(normalizekey);
	    		intWritable.set(word.length());
	    		context.write(text, intWritable);
			  }
	    	}
		  
	  
	  }
	  
	  
  }
}
