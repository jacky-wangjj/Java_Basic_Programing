package JSON;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by wangjj on 2018/11/12.
 */
public class JSONParse {

    public static void main(String[] args) {
        String objStr = "[{\"name\":\"jacky\",\"id\":1,\"addr\":\"Beijing\",\"hobby\":[\"ping-pong\",\"basketball\"]},{\"name\":\"XiaoMing\",\"id\":2,\"addr\":\"Hebei\",\"hobby\":[\"ping-pong\",\"football\"]}]";
        List<Student> studentList = JSON.parseArray(objStr, Student.class);
        for (Student student : studentList) {
            System.out.println("id:"+student.getId());
            System.out.println("name:"+student.getName());
            System.out.println("addr:"+student.getAddr());
            System.out.print("hobby:");
            for (String hobby : student.getHobby()) {
                System.out.print(hobby+" ");
            }
            System.out.println();
        }
    }
}
