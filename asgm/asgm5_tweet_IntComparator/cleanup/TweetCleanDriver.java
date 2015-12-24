package cleanup;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

/* 
 * MapReduce jobs are typically implemented by using a driver class.
 * The purpose of a driver class is to set up the configuration for the
 * MapReduce job and to run the job.
 * Typical requirements for a driver class include configuring the input
 * and output data formats, configuring the map and reduce classes,
 * and specifying intermediate data formats.
 * 
 * The following is the code for the driver class:
 */
public class TweetCleanDriver {

	public static void main(String[] args) throws Exception {

		
		if (args.length != 2) {
			System.out.printf("Usage: WordCount <input dir> <output dir>\n");
			System.exit(-1);
		}

		
		Job job = new Job();

		
		job.setJarByClass(TweetCleanDriver.class);

	
		job.setJobName("Tweet Cleaner");

		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		
		job.setMapperClass(TweetCleanMapper.class);
		job.setReducerClass(TweetCleanReducer.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);
		
		//to do:
		
		
		job.setMapOutputKeyClass(IntWritable.class);
	    job.setMapOutputValueClass(Text.class);
	    
	    job.setSortComparatorClass(IntComparator.class);
	    
	    job.setNumReduceTasks(1);

		
		boolean success = job.waitForCompletion(true);
		System.exit(success ? 0 : 1);
	}
}
