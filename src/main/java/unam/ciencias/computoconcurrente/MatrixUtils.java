package unam.ciencias.computoconcurrente;

public class MatrixUtils implements Runnable {
    /** Numero de hilos */
    private int threads;
    /** Identificador de hilo */
    private int id;
    /** Arreglo de sumas */
    private static double[] sums;
    /** Matriz */
    private static int[][] matrixGlobal; 

    public MatrixUtils() {
        this.threads = 1;
    }

    public MatrixUtils(int threads) {
        this.threads = threads;
    }

    @Override
    public void run() {
        int sectionLen = matrixGlobal.length / threads;
        int sectionInd = sectionLen * id;
        int maxVal = sectionInd + sectionLen;
        double sum = 0.0;

        // Si es el ultimo thread termina lo que falte
        // Si eres el ultimo y tu seccion es mayor, truncala
        if (id == threads-1 || maxVal > matrixGlobal.length)
            maxVal = matrixGlobal.length;

        for (int i=sectionInd; i < maxVal; i++)
            for (int j=0; j < matrixGlobal[i].length; j++)
                sum += matrixGlobal[i][j];

        // Supones que, por ser una matriz, todas las filas tienen
        // el mismo tamaño
        double rowLen = matrixGlobal[0].length;
        sums[id] = sum / ((maxVal - sectionInd) * rowLen);
    }

    /**
     * Metodo que recorre una matriz de dos dimensiones 
     * @param matrix - matriz de dos dimensiones 
     * @return promedio - promedio de la matriz
     */
    public double findAverage(int[][] matrix) throws InterruptedException {
        int totalThreads = matrix.length < threads ? matrix.length : threads;
        Thread[] t = new Thread[totalThreads];
        sums = new double[totalThreads];
        matrixGlobal = matrix;
        double total = 0;

        for (int i=0; i<totalThreads; i++) {
            MatrixUtils m = new MatrixUtils(totalThreads);
            m.id = i;
            t[i] = new Thread(m);
            t[i].start();
        }

        for (Thread ti : t) ti.join();

        for (double s : sums) total += s;
        return total / sums.length;
    }

    /**
     * Metodo que recorre una matriz de dos dimensiones 
     * @param matrix - matriz de dos dimensiones 
     * @return promedio - promedio de la matriz
     */
    public double Average(int[][] matrix) {
        return 1.0;
    }
}
