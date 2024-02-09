package dto;

import com.github.javafaker.Faker;

public class ProjectFactory {

    public static Project getRandom() {
        Faker faker = new Faker();
        Project project = Project.builder()
                .title(faker.funnyName().name())
                .code(faker.letterify("??????????").toUpperCase())
                .description(faker.lorem().paragraph(1))
                .isProjectPrivate(false)
                .access("all")
                .group("test")
                .build();
        return project;
    }
}
