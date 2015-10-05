package stubs;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockMapper extends Mapper<LongWritable, Text, Text, FloatWritable> {

  /**
   * 
   * Schema for each line:
   * exchange, stock_symbol, date, stock_price_open, stock_price_high, stock_price_low, stock_price_close, stock_volume, stock_price_adj_close
   * 
   * Example input line:
   * NYSE,ZTR,2010-02-08,3.75,3.81,3.72,3.78,298200,3.78
   *
   * name:0,symbol:1,high:4, low:5
   */
	
	Text sotckSymbol = new Text();
	FloatWritable stockChange = new FloatWritable();
	
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
	  /*  
	   * This mapper is used to calculate the difference between stock_price_low and stock_price_high 
	   * 
	   **/
	  
	  Float highPrice,lowPrice;
	  
	  String stock = value.toString();
	  
	  String[] stockarray = stock.split(",", -1) ;
	  
	  try{
		  sotckSymbol.set(stockarray[1]);
		  
		  highPrice = Float.parseFloat(stockarray[4]);
		  lowPrice = Float.parseFloat(stockarray[5]);
		  
		  Float endvalue = (highPrice-lowPrice)*100/lowPrice;
		  
		  stockChange.set(endvalue);
	  
		  context.write(sotckSymbol, stockChange);
	  }catch(Exception e){
		  
	  }  
  }
}
