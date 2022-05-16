package cz.habarta.typescript.generator;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DefaultNullableTest {

    @Test
    public void testJackson1DefaultNullableOn() {
        final Settings settings = TestUtils.settings();
        settings.defaultNullable = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Jackson1Bean.class));
        final String expected = "interface Jackson1Bean {\n" +
                "    boxedProperty: number | null;\n" +
                "    primitiveProperty: number;\n" +
                "    listProperty: string[];\n" +
                "    mapProperty: { [index: string]: string };\n" +
                "    notNullProperty: string;\n" +
                "    notEmptyProperty: string;\n" +
                "    methodStringProperty: string | null;\n" +
                "}";
        Assertions.assertEquals(expected.trim(), output.trim());
    }

    @Test
    public void testJackson1DefaultNullableOff() {
        final Settings settings = TestUtils.settings();
        settings.defaultNullable = false;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Jackson1Bean.class));
        final String expected = "interface Jackson1Bean {\n" +
                "    boxedProperty: number;\n" +
                "    primitiveProperty: number;\n" +
                "    listProperty: string[];\n" +
                "    mapProperty: { [index: string]: string };\n" +
                "    notNullProperty: string;\n" +
                "    notEmptyProperty: string;\n" +
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
        public List<String> listProperty;

        @org.codehaus.jackson.annotate.JsonProperty
        public Map<String, String> mapProperty;

        @org.codehaus.jackson.annotate.JsonProperty
        @javax.validation.constraints.NotNull public String notNullProperty;

        @org.codehaus.jackson.annotate.JsonProperty
        @javax.validation.constraints.NotEmpty public String notEmptyProperty;

        @org.codehaus.jackson.annotate.JsonProperty
        public String getMethodStringProperty() {
            return stringProperty;
        }
    }

}
