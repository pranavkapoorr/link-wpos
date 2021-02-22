window.onload=function(){
	if ("WebSocket" in window) {
		
		var connected = false;
		var ws = new WebSocket('ws://localhost:8080/ips-epos');
		var pedIp = document.getElementById('terminalIp');
		var pedPort = document.getElementById('terminalPort');
		var amount = document.getElementById('amount');
		var gtmessage = document.getElementById('GTmessage');
		var printFlag = document.getElementById('printFlag');
		var receiptField = document.getElementById('receiptMessageField');
		var statusMessageField = document.getElementById('statusMessageField');
		var payment = document.getElementById('payment');
		var reversal = document.getElementById('refund');
		var refund = document.getElementById('printFlag');
		var pedBalance = document.getElementById('pedBalance');
		var endofday = document.getElementById('endofday');
		var firstDll = document.getElementById('firstDll');
		var terminalStatus = document.getElementById('terminalStatus');
		var reprintReceipt = document.getElementById('reprintReceipt');
		var start = document.getElementById('start');
		var stop = document.getElementById('stop');
		var linkIp = document.getElementById('linkIp');
		var linkPort = document.getElementById('linkPort');
		ws.onopen = function(stat) {

			console.log('connection: '+stat.data);

			//ws.send('{"printFlag":"1","operationType":"PedBalance","pedIp":"192.168.86.199","pedPort":"40001","timeOut":"120"}');
		};

		ws.onmessage = function (evt) { 
			var received_msg = evt.data;
			//console.log('incoming> '+received_msg);
			if(received_msg.includes('statusMessage')){
				var msg = JSON.parse(received_msg);
				statusMessageField.innerHTML = msg.statusMessage;
			}else if(received_msg.includes('terminalId')){
				//var msg = JSON.parse(received_msg);
				receiptField.innerHTML = received_msg;
			}
			else if(received_msg.includes('Connected')){
				connected = true;
			}
			else if(received_msg.includes('Connection Failed')){
				connected = false;
			}
			else if(received_msg.includes('Connection Closed')){
				connected = false;
			}
			//receiptField.innerHTML = received_msg;
		};

		ws.onclose = function() { 


			console.log("Connection is closed..."); 
		};

		payment.addEventListener("click", function(e){ 
			if(connected){
				var msg = '{"printFlag":"' + printFlag.value +'","operationType":"Payment","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"90","amount":"'+ amount.value +'","gtbit":"1","transactionReference":"'+ gtmessage +'"}';
				ws.send(msg);
			}else{
				alert("start the connection then try again!")
			}
		});
		reversal.addEventListener("click", function(e){ 
			if(connected){
				var msg = '{"printFlag":"' + printFlag.value +'","operationType":"Refund","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"90","amount":"'+ amount.value +'","gtbit":"1","transactionReference":"'+ gtmessage +'"}';
				ws.send(msg);
			}else{
				alert("start the connection then try again!")
			}
			});
		refund.addEventListener("click", function(e){ 
			if(connected){
				var msg = '{"printFlag":"' + printFlag.value +'","operationType":"Reversal","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"90","gtbit":"1","transactionReference":"'+ gtmessage +'"}';
				ws.send(msg);
			}else{
				alert("start the connection then try again!")
			}
		});
		pedBalance.addEventListener("click", function(e){ 
			if(connected){
				var msg = '{"printFlag":"' + printFlag.value +'","operationType":"PedBalance","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"90"}';
				ws.send(msg);
			}else{
				alert("start the connection then try again!")
			}
		});
		endofday.addEventListener("click", function(e){ 
			if(connected){
				var msg = '{"printFlag":"' + printFlag.value +'","operationType":"EndOfDay","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"90"}';
				ws.send(msg);
			}else{
				alert("start the connection then try again!")
			}
		});
		terminalStatus.addEventListener("click", function(e){ 
			if(connected){
				var msg = '{"printFlag":"' + printFlag.value +'","operationType":"TerminalStatus","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"90"}';
				ws.send(msg);
			}else{
				alert("start the connection then try again!")
			}
		});
		firstDll.addEventListener("click", function(e){ 
			if(connected){
				var msg = '{"printFlag":"' + printFlag.value +'","operationType":"FirstDll","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"90"}';
				ws.send(msg);
			}else{
				alert("start the connection then try again!")
			}
		});
		reprintReceipt.addEventListener("click", function(e){ 
			if(connected){
				var msg = '{"printFlag":"' + printFlag.value +'","operationType":"ReprintReceipt","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"90"}';
				ws.send(msg);
			}else{
				alert("start the connection then try again!")
			}
		});
		stop.addEventListener("click", function(e){ 
			if(connected){
				var msg = '{"Stop":"stop"}';
				ws.send(msg);
			}else{
				alert("No Active connection to stop!")
			}
		});
		start.addEventListener("click", function(e){ 
			if(!connected){
				var msg = '{"Start":"'+ linkIp.value + '-' + linkPort.value +'"}';
				ws.send(msg);
			}else{
				alert("already connected!")
			}
		});
	} else {

		// The browser doesn't support WebSocket
		alert("Application NOT supported by your Browser!");
	}
}

