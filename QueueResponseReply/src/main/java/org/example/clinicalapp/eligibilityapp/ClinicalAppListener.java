package org.example.clinicalapp.eligibilityapp;

import jakarta.annotation.Resource;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.*;
import org.example.clinicalapp.model.Patient;

import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/EligibilityQueue") })
public class ClinicalAppListener implements MessageListener {

    private static final Logger LOG = Logger.getLogger(ClinicalAppListener.class.getName());

    @Inject
    private JMSContext context;

    @Resource(lookup = "jms/ClinicalQueue")
    private Queue queue;

    @Override
    public void onMessage(Message message) {

        LOG.info("checking eligibility...");

        JMSProducer producer = context.createProducer();
        boolean eligible = false;

        try {
            Patient patient = message.getBody(Patient.class);

            if (patient.getAge() > 18 && patient.getSalary() < 1000.0) {
                eligible = true;
            }
            producer.setProperty("isEligibile", eligible);
            producer.send(queue, patient);
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
