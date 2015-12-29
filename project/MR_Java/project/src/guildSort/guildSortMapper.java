package guildSort;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class guildSortMapper extends Mapper<LongWritable, Text, IntWritable,Text > {
	/**
	 * simple input
	 * guildname 123
	 */
	
	Text text = new Text();
	IntWritable intWritable = new IntWritable();

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		
		String[] list = line.split("\\t",-1);
		
		if (!list[0].contains("N/A")){
			
			text.set(list[0]);
			
			int count = Integer.parseInt(list[1]);
				
			intWritable.set(count);
			
			context.write(intWritable,text);
		}
		
	
		
	}	
}