package cleanup;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class TweetCleanMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
	/**
	 * This mapper is used in TweetCount project. 
	 * Use to clean up the record from first run.
	 * transposes key and value, so the count (as key now ) is sorted
	 * simple input: 
	 * word 10
	 * to 
	 * 10 word
	 */

	
	IntWritable intWritable = new IntWritable(1);
	Text text = new Text();
	
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String[] line = value.toString().split("\\t",-1);
		
		int count = Integer.parseInt(line[1]);
		
		intWritable.set(count);
		text.set(line[0]);
		
		context.write(intWritable, text);
		

	}
}