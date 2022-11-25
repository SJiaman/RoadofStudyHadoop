package fun.shijin.mrdemo.bean;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/7/1 17:41
 * @Desc
 */

public class EmpDriver {
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException, IOException {

        // 1 获取配置信息以及封装任务
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        // 2 设置jar加载路径
        job.setJarByClass(EmpDriver.class);

        // 3 设置map和reduce类
        job.setMapperClass(EmpMapper.class);
        job.setReducerClass(EmpReducer.class);

        // 4 设置map输出
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(EmpBean.class);

        // 5 设置最终输出kv类型
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(LongWritable.class);

        // 6 设置输入和输出路径
        FileInputFormat.setInputPaths(job, new Path("F:\\Demo\\bigdata\\MRDemo\\src\\main\\resources\\input\\emp.csv"));
        FileOutputFormat.setOutputPath(job, new Path("F:\\Demo\\bigdata\\MRDemo\\src\\main\\resources\\output4"));

        // 7 提交
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }
}
