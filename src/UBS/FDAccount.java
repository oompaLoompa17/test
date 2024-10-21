package UBS;

public class FDAccount extends BankAccount{
    
    private float interest = 1.03f;
    private int months = 6;


    public FDAccount (String name, Float accBal){
        super(name);
        super(accBal);
    }

    public FDAccount (String name, Float accBal, Float interest){

    }

    public FDAccount (String name, Float accBal, Float interest, Integer duration){

    }

    @Override 
    public void withdraw(){}
    @Override 
    public void deposit(){}
    @Override
    public void getAccBal(){

    }

}
