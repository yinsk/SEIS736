package guildSort;

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

public class guildSortDriver extends Configured implements Tool {

    public int run(String[] args) throws Exception {
	
		Job job = new Job();
		job.setJarByClass(guildSortDriver.class);
		job.setJobName("guild sort");

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(guildSortMapper.class);
		job.setReducerClass(guildSortReducer.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
	    	job.setMapOutputValueClass(Text.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);
		
		job.setSortComparatorClass(IntComparator.class);
		
		job.setNumReduceTasks(1);

		return (job.waitForCompletion(true) ? 0 : 1);
	}
    
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new guildSortDriver(), args);
        System.exit(exitCode);
    }
}
