package fun.shijin.mrdemo.partition;

import fun.shijin.mrdemo.bean.EmpBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/7/2 10:33
 * @Desc 自定义分区 mapper
 */

public class PartitonMapper extends Mapper<LongWritable, Text, LongWritable, EmpBean> {

    LongWritable k = new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        EmpBean v = new EmpBean(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                Integer.parseInt(fields[3]),
                fields[4],
                Integer.parseInt(fields[5]),
                Integer.parseInt(fields[6]),
                Integer.parseInt(fields[7])
        );

        k.set(Long.parseLong(fields[7]));

        context.write(k, v);
    }
}
