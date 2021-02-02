package com.jsrdxzw.mallshopbe.api.mini.v1;

import com.jsrdxzw.mallshopbe.model.Theme;
import com.jsrdxzw.mallshopbe.service.ThemeService;
import com.jsrdxzw.mallshopbe.vo.ThemePureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@Validated
@RestController
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping("/names")
    public List<ThemePureVO> getThemeByNames(@NotBlank @RequestParam("names") String names) {
        List<String> nameList = Arrays.asList(names.split(","));
        List<Theme> themeList = themeService.findByNames(nameList);
        return ThemePureVO.convert(themeList);
    }

    @GetMapping("/name/{name}/with_spu")
    public Theme getThemeByNameWithSpu(@PathVariable String name) {
        return themeService.findByName(name);
    }
}
