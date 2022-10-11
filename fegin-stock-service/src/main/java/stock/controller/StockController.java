package stock.controller;

import com.example.entity.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Slf4j
@RestController
@Api(value = "库存controoller", tags = "Sys v1.0 - 库存模块")
public class StockController {

    /**
     * http get
     * API接口文档swagger - @ApiOperation、@ApiImplicitParam
     * 参数验证valication - @Valid开启验证 @NotBlank
     *
     * @param id
     * @return Result 统一响应返回前端
     */
    @ResponseBody
    @RequestMapping(value = "/stock/reduct/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "/stock/reduct", notes = "减库存")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    public Result<String> reduct(@Valid @NotBlank(message = "id不能为空") @PathVariable("id") String id) {
        log.info("************ stock reduct order id: {}", id);
        return Result.success("ok reduct " + id);
    }

}
