package fun.shijin.mrdemo.emp;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/6/25 11:03
 * @Desc
 */

public class EmpMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    Text k = new Text();
    LongWritable v = new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1.获取每一行
        String[] fields = value.toString().split(",");

        k.set(fields[7]);
        v.set(Long.parseLong(fields[5]));
        context.write(k, v);
    }
}
