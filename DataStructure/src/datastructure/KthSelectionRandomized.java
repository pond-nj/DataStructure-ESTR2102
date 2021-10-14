/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructure;
import java.lang.Math;

/**
 *
 * @author nutdranai.jaruthikorn
 */


/**
 * 
 * Analysis of Algorithm and the algorithm itself is more important than implementation
 */

/**
 * Algorithm:
 * 
 */

/**
 * Analysis:

*/

//pivot.rank is index in range [1, lengthOfArray]
class pivot{
    int value;
    int rank;
    int lessThanPivotArray[];
    int lessThanPivotArrayLength;
    int moreThanPivotArray[];
    int moreThanPivotArrayLength;
    boolean validPivot = false;

    pivot( int x, int a, int[] b, int c , int[] d , int e){
        value = x;
        rank = a;
        lessThanPivotArray = b;
        lessThanPivotArrayLength = c;
        moreThanPivotArray = d;
        moreThanPivotArrayLength = e;
    }
    
    void printLessThanPivotArray( ){
        for( int i = 0 ; i < lessThanPivotArrayLength ; i++ ){
            System.out.print( lessThanPivotArray[i] + " " );
        }
        //System.out.println();
    }
    
    void printMoreThanPivotArray( ){
        for( int i = 0 ; i < moreThanPivotArrayLength ; i++ ){
            System.out.print( moreThanPivotArray[i] + " " );
        }
        //System.out.println();
    }
}


public class KthSelectionRandomized {
    

    
    private static pivot createPivot( int index, int[] A , int lengthOfA ){
        int[] lessThanPivot = new int[lengthOfA] ; //array of elements less than A[index]
        int[] moreThanPivot = new int[lengthOfA] ; //array of elements more than A[index]
        int lessThanPivotLength = 0;
        int moreThanPivotLength = 0;
        for( int i = 0 ; i < lengthOfA ; i++ ){
            if( A[i] < A[index] ){
                lessThanPivot[ lessThanPivotLength ] = A[ i ];
                lessThanPivotLength++;
            } else if ( A[i] > A[index] ){
                moreThanPivot[ moreThanPivotLength ] = A[ i ];
                moreThanPivotLength++;
            }
        }
        
        return new pivot( A[index] , lessThanPivotLength + 1 , lessThanPivot , lessThanPivotLength, moreThanPivot , moreThanPivotLength );
    }
    
    private static void validatePivot( pivot p , int lengthOfA ){
        int upperBoundIndex = (2*lengthOfA)/3;
        int lowerBoundIndex = lengthOfA/3;
        //System.out.println( "upperBound: " + upperBoundIndex + " , lowerBound: " + lowerBoundIndex );
        
        //p.rank is in [1, lengthOfArray]
        //upperBoundIndex and lowerBoundIndex is in [0, lengthOfArray -1]
        if( ( (p.rank - 1) <= upperBoundIndex ) && ( (p.rank - 1) >= lowerBoundIndex ) ){
             p.validPivot = true;
        }
    }
    
    public static int KthSelection( int k, int[] A , int lengthOfA ){
        
        int randomIndex;
        pivot p;
        while( true ){
            randomIndex = (int) ( Math.random() * lengthOfA );
            //System.out.println( "randomIndex: " + randomIndex );
            p = createPivot( randomIndex , A , lengthOfA );
            validatePivot( p , lengthOfA );
            if( p.validPivot ){
                break;
            }
        }
        
        //System.out.println( "p.rank is " + p.rank );
        if( p.rank == k ){
            return p.value;
        }else if ( p.rank > k ){
            /**
            System.out.print( "Looping into: ");
            p.printLessThanPivotArray();
            System.out.println( "with k = " + k );
            */
            return KthSelection( k , p.lessThanPivotArray , p.lessThanPivotArrayLength );
        }else {
            /**
            System.out.print( "Looping into: ");
            p.printMoreThanPivotArray();
            System.out.println( "with k = " + String.format("%d",k - p.rank) );
            */
            return KthSelection( k - p.rank , p.moreThanPivotArray , p.moreThanPivotArrayLength );
        }
        
        
        
    }
    
}
