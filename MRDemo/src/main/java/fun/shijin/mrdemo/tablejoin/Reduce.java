package fun.shijin.mrdemo.tablejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class Reduce extends Reducer<Text, Bean,Bean,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<Bean> values, Context context) throws IOException, InterruptedException {
        // 获取数据进行null值统计
        int count = 0;
        for (Bean bean:values){
            if (bean.getName()==null||bean.getName().equals("Null")){
                count++;
            }
            if (bean.getJob()==null||bean.getJob().equals("Null")){
                count++;
            }
            if (bean.getHobby()==null||bean.getHobby().equals("Null")){
                count++;
            }
            if (bean.getSex()==null||bean.getSex().equals("Null")){
                count++;
            }
            context.write(bean,new LongWritable(count));
        }
    }
}
