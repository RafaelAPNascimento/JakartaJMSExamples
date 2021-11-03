package org.example;

import jakarta.annotation.Resource;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.jms.*;
import jakarta.transaction.Transactional;

import java.util.logging.Logger;

@Model
public class WordService {

    Logger LOG = Logger.getLogger(WordService.class.getName());

    @Inject
    private JMSContext context;

    @Resource(lookup = "jms/wordTopic")
    private Topic wordTopic;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void send(String word) {

        TextMessage message = context.createTextMessage(word);
        TextMessage extra1 = context.createTextMessage("extra1");
        TextMessage extra2 = context.createTextMessage("extra2");

        JMSProducer producer = context.createProducer();

        producer.send(wordTopic, message);
        producer.send(wordTopic, extra1);
        producer.send(wordTopic, extra2);

        if (true)
            //throw new RuntimeException("TX should be aborted");

        LOG.info("message sent, ctc commited");
    }
}
