package com.example.geektrust.balance;

import com.example.geektrust.GenericManager;
import com.example.geektrust.Main;
import com.example.geektrust.entity.CurrentLoan;
import com.example.geektrust.features.CommandExecutable;

public class BalanceManager extends GenericManager implements CommandExecutable {
    public BalanceManager(String[] inputSplit) {
        super(inputSplit);
        inputBank = inputSplit[1];
        inputBorrower = inputSplit[2];
        inputEmiPaid = Integer.parseInt(inputSplit[3]);
    }

    @Override
    public void execute() {
        // input EMI paid -> remaining amount -> remaining number of EMIs
        CurrentLoan currentLoan = Main.userLoan.get(inputBorrower);
        int extraAmountPaid = currentLoan.amountPaidUpto(inputEmiPaid);
        int amountPaid = inputEmiPaid * currentLoan.monthlyEMI + extraAmountPaid;
        int remainingEMIs = (int)Math.ceil((currentLoan.total - amountPaid) / currentLoan.monthlyEMI * 1.0D);
        System.out.printf("%s %s %d %d%n", inputBank, inputBorrower, amountPaid, remainingEMIs);
    }
}