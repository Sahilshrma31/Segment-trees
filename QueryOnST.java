public class QueryOnST{

    static int tree[];

    public static void init(int n) {
        // 4*n is a safe size to store the entire segment tree
        tree = new int[4 * n];
    }

    public static int BuildST(int arr[], int sti, int start, int end) {

        // Base Case(Leaf node): single element segment
        if (start == end) {
            return tree[sti] = arr[start];
        }

        int mid = (start + end) / 2;
        // Left child -> 2*sti + 1 , range [start, mid]
        int l = BuildST(arr, 2 * sti + 1, start, mid);
        // Right child -> 2*sti + 2 , range [mid+1, end]
        int r = BuildST(arr, 2 * sti + 2, mid + 1, end);

        // Internal node stores sum of left and right segments
        return tree[sti] = l + r;
    }

    public static int getQuery(int arr[],int qi,int qj){
        int n=arr.length;
        return getQueryUtil(0,0,n-1,qi,qj);
    }

    public static int getQueryUtil(int i,int si,int sj,int qi,int qj){
        if(qi>=sj || si>=qj){ //no overlap
            return 0;
        }
        else if(qi<=si && sj<=qj ){ //full overlap or complete overlap
            return tree[i];
        }else{ //partial overlap
            int mid=(si+sj)/2;
            int l=getQueryUtil(2*i+1, si, mid, qi, qj);
            int r=getQueryUtil(2*i+2, mid+1, sj, qi, qj);
            return l+r;
        }
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7,8};
        int n = arr.length;

        init(n);
        BuildST(arr, 0, 0, n - 1);

        // for (int i = 0; i < tree.length; i++) {
        //     System.out.print(tree[i] + " ");
        // }

        System.out.println(getQuery(arr,2,5));
    }
}
