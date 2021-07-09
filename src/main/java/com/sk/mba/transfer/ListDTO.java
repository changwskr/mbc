package com.sk.mba.transfer;

import java.util.*;

/**
 *
 * 우리 시스템의 모든 List형식의 DTO의 최상위 class로 IListDTO의 기본 구현이다.
 */

public abstract class ListDTO extends DTO implements IListDTO
{

    /**
     * DTO를 담는 List
     */
    protected List elements;

    public ListDTO()
    {
        this.elements = new ArrayList();
    }

    /**
     * Method ListDTO.
     * @param elements
     */
    public ListDTO(List elements)
    {
        this.elements = elements;
    }

    public final IDTO get(int index)
    {
        return (IDTO)elements.get(index);
    }

    public final int size()
    {
        return elements.size();
    }

    public final Iterator iterator()
    {
        return elements.iterator();
    }

    public final void sort()
    {
        Collections.sort(elements);
    }

    public final void sort(Comparator c)
    {
        Collections.sort(elements, c);
    }

    public boolean add(IDTO object)
    {
        return elements.add(object);
    }

    public final boolean isEmpty()
    {
        return this.elements.isEmpty();
    }
}
