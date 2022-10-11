package com.example.utils;

import com.example.entity.result.CodeEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextHelper
 *
 * @author Frank Zhang
 * @date 2018-01-07 12:30 PM
 */
@Component
public class SpringBeanHelper implements ApplicationContextAware {
    private static ApplicationContext applicationContext;


    public static <T> T getBean(Class<T> targetClz){
        T beanInstance = null;

        try { //优先按type查
            beanInstance = (T) applicationContext.getBean(targetClz);
        }catch (Exception e){
        }

        if(beanInstance == null){ //按name查
            String simpleName = targetClz.getSimpleName();
            //首字母小写
            simpleName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
            beanInstance = (T) applicationContext.getBean(simpleName);
        }


        if(beanInstance == null){
            new RuntimeException(CodeEnum.EXCEPTION + "Component " + targetClz + " can not be found in Spring Container");
        }
        return beanInstance;
    }

    public static Object getBean(String claz){
        return applicationContext.getBean(claz);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanHelper.applicationContext = applicationContext;
    }
}
