package com.CrackCode.BarCodeQrCodeReadWrite.QrCode;

import lombok.Data;

@Data
public class PaymentRequest {
    private String userName;
    private String pin;
    private String mobileNo;
    private String accountType;
    private String accountNo;
}
