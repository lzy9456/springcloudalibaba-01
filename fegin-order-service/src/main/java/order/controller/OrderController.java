package order.controller;

import com.alibaba.fastjson.JSON;
import com.example.entity.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import order.fegin.api.IStockFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Slf4j
@RestController
@Api(value = "订单controoller", tags = "Sys v1.0 - 订单模块")
public class OrderController {

    public static final String URL_BY_IP_PORT            = "http://localhost:8082/stock/reduct/";
    public static final String URL_BY_NACOS_SERVICE_NAME = "http://fegin-stock-service/stock/reduct/";
    @Autowired
    RestTemplate               restTemplate;
    @Autowired
    IStockFeginService         stockFeginService;

    /**
     * Exception：@restTemplate 加了负载均衡注解@LoadBalanced，ip部分会解析位服务名匹配，无法识别，抛异常
     * 
     * restTemplate，http url调用方式
     * 
     * http get
     * API接口文档swagger - @ApiOperation、@ApiImplicitParam
     * 参数验证valication - @Valid开启验证 @NotBlank
     * 降级sentinel fallback - userMgrService.getUser - roleService.getRole()抛错降级执行fallback方法，跳过，不影响主流程
     *
     * @param id
     * @return Result 统一响应返回前端
     */
    @ResponseBody
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "order", notes = "获取订单")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    public Result<String> order(@Valid @NotBlank(message = "id不能为空") @PathVariable("id") String id) {
        String stockResponse = restTemplate.getForObject(URL_BY_IP_PORT + id, String.class);
        return Result.success("ok order " + id + ", stockResponse: " + stockResponse);
    }

    /**
     * restTemplate，nacos调用方式
     *
     * @param id
     * @return Result 统一响应返回前端
     */
    @ResponseBody
    @RequestMapping(value = "/order-rt-nacos/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "order", notes = "获取订单()")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    public Result<String> orderByRTNacos(@Valid @NotBlank(message = "id不能为空") @PathVariable("id") String id) {
        String stockResponse = restTemplate.getForObject(URL_BY_NACOS_SERVICE_NAME + id, String.class);
        return Result.success("ok order " + id + ", stockResponse: " + stockResponse);
    }

    /**
     * openfegin，nacos调用方式
     *
     * @param id
     * @return Result 统一响应返回前端
     */
    @ResponseBody
    @RequestMapping(value = "/order-fegin-nacos/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "order", notes = "获取订单(fegin-nacos)")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    public Result<String> orderByFeginNacos(@Valid @NotBlank(message = "id不能为空") @PathVariable("id") String id) {
        Result<String> reductResult = stockFeginService.reduct(id);
        return Result.success("ok order " + id + ", stockResponse: " + JSON.toJSONString(reductResult));
    }

}
