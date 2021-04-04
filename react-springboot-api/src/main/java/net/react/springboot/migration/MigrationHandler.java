package net.react.springboot.migration;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * This class finds all @Migration annotated beans in the Spring application context
 * and runs each one based on whether or not it has been run before and the criteria
 * defined in the annotation. It records the result of the run into the MongoDB database
 * so that there is an audit trail of attempts and results.
 * 
 * @author Vishal Kumar
 */
@Service
public class MigrationHandler implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MigrationHandler.class);
	
	private ApplicationContext applicationContext;
	
	@Autowired
	public MigrationHandler() {
		
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		this.applicationContext = applicationContext;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		
		this.handleMigrations();
	}
	
	private void handleMigrations() {
		List<MigrationProperties> knownMigrations = applicationContext.getBeansWithAnnotation(Migration.class)
                .values()
                .stream()
                .map(this::getMigrationProperties)
                .collect(toList());
        
        Map<String, List<MigrationProperties>> migrationsToRun = knownMigrations.stream()
                .collect(groupingBy(MigrationProperties::getContext,
                        collectingAndThen(toList(), list -> list.stream()
                                .sorted(Comparator.comparing(MigrationProperties::getSequence))
                                .collect(toList()))));
         
        migrationsToRun.values().stream()
                .forEach(values -> values.stream()
                        .forEach(this::processMigration));
	}
	
	private void processMigration(final MigrationProperties migrationProperties) {
        
        LOGGER.info(String.format("Processing migration %s:%s[%d]", migrationProperties.getContext(), migrationProperties.getId(), migrationProperties.getSequence()));
        Object migrationBean = null;
        try {
            migrationBean = migrationProperties.getBean();
            Method executeMethod = migrationBean.getClass().getMethod(migrationProperties.getExecuteMethod());
            executeMethod.invoke(migrationBean);
        } catch (Exception e) {
            if(migrationProperties.getFailOnError()) {
                throw new MigrationFailureException(e.getMessage(), e);
            }
        }
    }
	
	 private MigrationProperties getMigrationProperties(final Object annotatedBean) {

	        Migration migration = annotatedBean.getClass().getAnnotation(Migration.class);
	        MigrationProperties migrationProperties = MigrationProperties.newBuilder()
	                .withId(migration.id())
	                .withContext(migration.context())
	                .withSequence(migration.sequence())
	                .withExecuteMethod(migration.executeMethod())
	                .withRunalways(migration.runAlways())
	                .withRunOnChange(migration.runOnChange())
	                .withFailOnError(migration.failOnError())
	                .withAuthor(migration.author())
	                .withBean(annotatedBean)
	                .build();

	        return migrationProperties;
	    }
}
