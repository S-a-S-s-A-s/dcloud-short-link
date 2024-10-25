package work.etasas.util;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

/**
 * @author sas
 * @create 2024-10-25-15:14
 */
public class IdUtil {

    private static SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();

    public static Comparable<?> generateSnowFlakeId() {
        return snowflakeShardingKeyGenerator.generateKey();
    }

}
