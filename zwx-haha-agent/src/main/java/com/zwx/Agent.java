package com.zwx;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-28 18:11
 */
public class Agent {
    /**
     * 启动依附回调方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("========================= into premain =========================");
        agentArgs = "com.alibaba.dubbo.config.annotation.Reference";
        System.out.println("参数：" + agentArgs);
        inst.addTransformer(new PersonClassFileTransformer(agentArgs), true);
    }

    /**
     * Attach API依附回调方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        premain(agentArgs, inst);
    }
}


class PersonClassFileTransformer implements ClassFileTransformer {
    private String targetClassName;

    PersonClassFileTransformer(String targetClassName) {
        this.targetClassName = targetClassName;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // 修改类的限定名格式
        String target = className.replace("/", ".");
        System.out.printf("=============" + target);
//        if (targetClassName.equals(target)) {
        try {
            // 获取目标类
            ClassPool classPool = ClassPool.getDefault();
            //获取要修改的类的所有信息
            CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
            CtField[] declaredFields = ctClass.getDeclaredFields();
            for (CtField f : declaredFields) {
                FieldInfo fieldInfo = f.getFieldInfo();
                ConstPool cp = fieldInfo.getConstPool();
                AnnotationsAttribute attribute = (AnnotationsAttribute) fieldInfo.getAttribute(AnnotationsAttribute.visibleTag);
                System.out.println(attribute);
                //获取注解
                Annotation annotation = attribute.getAnnotation("com.alibaba.dubbo.config.annotation.Reference");
                if (annotation != null) {
                    System.out.println("找到引用Reference信息:" + annotation);
                    //获取注解的值
                    String text = ((StringMemberValue) annotation.getMemberValue("url")).getValue();
                    System.out.println("注解名称===" + text);
                    annotation.addMemberValue("url", new StringMemberValue("dubbo://127.0.0.1:10020", cp));
                    attribute.setAnnotation(annotation);
                    fieldInfo.addAttribute(attribute);
                }
            }
            return ctClass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }
        return null;
    }
}