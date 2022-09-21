package com.luom.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Bean
    public RedissonClient getRedisson(){

        Config config = new Config();
        //单机模式  依次设置redis地址和密码
        config.useSingleServer().
                setAddress("redis://" + host + ":" + port).
                setPassword(redisPassword);
        return Redisson.create(config);




        //主从
       /* Config config = new Config();
        config.useMasterSlaveServers()
                .setMasterAddress("127.0.0.1:6379")
                .addSlaveAddress("127.0.0.1:6389", "127.0.0.1:6332", "127.0.0.1:6419")
                .addSlaveAddress("127.0.0.1:6399");
        RedissonClient redisson = Redisson.create(config);*/


        //哨兵
       /* Config config = new Config();
        config.useSentinelServers()
                .setMasterName("mymaster")
                .addSentinelAddress("127.0.0.1:26389", "127.0.0.1:26379")
                .addSentinelAddress("127.0.0.1:26319");
        RedissonClient redisson = Redisson.create(config);*/


        //集群
       /* Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000) // cluster state scan interval in milliseconds
                .addNodeAddress("127.0.0.1:7000", "127.0.0.1:7001")
                .addNodeAddress("127.0.0.1:7002");
        RedissonClient redisson = Redisson.create(config);*/
    }
}
