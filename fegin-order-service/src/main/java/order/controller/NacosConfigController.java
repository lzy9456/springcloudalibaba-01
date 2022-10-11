package order.controller;

import com.alibaba.fastjson.JSON;
import com.example.entity.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import order.config.nacos.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Nacos controller", tags = "Sys v1.0 - nacos模块")
public class NacosConfigController {

    @Autowired
    UserConfig userConfig;

    /**
     * nacos获取属性测试
     * get nacos property test
     */
    @ResponseBody
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ApiOperation(value = "username", notes = "获取nacos配置中心属性")
    public Result<String> username() {
        return Result.success("userName: " + JSON.toJSONString(userConfig));
    }

}
