package Utils.Stream;

import Utils.Model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class listStream {

//    private  static void  main(String args[]){
//
//    }\

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Student student = new Student("01","刘德华",29,"11");
        Student student2 = new Student("02","王源",29,"12");
        Student student3 = new Student("03","刘亦菲",29,"13");
        Student student4 = new Student("04","杨千嬅",29,"11");

        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        //转 List  转数组
        List<String> oldIds = students.stream().map(e->e.getId()).collect(Collectors.toList());
        String      oldId[] = students.stream().map(Student::getId).collect(Collectors.toList()).toArray(new String[students.size()]);
        oldIds.forEach(e->{
            System.out.println("ID:"+e);
        });
        for (String s : oldId) {
            System.out.println("装成数组："+s);
        }
        //注意：PmUserSignInDTO::getUserId和e->e.getUserId()一样

        //  转list并用道号分割成字符串
        String str = students.stream().map(Student::getId).collect(Collectors.joining(","));
        System.out.println("转成字符串"+str);

        //转map
        Map<String,Student> map = students.stream().collect(Collectors.toMap(Student::getId, Student->Student));
        Map<String,String> map1 = students.stream().collect(Collectors.toMap(Student::getId,Student::getName));
        //分组
        Map<String,List<Student>> pcsSubTaskMap = students.stream().collect(Collectors.groupingBy(Student::getTel));
        map.forEach((k,v)->{
            System.out.println("转成map(String,Student)==》》  key:"+k+",value:"+v.toString());
        });
        map1.forEach((k,v)->{
            System.out.println("转成map(String,String)==》》  key:"+k+",value:"+v);
        });
        pcsSubTaskMap.forEach((k,v)->{
            System.out.println("转成map(String,List<Student>)==》》  key:"+k+",value:"+v.size());
        });

        //在foreach 里面 无法 改变值类型的值
        int i = 10;
        Boolean bl = true;
        Integer integer = 10;
        Map  map2 = new HashMap();
        Student stu = new Student();
        final Student stuFinal = new Student();
        students.forEach(e->{
            stu.setAge(10);
            stuFinal.setAge(10);
//            bl = false;
//            i = 11;
//            integer = 11;
            map.put("1",e);
        });
    }

}
