package work.etasas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sas
 * @create 2024-10-21-22:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private long accountNo;
    private String username;
    private String headImg;
    private String phone;
    private String mail;
    private String auth;
}
