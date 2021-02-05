package com.jsrdxzw.mallshopbe.model;

import com.jsrdxzw.mallshopbe.core.converter.MapJsonConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import java.util.Map;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Where(clause = "delete_time is null")
public class User extends BaseEntity {
    private String openid;

    private String nickname;

    private String email;

    private String mobile;

    private String password;

    private Integer unifyUid;

    @Convert(converter = MapJsonConverter.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> wxProfile;
}
