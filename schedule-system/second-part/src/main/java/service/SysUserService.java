package service;

import pojo.SysUser;

public interface SysUserService {
    /**
     * 根据用户名获得完整用户信息的方法
     * @param username 要查询的用户名
     * @return 如果找到了返回SysUser对象，找不到返回null
     */
    SysUser findByUsername(String username);
    /**
     * 用户完成注册的方法
     * @param registUser 用于保存注册用户名和密码的对象
     * @return 注册工程返回>0的整数，否则返回0
     */
    int regist(SysUser registUser);
}
