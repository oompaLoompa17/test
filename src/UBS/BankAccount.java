package UBS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class BankAccount {

    private final String name;
    private final String accNo;
    private float accBal;
    private ArrayList<String> ledger;
    private boolean isOpen; 
    private final LocalDateTime openDate;
    private LocalDateTime closeDate;

    public BankAccount(String name){
        this.name = name;
        this.accNo = randomNo();
        this.accBal = 0;
        this.openDate = LocalDateTime.now();
    }
    
    public BankAccount(String name, float accBal ){
        this.name = name;
        this.accNo = randomNo();
        this.accBal = accBal;
        this.openDate = LocalDateTime.now();
    }

    private String randomNo(){
        Random random = new Random();
        String stringNo = "";
        StringBuilder stringBuilder = new StringBuilder(9);

        for (int i=0; i < 9; i++){
            int no = random.nextInt(9);
            stringNo = String.valueOf(no);
            stringBuilder.append(stringNo);
        }
        stringNo = stringBuilder.toString();
        return stringNo;
    }

    public void deposit (float amt){
        if (amt <= 0 || !isOpen){
            throw new IllegalArgumentException("goddamn who u being funny for??");
        }else{
            this.accBal += amt;
            this.ledger.add("Deposit $" + amt + " at " + LocalDateTime.now());
        }
    }

    public void withdraw (float amt){
        if (amt <= 0 || amt > accBal || !isOpen){
            throw new IllegalArgumentException("goddamn who u being funny for??");
        }else{
            this.accBal -= amt;
            this.ledger.add("Withdraw $" + amt + " at " + LocalDateTime.now());
        }
    }

    //G+S
    public String getName() {return name;}
    public String getAccNo() {return accNo;}
    public float getAccBal() {return accBal;}
    public ArrayList<String> getLedger() {return ledger;}
    public boolean isIsOpen() {return isOpen;}
    public LocalDateTime getOpenDate() {return openDate;}
    public LocalDateTime getCloseDate() {return closeDate;}    

    public void setAccBal(float accBal) {this.accBal = accBal;}
    public void setLedger(ArrayList<String> ledger) {this.ledger = ledger;}
    public void setIsOpen(boolean isOpen) {this.isOpen = isOpen;}
    public void setCloseDate(LocalDateTime closeDate) {this.closeDate = closeDate;}
}
