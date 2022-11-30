package ru.job4j.template;

import junit.framework.TestCase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;

@Disabled
public class GeneratorTest {

    @Test
    public void whenEverythingIsOk() {
        Generator generator = new GeneratorObject();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        String str = "I am a ${name}, Who are ${subject}? ";
        String rsl = "I am a Petr Arsentev, Who are you? ";
        assertThat(generator.produce(str, map)).isEqualTo(rsl);
    }

    @Test
    public void whenItDoesNotWork() {
        Generator generator = new GeneratorObject();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan Ivanov");
        map.put("subject", "I");
        String str = "I am a ${name}, Who are ${subject}? ";
        String rsl = "I am a Petr Arsentev, Who are you? ";
        assertThat(generator.produce(str, map)).isNotEqualTo(rsl);
    }
}