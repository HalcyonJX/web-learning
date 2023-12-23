package service.impl;

import dao.SysUserDao;
import dao.impl.SysUserDaoImpl;
import pojo.SysUser;
import service.SysUserService;
import util.MD5Util;

public class SysUserServiceImpl implements SysUserService {
    private SysUserDao userDao = new SysUserDaoImpl();

    @Override
    public SysUser findByUsername(String username) {
        //调用dao层方法，继续查询
        return userDao.findByUsername(username);
    }

    @Override
    public int regist(SysUser registUser) {
        //将用户的明文密码转换为密文密码
         registUser.setUserPwd(MD5Util.encrypt(registUser.getUserPwd()));
         //调用dao层方法 将sysUser信息存入数据库
        return userDao.addSysUser(registUser);
    }
}
