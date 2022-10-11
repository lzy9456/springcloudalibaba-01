package rocketmq.simpletest.service.send;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@EnableBinding({ Source.class })
public class SimpleMqSendService {

    @Autowired
    private Source source;

    public void send(String msg) throws Exception {
        log.info("send msg: {}", msg);
        source.output().send(MessageBuilder.withPayload(msg).build());
    }

    public <T> void sendWithTags(T msg, String tag) throws Exception {
        log.info("sendWithTags msg {}", msg);
        Message message = MessageBuilder.createMessage(msg,
            new MessageHeaders(Stream.of(tag).collect(Collectors
                .toMap(str -> MessageConst.PROPERTY_TAGS, String::toString))));
        source.output().send(message);
    }

    public <T> void sendObject(T msg, String tag) throws Exception {
        log.info("sendObject msg: {}", msg);

        Message message = MessageBuilder.withPayload(msg)
            .setHeader(MessageConst.PROPERTY_TAGS, tag)
            .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
            .build();
        source.output().send(message);
    }

}
