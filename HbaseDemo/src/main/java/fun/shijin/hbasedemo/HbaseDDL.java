package fun.shijin.hbasedemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;


/**
 * @Author Jiaman
 * @Date 2021/7/2 21:05
 * @Desc
 */

public class HbaseDDL {

    public static void main(String[] args) throws IOException {
        boolean b = isTableExist("teacher");
        System.out.println("teacher" + b);
    }

    //TODO 判断表是否存在
    public static boolean isTableExist(String tableName) throws IOException {

        //1.创建配置信息并配置
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop01,hadoop02,hadoop03");

        //2.获取与HBase的连接
        Connection connection = ConnectionFactory.createConnection(configuration);

        //3.获取DDL操作对象
        Admin admin = connection.getAdmin();

        //4.判断表是否存在操作
        boolean exists = admin.tableExists(TableName.valueOf(tableName));

        //5.关闭连接
        admin.close();
        connection.close();

        //6.返回结果
        return exists;

    }
}