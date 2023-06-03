package emp.management.migration;

import java.util.Objects;

import com.google.common.base.MoreObjects;

public class MigrationProperties {

    private final String id;

    private final String context;

    private final int sequence;

    private final String executeMethod;

    private final boolean runAlways;

    private final boolean runOnChange;

    private final boolean failOnError;

    private final String author;
    
    private final Object bean;

    private MigrationProperties(Builder builder) {

        this.id = builder.id;
        this.context = builder.context;
        this.sequence = builder.sequence;
        this.executeMethod = builder.executeMethod;
        this.runAlways = builder.runAlways;
        this.runOnChange = builder.runOnChange;
        this.failOnError = builder.failOnError;
        this.author = builder.author;
        this.bean = builder.bean;
    }

    public final String getId() {

        return this.id;
    }

    public final String getContext() {

        return this.context;
    }

    public final int getSequence() {

        return this.sequence;
    }

    public final String getExecuteMethod() {

        return this.executeMethod;
    }

    public final boolean getRunAlways() {

        return this.runAlways;
    }

    public final boolean getRunOnChange() {

        return this.runOnChange;
    }

    public final boolean getFailOnError() {

        return this.failOnError;
    }

    public final String getAuthor() {

        return this.author;
    }
    
    public final Object getBean() {
        
        return this.bean;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(!MigrationProperties.class.equals(o.getClass())) {
            return false;
        }
        
        MigrationProperties that = (MigrationProperties)o;
        
        return Objects.equals(this.author, that.author) &&
                Objects.equals(this.context, that.context) &&
                Objects.equals(this.executeMethod, that.executeMethod) &&
                Objects.equals(this.failOnError, that.failOnError) &&
                Objects.equals(this.id, that.id) &&
                Objects.equals(this.sequence, that.sequence) &&
                Objects.equals(this.runAlways, that.runAlways) &&
                Objects.equals(this.runOnChange, that.runOnChange);
    }
    
    @Override
    public int hashCode() {
        
        return Objects.hash(this.author, this.context, this.executeMethod, this.failOnError, this.id, this.sequence, this.runAlways, this.runOnChange);
    }
    
    @Override
    public String toString() {
        
        return MoreObjects.toStringHelper(this)
                .add("author", author)
                .add("context", context)
                .add("executeMethod", executeMethod)
                .add("failOnError", failOnError)
                .add("id", id)
                .add("sequence", sequence)
                .add("runAlways", runAlways)
                .add("runOnChange", runOnChange)
                .toString();
    }
    
    public static Builder newBuilder() {

        return new Builder();
    }

    public static final class Builder {

        private String id;

        private String context;

        private int sequence;

        private String executeMethod;

        private boolean runAlways;

        private boolean runOnChange;

        private boolean failOnError;

        private String author;
        
        private Object bean;

        private Builder() {

        }

        public Builder withId(String id) {

            this.id = id;
            return this;
        }
        
        public Builder withContext(String context) {
            
            this.context = context;
            return this;
        }
        
        public Builder withSequence(int sequence) {
            
            this.sequence = sequence;
            return this;
        }
        
        public Builder withExecuteMethod(String executeMethod) {
            
            this.executeMethod = executeMethod;
            return this;
        }
        
        public Builder withRunalways(boolean runAlways) {
            
            this.runAlways = runAlways;
            return this;
        }
        
        public Builder withRunOnChange(boolean runOnChange) {
            
            this.runOnChange = runOnChange;
            return this;
        }
        
        public Builder withFailOnError(boolean failOnError) {
            
            this.failOnError = failOnError;
            return this;
        }
        
        public Builder withAuthor(String author) {
            
            this.author = author;
            return this;
        }
        
        public Builder withBean(Object bean) {
            
            this.bean = bean;
            return this;
        }
        
        public MigrationProperties build() {
            
            return new MigrationProperties(this);
        }
    }
}