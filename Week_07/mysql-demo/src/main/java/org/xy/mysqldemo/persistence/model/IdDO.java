package org.xy.mysqldemo.persistence.model;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.domain.Persistable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * IdDO
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
@Data
@MappedSuperclass
public abstract class IdDO implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(value = javax.persistence.AccessType.PROPERTY)
    protected Long id;

    /**
     * is new
     *
     * @return is new
     */
    @Transient
    @Override
    public boolean isNew() {
        return null == getId();
    }
}
