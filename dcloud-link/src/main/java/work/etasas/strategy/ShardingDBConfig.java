package work.etasas.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author sas
 * @create 2024-11-01-12:52
 */
public class ShardingDBConfig {

    /**
     * 存储数据库位置编号
     */
    private static final List<String> dbPrefixList = new ArrayList<>();

    private static Random random = new Random();

    static {
        dbPrefixList.add("0");
        dbPrefixList.add("1");
        dbPrefixList.add("a");
    }

    public  static String getDbPrefix() {
        return dbPrefixList.get(random.nextInt(dbPrefixList.size()));
    }


}
