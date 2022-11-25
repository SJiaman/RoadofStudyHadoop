package fun.shijin.mrdemo.distinct;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/7/1 8:56
 * @Desc
 */

public class DisMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");

        k.set(fields[7] + "-" + fields[2]);

        context.write(k, NullWritable.get());
    }
}
