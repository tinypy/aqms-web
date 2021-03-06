package com.osen.aqms.webSecurity.controller;

import com.osen.aqms.common.enums.TipsMessage;
import com.osen.aqms.common.result.RestResult;
import com.osen.aqms.common.utils.RestResultUtil;
import com.osen.aqms.modules.service.LogsLoginService;
import com.osen.aqms.webSecurity.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.osen.aqms.common.enums.InfoMessage.User_Logout_Success;

/**
 * User: PangYi
 * Date: 2019-08-29
 * Time: 17:41
 * Description: 认证控制器
 */
@RestController
@RequestMapping("${restful.prefix}")
@Slf4j
public class AuthorizationController {

    @Autowired
    private LogsLoginService logsLoginService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户退出登录
     *
     * @param authorization token
     * @return 信息
     */
    @PostMapping("/auth/logout")
    public RestResult logout(@RequestHeader("Authorization") String authorization, HttpServletRequest request) {
        log.info("system_user logout: " + authorization);
        // 登录令牌
        String authToken = authorization.substring(7);

        String access_token = stringRedisTemplate.boundValueOps(JwtTokenUtil.ACCESS_TOKEN + authToken).get();
        if (access_token == null)
            RestResultUtil.failed("无效登录令牌");
        // 删除
        stringRedisTemplate.delete(JwtTokenUtil.ACCESS_TOKEN + authToken);

        logsLoginService.saveLogs(request, TipsMessage.LogoutSuccess.getTips());

        // 返回
        return RestResultUtil.authorization(User_Logout_Success.getCode(), User_Logout_Success.getMessage());
    }

}
