package COURIER;

import org.apache.commons.lang3.RandomStringUtils;


public class CourierRandom {
    public Courier generic() {  //курьер по умолчанию
        return new Courier("mikiki", "1234", "qwertyT");

    }
    public Courier random() {  //курьер по умолчанию
        return new Courier(RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(6), RandomStringUtils.randomAlphanumeric(8));

    }
}
