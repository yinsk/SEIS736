package edu.stthomas.gps.tfidf;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapreduce.Mapper;



public class tfidfMapper extends Mapper<WordFilenameWritable, FloatWritable, FloatWritable, WordFilenameWritable> {
	/**
	 * This mapper is used in tfidf project. 
	 * this project used custom writable which called WordFilenameWritable
	 * simple input
	 * a,D1.txt 0.79
	 * to 
	 * 0.79 a,D1.txt
	 */

	@Override
	public void map(WordFilenameWritable key, FloatWritable value, Context context)
			throws IOException, InterruptedException {

		context.write(value, key);
		

	}
}