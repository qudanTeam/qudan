package com.qudan.qingcloud.msqudan.util;

import com.qudan.qingcloud.msqudan.MsQudanApplication;
import com.qudan.qingcloud.msqudan.mymapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MsQudanApplication.class)
@WebAppConfiguration
public class RunClass {

    @Autowired
    UserMapper userMapper;
}
