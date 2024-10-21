package work.etasas.controller.request;

import lombok.Data;

/**
 * @author sas
 * @create 2024-10-21-10:10
 */

@Data
public class AccountLoginRequest {

    private String phone;

    private String pwd;
}
