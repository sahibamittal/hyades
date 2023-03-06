package org.hyades.common;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class KafkaTopicTest {

    @io.quarkus.test.junit.TestProfile(KafkaTopicTest.TestProfile.class)
    public static class TestProfile implements QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            return Map.of(
                    "api.topic.prefix", "customPrefix."
            );
        }

        @Test
        void testKafkaTopicConfigWithPrefix() {
            System.setProperty("api.topic.prefix", "customPrefix.");
            assertEquals("customPrefix.dtrack.vulnerability.mirror.osv", KafkaTopic.MIRROR_OSV.getName());
        }
    }

    @Test
    void testKafkaTopicConfig() {
        assertEquals("dtrack.vulnerability.mirror.osv", KafkaTopic.MIRROR_OSV.getName());
    }
}