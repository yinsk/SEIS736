package edu.stthomas.gps.tfidf;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
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


		
public class tfidfDriver extends Configured implements Tool {

    public int run(String[] args) throws Exception {
		
		
		Configuration conf = getConf();
		
		conf.setBoolean("mapred.compress.map.output", true);
		conf.setClass("mapred.map.output.compression.codec", SnappyCodec.class,
		CompressionCodec.class);
		
		Job job = new Job(conf);
		
		FileOutputFormat.setCompressOutput(job, true);
		FileOutputFormat.setOutputCompressorClass(job,SnappyCodec.class);
		SequenceFileOutputFormat.setOutputCompressionType(job,
		CompressionType.BLOCK);
		
		
		job.setInputFormatClass(SequenceFileInputFormat.class);
		job.setOutputFormatClass(SequenceFileOutputFormat.class);

		
		job.setJarByClass(tfidfDriver.class);

	
		job.setJobName("tf idf");

		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
	
		
		job.setMapperClass(tfidfMapper.class);
		job.setReducerClass(tfidfReducer.class);
		
		job.setMapOutputKeyClass(FloatWritable.class);
	    job.setMapOutputValueClass(WordFilenameWritable.class);

		job.setOutputKeyClass(FloatWritable.class);
		job.setOutputValueClass(WordFilenameWritable.class);
		
		job.setSortComparatorClass(FloatComparator.class);
		
		job.setNumReduceTasks(1);
	    

		
		return (job.waitForCompletion(true) ? 0 : 1);
	}
    
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new tfidfDriver(), args);
        System.exit(exitCode);
    }
}
