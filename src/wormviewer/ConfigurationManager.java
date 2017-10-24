
package wormviewer;

/**
 *
 * @author mshao1
 */


public class ConfigurationManager {
    private static ConfigurationManager configurationManager =  null;
    
    private Configuration configuration = null;
    
    static {
        configurationManager = new ConfigurationManager();
    }
    
    private ConfigurationManager() {
        configuration =  new Configuration();
    }
    
    public final static ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }
    
    public final Configuration getConfiguration() {
        return configuration;
    }
}
