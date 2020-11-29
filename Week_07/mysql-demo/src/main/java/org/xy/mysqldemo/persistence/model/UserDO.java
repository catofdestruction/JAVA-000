package org.xy.mysqldemo.persistence.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * UserDO
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
@Data
@Entity
@Table(name = "t_user")
public class UserDO extends IdDO {

    /**
     * account
     */
    @Column(nullable = false, length = 30)
    private String account;

    /**
     * password
     */
    @Column(nullable = false, length = 20)
    private String password;

    /**
     * name
     */
    @Column(nullable = false, length = 30)
    private String name;

    /**
     * phone
     */
    @Column(nullable = false, length = 11)
    private String phone;

    /**
     * identity card
     */
    @Column(nullable = false, length = 18)
    private String identityCard;

    /**
     * order list
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<OrderDO> orders;
}
