package com.sk.mba.transfer;

import java.util.*;

/**
 *
 * 우리 시스템에서 사용하는 Collection을 표현하는 tpye
 *
 */

public interface IListDTO extends IDTO
{

    /**
     * DTO를 추가한다.<br>
     * 자세한 사항은 java.util.List의 add(Object) method를 참조하라.<br>
     *
     * @param idto
     * @return boolean
     */
    public abstract boolean add(IDTO idto);

    /**
     * 해당 index의 DTO를 반환한다.<br>
     * 나머지는 java.util.List의 get(int) method를 참조하라.<br>
     * @param i
     * @return IDTO
     */
    public abstract IDTO get(int i);

    /**
     * 현재 ListDTO의 element의 갯수를 구한다.<br>
     * 나머지는 java.util.List의 size() method를 참조하라.<br>
     *
     * @return int
     */
    public abstract int size();

    /**
     * 현재 ListDTO의 iterator를 리턴한다.<br>
     * 나머지는 java.util.List의 interator() method를 참조하라.<br>
     * @return Iterator
     */
    public abstract Iterator iterator();

    /**
     * 현재 ListDTO의 element를 ListDTO가 담고 있는 DTO의
     * natural order에 따라 오름차순으로 정렬한다.<br>
     * 자세한 사항은 java.util.Collections의 sort(List) method를 참조하라.<br>
     *
     */
    public abstract void sort();

    /**
     * 현재 ListDTO의 element를 comparator로
     * 정의한 순서에 따라 오름차순으로 정렬한다.<br>
     * 자세한 사항은 java.util.Collections의 sort(List, Comparator) method를 참조하라.<br>
     *
     * @param comparator 정렬 순서를 정의한 comparator
     *
     */
    public abstract void sort(Comparator comparator);

    /**
     * 현재 ListDTO가 element가 있는지 확인한다.
     * 만약 element가 없으면 참을 리턴한다.<br>
     */
    public boolean isEmpty();
}
