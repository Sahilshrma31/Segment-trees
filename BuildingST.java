public class BuildingST {

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

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7,8};
        int n = arr.length;

        init(n);
        BuildST(arr, 0, 0, n - 1);

        for (int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
    }
}
