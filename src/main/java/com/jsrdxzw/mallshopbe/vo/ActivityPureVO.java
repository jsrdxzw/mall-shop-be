package com.jsrdxzw.mallshopbe.vo;

import com.jsrdxzw.mallshopbe.model.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityPureVO {
    private Long id;
    private String title;
    private String entranceImg;
    private Boolean online;
    private String remark;
    private Date startTime;
    private Date endTime;

    public ActivityPureVO(Activity activity) {
        BeanUtils.copyProperties(activity, this);
    }
}
