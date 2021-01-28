package com.shige.proper.service.impl;

import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.entity.Result;
import com.shige.proper.entity.system.SUser;
import com.shige.proper.enums.ShigeExceptionEnum;
import com.shige.proper.exception.ShigeException;
import com.shige.proper.service.LoginService;
import com.shige.proper.util.PasswdEncryption;
import com.shige.proper.util.RedisUtil;
import com.shige.proper.util.ResultUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;

/**
 * @author mq
 * @description: TODO
 * @title: LoginServiceImpl
 * @projectName proper
 * @date 2021/1/2811:28
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class LoginServiceImpl implements LoginService {
    private final RedisUtil redisUtil;
    private final SUserServiceImpl userService;

    @Override
    public Result login(Map<String,String> map) {
        String username = map.get("userName");
        String passwd = map.get("password");
        SUser user = userService.findByUserName(username);
        if(StringUtils.isEmpty(user)){
            throw new ShigeException(ShigeExceptionEnum.USER_NOT_EXIST_ERROR);
        }
        String password = user.getPassword();
        StringBuilder sbf = new StringBuilder(passwd);
        //在用户输入的密码两头拼接字符
        sbf.insert(0, ShigeConstant.PASSWORD_CHAR);
        sbf.insert(passwd.length() - 1, ShigeConstant.PASSWORD_CHAR);
        //用md5加密
        String pwd = DigestUtils.md5DigestAsHex(sbf.toString().getBytes());
        String pd = PasswdEncryption.dencptyPasswd(password);

        if(pwd.equals(pd)) {
            String key = String.valueOf(UUID.randomUUID()).replace("-","");
            // 设置过期时间单位秒
            redisUtil.set(key, user, 500 * 60);
            return ResultUtil.success(key);
        }
        throw new ShigeException(ShigeExceptionEnum.PASSWORD_ERROR);
    }

    @Override
    public boolean logout(String token) {
        if(StringUtils.isEmpty(token)){
            throw new ShigeException(ShigeExceptionEnum.PARAMS_MISS_ERROR);
        }
        Object o = redisUtil.get(token);
        if(null == o){
            return true;
        }
        return  redisUtil.delete(token);
    }
}
