package fun.shijin.mrdemo.compare;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/7/2 9:10
 * @Desc
 */

public class EmpCompareDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1 获取配置信息以及封装任务
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        // 2 设置map和reduce类
        job.setMapperClass(EmpCompareMapper.class);

        // 3 设置map输出
        job.setMapOutputKeyClass(EmpCompareBean.class);
        job.setMapOutputValueClass(Text.class);

        // 4 设置最终输出kv类型
        job.setOutputKeyClass(EmpCompareBean.class);
        job.setOutputValueClass(Text.class);

        // 5 设置输入和输出路径
        FileInputFormat.setInputPaths(job, new Path("F:\\Demo\\bigdata\\MRDemo\\src\\main\\resources\\input\\emp.csv"));
        FileOutputFormat.setOutputPath(job, new Path("F:\\Demo\\bigdata\\MRDemo\\src\\main\\resources\\compareoutput"));

        // 6 提交
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }
}
