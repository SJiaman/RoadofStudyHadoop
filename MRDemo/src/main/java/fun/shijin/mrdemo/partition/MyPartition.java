package fun.shijin.mrdemo.partition;

import fun.shijin.mrdemo.bean.EmpBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @Author Jiaman
 * @Date 2021/7/2 10:42
 * @Desc
 */

public class MyPartition extends Partitioner<LongWritable, EmpBean> {

    @Override
    public int getPartition(LongWritable longWritable, EmpBean empBean, int i) {
        if (empBean.getDeptno() == 10) {
            return 1 % i;
        } else if (empBean.getDeptno() == 20) {
            return 2 % i;
        } else if (empBean.getDeptno() == 30) {
            return 3 % i;
        }
        return i;
    }
}
