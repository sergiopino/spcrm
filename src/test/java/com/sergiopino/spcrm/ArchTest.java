package com.sergiopino.spcrm;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.sergiopino.spcrm");

        noClasses()
            .that()
                .resideInAnyPackage("com.sergiopino.spcrm.service..")
            .or()
                .resideInAnyPackage("com.sergiopino.spcrm.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.sergiopino.spcrm.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
