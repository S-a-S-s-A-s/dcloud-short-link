package work.etasas.strategy;

import org.apache.commons.math3.util.Pair;
import work.etasas.util.WeightRandom;

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
    //private static final List<String> dbPrefixList = new ArrayList<>();

    private static Random random = new Random();

    private static List<Pair<String, Integer>> dbPrefixList = new ArrayList<>();

    private static WeightRandom<String, Integer> weightRandom;

    static {
        dbPrefixList.add(new Pair<>("db0", 1));
        dbPrefixList.add(new Pair<>("db1", 1));
        dbPrefixList.add(new Pair<>("dba", 1));
        weightRandom = new WeightRandom<>(dbPrefixList);
    }

    public static String getDbPrefix() {
        //取最后一位
        String db = weightRandom.random();
        return db.substring(db.length() - 1);
    }


}
