package org.example;

import jakarta.annotation.Resource;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.Topic;
import org.example.model.Employee;

import java.util.logging.Logger;

@Model
public class HrService {

    Logger LOG = Logger.getLogger(HrService.class.getName());

    @Inject
    private JMSContext context;

    @Resource(lookup = "jms/hrTopic")
    private Topic hrTopic;

    public void hireEmployee(Employee employee) {

        LOG.info("hiring new employee\n" + employee);

        JMSProducer producer = context.createProducer();
        producer.send(hrTopic, employee);
    }
}
