import java.util.ArrayList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        ArrayList<String> sList = new ArrayList<>();
        sList.add("문자열1");
        sList.add("문자열2");
        sList.add("문자열3");
        sList.add("문자열4");
        //System.out.println(sList);
//        for (int i = 0; i < sList.size(); i++) {
//            System.out.println(sList.get(i));
//        }
        for(String slist: sList) {
            System.out.println(slist);
        }

        List<Integer> iList = new ArrayList<>();
        iList.add(1);
        iList.add(2);
        iList.add(3);
        iList.add(4);

        for(int ilist: iList) {
            System.out.println(ilist);
        }
    }
}
