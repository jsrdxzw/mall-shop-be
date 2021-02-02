package com.jsrdxzw.mallshopbe.vo;

import com.jsrdxzw.mallshopbe.model.Theme;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@Data
@NoArgsConstructor
public class ThemePureVO {
    private Long id;
    private String title;
    private String description;
    private String name;
    private String entranceImg;
    private String extend;
    private String internalTopImg;
    private String titleImg;
    private String tplName;
    private Boolean online;

    public static List<ThemePureVO> convert(List<Theme> themeList) {
        return themeList.stream().map(it -> {
            ThemePureVO themePureVO = new ThemePureVO();
            BeanUtils.copyProperties(it, themePureVO);
            return themePureVO;
        }).collect(Collectors.toList());
    }
}
