package com.optimus.trans;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


/**
 * 将DTO转换为DO
 * @param <T>
 * @param <E>
 */
@Component
public  class TransToDO<T,E> {
   public E transToDo(T t,E e){
       // 把t拷贝到e
       BeanUtils.copyProperties(t,e);
       return e;
   }
}
