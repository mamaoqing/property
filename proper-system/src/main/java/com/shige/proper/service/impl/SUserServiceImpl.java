package com.shige.proper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shige.proper.constant.ShigeConstant;
import com.shige.proper.entity.system.SComp;
import com.shige.proper.entity.system.SOrg;
import com.shige.proper.entity.system.SRoleUser;
import com.shige.proper.entity.system.SUser;
import com.shige.proper.enums.ShigeExceptionEnum;
import com.shige.proper.exception.ShigeException;
import com.shige.proper.mapper.SOrgMapper;
import com.shige.proper.mapper.SRoleUserMapper;
import com.shige.proper.mapper.SUserMapper;
import com.shige.proper.service.SUserService;
import com.shige.proper.util.PasswdEncryption;
import com.shige.proper.util.Pinyin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mq
 * @since 2021-01-27
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class SUserServiceImpl extends ServiceImpl<SUserMapper, SUser> implements SUserService {
    private final SUserMapper userMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final SRoleUserMapper userRoleMapper;

    private final SOrgMapper orgMapper;


    @Override
    public Page<SUser> listUser(String token, Map<String, String> map) {
        if (map.isEmpty() || StringUtils.isEmpty(map.get("pageNo"))) {
            throw new ShigeException(ShigeExceptionEnum.PAGE_NO_MISS_ERROR);
        }
        int pageNo = parseInt(map.get("pageNo"));
        int size = 10;
        if (StringUtils.isEmpty(pageNo)) {
            throw new ShigeException(ShigeExceptionEnum.PAGE_NO_MISS_ERROR);
        }
        if (!StringUtils.isEmpty(map.get("size"))) {
            size = Integer.parseInt(map.get("size"));
        }
        Page<SUser> page = new Page<>(pageNo, size);
        SUser user = getUserByToken(token);
        QueryWrapper<SUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(map.get("name")),"name",map.get("name"));
        String concat = null;

        if (!StringUtils.isEmpty(map.get("orgId"))) {
            String orgId = map.get("orgId");
            // 先查询当前组织的上级
            SOrg sOrg = orgMapper.selectById(orgId);
            concat = sOrg.getParentIdList().concat(",").concat(orgId);
            List<String> strings = Arrays.asList(concat.split(","));
            queryWrapper.in("orgId",strings);
        }

        // 区分超级管理员跟普通用户
        if (!ShigeConstant.SUPER_ADMIN.equals(user.getType())) {
            queryWrapper.eq("compId",user.getCompId());
        }
        return userMapper.findUserList(page, queryWrapper);
    }

    @Override
    public Page<SUser> listUserComm(String token, Map<String, String> map) {
        if (StringUtils.isEmpty(map.get("pageNo"))) {
            throw new ShigeException(ShigeExceptionEnum.PAGE_NO_MISS_ERROR);
        }
        Integer pageNo = Integer.valueOf(map.get("pageNo"));
        Integer size = 10;
        if (!StringUtils.isEmpty(map.get("size"))) {
            size = Integer.valueOf(map.get("size"));
        }
        SUser user = getUserByToken(token);
        Page<SUser> page = new Page<>(pageNo, size);
        return userMapper.findUserCommList(page, map.get("compId"), map.get("userName"), map.get("name"), map.get("commId"), user.getId());
    }

    @Override
    public List<SUser> findOne(Integer id) {
        return userMapper.findOne(id);
    }

    @Override
    public SUser findByUserName(String username) {
        QueryWrapper<SUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", username);

        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean checkUser(String userName) {
        QueryWrapper<SUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", userName);
        SUser user = userMapper.selectOne(queryWrapper);
        if (null == user) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean autoSave(SComp company) {
        Long companyId = company.getId();
        String companyName = company.getCompName();
        String userName = Pinyin.getPinYinHeadChar(company.getCompName() + "admin");
        String password = PasswdEncryption.encptyPasswd("123456");
        String type = "管理员";
        String remark = "创建公司系统自动生成管理员账号";
        String state = "";
        SUser user = new SUser();
        user.setPassword(password);
        user.setUserName(userName);
        user.setCompId(companyId);
        user.setType(type);
        user.setState(state);

        int insert = userMapper.insert(user);
        if (insert > 0) {
            log.info("自动添加公司管理员角色，登录用户名={},密码=123456", userName);
            return insert > 0;
        }
        log.error("自动添加管理员角色失败,请联系管理员添加账号");
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_INSERT_ERROR);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setUserRole(Long userId, Long compId, String roleIds, String token) {
        // 如果参数有一个为空，直接返回
        if (StringUtils.isEmpty(roleIds) || StringUtils.isEmpty(userId)) {
            throw new ShigeException(ShigeExceptionEnum.PARAMS_MISS_ERROR);
        }
        // 更新用户权限的时候，需要先删除之前存在的用户角色关系然后从新生成。
        QueryWrapper<SRoleUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        // 先查询用户的权限，如果没有权限，直接添加，如果有权限，需要先删除在添加新的权限
        List<SRoleUser> sUserRoles = userRoleMapper.selectList(queryWrapper);
        if (!sUserRoles.isEmpty()) {
            int delete = userRoleMapper.delete(queryWrapper);
            if (!(delete > 0)) {
                throw new ShigeException(ShigeExceptionEnum.SET_USER_ROLE_ERROR);
            }
        }

        SUser user = getUserByToken(token);
        String[] roleIdArr = roleIds.split(",");
        for (String roleId : roleIdArr) {
            SRoleUser userRole = new SRoleUser();
            userRole.setUserId(userId);
            userRole.setCompId(compId);
            userRole.setRoleId(Long.valueOf(roleId));

            int insert = userRoleMapper.insert(userRole);
            if (!(insert > 0)) {
                throw new ShigeException(ShigeExceptionEnum.SET_USER_ROLE_ERROR);
            }
        }

        return true;
    }

    @Override
    public boolean save(SUser user, String token) {
        if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getUserName())) {
            throw new ShigeException(ShigeExceptionEnum.PARAMS_MISS_ERROR);
        }
        QueryWrapper<SUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", user.getUserName());
        SUser sUser = userMapper.selectOne(queryWrapper);
        if (null != sUser) {
            throw new ShigeException(ShigeExceptionEnum.USER_EXIST_ERROR);
        }
        SUser users = getUserByToken(token);
        String psw = PasswdEncryption.encptyPasswd(user.getPassword());
        user.setPassword(psw);
        user.setCompId(users.getCompId());
        user.setCreateUserId(users.getId());
        user.setCreateUserName(users.getUserName());
        int insert = userMapper.insert(user);
        if (insert > 0) {
            log.info("用户添加成功，添加人={}", users.getUserName());
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_INSERT_ERROR);

    }

    @Override
    public boolean saveOrUpdate(SUser user, String token) {
        SUser users = getUserByToken(token);
        int i = userMapper.updateById(user);
        if (i > 0) {
            log.info("用户更新成功，修改人={}", users.getUserName());
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_UPDATE_ERROR);

    }

    @Override
    public boolean removeById(Long id, String token) {
        SUser users = getUserByToken(token);
        int i = userMapper.deleteById(id);
        if (i > 0) {
            log.info("用户删除成功，删除人={}", users.getUserName());
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_DELETE_ERROR);

    }

    @Override
    public boolean reSetPassword(String password, Long id, String token, String oldPassword) {
        SUser userByToken = getUserByToken(token);
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(id) || StringUtils.isEmpty(oldPassword)) {
            throw new ShigeException(ShigeExceptionEnum.PARAMS_MISS_ERROR);
        }
        SUser user = userMapper.selectById(id);
        String s = PasswdEncryption.dencptyPasswd(user.getPassword());
        String s1 = null;
        try {
            s1 = PasswdEncryption.setMD5String(oldPassword);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (!s.equals(s1)) {
            throw new ShigeException(ShigeExceptionEnum.RESET_PASSWORD_ERROR);
        }

        String psw = PasswdEncryption.encptyPasswd(password);
        user.setPassword(psw);
        int i = userMapper.updateById(user);
        if (i > 0) {
            log.info("密码修改成功,修改人{}", userByToken.getUserName());
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_UPDATE_ERROR);
    }

    @Override
    public boolean reSetPassword(String password, String token, Long id) {
        SUser user = getUserByToken(token);
        if (StringUtils.isEmpty(password)) {
            throw new ShigeException(ShigeExceptionEnum.PARAMS_MISS_ERROR);
        }
        SUser sUser = userMapper.selectById(id);
        String psw = PasswdEncryption.encptyPasswd(password);
        sUser.setPassword(psw);
        int i = userMapper.updateById(sUser);
        if (i > 0) {
            log.info("用户{}密码重置成功,操作人{}", sUser.getUserName(), user.getUserName());
            return true;
        }
        throw new ShigeException(ShigeExceptionEnum.RESET_PASSWORD_ERROR_SYSTEM);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAdmin(Map<String, String> map) {
        String userName = map.get("userName");
        String password = map.get("password");
        SUser user = new SUser();
        user.setUserName(userName);
        user.setPassword(PasswdEncryption.encptyPasswd(password));
        Object compId = map.get("compId");
        String s = compId.toString();
        user.setCompId((Long.valueOf(s)));
        int insert = userMapper.insert(user);
        if (insert > 0) {
            SRoleUser roleUser = new SRoleUser();
            roleUser.setUserId(user.getId());
            roleUser.setCompId(Long.valueOf(s));
            roleUser.setRoleId(1L);
            int insert1 = userRoleMapper.insert(roleUser);
            if (insert1 > 0) {
                return true;
            }
        }
        throw new ShigeException(ShigeExceptionEnum.SYSTEM_INSERT_ERROR);
    }

    private SUser getUserByToken(String token) {
        Object o = redisTemplate.opsForValue().get(token);
        if (null == o) {
            log.error("登录失效，请重新登录。");
            throw new ShigeException(ShigeExceptionEnum.LOGIN_TIME_OUT);
        }
        return (SUser) o;
    }
}
