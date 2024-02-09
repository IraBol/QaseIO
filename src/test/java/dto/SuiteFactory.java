package dto;

import com.github.javafaker.Faker;

public class SuiteFactory {

    public static Suite getRandom() {
        Faker faker = new Faker();
        Suite suite = Suite.builder()
                .title(faker.funnyName().name())
                .description(faker.lorem().paragraph(1))
                .preconditions(faker.lorem().paragraph(1))
                .parentId(null)
                .build();
        return suite;
    }
}
