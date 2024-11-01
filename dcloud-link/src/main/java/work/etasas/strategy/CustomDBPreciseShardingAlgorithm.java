package work.etasas.strategy;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import work.etasas.enums.BizCodeEnum;
import work.etasas.exception.BizException;

import java.util.Collection;

/**
 * @author sas
 * @create 2024-11-01-11:16
 */
public class CustomDBPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {

        //拿到分片键的第一位
        String shardingValuePrefix = shardingValue.getValue().substring(0, 1);
        for (String dbName : availableTargetNames) {
            //拿到dbName的最后一位
            String dbValue = dbName.substring(dbName.length() - 1);
            //如果分片键的第一位和dbName的最后一位相等，则返回这个库
            if (dbValue.equals(shardingValuePrefix)) {
                return dbName;
            }
        }
        throw new BizException(BizCodeEnum.DB_ROUTE_FOUND);
    }

}
