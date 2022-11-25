package fun.shijin.mrdemo.compare;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/7/2 9:04
 * @Desc
 */

public class EmpCompareMapper extends Mapper<LongWritable, Text, EmpCompareBean, Text> {

    Text v = new Text();


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        EmpCompareBean k = new EmpCompareBean(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                Integer.parseInt(fields[3]),
                fields[4],
                Integer.parseInt(fields[5]),
                Integer.parseInt(fields[6]),
                Integer.parseInt(fields[7])
        );

        v.set(k.getDeptno() + "-" + k.getSal());

        context.write(k, v);
    }
}
