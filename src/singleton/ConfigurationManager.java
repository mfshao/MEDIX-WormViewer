
package singleton;

import object.Configuration;
import object.DVConfiguration;

/**
 *
 * @author mshao1
 */


public class ConfigurationManager {
    private static ConfigurationManager configurationManager =  null;
    
    private Configuration configuration = null;
    private DVConfiguration dvConfiguration = null;
    
    static {
        configurationManager = new ConfigurationManager();
    }
    
    private ConfigurationManager() {
        configuration =  new Configuration();
        dvConfiguration = new DVConfiguration();
    }
    
    public final static ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }
    
    public final Configuration getConfiguration() {
        return configuration;
    }
    
    public final DVConfiguration getDVConfiguration() {
        return dvConfiguration;
    }
}
