import java.io.*;

public class Input {
    String temp;
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader tastiera = new BufferedReader(input);

    public int inputInteger(){
        int input_int = -1;
        boolean input_corretto;
        do{
            try{
                temp = tastiera.readLine();
                input_int = Integer.valueOf(temp).intValue();
                input_corretto = true;
            }catch(Exception e){
                System.out.println("Errore, riprova: ");
                input_corretto = false;
            }
        }while(input_corretto == false);
        return input_int;
    }
}
