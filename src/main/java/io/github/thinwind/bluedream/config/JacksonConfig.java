/* 
 * Copyright 2022 Shang Yehua <niceshang@outlook.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.thinwind.bluedream.config;

import java.util.Date;
import java.util.TimeZone;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 * TODO JacksonConfig说明
 *
 * @author Shang Yehua <niceshang@outlook.com>
 * @since 2022-01-25  20:47
 *
 */
@Configuration
public class JacksonConfig {
    
    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper()
    {
        JsonMapper.Builder builder = JsonMapper.builder();
        // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
        // Include.Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
        // Include.NON_NULL 属性为NULL 不序列化
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        //有未知属性 要不要抛异常
        builder.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //是否允许JSON字符串包含未转义的控制字符(值小于32的ASCII字符，包括制表符和换行符)的特性。如果feature设置为false，则在遇到这样的字符时抛出异常。
        builder.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS, true);
        //确定解析器是否允许使用单引号(撇号，字符'\ ")引用字符串(名称和字符串值)的特性。如果是，这是除了其他可接受的标记。但不是JSON规范)。
        builder.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        ZoneId gmt8 = ZoneId.of("GMT+8");
        builder.defaultTimeZone(TimeZone.getTimeZone(gmt8));
        
        JsonMapper jsonMapper = builder.build();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //处理 时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(gmt8);
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
        javaTimeModule.addSerializer(Date.class, new JsonSerializer<Date>() {
            @Override
            public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                Instant instant= Instant.ofEpochMilli(value.getTime());
                gen.writeString(formatter.format(instant));
            }
        });
        //注册
        jsonMapper.registerModule(javaTimeModule);
        return jsonMapper;
    }
}
