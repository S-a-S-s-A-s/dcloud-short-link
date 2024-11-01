package work.etasas.strategy;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author sas
 * @create 2024-11-01-11:29
 */
public class CustomTablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        String logicTable = availableTargetNames.iterator().next();

        String codeSuffix = shardingValue.getValue().substring(shardingValue.getValue().length() - 1);
        return logicTable + "_" + codeSuffix;
    }
}
