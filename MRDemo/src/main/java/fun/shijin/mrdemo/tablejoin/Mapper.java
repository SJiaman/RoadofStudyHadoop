package fun.shijin.mrdemo.tablejoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

public class Mapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable, Text,Text, Bean> {

    Bean bean = new Bean();
    HashMap map = new HashMap();
    /**
     *  缓存hdfs上的数据表
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        try {
            // 获取缓存文件
            URI[] cacheFiles = context.getCacheFiles();
            // 通过缓存文件获取路径
            String path = cacheFiles[0].getPath().toString();
            System.out.println(path);
            // 读取文件信息
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
            String line;
            while (StringUtils.isNotEmpty(line = br.readLine())){
                // 游戏	大数据	1
                // 读取一行数据，拆分
                String[] fields = line.split("\t");
                map.put(fields[2],fields[0]+"\t"+fields[1]);
            }
            // 关闭资源
            IOUtils.closeStream(br);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1	张三	女
        // 读取一行数据，进行拆分
        String line = value.toString();
        String[] fields = line.split("\t");
        // 封装bean对象
        String id = fields[0];
        String name = fields[1];
        String sex = fields[2];
        String hobby = (String)map.get(id).toString().split("\t")[0];
        String job = (String)map.get(id).toString().split("\t")[1];
        bean.setId(id);
        bean.setName(name);
        bean.setSex(sex);
        bean.setHobby(hobby);
        bean.setJob(job);
        // 写出
        context.write(new Text(id),bean);
    }
}
