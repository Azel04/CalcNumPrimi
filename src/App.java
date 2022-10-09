import java.math.BigInteger;
import java.io.File;
public class App{
    public static void main(String[] args){
        BigInteger inizio, fine, fineHalf, fineQuarter, fineThreeQuarter, intervallo;
        Input gesInput = new Input();
        String dir = "tmp/";
        //BigInteger THREE = new BigInteger("3");
        File tempDir = new File(dir);

        File temp1 = new File("tmp/numprimi1.txt"); //Vengono creati/aperti i file temporanei
        File temp2 = new File("tmp/numprimi2.txt");
        File temp3 = new File("tmp/numprimi3.txt"); //Vengono creati/aperti i file temporanei
        File temp4 = new File("tmp/numprimi4.txt");

        if(!tempDir.exists()){ //Viene creata la cartella temporanea
            tempDir.mkdirs();
        }

        System.out.print("Inserisci il primo numero: "); //Input
        inizio = gesInput.inputBigInteger();
        System.out.print("Inserisci il secondo numero: ");
        fine = gesInput.inputBigInteger();

        fineHalf = inizio.add(fine);
        fineHalf = fineHalf.divide(BigInteger.TWO); //Calcolo metà tra inizio e fine

        fineQuarter = inizio.add(fineHalf);
        fineQuarter = fineQuarter.divide(BigInteger.TWO); //Calcolo metà tra inizio e metà tra inizio e fine (essenzialmente 1/4 di quello che deve andare a trovare)

        intervallo = fineHalf.subtract(fineQuarter); //calcola intervallo

        fineThreeQuarter = fineHalf.add(intervallo); //aggiunge intervallo alla metà tra inizio e fine

        /*
        Es. da 100000 a 200000
        la metà tra 100000 e 200000 è 150000
        
        poi si va ad aggiungere 100000 a 150000 e si ottiene 250000
        250000 diviso 2 fa 125000

        150000 - 125000 è 25000

        150000 + 25000 e 175000

        ora il carico sarà diviso in 4 parti

        da 100000 a 125000; da 125001 a 150000; da 150001 a 175000; da 175001 a 200000

        se stai leggendo questo, si mi sono fatto tante pippe mentali per raggiungere questa roba, fa molto schifo ma funziona quindi sticazzi
        */

        Calcolo primaMeta = new Calcolo(inizio, fineQuarter, "tmp/numprimi1.txt"); //Creazione thread
        Calcolo secondaMeta = new Calcolo(fineQuarter.add(BigInteger.ONE), fineHalf, "tmp/numprimi2.txt");
        Calcolo terzaMeta = new Calcolo(fineHalf.add(BigInteger.ONE), fineThreeQuarter, "tmp/numprimi3.txt"); //Creazione thread
        Calcolo quartaMeta = new Calcolo(fineThreeQuarter.add(BigInteger.ONE), fine, "tmp/numprimi4.txt");

        long tempoInizio = System.currentTimeMillis();

        primaMeta.start(); //Inizio thread
        secondaMeta.start();
        terzaMeta.start(); 
        quartaMeta.start();
        
        
        try{
            secondaMeta.join(); //Il main aspetta i thread
            primaMeta.join();
            quartaMeta.join(); //Il main aspetta i thread
            terzaMeta.join();
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

        temp3.delete(); //Cancellazione file temporanei
        temp4.delete();

        long tempoFine = System.currentTimeMillis();

        System.out.println("tempo di esecuzione: " + ((tempoFine - tempoInizio) / 1000F));
        System.out.println("Il risultato dell'operazione si trova su numeriprimi.txt");
    }
}
