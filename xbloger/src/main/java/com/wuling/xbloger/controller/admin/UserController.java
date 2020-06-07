package com.wuling.xbloger.controller.admin;

import com.wuling.xbloger.annotation.OperateRecord;
import com.wuling.xbloger.entity.vo.TokenVO;
import com.wuling.xbloger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wu_ling
 * @Date: 2020/5/29
 * @Desc: 管理员用户操作
 */
@RestController
@RequestMapping("admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    @OperateRecord("登录")
    public ResponseEntity<TokenVO> login(String username, String password) {
        String token = userService.login(username, password);
        if (StringUtils.isEmpty(token)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TokenVO vo = new TokenVO();
        vo.setToken(token);
        return ResponseEntity.ok(vo);
    }

    /**
     * 注销登录
     *
     * @return
     */
    @PostMapping("logout")
    @OperateRecord("注销")
    public ResponseEntity<Void> logout() {
        // TODO
        return null;
    }

}
