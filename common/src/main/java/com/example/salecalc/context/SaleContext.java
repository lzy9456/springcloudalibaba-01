package com.example.salecalc.context;

import com.example.salecalc.entity.BaseSaleDO;
import com.example.salecalc.service.ISaleCalc;
import com.example.salecalc.service.impl.DefaultSaleCalcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class SaleContext {
    Map<Integer, String> context = new HashMap<>();
    Map<Integer, Class> saleDoContext = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;


    /**
     * put all sale service to context map
     * @param sales
     */
    @Autowired(required=false)
    public void setSales(List<ISaleCalc> sales) {
        for (Iterator<ISaleCalc> iterator = sales.iterator(); iterator.hasNext(); ) {
            ISaleCalc sale = iterator.next();
            SaleAnno saleAnno = sale.getClass().getAnnotation(SaleAnno.class);

            Optional.ofNullable(saleAnno)
                    .map(sa -> saleAnno.type().code)
                    .map(sa -> saleAnno.value())
                    .map(sa -> context.put(saleAnno.type().code, saleAnno.value()));
        }
    }


    public ISaleCalc getService(SaleTypeEnum saleTypeEnum) {
        return Optional.ofNullable(saleTypeEnum)
                .map(ac -> context.get(saleTypeEnum.code))
                .map(serviceName -> applicationContext.getBean(serviceName))
                .map(sale -> (ISaleCalc) sale)
                .orElse((ISaleCalc) applicationContext.getBean(DefaultSaleCalcServiceImpl.class));
    }


    /**
     * put all sale service input params to saleDoContext
     * @param saleIDos
     */
    @Autowired(required=false)
    public void setSaleIDos(List<BaseSaleDO> saleIDos) {
        for (Iterator<BaseSaleDO> iterator = saleIDos.iterator(); iterator.hasNext(); ) {
            BaseSaleDO sale = iterator.next();
            SaleDoAnno saleDoAnno = sale.getClass().getAnnotation(SaleDoAnno.class);

            Optional.ofNullable(saleDoAnno)
                    .map(sa -> saleDoAnno.type().code)
                    .map(sa -> saleDoAnno.value())
                    .map(sa -> saleDoContext.put(saleDoAnno.type().code, sale.getClass()));
        }
    }

    public Object getSaleIDo(SaleTypeEnum saleTypeEnum) {
        return Optional.ofNullable(saleTypeEnum)
                .map(ac -> saleDoContext.get(saleTypeEnum.code))
                .map(beanClass -> applicationContext.getBean(beanClass))
                .map(saleIDo -> (BaseSaleDO)saleIDo)
                .orElse(null);
    }


}
