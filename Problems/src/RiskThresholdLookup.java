public class RiskThresholdLookup {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    // ================= LINEAR SEARCH (UNSORTED) =================
    public static int linearSearch(int[] arr, int target) {
        linearComparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // ================= BINARY SEARCH (EXACT MATCH) =================
    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        binaryComparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            binaryComparisons++;

            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // ================= INSERTION POINT =================
    public static int insertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low; // position where target should be inserted
    }

    // ================= FLOOR (largest <= target) =================
    public static int floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            binaryComparisons++;

            if (arr[mid] <= target) {
                result = arr[mid];
                low = mid + 1; // try bigger
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // ================= CEILING (smallest >= target) =================
    public static int ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            binaryComparisons++;

            if (arr[mid] >= target) {
                result = arr[mid];
                high = mid - 1; // try smaller
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        int[] unsorted = {50, 10, 100, 25}; // unsorted
        int[] sorted = {10, 25, 50, 100};   // sorted

        int target = 30;

        // -------- LINEAR SEARCH --------
        int linIndex = linearSearch(unsorted, target);
        System.out.println("Linear Search Index: " + linIndex +
                " (comparisons=" + linearComparisons + ")");

        // -------- BINARY SEARCH --------
        int binIndex = binarySearch(sorted, target);
        System.out.println("Binary Search Index: " + binIndex +
                " (comparisons=" + binaryComparisons + ")");

        // -------- INSERTION POINT --------
        int insertPos = insertionPoint(sorted, target);
        System.out.println("Insertion Point for " + target + ": " + insertPos);

        // -------- FLOOR & CEILING --------
        binaryComparisons = 0;
        int floorVal = floor(sorted, target);

        binaryComparisons = 0;
        int ceilVal = ceiling(sorted, target);

        System.out.println("Floor(" + target + "): " + floorVal);
        System.out.println("Ceiling(" + target + "): " + ceilVal);
    }
}