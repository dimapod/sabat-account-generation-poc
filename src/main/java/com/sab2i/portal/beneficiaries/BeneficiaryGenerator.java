package com.sab2i.portal.beneficiaries;

import com.github.javafaker.Faker;
import com.sab2i.sabhd.portal.beneficiary.avro.PortalBeneficiary;

import java.util.Locale;

class BeneficiaryGenerator {
    private Faker faker = new Faker(new Locale("fr"));

    PortalBeneficiary getNextBeneficiary() {
        PortalBeneficiary portalBeneficiary = new PortalBeneficiary();
        portalBeneficiary.setBic(faker.finance().bic());
        portalBeneficiary.setClientId(faker.idNumber().valid());
        portalBeneficiary.setClientCode(faker.idNumber().valid());
        portalBeneficiary.setName(faker.name().name());
        portalBeneficiary.setIban(faker.finance().iban());
        portalBeneficiary.setBeneficiaryBic(faker.finance().bic());
        portalBeneficiary.setCountry(faker.address().country());
        portalBeneficiary.setAddress(faker.address().streetAddress());
        portalBeneficiary.setPostalCode(faker.address().zipCode());
        portalBeneficiary.setCity(faker.address().city());
        return portalBeneficiary;
    }
}
