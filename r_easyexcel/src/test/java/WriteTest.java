import com.alibaba.excel.EasyExcel;
import com.demo.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.RenHongYang
 * @description :
 * @create 2022-08-24 16:31
 */
public class WriteTest {

    private static List<Student> initData() {
        ArrayList<Student> students = new ArrayList<Student>();
        Student data = new Student();
        for (int i = 0; i < 10; i++) {
            data.setName("模拟学号0" + i);
            data.setBirthday(new Date());
            data.setGender("男");
            students.add(data);
        }
        return students;
    }

    @Test
    public void writeExcel() {
        List<Student> students = initData();
        EasyExcel.write("D:\\IdeaProjects\\practice\\r_easyexcel\\src\\main\\resources\\static\\w.xlsx", Student.class).sheet("学生表").doWrite(students);
    }
}
