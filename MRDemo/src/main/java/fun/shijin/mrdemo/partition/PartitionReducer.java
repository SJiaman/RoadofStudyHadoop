package fun.shijin.mrdemo.partition;

import fun.shijin.mrdemo.bean.EmpBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/7/2 10:59
 * @Desc
 */

public class PartitionReducer extends Reducer<LongWritable, EmpBean, LongWritable, EmpBean> {

    @Override
    protected void reduce(LongWritable key, Iterable<EmpBean> values, Context context) throws IOException, InterruptedException {
        for (EmpBean value : values) {
            context.write(key,value);
        }
    }
}
