package com.sab2i.portal.beneficiaries;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        if (args.length != 2) {
            System.out.println("Usage: <nb of generated elements> <registry url>");
            System.exit(-1);
        }
        long count = Long.parseLong(args[0]);
        String schemaUrl = args[1];

        String topic = "portal-beneficiaries";

        PortalBeneficiaryProducer beneficiaryProducer = new PortalBeneficiaryProducer(schemaUrl, count);
        beneficiaryProducer.process(topic);
    }
}

