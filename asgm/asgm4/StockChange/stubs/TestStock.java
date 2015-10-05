package stubs;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class TestStock {

  /*
   * Declare harnesses that let you test a mapper, a reducer, and
   * a mapper and a reducer working together.
   */
  MapDriver<LongWritable, Text, Text, FloatWritable> mapDriver;
  ReduceDriver<Text, FloatWritable, Text, FloatWritable> reduceDriver;
  MapReduceDriver<LongWritable, Text, Text, FloatWritable, Text, FloatWritable> mapReduceDriver;

  /*
   * Set up the test. This method will be called before every test.
   */
  @Before
  public void setUp() {

    /*
     * Set up the mapper test harness.
     */
    StockMapper mapper = new StockMapper();
    mapDriver = new MapDriver<LongWritable, Text, Text, FloatWritable>();
    mapDriver.setMapper(mapper);

    /*
     * Set up the reducer test harness.
     */
    Reducer<Text, FloatWritable, Text, FloatWritable> reducer = new StockReducer();
    reduceDriver = new ReduceDriver<Text, FloatWritable, Text, FloatWritable>();
    reduceDriver.setReducer(reducer);

    /*
     * Set up the mapper/reducer test harness.
     */
    mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, FloatWritable, Text, FloatWritable>();
    mapReduceDriver.setMapper(mapper);
    mapReduceDriver.setReducer(reducer);
  }

  /*
   * Test the mapper.
   */
  @Test
  public void testMapper() {

    /*
     * 
     * TODO: implement
     */
	  mapDriver.withInput(new LongWritable(1), new Text("NYSE,ZTR,2010-02-08,3.75,3.81,3.72,3.78,298200,3.78"));
	  mapDriver.withOutput(new Text("ZTR"), new FloatWritable((float) 2.4193525));
	  mapDriver.runTest();
  }

  /*
   * Test the reducer.
   */
  @Test
  public void testReducer() {

    /*
     * For this test, the reducer's input will be "cat 1 1".
     * The expected output is "cat 2".
     * TODO: implement
     */
	  List<FloatWritable> values = new ArrayList<FloatWritable>();
	  	values.add(new FloatWritable((float) 2.4193525));
	  	values.add(new FloatWritable((float) 2.1193525));
	    reduceDriver.withInput(new Text("ZTR"), values);
	    reduceDriver.withOutput(new Text("ZTR"), new FloatWritable((float) 2.4193525));
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
	  mapReduceDriver.withInput(new LongWritable(1), new Text("NYSE,ZTR,2010-02-08,3.75,3.81,3.72,3.78,298200,3.78"));
	  mapDriver.withInput(new LongWritable(1), new Text("NYSE,ZTR,2010-02-08,3.75,3.81,3.80,3.78,298200,3.78"));
	  mapDriver.withInput(new LongWritable(1), new Text( "NYSE,AAA,2010-02-08,3.75,3.81,3.72,3.78,298200,3.78"));
	  mapDriver.withInput(new LongWritable(1), new Text("Did you check for clean data?"));
	  mapReduceDriver.addOutput(new Text("ZTR"), new FloatWritable((float) 2.4193525));
	  mapDriver.addOutput(new Text("AAA"), new FloatWritable((((float) 3.81 - (float) 3.72) * 100 / (float) 3.72)));
	  mapReduceDriver.runTest();
  }
}
