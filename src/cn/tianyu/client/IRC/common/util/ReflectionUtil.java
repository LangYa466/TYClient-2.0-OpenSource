/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.IRC.common.util;

import cn.tianyu.client.IRC.common.packet.IRCPacket;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ReflectionUtil {
    public static Class<?>[] getClassesInPackage(String packageName) {
        try {
            Set<String> classnames = ReflectionUtil.getClassNamesFromJarFile(Paths.get(ReflectionUtil.path(), new String[0]).toFile());
            ArrayList classes = new ArrayList();
            for (String classname : classnames) {
                try {
                    if (!classname.startsWith(packageName)) continue;
                    Class<?> clazz = Class.forName(classname);
                    classes.add(clazz);
                }
                catch (ClassNotFoundException | NoClassDefFoundError | UnsupportedClassVersionError throwable) {}
            }
            return (Class<?>[]) classes.toArray(new Class[classes.size()]);
        }
        catch (Exception exception) {
            File directory = ReflectionUtil.getPackageDirectory(packageName);
            if (!directory.exists()) {
                throw new IllegalArgumentException("Could not get directory resource for package " + packageName);
            }
            return ReflectionUtil.getClassesInPackage(packageName, directory);
        }
    }

    public static Set<String> getClassNamesFromJarFile(File givenFile) throws IOException {
        HashSet<String> classNames = new HashSet<String>();
        try (JarFile jarFile = new JarFile(givenFile);){
            Enumeration<JarEntry> e = jarFile.entries();
            while (e.hasMoreElements()) {
                JarEntry jarEntry = e.nextElement();
                if (!jarEntry.getName().endsWith(".class")) continue;
                String className = jarEntry.getName().replace("/", ".").replace(".class", "");
                classNames.add(className);
            }
            HashSet<String> hashSet = classNames;
            return hashSet;
        }
    }

    private static Class<?>[] getClassesInPackage(String packageName, File directory) {
        ArrayList classes = new ArrayList();
        for (String filename : Objects.requireNonNull(directory.list())) {
            if (filename.endsWith(".class")) {
                String classname = ReflectionUtil.buildClassname(packageName, filename);
                try {
                    classes.add(Class.forName(classname));
                }
                catch (ClassNotFoundException e) {
                    System.err.println("Error creating class " + classname);
                }
                continue;
            }
            if (filename.contains(".")) continue;
            String name = packageName + (packageName.endsWith(".") ? "" : ".") + filename;
            classes.addAll(Arrays.asList(ReflectionUtil.getClassesInPackage(name, ReflectionUtil.getPackageDirectory(name))));
        }
        return (Class<?>[]) classes.toArray(new Class[classes.size()]);
    }

    public static String buildClassname(String packageName, String filename) {
        return packageName + '.' + filename.replace(".class", "");
    }

    private static File getPackageDirectory(String packageName) {
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        if (cld == null) {
            throw new IllegalStateException("Can't get class loader.");
        }
        URL resource = cld.getResource(packageName.replace('.', '/'));
        if (resource == null) {
            throw new RuntimeException("Package " + packageName + " not found on classpath.");
        }
        return new File(resource.getFile());
    }

    public static boolean dirExist(String packageName) {
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        URL resource = cld.getResource(packageName.replace('.', '/'));
        return resource != null;
    }

    public static String path() throws URISyntaxException {
        return new File(IRCPacket.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
    }
}

