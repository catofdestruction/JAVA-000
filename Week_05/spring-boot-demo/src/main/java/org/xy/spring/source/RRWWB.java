package org.xy.spring.source;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xy.spring.jdbc.JdbcUtils;
import org.xy.spring.jdbc.model.ConcurrentResult;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * rr(run rune) ww(ww bar) b(block)
 *
 * @author wangxinyu
 * @date 2020/11/11
 */
@Slf4j
@Component
public class RRWWB {

    public static final Integer SPRING_BOOT_MAIN_ACTIVE_THREAD_COUNT = 3;
    public static final Integer SPRING_BOOT_WEB_MAIN_ACTIVE_THREAD_COUNT = 19;
    public static final Integer TIMEOUT = 81000;
    public static final String LINE = "==================================================================";
    public static final String LINES =
            "\n==========================================================================================\n";

    @Autowired
    private List<Returnable<Integer>> returnables;

    public void runReturnables() {
        List<ConcurrentResult> results =
                returnables.stream()
                           .map(returnable -> {
                               long start = System.currentTimeMillis();
                               Integer result = Optional.ofNullable(returnable.doReturn()).orElse(-1);
                               int duration = (int) (System.currentTimeMillis() - start);
                               return new ConcurrentResult(returnable.name(true), duration, result);
                           }).collect(Collectors.toList());
        try {
            JdbcUtils.batchInsert(results, true);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
