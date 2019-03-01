package annotations;

import enums.DBEngine;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
    String value() default "";
    DBEngine engine() default DBEngine.InnoDB;
    boolean dropExistingTable() default true;
}
