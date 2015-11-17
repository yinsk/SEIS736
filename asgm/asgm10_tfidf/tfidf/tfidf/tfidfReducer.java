package edu.stthomas.gps.tfidf;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class tfidfReducer extends Reducer<FloatWritable, WordFilenameWritable , FloatWritable, WordFilenameWritable > {
	/**
	 * for each key and value write to context
	 */
	
	
	WordFilenameWritable word = new WordFilenameWritable();

	
	@Override
	public void reduce(FloatWritable key, Iterable<WordFilenameWritable> values, Context context)
			throws IOException, InterruptedException {

		for (WordFilenameWritable value : values) {
			  word = value;
			  context.write(key, word);
		  }
	}
}