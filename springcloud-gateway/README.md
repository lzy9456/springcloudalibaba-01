# gateway + nacos + sentinel

## gateway

> 根据服务名访问：支持负载均衡

## nacos

> 根据服务名访问：支持负载均衡

## sentinel

注：网关流控与服务流控参数不一样

- 网关流控参数：
- 网关流控配置类：com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule

> 路由等配置各种限流

网关流控 - flow；快速失败；限流抛错异常
> {
"code": 429,
"message": "Blocked by Sentinel: FlowException"
}

降级 - 