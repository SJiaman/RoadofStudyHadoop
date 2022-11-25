package fun.shijin.mrdemo.tablejoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

public class Driver {
    public static void main(String[] args) {
        Job job;
        Configuration conf = new Configuration();
        try {
            // 获取job
            job = Job.getInstance(conf);
            // 配置
            job.setMapperClass(Mapper.class);
            job.setReducerClass(Reduce.class);
            job.setJarByClass(Driver.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Bean.class);
            job.setOutputKeyClass(Bean.class);
            job.setOutputValueClass(LongWritable.class);

            // 配置缓存
            job.addCacheFile(new URI("file:///F:/Demo/bigdata/MRDemo/src/main/resources/inputcache/imformation.txt"));

            // 自定义分区
            job.setPartitionerClass(Partition.class);
            // reduce计算的数量
            job.setNumReduceTasks(2);

            // 配置输入输出文件
            FileInputFormat.setInputPaths(job, new Path("F:\\Demo\\bigdata\\MRDemo\\src\\main\\resources\\input\\student.txt"));
            FileOutputFormat.setOutputPath(job, new Path("F:\\Demo\\bigdata\\MRDemo\\src\\main\\resources\\output3"));

            // 提交job
            boolean result = job.waitForCompletion(true);
            System.exit(result ? 0 : 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
