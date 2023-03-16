package com.application.payment.service.strategy;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.Wallet;

import com.application.payment.controller.dto.Currency;

public class BitcoinPaymentStrategy implements PaymentStrategy {
    private NetworkParameters networkParameters;
    private Wallet senderWallet;

    public BitcoinPaymentStrategy(Wallet senderWallet) {
        this.networkParameters = TestNet3Params.get();
        this.senderWallet = senderWallet;
    }

    @Override
    public boolean processPayment(double amount, Currency currency) {
        try {
            // Convert the amount to satoshis
            long satoshis = (long) (amount * 1e8);

            // Replace with the receiver's Bitcoin address
            Address receiverAddress = Address.fromString(networkParameters, "2N2JD6wb56AfK4tfmM6PwdVmoYk2dCKf4Br");

            // Create a transaction
            Coin coinAmount = Coin.valueOf(satoshis);
            Transaction transaction = senderWallet.createSend(receiverAddress, coinAmount);

            // Sign and broadcast the transaction
            senderWallet.commitTx(transaction);

            System.out.println("Bitcoin payment sent: " + transaction.getTxId().toString());
            return true;
        } catch (Exception e) {
            System.err.println("Error processing Bitcoin payment: " + e.getMessage());
            return false;
        }
    }
}