package sortJoin;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class SortMapper extends Mapper<LongWritable, Text, IntWritable,Text > {
	/**
	 * simple input
	 * 10000   1       "title":"Guardian of the Horde: Warlords Season 1"	10
	 */
	
	Text text = new Text();
	IntWritable intWritable = new IntWritable();

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		
		String[] list = line.split("\\t",-1);
		
		String newVaule = list[0]+ "\t"+ list[2];
		
		text.set(newVaule);
		
		int newKey = Integer.parseInt(list[1]);
			
		intWritable.set(newKey);
		
		context.write(intWritable,text);
	
	}	
}