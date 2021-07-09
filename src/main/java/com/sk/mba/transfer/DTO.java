package com.sk.mba.transfer;

import com.sk.fwk.transfer.IDTO;
import java.util.Map;
import java.util.HashMap;

/**
 * @author 장 우승
 * @version 1.0
 *
 * 우리 시스템의 DTO의 최상위 class로 모든 DTO가 구현해야 하는 공통 method를 구현한다.
 */
public abstract class DTO implements IDTO
{
    private Map dtoProperties;

    /**
     * @see java.lang.Object#Object()
     */
    public DTO()
    {
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return "Reflector.ojbectToString";
//        return Reflector.objectToString(this);

    }

    private Map getDTOProperties() {
        if (dtoProperties == null) {
            dtoProperties = new HashMap(2);
        }
        return dtoProperties;
    }

    public final void clearDTOProperty() {
        if (dtoProperties != null) {
            dtoProperties.clear();
        }
    }

    public final Object getDTOProperty(Object key) {
        if (dtoProperties == null) {
            return null;
        } else {
            return getDTOProperties().get(key);
        }
    }

    public final void putDTOProperty(Object key, Object value) {
        getDTOProperties().put(key, value);
    }
}


