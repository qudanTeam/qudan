package com.qudan.qingcloud.msqudan.service.Impl;


import com.qudan.qingcloud.msqudan.mymapper.self.WeixinQrMapperSelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SceneIdServiceImpl {
    private AtomicInteger everQrSequence;

    private AtomicInteger tempQrSequence;

    @Autowired
    WeixinQrMapperSelf weixinQrMapperSelf;

    /**
     * 获取永久二维码的预设sceneId
     * @return
     */
   public Integer getEverQrSequence(){
        if (everQrSequence == null) {
            synchronized (this) {
                if(everQrSequence == null) {
                    //双重检查
                    Integer lastQrId = weixinQrMapperSelf.getLastQrId(1);
                    if(lastQrId == null){
                        lastQrId = 0;
                    }
                    everQrSequence = new AtomicInteger(lastQrId);
                }
            }
        }
        return everQrSequence.incrementAndGet();
    }

    /**
     * 获取临时二维码的预设sceneId
     * @return
     */
    public Integer getTempQrSequence(){
        if (tempQrSequence == null) {
            synchronized (this) {
                if(tempQrSequence == null) {
                    //双重检查
                    Integer lastQrId = weixinQrMapperSelf.getLastQrId(2);
                    if(lastQrId == null){
                        lastQrId = 100000;
                    }
                    tempQrSequence = new AtomicInteger(lastQrId);
                }
            }
        }
        return tempQrSequence.incrementAndGet();
    }
}
