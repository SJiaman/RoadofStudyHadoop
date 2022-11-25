package fun.shijin.mrdemo.partition;

import fun.shijin.mrdemo.bean.EmpBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/7/2 10:59
 * @Desc
 */

public class PartitionDriver {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        //  获取配置信息以及封装任务
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        //  设置map和reduce类
        job.setMapperClass(PartitonMapper.class);
        job.setReducerClass(PartitionReducer.class);

        //  设置map输出
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(EmpBean.class);

        //  设置最终输出kv类型
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(EmpBean.class);

        // 设置分区类
        job.setPartitionerClass(MyPartition.class);

        // 设置reduce task个数
        job.setNumReduceTasks(3);

        //  设置输入和输出路径
        FileInputFormat.setInputPaths(job, new Path("F:\\Demo\\bigdata\\MRDemo\\src\\main\\resources\\input\\emp.csv"));
        FileOutputFormat.setOutputPath(job, new Path("F:\\Demo\\bigdata\\MRDemo\\src\\main\\resources\\output"));

        //  提交
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }
}
