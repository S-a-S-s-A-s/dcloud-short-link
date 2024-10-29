package work.etasas.controller.request;

import lombok.Data;

/**
 * @author sas
 * @create 2024-10-29-14:25
 */

@Data
public class LinkGroupUpdateRequest {

    private Long id;

    /**
     * 分组名称
     */
    private String title;
}
