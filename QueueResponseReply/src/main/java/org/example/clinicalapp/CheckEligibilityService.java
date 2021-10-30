package org.example.clinicalapp;

import jakarta.annotation.Resource;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.Queue;
import org.example.clinicalapp.model.Patient;

import java.util.logging.Logger;

@Model
public class CheckEligibilityService {

    private static final Logger LOG = Logger.getLogger(CheckEligibilityService.class.getName());

    @Inject
    private JMSContext context;

    @Resource(lookup = "jms/EligibilityQueue")
    private Queue queue;

    public void checkPatient(Patient patient) {

        LOG.info("checking patient");
        JMSProducer producer = context.createProducer();
        producer.send(queue, patient);
    }
}
