import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by JYD_XL on 2017/7/7.
 */
public class Test {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> collect = Stream.of(1, 2, 3, 1).collect(Collectors.groupingBy((Integer::intValue)));
        collect.forEach((k, v) -> {
            v.forEach(System.out::print);
            System.out.println();
        });
        Multimap<Integer,List<Integer>> multimap = ArrayListMultimap.create();
        collect.forEach(multimap::put);
    }
}
