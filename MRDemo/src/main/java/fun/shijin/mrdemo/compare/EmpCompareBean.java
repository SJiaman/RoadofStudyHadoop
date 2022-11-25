package fun.shijin.mrdemo.compare;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Author Jiaman
 * @Date 2021/7/2 8:43
 * @Desc  自定义排序，实现compare接口
 */

public class EmpCompareBean implements WritableComparable {
    private int empno;
    private String empname;
    private String job;
    private int leaderno;
    private String enterdate;
    private int sal;
    private int comm;
    private int deptno;

    public EmpCompareBean() {
    }

    public EmpCompareBean(int empno, String empname, String job, int leaderno, String enterdate, int sal, int comm, int deptno) {
        this.empno = empno;
        this.empname = empname;
        this.job = job;
        this.leaderno = leaderno;
        this.enterdate = enterdate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getLeaderno() {
        return leaderno;
    }

    public void setLeaderno(int leaderno) {
        this.leaderno = leaderno;
    }

    public String getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(String enterdate) {
        this.enterdate = enterdate;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public int getComm() {
        return comm;
    }

    public void setComm(int comm) {
        this.comm = comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "EmpCompareBean{" +
                "empno=" + empno +
                ", empname='" + empname + '\'' +
                ", job='" + job + '\'' +
                ", leaderno=" + leaderno +
                ", enterdate='" + enterdate + '\'' +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                '}';
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(this.empno);
        out.writeUTF(this.empname);
        out.writeUTF(this.job);
        out.writeInt(this.leaderno);
        out.writeUTF(this.enterdate);
        out.writeInt(this.sal);
        out.writeInt(this.comm);
        out.writeInt(this.deptno);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.empno = in.readInt();
        this.empname = in.readUTF();
        this.job = in.readUTF();
        this.leaderno = in.readInt();
        this.enterdate = in.readUTF();
        this.sal = in.readInt();
        this.comm = in.readInt();
        this.deptno = in.readInt();
    }

    @Override
    public int compareTo(Object o) {
        EmpCompareBean o1 = (EmpCompareBean) o;
        if (this.getDeptno() > o1.getDeptno()) {
            return 1;
        }else if (this.getDeptno() < o1.getDeptno()) {
            return -1;
        }else {
            return -(this.getSal() - o1.getSal());
        }
    }
}
