package org.xy.spring.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * concurrent result
 *
 * @author wangxinyu
 * @date 2020/11/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcurrentResult {

    /**
     * concurrent way name
     */
    private String name;

    /**
     * duration of computing fibo40
     */
    private int duration;

    /**
     * result of computing fibo40
     */
    private int result;
}
