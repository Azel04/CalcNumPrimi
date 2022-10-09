import java.io.File;
import java.math.BigInteger;
import java.io.FileWriter;

public class Calcolo extends Thread{
    BigInteger inizio, fine;
    File File;
    File tempDir;
    FileWriter scritturaFile;
    public Calcolo(BigInteger inizio, BigInteger fine, String nomeFile){
        this.inizio = inizio;
        this.fine = fine;
        this.File = new File(nomeFile);

        try{
            this.scritturaFile = new FileWriter(nomeFile);
        }catch(Exception e){
            System.out.println("Errore nel file");
        }
        
    }
    @Override
        public void run(){
            BigInteger i, j, k;
            int primo = 1;
            primo = 1;
            //System.out.println("Da " + inizio + " a " + fine);
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
        }
}
