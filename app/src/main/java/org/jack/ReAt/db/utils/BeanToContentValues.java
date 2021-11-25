package org.jack.ReAt.db.utils;

import org.jack.ReAt.bean.Person;

public class BeanToContentValues {
    public static void BeanToValue(Person person){
        person.getClass().getDeclaredFields();
    }
}
