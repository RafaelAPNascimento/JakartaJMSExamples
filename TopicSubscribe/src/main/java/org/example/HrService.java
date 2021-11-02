package org.example;

import jakarta.annotation.Resource;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.jms.*;
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

        try {
            JMSProducer producer = context.createProducer();

            ObjectMessage message = context.createObjectMessage();
            message.setBooleanProperty("gender", employee.isGender());
            message.setObject(employee);

            //producer.send(hrTopic, employee);
            producer.send(hrTopic, message);
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
