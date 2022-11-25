package fun.shijin.mrdemo.tablejoin;


import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Bean implements Writable {
    // 封装属性
    private String id;
    private String name;
    private String sex;
    private String hobby;
    private String job;
    // 无参构造器
    public Bean() {
    }
    // 重写toString
    @Override
    public String toString() {
        return "Bean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", hobby='" + hobby + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
    // 序列化
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(id);
        dataOutput.writeUTF(name);
        dataOutput.writeUTF(sex);
        dataOutput.writeUTF(hobby);
        dataOutput.writeUTF(job);
    }
    // 反序列化
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        id = dataInput.readUTF();
        name = dataInput.readUTF();
        sex = dataInput.readUTF();
        hobby = dataInput.readUTF();
        job = dataInput.readUTF();
    }
    // set\get
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}

