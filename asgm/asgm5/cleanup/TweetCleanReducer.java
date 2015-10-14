package cleanup;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class TweetCleanReducer extends Reducer<IntWritable, Text , IntWritable, Text > {

	/*
	 * input:
	 * 10 word  
	 * 10 words
	 * output:
	 * 10 word words
	 */
	
	Text text =new Text();
	
	@Override
	public void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		String word = null;
		String line = "";
		
		for (Text value : values) {

			word = value.toString();
			
			line = line + " " + word;
		}
		text.set(line);
		
		
		context.write(key, text);
	}
}