package com.zard.backend.controller;

import com.zard.backend.common.ApiResult;
import com.zard.backend.model.NavigateModel;
import com.zard.backend.param.Points;
import com.zard.backend.util.Navigate;
import com.zard.backend.util.Point;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 乌贼
 * @Date 2023-08-15 23:38
 * @Description
 */

@RestController
public class navigateController {
    @PostMapping("/navigate")
    public static ApiResult<NavigateModel> navigate(@RequestBody Points points) throws Exception {
        Point origin = points.getOrigin();
        Point destination = points.getDestination();

        return ApiResult.success(Navigate.navigation(origin, destination));
    }
}
