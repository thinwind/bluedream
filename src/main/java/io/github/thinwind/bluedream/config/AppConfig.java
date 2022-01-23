package io.github.thinwind.bluedream.config;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import io.github.thinwind.bluedream.misc.Consts;
import io.github.thinwind.bluedream.misc.NetUtil;

@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@Configuration
public class AppConfig {

    @Bean
    public EnvConfig configEvn(@Value("${"+Consts.HOST_KEY+":}") String host, @Value("${server.port}") int port) {
        EnvConfig config = new EnvConfig(getRndStr(6));
        String envHost = System.getProperty(Consts.HOST_KEY);
        if (StringUtils.isNotBlank(envHost)){
            config.setHost(envHost);
        }else if(StringUtils.isNotBlank(host)){
            config.setHost(host);
        }else{
            config.setHost(NetUtil.localIP());
        }
        config.setPort(port);
        return config;
    }
    
    private static String getRndStr(int length) {
        int a = 'a';
        int z = 'z';
        int n0 = '0';
        int n9 = '9';
        int A = 'A';
        int Z = 'Z';
        var chars = new ArrayList<Integer>();
        for (int i = a; i <= z; i++) {
            chars.add(i);
        }
        for (int i = n0; i <= n9; i++) {
            chars.add(i);
        }
        for (int i = A; i <= Z; i++) {
            chars.add(i);
        }
        var random = new Random();
        var builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(Character.toString(chars.get(random.nextInt(chars.size()))));
        }
        return builder.toString();
    }
}
