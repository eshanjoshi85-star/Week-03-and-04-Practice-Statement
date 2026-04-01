import java.util.Arrays;

class AccountTransaction {
    String accountId;

    AccountTransaction(String accountId) {
        this.accountId = accountId;
    }
}

public class AccountLookup {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    // ================= LINEAR SEARCH =================

    public static int linearFirst(AccountTransaction[] arr, String target) {
        linearComparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].accountId.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static int linearLast(AccountTransaction[] arr, String target) {
        linearComparisons = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            linearComparisons++;
            if (arr[i].accountId.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    // ================= BINARY SEARCH =================

    public static int binarySearch(AccountTransaction[] arr, String target) {
        int low = 0, high = arr.length - 1;
        binaryComparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            binaryComparisons++;

            int cmp = arr[mid].accountId.compareTo(target);

            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // ================= FIRST OCCURRENCE (BINARY) =================

    public static int firstOccurrence(AccountTransaction[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            binaryComparisons++;

            int cmp = arr[mid].accountId.compareTo(target);

            if (cmp == 0) {
                result = mid;
                high = mid - 1; // move left
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // ================= LAST OCCURRENCE (BINARY) =================

    public static int lastOccurrence(AccountTransaction[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            binaryComparisons++;

            int cmp = arr[mid].accountId.compareTo(target);

            if (cmp == 0) {
                result = mid;
                low = mid + 1; // move right
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // ================= COUNT OCCURRENCES =================

    public static int countOccurrences(AccountTransaction[] arr, String target) {
        binaryComparisons = 0;

        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        AccountTransaction[] logs = {
                new AccountTransaction("accB"),
                new AccountTransaction("accA"),
                new AccountTransaction("accB"),
                new AccountTransaction("accC")
        };

        String target = "accB";

        // -------- LINEAR SEARCH --------
        int firstLin = linearFirst(logs, target);
        int firstLinComp = linearComparisons;

        int lastLin = linearLast(logs, target);
        int lastLinComp = linearComparisons;

        System.out.println("Linear First Occurrence: " + firstLin +
                " (comparisons=" + firstLinComp + ")");
        System.out.println("Linear Last Occurrence: " + lastLin +
                " (comparisons=" + lastLinComp + ")");

        // -------- SORT FOR BINARY SEARCH --------
        Arrays.sort(logs, (a, b) -> a.accountId.compareTo(b.accountId));

        System.out.print("\nSorted Logs: ");
        for (AccountTransaction t : logs) {
            System.out.print(t.accountId + " ");
        }
        System.out.println();

        // -------- BINARY SEARCH --------
        int index = binarySearch(logs, target);
        int comp = binaryComparisons;

        int count = countOccurrences(logs, target);

        System.out.println("\nBinary Search Index: " + index +
                " (comparisons=" + comp + ")");
        System.out.println("Count of " + target + ": " + count);
    }
}
