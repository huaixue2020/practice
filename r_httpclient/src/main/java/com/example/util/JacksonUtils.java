package com.example.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.SneakyThrows;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

public class JacksonUtils {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final ObjectMapper om = new ObjectMapper();

    static {
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        om.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        om.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        om.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
        om.registerModule(javaTimeModule);
        om.setTimeZone(TimeZone.getDefault());
    }

    public static JavaType makeJavaType(Class<?> parametrized, Class<?>... parameterClasses) {
        return om.getTypeFactory().constructParametricType(parametrized, parameterClasses);
    }

    public static JavaType makeJavaType(Class<?> rawType, JavaType... parameterTypes) {
        return om.getTypeFactory().constructParametricType(rawType, parameterTypes);
    }

    public static String toString(Object value) {
        if (Objects.isNull(value)) {
            return null;
        }
        if (value instanceof String) {
            return (String) value;
        }
        return toJSONString(value);
    }

    @SneakyThrows
    public static String toJSONString(Object value) {
        return om.writeValueAsString(value);
    }

    @SneakyThrows
    public static String toPrettyString(Object value) {
        return om.writerWithDefaultPrettyPrinter().writeValueAsString(value);
    }

    @SneakyThrows
    public static JsonNode fromJavaObject(Object value) {
        JsonNode result = null;
        if (Objects.nonNull(value) && (value instanceof String)) {
            result = parseObject((String) value);
        } else {
            result = om.valueToTree(value);
        }
        return result;
    }

    @SneakyThrows
    public static JsonNode parseObject(String content) {
        return om.readTree(content);
    }

    public static JsonNode getJsonElement(JsonNode node, String name) {
        return node.get(name);
    }

    public static JsonNode getJsonElement(JsonNode node, int index) {
        return node.get(index);
    }

    @SneakyThrows
    public static <T> T toJavaObject(TreeNode node, Class<T> clazz) {
        return om.treeToValue(node, clazz);
    }

    @SneakyThrows
    public static <T> T toJavaObject(TreeNode node, JavaType javaType) {
        return om.convertValue(node, javaType);
    }

    @SneakyThrows
    public static <T> T toJavaObject(TreeNode node, TypeReference<?> typeReference) {
        return om.convertValue(node, typeReference);
    }

    public static <T> T toJavaObject(TreeNode node, Type type) {
        return toJavaObject(node, om.constructType(type));
    }

    public static <E> List<E> toJavaList(TreeNode node, Class<E> clazz) {
        return toJavaObject(node, makeJavaType(List.class, clazz));
    }

    public static List<Object> toJavaList(TreeNode node) {
        return toJavaObject(node, new TypeReference<List<Object>>(){});
    }

    public static <V> Map<String, V> toJavaMap(TreeNode node, Class<V> clazz) {
        return toJavaObject(node, makeJavaType(Map.class, String.class, clazz));
    }

    public static Map<String, Object> toJavaMap(TreeNode node) {
        return toJavaObject(node, new TypeReference<Map<String, Object>>(){});
    }

    @SneakyThrows
    public static <T> T toJavaObject(String content, Class<T> clazz) {
        return om.readValue(content, clazz);
    }

    @SneakyThrows
    public static <T> T toJavaObject(String content, JavaType javaType) {
        return om.readValue(content, javaType);
    }

    @SneakyThrows
    public static <T> T toJavaObject(String content, TypeReference<?> typeReference) {
        return om.readValue(content, typeReference);
    }

    public static <T> T toJavaObject(String content, Type type) {
        return toJavaObject(content, om.constructType(type));
    }

    public static <E> List<E> toJavaList(String content, Class<E> clazz) {
        return toJavaObject(content, makeJavaType(List.class, clazz));
    }

    public static List<Object> toJavaList(String content) {
        return toJavaObject(content, new TypeReference<List<Object>>(){});
    }

    public static <V> Map<String, V> toJavaMap(String content, Class<V> clazz) {
        return toJavaObject(content, makeJavaType(Map.class, String.class, clazz));
    }

    public static Map<String, Object> toJavaMap(String content) {
        return toJavaObject(content, new TypeReference<Map<String, Object>>(){});
    }

}
