package stubs;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class TestWordCount {


  MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
  ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
  MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

  
  @Before
  public void setUp() {


    TweetCountMapper mapper = new TweetCountMapper();
    mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
    mapDriver.setMapper(mapper);

   
    TweetCountReducer reducer = new TweetCountReducer();
    reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>();
    reduceDriver.setReducer(reducer);


    mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>();
    mapReduceDriver.setMapper(mapper);
    mapReduceDriver.setReducer(reducer);
  }


  @Test
  public void testMapper() {


	  mapDriver.withInput(new LongWritable(1), new Text("shengkai	This is a test test	en	Thu Oct 02 10:21:12 CDT 2014	null"));
	  mapDriver.withOutput(new Text("This"), new IntWritable(1));
	  mapDriver.withOutput(new Text("is"), new IntWritable(1));
	  mapDriver.withOutput(new Text("a"), new IntWritable(1));
	  mapDriver.withOutput(new Text("test"), new IntWritable(1));
	  mapDriver.withOutput(new Text("test"), new IntWritable(1));
	  mapDriver.runTest();

  }


  @Test
  public void testReducer() {

    /*
     * For this test, the reducer's input will be "cat 1 1".
     * The expected output is "cat 2".
     * TODO: implement
     */
	 /*
	  List<IntWritable> values = new ArrayList<IntWritable>();
	  values.add( new IntWritable(1) );
	  values.add( new IntWritable(1) );
	  reduceDriver.withInput( new Text(“cat”), values);
	  reduceDriver.withOutput( new Text(“cat”), new IntWritable(2) );
	  reduceDriver.runTest();
	  */
	  List<IntWritable> values = new ArrayList<IntWritable>();
	    values.add(new IntWritable(1));
	    values.add(new IntWritable(1));
	    reduceDriver.withInput(new Text("cat"), values);
	    reduceDriver.withOutput(new Text("cat"), new IntWritable(2));
	    reduceDriver.runTest();

  }


  /*
   * Test the mapper and reducer working together.
   */
  @Test
  public void testMapReduce() {

    /*
     * For this test, the mapper's input will be "1 cat cat dog" 
     * The expected output (from the reducer) is "cat 2", "dog 1". 
     * TODO: implement
     */
	  mapReduceDriver.withInput(new LongWritable(1), new Text("shengkai	This is a test test	2014	null"));
	  mapDriver.withOutput(new Text("This"), new IntWritable(1));
	  mapDriver.withOutput(new Text("is"), new IntWritable(1));
	  mapDriver.withOutput(new Text("a"), new IntWritable(1));
	  mapDriver.withOutput(new Text("test"), new IntWritable(2));
	  mapReduceDriver.runTest();

  }
}
