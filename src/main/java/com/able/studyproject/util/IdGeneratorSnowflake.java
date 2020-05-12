package com.able.studyproject.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @param
 * @author jipeng
 * @date 2020-04-30 10:24
 */
@Slf4j
@Component
public class IdGeneratorSnowflake {
    private long workerId=0;
    private long datacenterId=0;
    private Snowflake snowflake= IdUtil.createSnowflake(workerId,datacenterId);

    @PostConstruct
    public void  init(){
         try {
             workerId= NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
             log.info("当前机器的workerId= {}",workerId);
         }catch (Exception e){
             e.printStackTrace();
             log.error("当前机器id获取失败",e);
             workerId=NetUtil.getLocalhostStr().hashCode();
         }
    }

    public synchronized long snowflakeId(){

        return snowflake.nextId();
    }


}

