import java.util.Random;

public class Q6 {

    // QuickSort com o primeiro elemento como pivô
    public static void quickSortFirstPivot(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionFirstPivot(array, left, right);
            quickSortFirstPivot(array, left, pivotIndex - 1);
            quickSortFirstPivot(array, pivotIndex + 1, right);
        }
    }

    private static int partitionFirstPivot(int[] array, int left, int right) {
        int pivot = array[left];
        int i = left + 1;
        for (int j = left + 1; j <= right; j++) {
            if (array[j] < pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, left, i - 1);
        return i - 1;
    }

    // QuickSort com o último elemento como pivô
    public static void quickSortLastPivot(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionLastPivot(array, left, right);
            quickSortLastPivot(array, left, pivotIndex - 1);
            quickSortLastPivot(array, pivotIndex + 1, right);
        }
    }

    private static int partitionLastPivot(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (array[j] < pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, right);
        return i;
    }

    // QuickSort com pivô aleatório
    public static void quickSortRandomPivot(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionRandomPivot(array, left, right);
            quickSortRandomPivot(array, left, pivotIndex - 1);
            quickSortRandomPivot(array, pivotIndex + 1, right);
        }
    }

    private static int partitionRandomPivot(int[] array, int left, int right) {
        Random random = new Random();
        int randomPivot = left + random.nextInt(right - left + 1);
        swap(array, randomPivot, right);
        return partitionLastPivot(array, left, right);
    }

    // QuickSort com a mediana de três elementos como pivô
    public static void quickSortMedianOfThree(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionMedianOfThree(array, left, right);
            quickSortMedianOfThree(array, left, pivotIndex - 1);
            quickSortMedianOfThree(array, pivotIndex + 1, right);
        }
    }

    private static int partitionMedianOfThree(int[] array, int left, int right) {
        int middle = (left + right) / 2;
        int median = medianOfThree(array, left, middle, right);
        swap(array, median, right);
        return partitionLastPivot(array, left, right);
    }

    private static int medianOfThree(int[] array, int left, int middle, int right) {
        if ((array[left] > array[middle]) != (array[left] > array[right])) {
            return left;
        } else if ((array[middle] > array[left]) != (array[middle] > array[right])) {
            return middle;
        } else {
            return right;
        }
    }

    // Função de troca
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Função para medir o tempo de execução
    private static long measureTime(Runnable quickSortMethod) {
        long start = System.nanoTime();
        quickSortMethod.run();
        long end = System.nanoTime();
        return end - start;
    }

    // Função para gerar arrays
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 10); // Valores aleatórios
        }
        return array;
    }

    private static int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    private static int[] generateSortedArrayDecre(int size) {
        int[] array = new int[size];
        for (int i = 0, j = size; j > 0; j--, i++) {
            array[i] = j;
        }
        return array;
    }

    private static int[] generateNearlySortedArray(int size) {
        int[] array = generateSortedArray(size);
        Random random = new Random();
        for (int i = 0; i < size / 10; i++) {
            int index1 = random.nextInt(size);
            int index2 = random.nextInt(size);
            swap(array, index1, index2); // Pequenas desordens
        }
        return array;
    }

    private static int[] generateNearlySortedArrayDecre(int size) {
        int[] array = generateSortedArrayDecre(size);
        Random random = new Random();
        for (int i = 0; i < size / 10; i++) {
            int index1 = random.nextInt(size);
            int index2 = random.nextInt(size);
            swap(array, index1, index2); // Pequenas desordens
        }
        return array;
    }

    // Função principal para testar e medir os tempos
    public static void main(String[] args) {
        int[] sizes = { 100, 1000, 10000 };

        for (int size : sizes) {
            System.out.println("\nTamanho do array: " + size);

            int[] randomArray = generateRandomArray(size);
            int[] sortedArray = generateSortedArray(size);
            int[] sortedArrayDecre = generateSortedArrayDecre(size);
            int[] nearlySortedArray = generateNearlySortedArray(size);
            int[] nearlySortedArrayDecre = generateNearlySortedArrayDecre(size);

            // Teste para array aleatório
            System.out.println("Array Aleatório:");
            System.out.println("First Pivot: "
                    + measureTime(() -> quickSortFirstPivot(randomArray.clone(), 0, randomArray.length - 1)) + "ms");
            System.out.println("Last Pivot: "
                    + measureTime(() -> quickSortLastPivot(randomArray.clone(), 0, randomArray.length - 1)) + "ms");
            System.out.println("Random Pivot: "
                    + measureTime(() -> quickSortRandomPivot(randomArray.clone(), 0, randomArray.length - 1)) + "ms");
            System.out.println("Median of Three: "
                    + measureTime(() -> quickSortMedianOfThree(randomArray.clone(), 0, randomArray.length - 1)) + "ms");

            // Teste para array ordenado
            System.out.println("\nArray Ordenado:");
            System.out.println("First Pivot: "
                    + measureTime(() -> quickSortFirstPivot(sortedArray.clone(), 0, sortedArray.length - 1)) + "ms");
            System.out.println("Last Pivot: "
                    + measureTime(() -> quickSortLastPivot(sortedArray.clone(), 0, sortedArray.length - 1)) + "ms");
            System.out.println("Random Pivot: "
                    + measureTime(() -> quickSortRandomPivot(sortedArray.clone(), 0, sortedArray.length - 1)) + "ms");
            System.out.println("Median of Three: "
                    + measureTime(() -> quickSortMedianOfThree(sortedArray.clone(), 0, sortedArray.length - 1)) + "ms");

            System.out.println("\nArray Ordenado Decrescente:");
            System.out.println("First Pivot: "
                    + measureTime(() -> quickSortFirstPivot(sortedArrayDecre.clone(), 0, sortedArrayDecre.length - 1))
                    + "ms");
            System.out.println("Last Pivot: "
                    + measureTime(() -> quickSortLastPivot(sortedArrayDecre.clone(), 0, sortedArrayDecre.length - 1))
                    + "ms");
            System.out.println("Random Pivot: "
                    + measureTime(() -> quickSortRandomPivot(sortedArrayDecre.clone(), 0, sortedArrayDecre.length - 1))
                    + "ms");
            System.out.println("Median of Three: "
                    + measureTime(
                            () -> quickSortMedianOfThree(sortedArrayDecre.clone(), 0, sortedArrayDecre.length - 1))
                    + "ms");

            // Teste para array quase ordenado
            System.out.println("\nArray Quase Ordenado:");
            System.out.println("First Pivot: "
                    + measureTime(() -> quickSortFirstPivot(nearlySortedArray.clone(), 0, nearlySortedArray.length - 1))
                    + "ms");
            System.out.println("Last Pivot: "
                    + measureTime(() -> quickSortLastPivot(nearlySortedArray.clone(), 0, nearlySortedArray.length - 1))
                    + "ms");
            System.out.println("Random Pivot: "
                    + measureTime(
                            () -> quickSortRandomPivot(nearlySortedArray.clone(), 0, nearlySortedArray.length - 1))
                    + "ms");
            System.out.println("Median of Three: "
                    + measureTime(
                            () -> quickSortMedianOfThree(nearlySortedArray.clone(), 0, nearlySortedArray.length - 1))
                    + "ms");

            // Teste para array quase ordenado
            System.out.println("\nArray Quase Ordenado Decrescente:");
            System.out.println("First Pivot: "
                    + measureTime(() -> quickSortFirstPivot(nearlySortedArrayDecre.clone(), 0,
                            nearlySortedArrayDecre.length - 1))
                    + "ms");
            System.out.println("Last Pivot: "
                    + measureTime(() -> quickSortLastPivot(nearlySortedArrayDecre.clone(), 0,
                            nearlySortedArrayDecre.length - 1))
                    + "ms");
            System.out.println("Random Pivot: "
                    + measureTime(
                            () -> quickSortRandomPivot(nearlySortedArrayDecre.clone(), 0,
                                    nearlySortedArrayDecre.length - 1))
                    + "ms");
            System.out.println("Median of Three: "
                    + measureTime(
                            () -> quickSortMedianOfThree(nearlySortedArrayDecre.clone(), 0,
                                    nearlySortedArrayDecre.length - 1))
                    + "ms");
        }
    }
}
