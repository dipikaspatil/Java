## Array

### 1. Declaration & Initialization

#### 1D array
int[] arr = new int[5];                // size 5, default 0
int[] arr2 = {1,2,3,4,5};             // initialize with values

#### 2D array
int[][] matrix = new int[3][4];       // 3 rows, 4 cols
int[][] grid = {{1,2,3},{4,5,6},{7,8,9}}; 

#### ArrayList (dynamic array)
ArrayList<Integer> list = new ArrayList<>();
list.add(10);      // append
list.get(0);       // access
list.set(0, 20);   // modify
list.size();       // length

### 2. Traversal

#### 1D array
for(int i = 0; i < arr.length; i++)
    System.out.println(arr[i]);

for(int num : arr)
    System.out.println(num);

#### 2D array
for(int i = 0; i < matrix.length; i++){
    for(int j = 0; j < matrix[0].length; j++)
        System.out.print(matrix[i][j] + " ");
    System.out.println();
}

### 3. Common Operations
import java.util.Arrays;

#### Sorting
Arrays.sort(arr);                   // ascending
Arrays.sort(arr, Collections.reverseOrder()); // descending (Integer[])

#### Searching
int idx = Arrays.binarySearch(arr, 5); // index or negative if not found

#### Copy / Clone
int[] copy = arr.clone();
int[] copy2 = Arrays.copyOf(arr, arr.length);

#### Fill / Initialize
Arrays.fill(arr, 7);                 // fill all with 7

#### Compare
boolean eq = Arrays.equals(arr, arr2);

#### Print
System.out.println(Arrays.toString(arr));
System.out.println(Arrays.deepToString(matrix)); // for 2D

### 4. Prefix Sum & Sliding Window

#### Prefix sum
int[] prefix = new int[arr.length];
prefix[0] = arr[0];
for(int i = 1; i < arr.length; i++)
    prefix[i] = prefix[i-1] + arr[i];

#### Sliding window sum of size k
int k = 3;
int windowSum = 0;
for(int i = 0; i < k; i++)
    windowSum += arr[i];
int maxSum = windowSum;
for(int i = k; i < arr.length; i++){
    windowSum += arr[i] - arr[i-k];
    maxSum = Math.max(maxSum, windowSum);
}

### 5. Two Pointers & Reverse

#### Reverse 1D array
int left = 0, right = arr.length-1;
while(left < right){
    int temp = arr[left]; arr[left++] = arr[right]; arr[right--] = temp;
}

#### Two pointers example (sorted array sum)
int target = 10;
int i = 0, j = arr.length-1;
while(i < j){
    int sum = arr[i] + arr[j];
    if(sum == target) break;
    else if(sum < target) i++;
    else j--;
}

### 6. Rotate Array

#### Rotate right k steps
k = k % arr.length;
reverse(arr, 0, arr.length-1);
reverse(arr, 0, k-1);
reverse(arr, k, arr.length-1);

void reverse(int[] a, int start, int end){
    while(start < end){
        int t = a[start]; a[start++] = a[end]; a[end--] = t;
    }
}
