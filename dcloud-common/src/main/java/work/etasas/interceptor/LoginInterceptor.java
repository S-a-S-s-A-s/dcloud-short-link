package work.etasas.interceptor;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import work.etasas.enums.BizCodeEnum;
import work.etasas.model.LoginUser;
import work.etasas.util.CommonUtil;
import work.etasas.util.JWTUtil;
import work.etasas.util.JsonData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sas
 * @create 2024-10-22-8:41
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    public  static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(HttpMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
        String accessToken = request.getHeader("token");
        if(StringUtils.isBlank(accessToken)) {
            accessToken = request.getParameter("token");
        }

        if(StringUtils.isNotBlank(accessToken)) {
            Claims claims = JWTUtil.parseToken(accessToken);
            if (claims == null) {
                CommonUtil.sendJsonMessage(response, JsonData.buildResult(BizCodeEnum.ACCOUNT_UNLOGIN));
                return false;
            }
            long accountNo = Long.parseLong(claims.get("accountNo").toString());
            String username = claims.get("username").toString();
            String headImg = claims.get("headImg").toString();
            String phone = claims.get("phone").toString();
            String mail = "";
            if (claims.get("mail") != null) mail = claims.get("mail").toString();
            String auth = claims.get("auth").toString();

            LoginUser loginUser = LoginUser.builder()
                    .accountNo(accountNo).username(username).headImg(headImg).phone(phone).mail(mail).auth(auth).build();


            //request.setAttribute("loginUser", loginUser);

            //threadLocal.set(loginUser);
            threadLocal.set(loginUser);
            return true;
        }
        CommonUtil.sendJsonMessage(response, JsonData.buildResult(BizCodeEnum.ACCOUNT_UNLOGIN));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        threadLocal.remove();
    }

}
