package fun.shijin.mrdemo.tablejoin;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class Partition extends Partitioner<Text, Bean> {
    @Override
    public int getPartition(Text text,Bean bean, int numPartitions) {
        // 获取用户信息
        String sex = bean.getSex();
        // 指定分区数
        int partition = 1;
        if (sex.equals("男")){
            partition = 0;
        } else {
            partition = 1;
        }
        return partition;
    }
}
