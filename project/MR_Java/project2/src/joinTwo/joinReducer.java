package joinTwo;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class joinReducer extends Reducer<Text, Text , Text, Text > {
	
	/**
	 * simple input
	 * 211     105
	 * 211	"title":"Storm Glory"
	 */
	
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		
		String title = "";
		int count = 0;
		
		for (Text value : values) {
			
			String line = value.toString();
			
			if (line.contains("title")){
				title =  line;
			}else{
				try{
					count = Integer.parseInt(line);
				}
				catch(Exception e){
					System.out.print("error happened");
				}
			}		
		}

		String newLine = count + "\t" + title;
		context.write(key,new Text(newLine));
	}
}
