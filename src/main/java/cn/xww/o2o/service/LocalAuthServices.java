package cn.xww.o2o.service;

import cn.xww.o2o.domain.LocalAuth;
import cn.xww.o2o.dto.LocalAuthExecution;

public interface LocalAuthServices {
    /**
     *
     * @param userName
     * @return
     */
    LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password);

    /**
     *
     * @param userName
     * @return
     */
    LocalAuth getLocalAuthByUserName(String userName);

    /**
     *
     * @param userName
     * @return
     */
    int insertLocalAuth(LocalAuth localAuth);

    /**
     *
     * @param userId
     * @return
     */
    LocalAuth getLocalAuthByUserId(long userId);

    /**
     *
     * @param localAuth
     * @param profileImg
     * @return
     * @throws RuntimeException
     */
//    LocalAuthExecution register(LocalAuth localAuth,
//                                CommonsMultipartFile profileImg) throws RuntimeException;

    /**
     *绑定账号
     * @param localAuth
     * @return
     * @throws RuntimeException
     */
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth)
            throws RuntimeException;

    /**
     *修改平台账号的登录密码
     * @param //localAuthId
     * @param userName
     * @param password
     * @param newPassword
     * @param //lastEditTime
     * @return
     */
    LocalAuthExecution modifyLocalAuth(Long userId, String userName,
                                       String password, String newPassword);


}
