import java.io.File;
import java.math.BigInteger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class App {
    public static void main(String[] args) throws IOException{
        BigInteger resto;
        File file = new File("numprimi.txt");
        BigInteger inizio, fine;
        FileWriter scritturaFile = new FileWriter(file);

        inizio = new BigInteger("100000");
        fine = new BigInteger("200000");

        BigInteger i, j, k;

        int primo;

        primo = 1;


        long tempoInizio = System.currentTimeMillis();
        for(i = inizio; i.compareTo(fine) <= 0; i = i.add(BigInteger.ONE)){
            if(i.mod(BigInteger.TWO) != BigInteger.ZERO){
                /* 
                for(j = new BigInteger("3"); j.compareTo(fine.sqrt()) < 0; j = j.add(BigInteger.TWO)){
                    resto = i.mod(j);
                    //System.out.println("Resto divisione tra " + i + " e " + j + " è " + resto);
                    if(resto.compareTo(BigInteger.ZERO) == 0 && j.compareTo(i) != 0){
                        primo = 0;
                        break;
                    }
                }
                */
                
                j = BigInteger.TWO;
                k = j.modPow(i.subtract(BigInteger.ONE), i);
                if(!(k.compareTo(BigInteger.ONE) == 0)){
                    primo = 0;
                }
            }
            else{
                primo = 0;
            }
            if(primo == 1){
                try{
                    scritturaFile.write(i + "\n");
                    scritturaFile.flush();

                }catch(Exception e){
                    System.out.println("Errore scrittura file");
                    break;
                }
            }
            primo = 1;
        }

        long tempoFine = System.currentTimeMillis();

        System.out.println("tempo di esecuzione: " + ((tempoFine - tempoInizio) / 1000F) );
    }
}
