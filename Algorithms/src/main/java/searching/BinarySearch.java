package searching;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<Integer>();

        int x = 10;
        int result = binarySearch((ArrayList<Integer>) arr, x);
        if (result == -1) {
            System.out.println("Element not present in array");
        } else {
            System.out.println("Element found at index " + result);
        }
    }

    // Returns index of x if it is present in arr[l..r], else return -1
    public static int binarySearch(ArrayList<Integer> arr, int target) {
        int low=0,high=arr.size()-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr.get(mid)==target){
                return mid;
            }
            else if(arr.get(mid)<target){
                low=mid+1;
            }
            else{
                high=mid-1;
            }

        }
        return low;
    }
}
