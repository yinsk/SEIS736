package population;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.SnappyCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


		
public class popDriver extends Configured implements Tool {

    public int run(String[] args) throws Exception {
		
		
			
		Job job = new Job();
		
		
		job.setJarByClass(popDriver.class);

	
		job.setJobName("fatcion count");

		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
	
		
		job.setMapperClass(popMapper.class);
		job.setReducerClass(popReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
	    job.setMapOutputValueClass(IntWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		
		job.setNumReduceTasks(1);
	    

		
		return (job.waitForCompletion(true) ? 0 : 1);
	}
    
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new popDriver(), args);
        System.exit(exitCode);
    }
}
