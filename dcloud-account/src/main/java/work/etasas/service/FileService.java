package work.etasas.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author sas
 * @create 2024-10-20-9:26
 */
public interface FileService {
    /**
     * 上传用户头像
     * @param file
     * @return
     */
    String uploadUserImg(MultipartFile file);
}
