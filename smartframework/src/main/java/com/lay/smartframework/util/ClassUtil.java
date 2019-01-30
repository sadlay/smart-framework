package com.lay.smartframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Description:类操作工具类
 * @Author: lay
 * @Date: Created in 14:48 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public final class ClassUtil {

    private static final Logger LOGGER= LoggerFactory.getLogger(ClassUtil.class);

    /**
     *
     * @Description: 获取类加载器
     * @param:
     * @return: java.lang.ClassLoader
     * @auther: lay
     * @date: 14:51 2019/1/25
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }
    /**
     *
     * @Description: 加载类
     * @param:
     * @param className
     * @return: java.lang.Class<?>
     * @auther: lay
     * @date: 14:52 2019/1/25
     */
    public static Class<?>  loadClass(String className){
        Class<?> clz = loadClass(className, true);
        return clz;
    }

    /**
     *
     * @Description: 加载类
     * @param:
     * @param className
     * @param isInitialized
     * @return: java.lang.Class<?>
     * @auther: lay
     * @date: 14:52 2019/1/25
     */
    public static Class<?>  loadClass(String className,boolean isInitialized){
        Class clz;
        try{
            clz=Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure",e);
            throw new RuntimeException(e);
        }
        return clz;
    }


    /**
     *
     * @Description: 获取指定包下的所有类
     * @param:
     * @param packageName
     * @return: java.util.Set<java.lang.Class<?>>
     * @auther: lay
     * @date: 14:53 2019/1/25
     */
    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(url!=null){
                    String protocol=url.getProtocol();
                    if(protocol.equals("file")){
                        String packagePath = url.getPath().replace("%20", " ");//transfer space
                        addClass(classSet,packagePath,packageName);
                    }else if (protocol.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if(jarURLConnection!=null){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if(jarFile!=null){
                                Enumeration<JarEntry> entries = jarFile.entries();
                                while (entries.hasMoreElements()){
                                    JarEntry jarEntry = entries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")){
                                        String className=jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                        doAddClass(classSet,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("get Class-Set failure",e);
            throw new RuntimeException(e);
        }
        return classSet;
    }


    /**
     *
     * @Description: 递归遍历添加class
     * @param:
     * @param classSet
     * @param packagePath
     * @param packageName
     * @return: void
     * @auther: lay
     * @date: 16:10 2019/1/25
     */
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(file -> (file.isFile()&&file.getName().endsWith(".class"))||file.isDirectory());
        for (File file : files) {
            String fileName=file.getName();
            if(file.isFile()){
                String className=fileName.substring(0,fileName.lastIndexOf("."));
                if(StringUtil.isNotEmpty(packageName)){
                    className=packageName+"."+className;
                }
                doAddClass(classSet,className);
            }else {
                String subPackagePath=fileName;
                if(StringUtil.isNotEmpty(packagePath)){
                    subPackagePath=packagePath+"/"+subPackagePath;
                }
                String subPackageName=fileName;
                if(StringUtil.isNotEmpty(packageName)){
                    subPackageName=packageName+"."+subPackageName;
                }
                addClass(classSet,subPackagePath,subPackageName);
            }

        }
    }

    /**
     *
     * @Description: 加载类
     * @param:
     * @param classSet
     * @param className
     * @return: void
     * @auther: lay
     * @date: 16:10 2019/1/25
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);

    }
}
