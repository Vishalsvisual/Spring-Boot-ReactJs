package emp.management.migration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Migration {

    /**
     * The id for a migration is an arbitrary identifier for a migration. 
     */
    String id();
    
    /**
     * The context is an arbitrary string value used to group migrations together. All
     * migrations with a given context will be executed together in sequence number
     * order. The order of execution of contexts is undefined, so migrations with dependencies
     * on each other should always be part of the same context.
     */
    String context();

    /**
     * Sequence determines the ordering of migrations within a given context, lower
     * numbers being executed first.
     */
    int sequence();

    /**
     * @return The name of the method to call to run the migration. The method must be public and take no parameters
     * and must throw an exception if there is an error in processing.
     */
    String executeMethod() default "execute";
    
    /**
     * Should the migration be run on every application startup. Defaults to false.
     * 
     * @return True if the migration should be run every time the application starts, false otherwise.
     */
    boolean runAlways() default false;
    
    /**
     * Should the migration be re-run if it has changed since the last time it ran.
     * Defaults to true.
     * 
     * @return True if the migration should be re-run when changed, false otherwise.
     */
    boolean runOnChange() default true;
    
    /**
     * Whether or not an error in the migration should cause it to fail and stop migration processing. 
     * Defaults to true.
     * 
     * @return True if an error should stop processing, false otherwise.
     */
    boolean failOnError() default true;
    
    /**
     * @return The author of the migration - defaults to 'Unknown'.
     */
    String author() default "Unknown";
}
