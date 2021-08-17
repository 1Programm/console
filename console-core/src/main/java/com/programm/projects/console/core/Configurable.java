package com.programm.projects.console.core;

public interface Configurable {

    /**
     * Method to restore default configurations which are specified for this configuration.
     * @return Returns a reference to itself for method-chaining.
     */
    Configurable restoreConfig();

    /**
     * Method to configure this object by assigning a value to a key.
     * @param key The key to identify the configuration.
     * @param value The value to assign for that configuration.
     * @return Returns a reference to itself for method-chaining.
     * @throws IllegalArgumentException If the key expected a different type of value.
     */
    Configurable setConfig(Object key, Object value);

}
