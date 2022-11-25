package fun.shijin.mrdemo.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;

/**
 * @Author Jiaman
 * @Date 2021/6/18 11:31
 * @Desc
 */

public class WordCountDriver {

    private static String HDFSUri = "hdfs://hadoop201:9000";

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // 1 获取配置信息以及封装任务
        Configuration configuration = new Configuration();
        // 设置HDFS NameNode的地址
        configuration.set("fs.defaultFS", HDFSUri);
        // 指定MapReduce运行在Yarn上
        configuration.set("mapreduce.framework.name","yarn");
        // 指定mapreduce可以在远程集群运行
        configuration.set("mapreduce.app-submission.cross-platform","true");
        //指定Yarn resourcemanager的位置
        configuration.set("yarn.resourcemanager.hostname","hadoop202");

        //设置mapred.jar的路径, 打包的文件路径
        configuration.set("mapred.jar","F:\\Demo\\bigdata\\MRDemo\\target\\MRDemo-1.0-SNAPSHOT.jar");

        // 设置hadoop的用户名
        System.setProperty("HADOOP_USER_NAME","jiaman");

        Path inPath = new Path(HDFSUri + "/datas/personcsv.csv");
        Path outPath = new Path(HDFSUri +"/output");

        FileSystem fileSystem = FileSystem.get(URI.create(HDFSUri), configuration);
        //删除输出路径
        if(fileSystem.exists(outPath)) {
            fileSystem.delete(outPath,true);
        }

        Job job = Job.getInstance(configuration);

        // 2 设置jar加载路径
        job.setJarByClass(WordCountDriver.class);

        // 3 设置map和reduce类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // 4 设置map输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 5 设置最终输出kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, inPath);
        FileOutputFormat.setOutputPath(job, outPath);

        // 7 提交
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }
}
