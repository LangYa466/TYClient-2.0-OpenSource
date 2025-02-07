package cn.tianyu.client.event;

import java.util.Iterator;
import java.util.Comparator;
import cn.tianyu.client.event.annotations.EventPriority;
import java.lang.annotation.Annotation;
import java.util.concurrent.CopyOnWriteArrayList;
import cn.tianyu.client.event.annotations.EventTarget;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import cn.tianyu.client.event.impl.Event;
import java.lang.reflect.Method;
import java.util.Map;

public class EventManager
{
    private final Map<Method, Class<?>> registeredMethodMap;
    private final Map<Method, Object> methodObjectMap;
    private final Map<Class<? extends Event>, List<Method>> priorityMethodMap;

    public EventManager() {
        this.registeredMethodMap = new ConcurrentHashMap<Method, Class<?>>();
        this.methodObjectMap = new ConcurrentHashMap<Method, Object>();
        this.priorityMethodMap = new ConcurrentHashMap<Class<? extends Event>, List<Method>>();
    }

    public void register(final Object... obj) {
        for (final Object object : obj) {
            this.register(object);
        }
    }

    public void register(final Object obj) {
        final Class<?> clazz = obj.getClass();
        final Method[] declaredMethods;
        final Method[] methods = declaredMethods = clazz.getDeclaredMethods();
        for (final Method method : declaredMethods) {
            final Annotation[] declaredAnnotations;
            final Annotation[] annotations = declaredAnnotations = method.getDeclaredAnnotations();
            for (final Annotation annotation : declaredAnnotations) {
                if (annotation.annotationType() == EventTarget.class && method.getParameterTypes().length == 1) {
                    this.registeredMethodMap.put(method, method.getParameterTypes()[0]);
                    this.methodObjectMap.put(method, obj);
                    final Class<? extends Event> eventClass = method.getParameterTypes()[0].asSubclass(Event.class);
                    this.priorityMethodMap.computeIfAbsent(eventClass, k -> new CopyOnWriteArrayList()).add(method);
                }
            }
        }
    }

    public void unregister(final Object obj) {
        final Class<?> clazz = obj.getClass();
        final Method[] declaredMethods;
        final Method[] methods = declaredMethods = clazz.getDeclaredMethods();
        for (final Method method : declaredMethods) {
            if (this.registeredMethodMap.containsKey(method)) {
                this.registeredMethodMap.remove(method);
                this.methodObjectMap.remove(method);
                final Class<? extends Event> eventClass = method.getParameterTypes()[0].asSubclass(Event.class);
                final List<Method> priorityMethods = this.priorityMethodMap.get(eventClass);
                if (priorityMethods != null) {
                    priorityMethods.remove(method);
                }
            }
        }
    }

    public Event call(final Event event) {
        final Class<? extends Event> eventClass = event.getClass();
        final List<Method> methods = this.priorityMethodMap.get(eventClass);
        if (methods != null) {
            Method method;
            methods.sort(Comparator.comparingInt(method2 -> {
                final EventPriority priority = method2.getAnnotation(EventPriority.class);
                return (priority != null) ? priority.value() : 10;
            }));
            for (Method value : methods) {
                method = value;
                final Object obj = this.methodObjectMap.get(method);
                method.setAccessible(true);
                try {
                    method.invoke(obj, event);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return event;
    }
}
