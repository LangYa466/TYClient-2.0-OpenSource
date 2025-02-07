/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC.common.packet;

import cn.tianyu.client.IRC.common.packet.annotations.ProtocolField;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.viaversion.viaversion.libs.gson.reflect.TypeToken;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IRCPacket {
    public static final Gson gson = new Gson();

    default public void readPacket(JsonObject jsonObject) {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(ProtocolField.class) || Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            ProtocolField protocolField = field.getAnnotation(ProtocolField.class);
            try {
                Object value;
                Type genericType;
                JsonElement element = jsonObject.get(protocolField.value());
                if (element == null) continue;
                Class<?> fieldType = field.getType();
                if (List.class.isAssignableFrom(fieldType)) {
                    genericType = ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];
                    value = gson.fromJson(element, TypeToken.getParameterized(List.class, new Type[]{genericType}).getType());
                } else if (Set.class.isAssignableFrom(fieldType)) {
                    genericType = ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];
                    value = gson.fromJson(element, TypeToken.getParameterized(Set.class, new Type[]{genericType}).getType());
                } else if (Map.class.isAssignableFrom(fieldType)) {
                    Type[] genericTypes = ((ParameterizedType)field.getGenericType()).getActualTypeArguments();
                    Type keyType = genericTypes[0];
                    Type valueType = genericTypes[1];
                    value = gson.fromJson(element, TypeToken.getParameterized(Map.class, new Type[]{keyType, valueType}).getType());
                } else {
                    value = gson.fromJson(element, fieldType);
                }
                field.set(this, value);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException("Error while reading packet", e);
            }
        }
    }

    default public JsonObject writePacket() {
        JsonObject jsonObject = new JsonObject();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(ProtocolField.class)) continue;
            if (Modifier.isStatic(field.getModifiers())) {
                throw new RuntimeException("Protocol field can't be static.");
            }
            field.setAccessible(true);
            ProtocolField protocolField = field.getAnnotation(ProtocolField.class);
            try {
                Object value = field.get(this);
                if (value instanceof List || value instanceof Set) {
                    JsonArray jsonArray = gson.toJsonTree(value).getAsJsonArray();
                    jsonObject.add(protocolField.value(), jsonArray);
                    continue;
                }
                if (value instanceof Map) {
                    JsonElement jsonElement = gson.toJsonTree(value);
                    jsonObject.add(protocolField.value(), jsonElement);
                    continue;
                }
                jsonObject.addProperty(protocolField.value(), value.toString());
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException("Error while writing packet", e);
            }
        }
        return jsonObject;
    }
}

