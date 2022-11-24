package fun.shijin.hdfsdemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.net.URI;

/**
 * @ClassName HDFSClient
 * @Description
 * @Author Shijin
 * @Date 2020/10/4 18:23
 */

public class HDFSClient {
    FileSystem fileSystem;

    //新建对象
    @Before
    public void before() throws IOException, InterruptedException {
        fileSystem = FileSystem.get(URI.create("hdfs://hadoop01:8020"), new Configuration(), "jiaman");
    }

    //关闭集群
    @After
    public void after() throws IOException {
        fileSystem.close();
    }


    //上传
    @Test
    public void put() throws IOException, InterruptedException {
        fileSystem.copyFromLocalFile(
                new Path("D:\\hello.txt")
                , new Path("/"));
    }

    //下载
    @Test
    public void get() throws IOException {
        fileSystem.copyToLocalFile(
                new Path("/output/part-r-00000"),
                new Path("D:/")
        );
    }

    //追加
    @Test
    public void append() throws IOException {
        FSDataOutputStream append = fileSystem.append(new Path("/新建文本文档.txt"));
        append.write("这是追加的abc".getBytes());

        IOUtils.closeStream(append);
    }

    @Test
    public void testListFiles() throws IOException {

        // 2 获取文件详情
        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path("/"), true);

        while (((RemoteIterator) listFiles).hasNext()) {
            LocatedFileStatus status = listFiles.next();

            // 输出详情
            // 文件名称
            System.out.println(status.getPath().getName());
            // 长度
            System.out.println(status.getLen());
            // 权限
            System.out.println(status.getPermission());
            // 分组
            System.out.println(status.getGroup());

            // 获取存储的块信息
            BlockLocation[] blockLocations = status.getBlockLocations();

            for (BlockLocation blockLocation : blockLocations) {

                // 获取块存储的主机节点
                String[] hosts = blockLocation.getHosts();

                for (String host : hosts) {
                    System.out.println(host);
                }
            }

            System.out.println("-----------分割线----------");
        }
    }

    @Test
    public void mv() throws IOException {
        fileSystem.rename(new Path("/新建文本文档.txt"),
                new Path("/logs/1.txt"));
    }
}
