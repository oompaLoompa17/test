package Bank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class BankAccount {
    
    // members of class
    private final String NAME;
    private final String ACC_NO;
    private float accBal;
    private ArrayList<String> trx;
    private boolean isClosed;
    private final LocalDate DATE_OPEN;
    private LocalDate dateClosed;

    Random rand = new Random();

    // Constructor
    public BankAccount(String NAME){
        this.NAME = NAME;
        this.ACC_NO = Integer.toString(rand.nextInt(10000));
        this.accBal = 0;
        this.DATE_OPEN = LocalDate.now();
    }
    public BankAccount(String NAME, float accBal){
        this.NAME = NAME;
        this.ACC_NO = Integer.toString(rand.nextInt(10000));
        this.accBal = accBal;
        this.DATE_OPEN = LocalDate.now();
    }

    // methods
    // deposit - any deposit should be reflected in the txn list; look out for 
    public void deposit(float amt) {
        if (amt <= 0 || this.isClosed == true){
            throw new IllegalArgumentException("Please key in a positive amount.\n");
        } else {
            this.accBal += amt;
            String formattedOutput = String.format("Deposited $%f at <%s>\n", amt, LocalDateTime.now());
            System.out.println(formattedOutput);
            trx.add(formattedOutput);
        }
    }

    // withdraw - any withdrawal should be reflected in the txn list; 
    public void withdraw(float amt){
        if (amt <= 0 ||  amt > this.accBal || this.isClosed == true){
            throw new IllegalArgumentException("Please key in a positive amount.\n");
        } else {
            this.accBal -= amt;
            String formattedOutput = String.format("Withdrew $%f at <%s>\n", amt, LocalDateTime.now());
            System.out.println(formattedOutput);
            trx.add(formattedOutput);
        }
    }


    // G+S
    public String getNAME() {return NAME;}

    public String getACC_NO() {return ACC_NO;}

    public float getAccBal() {return accBal;}
    public void setAccBal(float accBal) {}

    public ArrayList<String> getTrx() {return trx;}
    public void setTrx(ArrayList<String> trx) {this.trx = trx;}

    public LocalDate getDATE_OPEN() {return DATE_OPEN;}

    public boolean isIsClosed() {return isClosed;}
    public void setIsClosed(boolean isClosed) {this.isClosed = isClosed;}




    
}
