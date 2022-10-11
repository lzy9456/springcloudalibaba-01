package rocketmq.simpletest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rocketmq.simpletest.service.send.SimpleMqSendService;

import java.util.UUID;

/**
 * @author _lizy
 * @description mq rest test
 * @date 2021/1/2 15:12
 */
@RestController
public class MqSendController {

    @Autowired
    private SimpleMqSendService simpleMqSendService;

    @ResponseBody
    @RequestMapping(value = "/send1", method = RequestMethod.GET)
    public String send() throws Exception {
        simpleMqSendService.send(UUID.randomUUID().toString() + " helloworld!");
        return "ok helloworld! " + System.currentTimeMillis();
    }

    // @ResponseBody
    // @RequestMapping(value = "/send2", method = GET)
    // public String sendOp1Tags() throws Exception {
    // mqSendService.sendWithTags(UUID.randomUUID().toString(), Constants.MQ_TAG_UNREG_USER);
    // return "tag ok " + System.currentTimeMillis();
    // }
    //
    // @ResponseBody
    // @RequestMapping(value = "/send3", method = GET)
    // public String send3() throws Exception {
    // mqSendService.sendObject(Foo.newF().setId(1).setBar("test"), Constants.MQ_TAG_OBJ);
    // return "obj ok " + System.currentTimeMillis();
    // }
    //
    // @ResponseBody
    // @RequestMapping(value = "/send4", method = GET)
    // public String send4() throws Exception {
    // mqSendService.sendTransactionalMsg(UUID.randomUUID().toString(), Integer.valueOf(RandomStringUtils.randomNumeric(8)));
    // return "tx ok " + System.currentTimeMillis();
    // }

}
