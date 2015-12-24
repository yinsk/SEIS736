package stubs;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LetterMapper2 extends Mapper<LongWritable, Text, Text, Text> {

	IntWritable intWritable = new IntWritable();
	Text text = new Text();
	Text textkey = new Text();
	
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    /*
     * Mapper is used to change the word to lower case
     * and use the first letter as the key
     * Also ignore the words that begin with a character other than a-z
     * use the first letter as key and word itself as value
     */
	  
	  String line = value.toString();
	  
	  for (String word : line.split("\\W+")) {
		  
		  if (word.length() > 0) {
			  
			  String lowkey = word.substring(0,1).toLowerCase();
			  String normalizekey = lowkey.replaceAll("[^a-z]",""); 
			  
			  if (normalizekey.length() != 0 ){
				textkey.set(normalizekey);
	    		text.set(word);
	    		context.write(textkey, text);
			  }
	    	}
		  
	  
	  }
	  
	  
  }
}
