package org.fancy.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * SafeStates
 * <p/>
 * Initialization safety for immutable objects
 *
 *线程安全：不可变成员变量初始化安全
 */
public class SafeStates {
	
    private final Map<String, String> states;

    public SafeStates() {
        states = new HashMap<String, String>();
        states.put("alaska", "AK");
        states.put("alabama", "AL");
        /*...*/
        states.put("wyoming", "WY");
    }

    public String getAbbreviation(String s) {
        return states.get(s);
    }
}
