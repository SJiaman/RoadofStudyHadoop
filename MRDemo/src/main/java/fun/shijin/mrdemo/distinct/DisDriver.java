package fun.shijin.mrdemo.distinct;

import fun.shijin.mrdemo.emp.EmpMapper;
import fun.shijin.mrdemo.emp.EmpReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/7/1 9:10
 * @Desc
 */

public class DisDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());

        // 3 设置map和reduce类
        job.setMapperClass(DisMapper.class);
        job.setReducerClass(DisReducer.class);

        // 4 设置map输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        // 5 设置最终输出kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        // 6 设置输入和输出路径
        FileInputFormat.setInputPaths(job, new Path("F:\\Demo\\bigdata\\MRDemo\\src\\main\\resources\\input\\emp.csv"));
        FileOutputFormat.setOutputPath(job, new Path("F:\\Demo\\bigdata\\MRDemo\\src\\main\\resources\\output"));

        // 7 提交
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }
}
