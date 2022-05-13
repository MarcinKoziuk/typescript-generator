package cz.habarta.typescript.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cz.habarta.typescript.generator.parser.*;


public class DefaultNullableTest {

    @Test
    public void testJackson1DefaultNullableOn() {
        final Settings settings = TestUtils.settings();
        settings.defaultNullable = true;
        ModelParser parser = new Jackson1Parser(settings, new DefaultTypeProcessor());
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Jackson1Bean.class));
        final String expected = "interface Jackson1Bean {\n" +
                "    boxedProperty: number | null;\n" +
                "    primitiveProperty: number;\n" +
                "    methodStringProperty: string | null;\n" +
                "}";
        Assertions.assertEquals(expected.trim(), output.trim());
    }

    @Test
    public void testJackson1DefaultNullableOff() {
        final Settings settings = TestUtils.settings();
        settings.defaultNullable = false;
        ModelParser parser = new Jackson1Parser(settings, new DefaultTypeProcessor());
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Jackson1Bean.class));
        final String expected = "interface Jackson1Bean {\n" +
                "    boxedProperty: number;\n" +
                "    primitiveProperty: number;\n" +
                "    methodStringProperty: string;\n" +
                "}";
        Assertions.assertEquals(expected.trim(), output.trim());
    }


    static class Jackson1Bean {
        @org.codehaus.jackson.annotate.JsonProperty
        private String stringProperty;

        @org.codehaus.jackson.annotate.JsonProperty
        public Integer boxedProperty;

        @org.codehaus.jackson.annotate.JsonProperty
        public int primitiveProperty;

        @org.codehaus.jackson.annotate.JsonProperty
        public String getMethodStringProperty() {
            return stringProperty;
        }
    }

}
