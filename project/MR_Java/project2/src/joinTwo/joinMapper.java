package joinTwo;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class joinMapper extends Mapper<LongWritable, Text, Text, Text> {
	/**
	 * simple input
	 * 211     105
	 * 211	"title":"Storm Glory"	10
	 */
	
	

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		
		String[] list = line.split("\\t",2);
		
				
		try{
			
			context.write(new Text(list[0]), new Text(list[1]));
		}catch(Exception e){
			  
		} 
		
	}	
}