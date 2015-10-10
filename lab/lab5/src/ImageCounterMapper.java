package stubs;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Example input line:
 * 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] "GET /cat.jpg HTTP/1.1" 200 12433
 *
 */
public class ImageCounterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {


  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    /*
     * TODO implement
     */
	  
	  String line = value.toString().toLowerCase();
	  
	  if (line.contains("gif")){
		  context.getCounter("ImageCounter","GIF").increment(1); 
		  context.getCounter(ImageCount.GIF).increment(1); 
	  }else if(line.contains("jpg")){
		  context.getCounter("ImageCounter","JPEG").increment(1);
		  context.getCounter(ImageCount.JPEG).increment(1); 
	  }else {
		  context.getCounter("ImageCounter","OTHER").increment(1);
		  context.getCounter(ImageCount.OTHER).increment(1); 
	  }
	  
  }
}

enum ImageCount {
	  GIF, JPEG, OTHER;
	  
};

