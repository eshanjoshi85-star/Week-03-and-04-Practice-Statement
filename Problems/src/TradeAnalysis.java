class Trade {
    int id;
    int volume;

    Trade(int id, int volume) {
        this.id = id;
        this.volume = volume;
    }
}

public class TradeAnalysis {

    // ================= MERGE SORT (ASCENDING, STABLE) =================
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(Trade[] arr, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].volume <= arr[j].volume) { // stable
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

    // ================= QUICK SORT (DESCENDING, IN-PLACE) =================
    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume; // Lomuto pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) { // DESC order
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Trade[] arr, int i, int j) {
        Trade temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ================= MERGE TWO SORTED LISTS =================
    public static Trade[] mergeLists(Trade[] a, Trade[] b) {
        Trade[] result = new Trade[a.length + b.length];

        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // ================= TOTAL VOLUME =================
    public static int totalVolume(Trade[] trades) {
        int sum = 0;
        for (Trade t : trades) {
            sum += t.volume;
        }
        return sum;
    }

    // ================= PRINT HELPER =================
    public static void printTrades(Trade[] trades) {
        for (Trade t : trades) {
            System.out.print("[" + t.id + ":" + t.volume + "] ");
        }
        System.out.println();
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        Trade[] trades = {
                new Trade(3, 500),
                new Trade(1, 100),
                new Trade(2, 300)
        };

        // Merge Sort (Ascending)
        mergeSort(trades, 0, trades.length - 1);
        System.out.print("Merge Sort (Asc): ");
        printTrades(trades);

        // Quick Sort (Descending)
        quickSort(trades, 0, trades.length - 1);
        System.out.print("Quick Sort (Desc): ");
        printTrades(trades);

        // Merge Two Sorted Lists (Morning + Afternoon)
        Trade[] morning = {
                new Trade(1, 100),
                new Trade(2, 300)
        };

        Trade[] afternoon = {
                new Trade(3, 500)
        };

        Trade[] merged = mergeLists(morning, afternoon);
        System.out.print("Merged Trades: ");
        printTrades(merged);

        // Total Volume
        int total = totalVolume(merged);
        System.out.println("Total Volume: " + total);
    }
}