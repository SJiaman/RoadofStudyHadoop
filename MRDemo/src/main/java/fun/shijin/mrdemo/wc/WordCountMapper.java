package fun.shijin.mrdemo.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/6/18 11:15
 * @Desc
 */

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    Text k = new Text();
    IntWritable v = new IntWritable(1);


    /**
     *框架把数据一行行的输入进来，我们把数据变成（单词，1）的形式
     * @param key 行号
     * @param value 行内容
     * @param context 框架本身
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1 获取一行
        String line = value.toString();

        // 2 切割
        String[] words = line.split(",");

        // 3 输出
        for (String word : words) {

            k.set(word);
            context.write(k, v);
        }
    }

}