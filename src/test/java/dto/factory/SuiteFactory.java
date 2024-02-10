package dto.factory;

import com.github.javafaker.Faker;
import dto.Suite;

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
