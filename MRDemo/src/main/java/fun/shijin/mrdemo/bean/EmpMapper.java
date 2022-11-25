package fun.shijin.mrdemo.bean;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/7/1 17:41
 * @Desc
 */

public class EmpMapper extends Mapper<LongWritable, Text, LongWritable, EmpBean> {

    LongWritable k =  new LongWritable();
    EmpBean emp = new EmpBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        emp.setEmpno(Integer.parseInt(fields[0]));
        emp.setEmpname(fields[1]);
        emp.setJob(fields[2]);
        emp.setLeaderno(Integer.parseInt(fields[3]));
        emp.setEnterdate(fields[4]);
        emp.setSal(Integer.parseInt(fields[5]));
        emp.setComm(Integer.parseInt(fields[6]));
        emp.setDeptno(Integer.parseInt(fields[7]));

        k.set(Long.parseLong(fields[7]));

        context.write(k, emp);
    }
}
