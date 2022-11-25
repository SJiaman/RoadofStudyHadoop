package fun.shijin.mrdemo.emp;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/6/25 11:43
 * @Desc
 */

public class EmpReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
    LongWritable v = new LongWritable();


    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long sum = 0;
        for (LongWritable count: values) {
            sum += count.get();
        }

        v.set(sum);

        context.write(key, v);
    }
}
