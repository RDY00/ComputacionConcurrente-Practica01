package unam.ciencias.computoconcurrente;
import java.lang.Math;
public class PrimeNumberCalculator implements Runnable{

    private int threads;
    private static int numPrimo;
    public static boolean result;
    public static int longitudSubInter; //Dividimos el intervalo [2,N-1] en this.threads cantidad de sub interbalos, uno por cada hilo
    public int numeroHilo;

    public PrimeNumberCalculator() {
        this.threads = 1;
    }

    public PrimeNumberCalculator(int threads) {
        this.threads = threads > 1 ? threads : 1;
    }
    

    public boolean isPrime(int n) throws InterruptedException{
        Thread[] hilos = new Thread[threads];
        if(n<0){
            this.numPrimo = -n;
        }
        else{
            this.numPrimo = n;
        }
        this.result = true;
        this.longitudSubInter = (int)((Math.sqrt(this.numPrimo)+1)/threads);
        for(int i = 0; i<threads; i++){
            PrimeNumberCalculator pnc = new PrimeNumberCalculator(threads);
            pnc.numeroHilo = i;
            hilos[i] = new Thread(pnc);
        }
        for(Thread t:hilos){
            t.start();
        }
        for (Thread t : hilos){
            t.join();
        }
        return this.result;
    }
    
    @Override
    public void run(){
        if(this.numPrimo == 0 || this.numPrimo == 1){
            this.result = false;
            return;
        }
        for(int i = 0; i<longitudSubInter; i++){
            int candidato = i+(this.longitudSubInter*this.numeroHilo);
            if(candidato<2)
                continue;
            if(this.numPrimo%candidato==0){
                this.result = false;
                return;
            }
        }
    }
}
