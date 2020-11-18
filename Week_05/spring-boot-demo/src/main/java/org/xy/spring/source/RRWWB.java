package org.xy.spring.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * rr(run rune) ww(ww bar) b(block)
 *
 * @author wangxinyu
 * @date 2020/11/11
 */
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
        returnables.forEach(Returnable::doReturn);
    }
}
