package work.etasas.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author sas
 * @create 2024-11-01-12:56
 */
public class ShardingTableConfig {
    private static final List<String> tableSuffixList = new ArrayList<>();

    private static Random random = new Random();

    static {
        tableSuffixList.add("0");
        tableSuffixList.add("a");
    }

    public  static String getTableSuffix() {
        return tableSuffixList.get(random.nextInt(tableSuffixList.size()));
    }

}
