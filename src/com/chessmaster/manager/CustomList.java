package com.chessmaster.manager;

@SuppressWarnings("unchecked")
public class CustomList<T>{

    private int initialCapacity =10;
    private T[] baseList;

    private int firstPositionIndex = 0;

    public CustomList(){
        this.baseList = (T[]) new  Object[initialCapacity];
    }

    public void add(T element){

        try {
            baseList[firstPositionIndex] = element;
        } catch (ArrayIndexOutOfBoundsException e){
            T[] tempList = copyList(baseList);
            initialCapacity = initialCapacity*2;
            this.baseList = (T[]) new  Object[initialCapacity];
            baseList = extendList(baseList,tempList);
            baseList[firstPositionIndex] = element;
        }
        finally {
            firstPositionIndex++;
        }

    }

    public T get(int index){
        return baseList[index];
    }

    private T[] extendList(T[] baseList, T[] extendedList) {
        T[] newList= copyList(baseList);

        System.arraycopy(extendedList, 0, newList, 0, extendedList.length);
        return newList;
    }

    private T[] copyList(T[] originalList) {
        T[] newList= (T[])new Object[originalList.length];

        System.arraycopy(originalList, 0, newList, 0, originalList.length);
        return newList;
    }

}
