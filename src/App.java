import java.math.BigInteger;
import java.io.File;
public class App{
    public static void main(String[] args){
        BigInteger inizio, fine, fineHalf;
        Input gesInput = new Input();
        String dir = "tmp/";

        File tempDir = new File(dir);
        File temp1 = new File("tmp/numprimi1.txt"); //Vengono creati/aperti i file temporanei
        File temp2 = new File("tmp/numprimi2.txt");

        if(!tempDir.exists()){ //Viene creata la cartella temporanea
            tempDir.mkdirs();
        }

        System.out.print("Inserisci il primo numero: "); //Input
        inizio = gesInput.inputBigInteger();
        System.out.print("Inserisci il secondo numero: ");
        fine = gesInput.inputBigInteger();

        fineHalf = inizio.add(fine);
        fineHalf = fineHalf.divide(BigInteger.TWO); //Calcolo metà tra inizio e fine

        Calcolo primaMeta = new Calcolo(inizio, fineHalf, "tmp/numprimi1.txt"); //Creazione thread
        Calcolo secondaMeta = new Calcolo(fineHalf.add(BigInteger.ONE), fine, "tmp/numprimi2.txt");

        long tempoInizio = System.currentTimeMillis();

        primaMeta.start(); //Inizio thread
        secondaMeta.start();
        
        
        try{
            secondaMeta.join(); //Il main aspetta i thread
            primaMeta.join();
        }catch(Exception e){
            System.out.println("Errore nella join del thread");
        }
        
        try{ //Unione file temporanei
            FileMerge.unisciFile();
        }catch(Exception e){
            System.out.println("Errore nell'unione dei file temporanei");
        }

        temp1.delete(); //Cancellazione file temporanei
        temp2.delete();

        long tempoFine = System.currentTimeMillis();

        System.out.println("tempo di esecuzione: " + ((tempoFine - tempoInizio) / 1000F));
        System.out.println("Il risultato dell'operazione si trova su numeriprimi.txt");
    }
}
