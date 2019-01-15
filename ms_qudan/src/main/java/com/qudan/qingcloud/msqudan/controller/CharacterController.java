package com.qudan.qingcloud.msqudan.controller;

import com.google.common.collect.Maps;
import com.qudan.qingcloud.msqudan.service.Impl.CharacterServiceImpl;
import com.qudan.qingcloud.msqudan.util.DateFormatUtil;
import com.qudan.qingcloud.msqudan.util.DateUtil;
import com.qudan.qingcloud.msqudan.util.LocalUserHelper;
import com.qudan.qingcloud.msqudan.util.RandomUtils;
import com.qudan.qingcloud.msqudan.util.responses.ApiResponseEntity;
import com.qudan.qingcloud.msqudan.util.responses.RankVo;
import org.apache.ibatis.annotations.Param;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/msqudan/api/")//窄化请求地址
public class CharacterController {

    @Autowired
    CharacterServiceImpl characterService;

    @GetMapping("/vips")
    public ResponseEntity<Map<String, Object>> vips() {
        ApiResponseEntity ARE = new ApiResponseEntity();
        ARE.setData(characterService.vips(ARE));
        return ARE.createResponseEntity();
    }

    @GetMapping("/user/vip/check")
    public ResponseEntity<Map<String, Object>> vipCheck(@RequestParam("vip_id")Integer vipId) {
        ApiResponseEntity ARE = new ApiResponseEntity();
        ARE.setUserId(LocalUserHelper.getUserId());
        ARE.setData(characterService.vipCheck(ARE, vipId));
        return ARE.createResponseEntity();
    }


    @GetMapping("/agents")
    public ResponseEntity<Map<String, Object>> agents() {
        ApiResponseEntity ARE = new ApiResponseEntity();
        ARE.setData(characterService.agents(ARE));
        return ARE.createResponseEntity();
    }

    @GetMapping("/ranks")
    public ResponseEntity<Map<String, Object>> ranks() {
        ApiResponseEntity ARE = new ApiResponseEntity();
        BigDecimal top1_base = new BigDecimal(7214.12);
        BigDecimal top2_base = new BigDecimal(7120.22);
        BigDecimal top3_base = new BigDecimal(7065.45);
        BigDecimal top4_base = new BigDecimal(6912.98);
        BigDecimal top5_base = new BigDecimal(6812.65);
        Date basedate = DateUtil.StrToDate("2018-12-31", "yyyy-MM-dd");
        Date now = new Date();
        long miao = now.getTime()-basedate.getTime();
        long yitian = 3600*24;
        BigDecimal addRate = new BigDecimal((miao/yitian)*0.0007124).setScale(6, BigDecimal.ROUND_HALF_UP);

        top1_base = (top1_base.add(top1_base.multiply(addRate))).setScale(2, BigDecimal.ROUND_HALF_UP);
        top2_base = (top2_base.add(top2_base.multiply(addRate))).setScale(2, BigDecimal.ROUND_HALF_UP);
        top3_base = (top3_base.add(top3_base.multiply(addRate))).setScale(2, BigDecimal.ROUND_HALF_UP);
        top4_base = (top4_base.add(top4_base.multiply(addRate))).setScale(2, BigDecimal.ROUND_HALF_UP);
        top5_base = (top5_base.add(top5_base.multiply(addRate))).setScale(2, BigDecimal.ROUND_HALF_UP);

        RankVo top1R = new RankVo(top1_base, "苏正超","http://qudanmedia.myhshop.top/msqudan/top1.jpg");
        RankVo top2R = new RankVo(top2_base, "唐智","http://qudanmedia.myhshop.top/msqudan/top2.jpg");
        RankVo top3R = new RankVo(top3_base, "张小梦","http://qudanmedia.myhshop.top/msqudan/top3.jpeg");
        RankVo top4R = new RankVo(top4_base, "张付勤","http://qudanmedia.myhshop.top/msqudan/top4.jpeg");
        RankVo top5R = new RankVo(top5_base, "刘华","http://qudanmedia.myhshop.top/msqudan/top5.jpeg");
        List<RankVo> rankVoList  = Lists.newArrayList();
        rankVoList.add(top1R);
        rankVoList.add(top2R);
        rankVoList.add(top3R);
        rankVoList.add(top4R);
        rankVoList.add(top5R);
        BigDecimal all = top1_base.add(top2_base).add(top3_base).add(top4_base).add(top5_base).add(new BigDecimal(1111.11)).multiply(new BigDecimal("1.2845"));
        all = all.setScale(2, BigDecimal.ROUND_HALF_UP);
        Map<String,Object> data = Maps.newHashMap();
        data.put("ranks", rankVoList);
        data.put("allRevenue", all);
        BigDecimal num = all.divide(new BigDecimal(8.23) ,BigDecimal.ROUND_HALF_DOWN);
        num = num.setScale(0, BigDecimal.ROUND_HALF_UP);
        data.put("all", num);
        ARE.setData(data);
        return ARE.createResponseEntity();
    }
}
