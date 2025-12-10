public class MaxElement {
    static int tree[];

    public static void init(int n) {
        tree = new int[4 * n];
    }

    public static void buildST(int arr[], int i, int si, int sj) {
        // Base case: leaf node represents a single element
        if (si == sj) {
            tree[i] = arr[si];
            return;
        }
        int mid = (si + sj) / 2;
        // Build left and right subtrees
        buildST(arr, 2 * i + 1, si, mid);
        buildST(arr, 2 * i + 2, mid + 1, sj);
        // Internal node stores maximum of its children
        tree[i] = Math.max(tree[2 * i + 1], tree[2 * i + 2]);
    }

    public static int getQuery(int arr[], int qi, int qj) {
        int n = arr.length;
        return getQueryUtil(arr, 0, 0, n - 1, qi, qj);
    }

    public static int getQueryUtil(int arr[], int i, int si, int sj, int qi, int qj) {
        // no overlap
        if (qi > sj || qj < si) {
            return Integer.MIN_VALUE;
        }
        // complete overlap
        else if (qi <= si && sj <= qj) {
            return tree[i];
        }
        // partial overlap
        else {
            int mid = (si + sj) / 2;
            int left = getQueryUtil(arr, 2 * i + 1, si, mid, qi, qj);
            int right = getQueryUtil(arr, 2 * i + 2, mid + 1, sj, qi, qj);
            return Math.max(left, right);
        }
    }

    public static void update(int arr[], int idx, int newval) {
        int n = arr.length;
        arr[idx] = newval; // update original array
        updateUtil(idx, newval, 0, 0, n - 1);
    }

    public static void updateUtil(int idx, int newval, int i, int si, int sj) {
        // outside range
        if (idx < si || idx > sj) {
            return;
        }

        // leaf node (exact index)
        if (si == sj) {
            tree[i] = newval;   // directly set new value
            return;
        }

        int mid = (si + sj) / 2;

        // go down to both children (only one will actually contain idx)
        updateUtil(idx, newval, 2 * i + 1, si, mid);
        updateUtil(idx, newval, 2 * i + 2, mid + 1, sj);

        // after children are updated, recompute this node
        tree[i] = Math.max(tree[2 * i + 1], tree[2 * i + 2]);  // recompute from children
    }

    public static void main(String[] args) {
        int arr[] = {6, 8, -1, 2, 17, 1, 3, 2, 4};
        int n = arr.length;

        init(n);
        buildST(arr, 0, 0, n - 1);

        System.out.println(getQuery(arr, 2, 5)); // before update

        update(arr, 2, 20); // point update

        System.out.println(getQuery(arr, 2, 5)); // after update
    }
}
