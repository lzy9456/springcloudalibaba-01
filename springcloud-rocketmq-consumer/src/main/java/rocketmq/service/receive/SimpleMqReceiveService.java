package rocketmq.service.receive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @author _lizy
 * @description mq默认简单收发测试
 * @date
 */
@Slf4j
@Service
@EnableBinding({ Sink.class })
public class SimpleMqReceiveService {

    @StreamListener(Sink.INPUT)
    public void receiveInput(String receiveMsg) {
        log.info("simple input receive msg: " + receiveMsg);
        System.out.println("simple input receive msg: " + receiveMsg);
    }

}
