package org.xy.mysqldemo.persistence.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.util.StopWatch;
import org.xy.mysqldemo.persistence.model.OrderDO;
import org.xy.mysqldemo.persistence.model.QUserDO;
import org.xy.mysqldemo.persistence.model.UserDO;
import org.xy.mysqldemo.persistence.repository.UserRepository;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.xy.mysqldemo.persistence.common.OrderStatusEnum.ORDERED;
import static org.xy.mysqldemo.persistence.common.PayTypeEnum.CREDIT_CARD;

/**
 * @author wangxinyu
 * @date 2020/12/2
 */
@Slf4j
class OrderServiceTest extends BaseJpaTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    private UserDO user;

    @BeforeEach
    void setUp() {
        QUserDO qUserDO = QUserDO.userDO;
        Optionals.ifPresentOrElse(userRepository.findOne(qUserDO.account.eq("mrCat")),
                userDO -> user = userDO,
                () -> user = userRepository.save(UserDO.builder()
                                                       .account("mrCat")
                                                       .password("mrCat1202")
                                                       .nickName("mrCat")
                                                       .gender(0)
                                                       .realName("cat")
                                                       .phone("13811118888")
                                                       .identityCard("110105198512028888")
                                                       .registerTime(System.currentTimeMillis())
                                                       .birthday(new Date(1985, 12, 2))
                                                       .level(5)
                                                       .build()));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveMillion() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        IntStream.range(0, 1000).parallel().forEach(integer -> {
            CopyOnWriteArrayList<OrderDO> orderBatch = new CopyOnWriteArrayList<>();
            IntStream.range(0, 1000).parallel().forEach(value -> orderBatch.add(this.createOrder()));
            orderService.saveAll(orderBatch);
        });
        stopWatch.stop();
        log.warn("==========> saveMillion in: {} s", stopWatch.getTotalTimeSeconds());
    }

    private OrderDO createOrder() {
        long currentTimeMillis = System.currentTimeMillis();
        return OrderDO.builder()
                      .orderSn(uuid("order"))
                      .userId(user.getId())
                      .userName(user.getRealName())
                      .totalAmount(88.88)
                      .freightAmount(8.8)
                      .payAmount(88.88)
                      .payType(CREDIT_CARD)
                      .status(ORDERED)
                      .addressId(88L)
                      .deliverySn(uuid("delivery"))
                      .remark("go")
                      .isDeleted(false)
                      .createdDate(currentTimeMillis)
                      .lastModifiedDate(currentTimeMillis)
                      .build();
    }
    private String uuid(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString().replaceAll("-", "");
    }
}