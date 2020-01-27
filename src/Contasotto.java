package src;

public class Contasotto extends Thread {
    private int tempo;
    Contasotto(int tempo){
        this.tempo=tempo;
    }
    @Override
    public void run() {
        for (int c=tempo/1000;c>0;c--){
            try {
                sleep(1000);
                System.out.print("Mancano:"+c+"secondi\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
