package com.testlab.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.testlab")
class ArchitectureRulesTest {

    @ArchTest
    static final ArchRule layeredArchitectureRule = layeredArchitecture()
        .consideringAllDependencies()
        .layer("Controller").definedBy("..controller..")
        .layer("Service").definedBy("..service..")
        .layer("Repository").definedBy("..repository..")
        .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
        .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
        .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");

    @ArchTest
    static final ArchRule controllersShouldBeAnnotatedWithRestController =
        classes().that().resideInAPackage("..controller..")
            .should().beAnnotatedWith(RestController.class);

    @ArchTest
    static final ArchRule servicesShouldBeAnnotatedWithService =
        classes().that().resideInAPackage("..service..")
            .should().beAnnotatedWith(Service.class);
}
