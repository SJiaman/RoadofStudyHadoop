package fun.shijin.mrdemo.bean;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/7/1 17:41
 * @Desc
 */

public class EmpReducer extends Reducer<LongWritable, EmpBean, LongWritable, LongWritable> {
    LongWritable sal = new LongWritable();

    @Override
    protected void reduce(LongWritable key, Iterable<EmpBean> values, Context context) throws IOException, InterruptedException {
        long sum = 0;
        for (EmpBean value: values) {
            sum += value.getSal();
        }

        sal.set(sum);

        context.write(key, sal);
    }
}
