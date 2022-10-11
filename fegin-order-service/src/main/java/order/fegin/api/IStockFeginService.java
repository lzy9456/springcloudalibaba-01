package order.fegin.api;


import com.example.entity.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@FeignClient(name = "fegin-stock-service")
public interface IStockFeginService {

    @RequestMapping(value = "/stock/reduct/{id}", method = RequestMethod.GET)
    Result<String> reduct(@Valid @NotBlank(message = "id不能为空") @PathVariable("id") String id);
}
