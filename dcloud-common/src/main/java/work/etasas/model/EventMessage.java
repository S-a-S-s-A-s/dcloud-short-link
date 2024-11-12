package work.etasas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author sas
 * @create 2024-11-12-16:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventMessage implements Serializable {

    private String messageId;

    private String eventMessageType;

    /**
     * 业务id
     */
    private String bizId;

    private Long accountNo;

    private String content;

    private String remark;

}
