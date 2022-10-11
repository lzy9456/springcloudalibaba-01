# SpringCloud Alibaba

> helloworld

# fegin + nacos + gateway + sentinel整合

# Fegin

> 服务调用

> 开启rabbin负载均衡

> 整合nacos动态服务调用

## Gateway

> 整合nacos，根据服务名访问，本地负载均衡

> 跨域

> 安全

> 整合sentinel，在网关，流控，降级

## Nacos

> 服务注册发现

> 配置中心

## Sentinel

> 路由等配置各种限流

流控 - flow；快速失败；限流抛错异常
> {
"code": 429,
"message": "Blocked by Sentinel: FlowException"
}

降级 -

## RocketMQ

spring-cloud-stream方式整合RocketMQ

> - binder：对应mq server
> - binding：收发消息通道，input，output
