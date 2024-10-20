package work.etasas.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import work.etasas.config.OSSConfig;
import work.etasas.service.FileService;
import work.etasas.util.CommonUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author sas
 * @create 2024-10-20-9:27
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private OSSConfig ossConfig;

    @Override
    public String uploadUserImg(MultipartFile file) {
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        String bucketName = ossConfig.getBucketName();

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String originalFilename = file.getOriginalFilename();

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //user/2024/09/05/sdsd/
        String folder = pattern.format(ldt);
        String fileName = CommonUtil.generateUUID();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

        //最终文件路径
        String newFileName = "user/" + folder + "/" + fileName + fileExtension;
        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, newFileName, file.getInputStream());
            //拼装返回路径
            if (putObjectResult != null) {
                return "https://" + bucketName + "." + endpoint + "/" + newFileName;
            }
        } catch (IOException e) {
            log.error("上传失败:{}", e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return null;
    }
}
