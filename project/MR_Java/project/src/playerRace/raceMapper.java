package playerRace;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class raceMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	/**
	 * simple input
	 * 1	human	mage	Myshella	stormrage	Progression	alliance	2579	60	254	152	257	880	231	81	66	227	102	34	120	115
	 * to 
	 * alliance 1
	 */
	
	

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		
		String[] list = line.split("\\t",-1);
		
		if (list.length > 7){
			try{
				String playerRace = list[1]; 
				String faction = list[6];
				String factionShort= "";
				
				if (faction.equals("alliance")){
					factionShort = "A";
				}else if(faction.equals("horde")){
					factionShort = "H";
				}else{
					factionShort = "N";
					
				}
				
				String newLine = faction + "-"+ playerRace;
				
				context.write(new Text(newLine), new IntWritable(1));
			}catch(Exception e){
				  
			} 
		}
	}	
}