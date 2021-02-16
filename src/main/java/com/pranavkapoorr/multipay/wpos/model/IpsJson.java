package com.pranavkapoorr.multipay.wpos.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Pranav
 *
 */
@JsonInclude(Include.NON_NULL)
public class IpsJson {
    @JsonProperty
    private String printFlag ;
    @JsonProperty
    private String operationType ;
    @JsonProperty
    private String amount;
    @JsonProperty
    private String pedIp;
    @JsonProperty
    private String pedPort;
    @JsonProperty
    private String statusMessageIp;
    @JsonProperty
    private String GTbit;
    @JsonProperty
    private String statusMessagePort;
    @JsonProperty
    private String transactionReference;
    @JsonProperty
    private String timeOut;
    @JsonProperty
    private String wait4CardRemoved;
	
	public IpsJson() {
	//empty Constructor
	}
	
    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPedIp(String pedIp) {
        this.pedIp = pedIp;
    }

    public void setPedPort(String pedPort) {
        this.pedPort = pedPort;
    }

    public void setStatusMessageIp(String statusMessageIp) {
        this.statusMessageIp = statusMessageIp;
    }

    public void setGTbit(String gTbit) {
        GTbit = gTbit;
    }

    public void setStatusMessagePort(String statusMessagePort) {
        this.statusMessagePort = statusMessagePort;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public void setWait4CardRemoved(String wait4CardRemoved) {
        this.wait4CardRemoved = wait4CardRemoved;
    }

	public String getPrintFlag() {
		return printFlag;
	}

	public String getOperationType() {
		return operationType;
	}

	public String getAmount() {
		return amount;
	}

	public String getPedIp() {
		return pedIp;
	}

	public String getPedPort() {
		return pedPort;
	}

	public String getStatusMessageIp() {
		return statusMessageIp;
	}

	public String getGTbit() {
		return GTbit;
	}

	public String getStatusMessagePort() {
		return statusMessagePort;
	}

	public String getTransactionReference() {
		return transactionReference;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public String getWait4CardRemoved() {
		return wait4CardRemoved;
	}
	
}
