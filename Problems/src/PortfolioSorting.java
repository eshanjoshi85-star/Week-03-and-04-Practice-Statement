import java.util.Random;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }
}

public class PortfolioSorting {

    // ================= MERGE SORT (ASCENDING, STABLE) =================
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            // Stable: <= keeps original order for ties
            if (arr[i].returnRate <= arr[j].returnRate) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }
    }

    // ================= QUICK SORT (HYBRID) =================
    private static final int INSERTION_SORT_THRESHOLD = 10;
    private static Random rand = new Random();

    public static void quickSort(Asset[] arr, int low, int high) {
        if (high - low + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, low, high);
            return;
        }

        if (low < high) {
            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high);

            int p = partition(arr, low, high);

            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    // ================= PARTITION =================
    private static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // ================= COMPARATOR =================
    // DESC returnRate, ASC volatility
    private static int compare(Asset a, Asset b) {
        if (a.returnRate != b.returnRate) {
            return Double.compare(b.returnRate, a.returnRate); // DESC
        }
        return Double.compare(a.volatility, b.volatility); // ASC
    }

    // ================= MEDIAN OF THREE =================
    private static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        Asset a = arr[low];
        Asset b = arr[mid];
        Asset c = arr[high];

        // Find median value index
        if (compare(a, b) < 0) {
            if (compare(b, c) < 0) return mid;
            else if (compare(a, c) < 0) return high;
            else return low;
        } else {
            if (compare(a, c) < 0) return low;
            else if (compare(b, c) < 0) return high;
            else return mid;
        }
    }

    // ================= INSERTION SORT =================
    private static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low && compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // ================= UTIL =================
    private static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printAssets(Asset[] arr) {
        for (Asset a : arr) {
            System.out.print("[" + a.name + ":" + a.returnRate + "%, vol=" + a.volatility + "] ");
        }
        System.out.println();
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 9),
                new Asset("GOOG", 15, 4)
        };

        // Merge Sort (Ascending returnRate)
        mergeSort(assets, 0, assets.length - 1);
        System.out.print("Merge Sort (Asc): ");
        printAssets(assets);

        // Quick Sort (Desc returnRate, Asc volatility)
        quickSort(assets, 0, assets.length - 1);
        System.out.print("Quick Sort (Desc + Volatility): ");
        printAssets(assets);
    }
}