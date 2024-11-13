package work.etasas.controller.request;

import lombok.Data;

import java.util.Date;

/**
 * @author sas
 * @create 2024-11-13-9:06
 */
@Data
public class ShortLinkAddRequest {
    private Long groupId;

    private String title;

    private String originalUrl;

    private Long domainId;

    private String domainType;

    private Date expireTime;
}
