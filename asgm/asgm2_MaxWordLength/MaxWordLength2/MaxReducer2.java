package stubs;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxReducer2 extends Reducer<Text, Text, Text, Text> {

	Text text = new Text();
	
  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {

    /*
     * The reducer is to find the max record(word length and an example) for each input
     */
	  
	  int wordLength = 0;
	  int max = 0;
	  String word = "null";
	  String longword = "null";
	  
	  for (Text value : values) {
		  
		  word = value.toString();
		  wordLength = word.length();
				  
		  if (wordLength > max ){
			  max = wordLength;
			  longword = word;
		  }
	  }
	  	  
	  text.set(max+ "	" + longword);
	  
	  context.write(key, text);
	  
  }
}