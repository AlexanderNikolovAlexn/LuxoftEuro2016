package com.samodeika.filtering;

import com.samodeika.entity.Player;

import java.util.*;

/**
 * Created by Alexander Nikolov on 14.6.2016 ?..
 */
public class CollectionFilteringAndSorting {

    public static List<Player> filterCollectionByDate(List<Player> inputList, Date date) {
        List<Player> resultList = new LinkedList<>(inputList);
        for (Iterator<Player> iterator = resultList.iterator(); iterator.hasNext();) {
            Player player = iterator.next();
            if (player.getBirthDate().compareTo(date) <= 0) {
                // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }

        System.out.println("After filtering results are " + resultList.size());
        return resultList;
    }

    public static List<Player> customFilterCollectionByDate(List<Player> inputList, Date date) {
        List<Player> resultList = new ArrayList<>(inputList.size());
        for (Player player : inputList) {
            if(player.getBirthDate().compareTo(date) > 0) {
                resultList.add(player);
            }
        }

        System.out.println("After filtering results are " + resultList.size());
        return resultList;
    }

    public static void sortCollectionByDate(List<Player> inputList) {
        Collections.sort(inputList, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return (o1.getBirthDate().compareTo(o2.getBirthDate())) * (-1);
            }
        });
    }

    public static void customMergeSort(List<Player> inputList) {
        // input check
        if (inputList == null || inputList.isEmpty() || inputList.size() <= 1) {
            return;
        }

        int lenght = inputList.size();
        int firstLenght = lenght/2;
        int secondLenght = lenght - firstLenght;

        List<Player> first = new ArrayList<>(Collections.nCopies(firstLenght, null));
        List<Player> second = new ArrayList<>(Collections.nCopies(secondLenght, null));
        copyList(inputList, 0, first, 0, firstLenght);
        copyList(inputList, firstLenght, second, 0, secondLenght);

        //sort each half via recursion
        customMergeSort(first);
        customMergeSort(second);

        merge(first, second, inputList);

    }

    private static void merge(List<Player> first, List<Player> second, List<Player> result) {
        //Index Position in first array - starting with first element
        int iFirst = 0;

        //Index Position in second array - starting with first element
        int iSecond = 0;

        //Index Position in merged array - starting with first position
        int iMerged = 0;

        while (iFirst < first.size() && iSecond < second.size())
        {
            if (first.get(iFirst).getBirthDate().compareTo(second.get(iSecond).getBirthDate()) > 0)
            {
                result.set(iMerged, first.get(iFirst));
                iFirst++;
            }
            else
            {
                result.set(iMerged, second.get(iSecond));
                iSecond++;
            }
            iMerged++;
        }
        //copy remaining elements from both halves - each half will have already sorted elements
        copyList(first, iFirst, result, iMerged, first.size() - iFirst);
        copyList(second, iSecond, result, iMerged, second.size() - iSecond);
    }
    
    private static void copyList(List<Player> firstList, int srcPos, List<Player> destList, int destPos, int lenght) {
        for (int i = srcPos; i < srcPos + lenght; i++) {
            destList.set(destPos, firstList.get(i));
            destPos++;
        }
    }

}
