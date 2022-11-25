package fun.shijin.mrdemo.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/6/18 11:30
 * @Desc
 */

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    int sum;
    IntWritable v = new IntWritable();

    /**
     *
     * @param key 输入的key
     * @param values 每个(k.v)对的值都为1
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {

        // 1.累加求和
        sum = 0;
        for (IntWritable count : values) {
            sum += count.get();
        }

        // 2.输出
        v.set(sum);
        context.write(key,v);
    }
}