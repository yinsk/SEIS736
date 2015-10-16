package stubs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class TweetCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	/**
	 * This mapper is used in TweetCount project. 
	 * Use to count how many times the word appears and also count the bad record.
	 * Input Schema: Screen	name,	Tweet,	Language,	Timestamp,	GeoLocation
	 * Simple input line: 
	 * EveilCharm      @evilangela Cliques so tight ... their side.      en      Thu Oct 02 10:21:12 CDT 2014 null
	 * fixed:input simple
	 * shengkai	This is a test	en	Thu Oct 02 10:21:12 CDT 2014	null
	 */

	static enum Tweet {
		NUM_TWEETS, BAD_RECORD;
		
	};
	
	IntWritable intWritable = new IntWritable(1);
	Text text = new Text();
	
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

	
		String[] record = value.toString().split("\\t");
		
		if(record.length==5){
		
			try{
				for (String word : record[1].split("\\W+")) {
					if (word.length() > 0) {
		
						text.set(word);
						context.write(text, intWritable);
						
					}
				}
				
				context.getCounter(Tweet.NUM_TWEETS).increment(1);
			
			}catch(Exception e){
				context.getCounter(Tweet.BAD_RECORD).increment(1);
				
			}
		}else{
			context.getCounter(Tweet.BAD_RECORD).increment(1);
			
		}
	}
}
