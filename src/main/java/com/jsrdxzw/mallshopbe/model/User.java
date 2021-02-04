package com.jsrdxzw.mallshopbe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

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

    private Long unifyUid;

}
