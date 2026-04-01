import java.util.*;

class FeeTransaction {
    String id;
    double fee;
    String timestamp; // HH:mm format

    public FeeTransaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionSorter {

    // Bubble Sort (Stable)
    public static void bubbleSort(List<FeeTransaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // swap
                    FeeTransaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            // Early termination
            if (!swapped) break;
        }

        System.out.println("Bubble Sort Result: " + list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // Insertion Sort (Stable)
    public static void insertionSort(List<FeeTransaction> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            FeeTransaction key = list.get(i);
            int j = i - 1;

            // Sort by fee, then timestamp
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort Result: " + list);
    }

    // Comparator: fee first, then timestamp
    private static int compare(FeeTransaction t1, FeeTransaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    // High-fee outliers
    public static void findOutliers(List<FeeTransaction> list) {
        System.out.print("High-fee outliers (>50): ");
        boolean found = false;

        for (FeeTransaction t : list) {
            if (t.fee > 50) {
                System.out.print(t + " ");
                found = true;
            }
        }

        if (!found) {
            System.out.print("None");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<FeeTransaction> transactions = new ArrayList<>();

        transactions.add(new FeeTransaction("id1", 10.5, "10:00"));
        transactions.add(new FeeTransaction("id2", 25.0, "09:30"));
        transactions.add(new FeeTransaction("id3", 5.0, "10:15"));

        // Choose algorithm based on size
        if (transactions.size() <= 100) {
            bubbleSort(transactions);
        } else if (transactions.size() <= 1000) {
            insertionSort(transactions);
        }

        findOutliers(transactions);
    }
}
