<%-- 
    Document   : SIMULATORx
    Created on : 06-Oct-2017, 10:56:09
    Author     : Pranav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IPS-LINK WEB INTERFACE</title>
        <style>
* {
    box-sizing: border-box;
}
body{
    background-color: rgb(153,153,153);
}
.header{
    margin-top: 0px;
    width:100%;
    background-color: white;
     overflow: hidden;
}
.header h2{
    color: #406b95;
    font-family: Impact, Charcoal, sans-serif;
}
.header img {
  float: right;
   max-width: 75%;
    height: auto;
  padding-right: 5px;
  
  padding-bottom: 0px;
}


.row::after {
    content: "";
    clear: both;
    display: block;
}
[class*="col-"] {
    float: left;
    padding: 5px;
}
html {
    font-family: "Lucida Sans", sans-serif;
}

.menu ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
}
.menu li {
    padding: 8px;
    margin-bottom: 7px;
    background-color: #33b5e5;
    color: #ffffff;
    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
}
.menu li:hover {
    background-color: #0099cc;
}
.aside {
    background-color: rgb(204,204,204);
    padding: 15px;
    color: #ffffff;
    text-align: center;
    font-size: 14px;
    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
    border-width:5px;  
    border-top-width: 1px;
    border-left-width: 1px;
    border-style: inset;
    border-top-color: #4e5357;
    border-left-color: #434547;
    border-right-color: white;
    border-bottom-color: whitesmoke;
    padding: 25px;
    margin: 10px;
}

.footer {
    width:100%;
    background-color: white;
    color: darkgrey;
    text-align: center;
    font-size: 13px;
    padding: 15px;
}
/* For desktop: */
.col-1 {width: 8.33%;}
.col-2 {width: 16.66%;}
.col-3 {width: 25%;}
.col-4 {width: 33.33%;}
.col-5 {width: 41.66%;}
.col-6 {width: 50%;}
.col-7 {width: 58.33%;}
.col-8 {width: 66.66%;}
.col-9 {width: 75%;}
.col-10 {width: 83.33%;}
.col-11 {width: 91.66%;}
.col-12 {width: 100%;}

.buttonsPanel,.detailsPanel{
     background-color: lightgrey;
    width: 480px;
    border-width:5px;  
    border-top-width: 1px;
    border-left-width: 1px;
    border-style: inset;
    border-top-color: #4e5357;
    border-left-color: #434547;
    border-right-color: white;
    border-bottom-color: whitesmoke;
    padding: 25px;
    margin: 10px;
}
.buttonX:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}
.buttonX{
    width: 189px;
     box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
    background-color: #4CAF50; /* Green */
    border-style: outset;
    border-width: 3px;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 13px;
    margin: 8px 8px;
    cursor: pointer;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}
.details label,input {
  display: inline-block;
  margin-bottom:10px;
}

#statusMessageField,#receiptMessageField{
    border-style: inset;
    border-width:5px;  
    border-top-width: 1px;
    border-left-width: 2px;
    border-style: inset;
    border-top-color: #4e5357;
    border-left-color: #434547;
    border-right-color: white;
    border-right-width: 1px;
    border-bottom-color: #959ca2;
}
   


@media only screen and (max-width: 768px) {
    /* For mobile phones: */
    [class*="col-"] {
        width: 100%;
    }
    .buttonsPanel,.detailsPanel{
      width: inherit;  
      font-size: 10px;
      margin: 0px;
      margin-bottom: 10px
    };
   #terminalIp {
    width: 1px;
    margin-bottom: 10px;
    background-color: yellow;
};
    
}
</style>
   
</head>
<body>

<div class="header">
   
         <img src="ipsx.png" width="170" height="110" ><h2>IPS-LINK POINT OF SALE</h2> 
    
</div>

<div class="row">

    <div class="col-2 menu">
      <ul>
        <li>IPS CLOUD INTERFACE</li>
        <li>easy to INTEGRATE</li>
        <li>easy to USE</li>
        <li>FAST</li>
      </ul>
    </div>

    <div class="col-5" >
     
        <div class="detailsPanel">
            <input  type="text" name="terminalIp" placeholder="TERMINAL IP" id="terminalIp"> <input type="text" name="terminalPort" placeholder="TERMINAL PORT" id="terminalPort"><br>
            <input  type="text" name="statusMessageIp" placeholder="SMESSAGE IP" id="statusMessageIp"> <input  type="text" name="statusMessagePort" placeholder="SMESSAGE PORT" id="statusMessagePort"><br>
             <input type="text" name="amount" placeholder="AMOUNT IN PENCE" id="amount">
       </div>
        
        <div class="buttonsPanel">
            <button type="button" class="buttonX" onclick="alert('Hello World!')">PAYMENT</button>
            <button type="button" class="buttonX" onclick="alert('Hello World!')">REVERSAL</button>
            <button type="button" class="buttonX" onclick="alert('Hello World!')">REFUND</button>
            <button type="button" class="buttonX" onclick="alert('Hello World!')">FIRST DLL</button>
            <button type="button" class="buttonX" onclick="alert('Hello World!')">X-Report</button>
            <button type="button" class="buttonX" onclick="alert('Hello World!')">Z-Report</button>
            <button type="button" class="buttonX" onclick="alert('Hello World!')">TERMINAL STATUS</button>
            <button type="button" class="buttonX" onclick="alert('Hello World!')">REPRINT RECEIPT</button>
        </div>
        
    </div>

    <div class="col-3 right">
      <div class="aside receiptPanel">
          <textarea id="statusMessageField" name="statusMessageField" rows="3" cols="30"></textarea>
          <textarea id="receiptMessageField" name="receiptMessageField" rows="25" cols="30"></textarea>
      </div>
</div>

</div>
<div class="footer">
  <p>IPS-INTER PRODUCT .</p>
</div>

</body>
</html>


