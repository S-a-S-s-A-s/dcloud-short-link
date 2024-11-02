package work.etasas.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import work.etasas.enums.ShortLinkStateEnum;
import work.etasas.service.ShortLinkService;
import work.etasas.util.CommonUtil;
import work.etasas.vo.ShortLinkVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sas
 * @create 2024-11-02-13:05
 */
@Controller
@Slf4j
public class LinkApiController {

    @Autowired
    private ShortLinkService shortLinkService;

    @GetMapping(path = "/{shortLinkCode}")
    public void dispatch(@PathVariable(name = "shortLinkCode") String shortLinkCode,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        log.info("短链接code:{}", shortLinkCode);
        //校验短链接code
        try {
            if (isLetterDigit(shortLinkCode)) {
                ShortLinkVO shortLinkVO = shortLinkService.queryByCode(shortLinkCode);
                if (isVisitable(shortLinkVO)) {
                    response.setHeader("Location", shortLinkVO.getOriginalUrl());
                    response.setStatus(HttpStatus.FOUND.value());
                }
            }else{
                response.setStatus(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    public static boolean isLetterDigit(String str){
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }

    private static boolean isVisitable(ShortLinkVO shortLinkVO) {
        if (shortLinkVO!=null) {
            if(shortLinkVO.getExpired().getTime() > CommonUtil.getCurrentTimestamp() || shortLinkVO.getExpired().getTime() == -1){
                return ShortLinkStateEnum.ACTIVE.name().equalsIgnoreCase(shortLinkVO.getState());
            }
        }
        return false;
    }

}
