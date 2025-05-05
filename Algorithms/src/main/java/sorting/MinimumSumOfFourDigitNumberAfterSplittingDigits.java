package sorting;
/*
You are given a positive integer num consisting of exactly four digits.
Split num into two new integers new1 and new2 by using the digits found in num.
Leading zeros are allowed in new1 and new2, and
all the digits found in num must be used.

For example, given num = 2932, you have the following digits:
 two 2's, one 9 and one 3. Some of the possible pairs [new1, new2]
 are [22, 93], [23, 92], [223, 9] and [2, 329].
Return the minimum possible sum of new1 and new2.



Example 1:

Input: num = 2932
Output: 52
Explanation: Some possible pairs [new1, new2] are [29, 23], [223, 9], etc.
The minimum sum can be obtained by the pair [29, 23]: 29 + 23 = 52.
Example 2:

Input: num = 4009
Output: 13
Explanation: Some possible pairs [new1, new2] are [0, 49], [490, 0], etc.
The minimum sum can be obtained by the pair [4, 9]: 4 + 9 = 13.
 */
public class MinimumSumOfFourDigitNumberAfterSplittingDigits {
    public static void main(String[] args) {
        int num = 2932;
        System.out.println(minimumSum(num)); // Output: 52
        num = 4009;
        System.out.println(minimumSum(num)); // Output: 13
    }

    private static int minimumSum(int num){
        int[] digits=new int[4];
        digits[0]=num/1000;
        digits[1]=(num/100)%10;
        digits[2]=(num/10)%10;
        digits[3]=num%10;
        mergeSort(digits,0,digits.length-1);
        int num1=0,num2=0;
        for(int i=0;i<digits.length;i++){
            if(i%2==0){
                num1=num1*10+digits[i];
            }else{
                num2=num2*10+digits[i];
            }
        }
        return num1+num2;
    }

    private static void mergeSort(int[] arr,int left,int right){
        if(left<right){
            int mid=left+(right-left)/2;
            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }

    private static void merge(int[] arr,int left,int mid,int right){
        int n1=mid-left+1;
        int n2=right-mid;
        int[] L=new int[n1];
        int[] R=new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr,mid+1, R, 0, n2);
        int i=0,j=0,k=left;
        while(i<n1 && j<n2){
            if(L[i]<=R[j]){
                arr[k++]=L[i++];
            }else{
                arr[k++]=R[j++];
            }
        }
        while(i<n1){
            arr[k++]=L[i++];
        }
        while(j<n2){
            arr[k++]=R[j++];
        }
        int num1=0,num2=0;
        for(int m=0;m<arr.length;m++){
            if(m%2==0){
                num1=num1*10+arr[m];
            }else{
                num2=num2*10+arr[m];
            }
        }
    }
}
