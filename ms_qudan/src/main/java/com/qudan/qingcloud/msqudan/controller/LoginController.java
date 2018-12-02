package com.qudan.qingcloud.msqudan.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.qudan.qingcloud.msqudan.service.Impl.UserServiceImpl;
import com.qudan.qingcloud.msqudan.util.PasswordUtils;
import com.qudan.qingcloud.msqudan.util.requestBody.UserLoginRB;
import com.qudan.qingcloud.msqudan.util.requestBody.WxLoginRB;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/msqudan/api")//窄化请求地址
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserLoginRB userLoginRB) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        ARE.setData(userService.login(ARE, userLoginRB));
        return ARE.createResponseEntity();
    }

    @PostMapping("/wx/login")
    public ResponseEntity<Map<String, Object>> wxlogin(WxLoginRB wxLoginRB) {
        ApiResponseEntity ARE = new ApiResponseEntity();

        return ARE.createResponseEntity();
    }

    @RequestMapping(value = "/image-code",method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> imageCode() throws Exception {

        ApiResponseEntity are = new ApiResponseEntity();

        String capText = defaultKaptcha.createText().toLowerCase();

        BufferedImage bi = defaultKaptcha.createImage(capText);

        Base64.Encoder base64 = Base64.getEncoder();

        ByteArrayOutputStream bs = new ByteArrayOutputStream();

        String cat = PasswordUtils.encodeCat(capText);

        // write the data out
        Map<String,String> map = new HashMap<>();
        ImageIO.setUseCache(false);
        ImageIO.write(bi, "jpg", bs);
        String imgsrc = null;
        try {
            map.put("cap",cat);
            imgsrc = base64.encodeToString(bs.toByteArray());
            map.put("imgsrc","data:image/jpg;base64,"+imgsrc);
            are.setData(map);
            bs.flush();
        } finally {
            bs.close();
        }
        return are.createResponseEntity();
    }
}
