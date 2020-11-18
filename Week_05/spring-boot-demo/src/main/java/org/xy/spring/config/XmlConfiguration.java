package org.xy.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * XML configuration
 * ref: https://www.jianshu.com/p/33489103e709?utm_campaign=hugo&utm_medium=reader_share&utm_content=note
 * ref: https://www.jianshu.com/p/14dd69b5c516?utm_campaign=hugo&utm_medium=reader_share&utm_content=note
 *
 * @author wangxinyu
 * @date 2020/11/18
 */
@Configuration
@ImportResource({"classpath*:applicationContext.xml"})
public class XmlConfiguration {
}
