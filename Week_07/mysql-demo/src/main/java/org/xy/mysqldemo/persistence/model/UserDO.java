package org.xy.mysqldemo.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * UserDO
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
public class UserDO extends IdDO {

    /**
     * account
     */
    @Column(nullable = false, length = 30, unique = true)
    private String account;

    /**
     * password
     */
    @Column(nullable = false, length = 20)
    private String password;

    /**
     * nick name
     */
    @Column(length = 30, unique = true)
    private String nickName;

    /**
     * gender
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Integer gender;

    /**
     * real name
     */
    @Column(nullable = false, length = 10)
    private String realName;

    /**
     * phone
     */
    @Column(nullable = false, length = 11, unique = true)
    private String phone;

    /**
     * identity card
     */
    @Column(nullable = false, length = 19)
    private String identityCard;

    /**
     * register time
     */
    @Column(nullable = false, columnDefinition = "BIGINT(20)")
    private Long registerTime;

    /**
     * birthday
     */
    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * gender
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Integer level;
}
