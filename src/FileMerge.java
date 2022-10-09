import java.io.*;
import java.math.BigInteger;
  
public class FileMerge {
    public static void unisciFile() throws IOException{
        // PrintWriter object for file3.txt
        PrintWriter filefinale = new PrintWriter("numprimi.txt"); //Creazione file finale
        BigInteger cont = BigInteger.ONE;
        // BufferedReader object for file1.txt
        BufferedReader FileTemp = new BufferedReader(new FileReader("tmp/numprimi1.txt")); //Viene aperto il primo file
          
        String line = FileTemp.readLine();
          
        //Viene copiato il file 1 su file finale
        while (line != null){
            filefinale.println(cont + ": " + line); //Scrive su file finale
            line = FileTemp.readLine(); //Legge riga del file
            cont = cont.add(BigInteger.ONE);
        }
        FileTemp.close();

        FileTemp = new BufferedReader(new FileReader("tmp/numprimi2.txt")); //Viene aperto il secondo file
          
        line = FileTemp.readLine();
          
        //Viene copiato il file 2 su file finale
        while(line != null)
        {
            filefinale.println(cont + ": " + line); //Scrive su file finale
            line = FileTemp.readLine(); //Legge riga del file
            cont = cont.add(BigInteger.ONE);
        }
          
        filefinale.flush(); //Viene pulito il buffer
          
        //Chiusura file
        FileTemp.close();

        FileTemp = new BufferedReader(new FileReader("tmp/numprimi3.txt")); //Viene aperto il secondo file
          
        line = FileTemp.readLine();
          
        //Viene copiato il file 2 su file finale
        while(line != null)
        {
            filefinale.println(cont + ": " + line); //Scrive su file finale
            line = FileTemp.readLine(); //Legge riga del file
            cont = cont.add(BigInteger.ONE);
        }

        FileTemp.close();

        FileTemp = new BufferedReader(new FileReader("tmp/numprimi4.txt")); //Viene aperto il secondo file
          
        line = FileTemp.readLine();
          
        //Viene copiato il file 2 su file finale
        while(line != null)
        {
            filefinale.println(cont + ": " + line); //Scrive su file finale
            line = FileTemp.readLine(); //Legge riga del file
            cont = cont.add(BigInteger.ONE);
        }
        FileTemp.close();
        filefinale.close();
    }
}
