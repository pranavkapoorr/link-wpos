package com.pranavkapoorr.multipay.wpos.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReceiptJson {
    @JsonProperty
    private String terminalId;
    @JsonProperty
    private String transactionStatus;
    @JsonProperty
    private String transactionStatusText;
   // @JsonProperty
   // private String aquirerCode;
    @JsonProperty
    private String STAN;
    @JsonProperty
    private String amount;
    @JsonProperty
    private String operationType;
    @JsonProperty
    private String transactionType;
    @JsonProperty
    private String aquirerId;
    //@JsonProperty
    //private String cardType;
    @JsonProperty
    private String cardPAN;
    @JsonProperty
    private String actionCode;
    @JsonProperty
    private String progressiveNumber;
    @JsonProperty
    private String authCode;
    @JsonProperty
    private String transactionDate;
    @JsonProperty
    private String transactionTime;
/*    @JsonProperty
    private String hostTotalAmount;
    @JsonProperty
    private String hostTotalAmountReqByHost;*/
    @JsonProperty
    private String cardPresentToken;
    @JsonProperty
    private String omniChannelToken;
    @JsonProperty
    private String omniChannelGUID;
    @JsonProperty
    private String pedDate;
    @JsonProperty
    private String pedTime;
    @JsonProperty
    private String pedStatus;
    @JsonProperty
    private String firmwareVersion;
    @JsonProperty
    private String partNumber;
    @JsonProperty
    private String serialNumber;
    @JsonProperty
    private String DccAmount;
    @JsonProperty
    private String DccCurrency;
    @JsonProperty
    private String DccCurrencyCode;
    @JsonProperty
    private String DccConversionRate;
    @JsonProperty
    private String DccTransactionAmount;
    @JsonProperty
    private String DccTransactionCurrencyDecimal;
    @JsonProperty
    private String signatureRequired; 
    @JsonProperty
    private String pedConnectivity;
    @JsonProperty
    private String gatewayConnectivity;
        

        @JsonProperty
        private String receipt;
        
        public ReceiptJson() {
            //empty Constructor
            }
       
        public String getReceipt(){
            return this.receipt;
        }


        /**shows receipt fields in receipt area of simulator**/
       public String getFormattedReceipt(){
           return ifNotNull(new String[]{terminalId, transactionStatus,transactionStatusText, STAN, amount, 
                   transactionType, aquirerId, operationType, cardPAN, actionCode, progressiveNumber, authCode, transactionDate,
                   transactionTime, cardPresentToken,omniChannelToken,omniChannelGUID,pedDate, pedTime ,pedStatus,
                   firmwareVersion, partNumber, serialNumber, DccAmount, DccCurrency, DccCurrencyCode, DccConversionRate, DccTransactionAmount,
                   DccTransactionCurrencyDecimal,pedConnectivity,gatewayConnectivity,  
                   (char)10 + receipt + (char)10 , signatureRequired});
       }
       /**formats the fields and remove the null values to show in simulato receipt area**/
       private String ifNotNull(String toDisplay[]){
           StringBuffer buffer = new StringBuffer();
           for(int i = 0; i < toDisplay.length ; i++){
              if(toDisplay[i] != null && !toDisplay[i].contains("null")){
                  buffer.append(toDisplay[i] +";");
              }
           }
           return buffer.toString();
    } 
}


