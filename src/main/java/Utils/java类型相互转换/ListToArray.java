package Utils.java类型相互转换;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToArray {
    public static void main(String[] args) {
        //1.1     LIST TO STRING
        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");
        String arrIds[] =  ids.toArray(new String[0]); //new String[0]是指定返回数组的类型
        System.out.println(arrIds.toString());
        // 1.2     STRING TO LIST
        List<String> lists= Arrays.asList(arrIds);
        System.out.println(lists.toString());
    }
}
