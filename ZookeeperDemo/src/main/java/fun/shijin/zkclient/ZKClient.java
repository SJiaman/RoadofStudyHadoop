package fun.shijin.zkclient;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @Author Shijin
 * @Date 2020/11/6 20:42
 * @Desc zookeeper API的使用
 */

public class ZKClient {
    private String connectString = "hadoop01:2181,hadoop02:2181,hadoop03:2181";
    private static int sessionTimeout = 2000;
    private  ZooKeeper zooKeeper = null;

    @Before
    public void before() throws IOException {
        //创建一个zookeeper对象
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                // 收到事件通知后的回调函数（用户的业务逻辑）
                System.out.println(watchedEvent.getType() + "--" + watchedEvent.getPath());

                // 再次启动监听
                try {
                    zooKeeper.getChildren("/", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @After
    public void after() throws InterruptedException {
        //关闭资源
        zooKeeper.close();
    }

    @Test
    public void create() throws KeeperException, InterruptedException {
        // 参数1：要创建的节点的路径； 参数2：节点数据 ； 参数3：节点权限 ；参数4：节点的类型
//        zooKeeper.create("/jiaman", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create("/ppp", "lishi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create("/lishi", "lishi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void ls() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/", new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("自定义回调函数！");
            }
        });
        for (String c : children) {
            System.out.println(c);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists("/lishi", false);
        System.out.println(stat == null ? "not exist" : "exist!");
    }

    @Test
    public void get() throws IOException, KeeperException, InterruptedException {
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/lishi", false, stat);
        System.out.println(stat.getDataLength());
        System.out.write(data);
        System.out.println();
    }

    @Test
    public void set() throws KeeperException, InterruptedException {
        String node = "/jiaman";
        Stat stat = zooKeeper.exists(node, false);
        //version目的保证修改的都是同一个东西
        if (stat == null){
            System.out.println("节点不存在！");
        }else {
            zooKeeper.setData("/jiaman", "abdc".getBytes(), stat.getVersion());
        }
    }

    @Test
    public void delete() throws KeeperException, InterruptedException {
        zooKeeper.delete("/lishi", 0);
    }

}
