window.onload=function(){
	if ("WebSocket" in window) {
		
		var connected = false;
		var ip = window.location.hostname;
		var ws = new WebSocket('ws://' + ip + '/ips-epos');
		//var ws = new WebSocket('wss://ips-link-service.herokuapp.com/ips-epos');
		//var ws = new WebSocket('wss://echo.websocket.org');
		var pedIp = document.getElementById('terminalIp');
		var pedPort = document.getElementById('terminalPort');
		var amount = document.getElementById('amount');
		var gtmessage = document.getElementById('GTmessage');
		var printFlag = document.getElementById('printFlag');
		var timeout = document.getElementById('timeout');
		var receiptField = document.getElementById('receiptMessageField');
		var statusMessageField = document.getElementById('statusMessageField');
		var payment = document.getElementById('payment');
		var reversal = document.getElementById('reversal');
		var refund = document.getElementById('refund');
		var pedBalance = document.getElementById('pedBalance');
		var endofday = document.getElementById('endofday');
		var firstDll = document.getElementById('firstDll');
		var updateDll = document.getElementById('updateDll');
		var pedStatus = document.getElementById('pedStatus');
		var probePed = document.getElementById('probePed');
		var lastTransStatus = document.getElementById('lastTransStatus');
		var reprintReceipt = document.getElementById('reprintReceipt');
		var start = document.getElementById('start');
		var stop = document.getElementById('stop');
		var linkIp = document.getElementById('linkIp');
		var linkPort = document.getElementById('linkPort');
		var alertbox = document.getElementById('alert');
		var connstatus = document.getElementById('connstatus');
		var print = document.getElementById('print');
		function printElem(){
		    var mywindow = window.open('', 'PRINT', 'height=400,width=600');

		    mywindow.document.write('<html><head><title>' + document.title  + '</title>');
		    mywindow.document.write('</head><body >');
		    mywindow.document.write('<h1>' + document.title  + '</h1>');
		    mywindow.document.write(document.getElementById('receiptMessageField').innerHTML);
		    mywindow.document.write('</body></html>');

		    mywindow.document.close(); // necessary for IE >= 10
		    mywindow.focus(); // necessary for IE >= 10*/

		    mywindow.print();
		    mywindow.close();

		    return true;
		}
		
		function clearAll(){
			statusMessageField.innerHTML = '';
			receiptField.innerHTML = '';
		}
		ws.onopen = function(stat) {
			console.log('connection: '+stat.data);
		};

		ws.onmessage = function (evt) { 
			
			var received_msg = evt.data;
			//console.log('incoming> '+received_msg);
			if(received_msg.includes('statusMessage')){
				var msg = JSON.parse(received_msg);
				statusMessageField.innerHTML = msg.statusMessage;
			}else if(received_msg.includes('terminalId') || 
					received_msg.includes('pedConnectivity') || 
					received_msg.includes('receipt') || 
					received_msg.includes('errorCode') ||
					received_msg.includes('operationType')
					){
				//var msg = JSON.parse(received_msg);
				receiptField.innerHTML = received_msg;
			}
			else if(received_msg.includes('Connected')){
				connected = true;
				alertbox.style.display = 'block';
				alertbox.style.backgroundColor = 'green';
				document.getElementById('alert-msg').innerHTML = received_msg;
				connstatus.style.backgroundColor = 'green';
				connstatus.innerHTML = 'Connected';
			}
			else if(received_msg.includes('Connection Failed')){
				connected = false;
				alertbox.style.display = 'block';
				alertbox.style.backgroundColor = 'red';
				document.getElementById('alert-msg').innerHTML = received_msg;
				connstatus.style.backgroundColor = 'red';
				connstatus.innerHTML = 'Disconnected';
			}
			else if(received_msg.includes('Connection Closed')){
				connected = false;
				alertbox.style.display = 'block';
				alertbox.style.backgroundColor = 'red';
				document.getElementById('alert-msg').innerHTML = received_msg;
				connstatus.style.backgroundColor = 'red';
				connstatus.innerHTML = 'Disconnected';
			}
			//receiptField.innerHTML = received_msg;
		};

		ws.onclose = function() { 
			connstatus.style.backgroundColor = 'green';
			connstatus.innerHTML = 'Disconnected';
			if(confirm("Connection Issues - Please Refresh Page!")){
				location.reload();
			}
		};

		payment.addEventListener("click", function(e){
			clearAll();
			if(connected){
				if(amount.value.length > 0 && pedIp.value.length > 0 && pedPort.value.length > 0){
					var msg = '{"printFlag":"' + printFlag.value +'","operationType":"Payment","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"'+ timeout.value +'","amount":"'+ amount.value +'","gtbit":"1","transactionReference":"'+ gtmessage.value +'"}';
					ws.send(msg);
				}else{
					alert('check all the fields first!');
				}
			}else{
				alert("start the connection then try again!")
			}
		});
		reversal.addEventListener("click", function(e){
			clearAll();
			if(connected){
				if(pedIp.value.length > 0 && pedPort.value.length > 0){
					var msg = '{"printFlag":"' + printFlag.value +'","operationType":"Reversal","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"'+ timeout.value +'","gtbit":"1","transactionReference":"'+ gtmessage.value +'"}';
					ws.send(msg);
				}else{
					alert('check all the fields first!');
				}
				
			}else{
				alert("start the connection then try again!")
			}
			});
		refund.addEventListener("click", function(e){
			clearAll();
			if(connected){
				if(amount.value.length > 0 && pedIp.value.length > 0 && pedPort.value.length > 0){
					var msg = '{"printFlag":"' + printFlag.value +'","operationType":"Refund","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"'+ timeout.value +'","amount":"'+ amount.value +'","gtbit":"1","transactionReference":"'+ gtmessage.value +'"}';
					ws.send(msg);
				}else{
					alert('check all the fields first!');
				}
			}else{
				alert("start the connection then try again!")
			}
		});
		pedBalance.addEventListener("click", function(e){
			clearAll();
			if(connected){
				if(pedIp.value.length > 0 && pedPort.value.length > 0){
					var msg = '{"printFlag":"' + printFlag.value +'","operationType":"PedBalance","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"'+ timeout.value +'"}';
					ws.send(msg);
				}else{
					alert('check all the fields first!');
				}
			}else{
				alert("start the connection then try again!")
			}
		});
		endofday.addEventListener("click", function(e){
			clearAll();
			if(connected){
				if(pedIp.value.length > 0 && pedPort.value.length > 0){
					var msg = '{"printFlag":"' + printFlag.value +'","operationType":"EndOfDay","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"'+ timeout.value +'"}';
					ws.send(msg);
				}else{
					alert('check all the fields first!');
				}
			}else{
				alert("start the connection then try again!")
			}
		});
		pedStatus.addEventListener("click", function(e){
			clearAll();
			if(connected){
				if(pedIp.value.length > 0 && pedPort.value.length > 0){
					var msg = '{"printFlag":"1","operationType":"PedStatus","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"'+ timeout.value +'"}';
					ws.send(msg);
				}else{
					alert('check all the fields first!');
				}	
			}else{
				alert("start the connection then try again!")
			}
		});
		probePed.addEventListener("click", function(e){
			clearAll();
			if(connected){
				if(pedIp.value.length > 0 && pedPort.value.length > 0){
					var msg = '{"printFlag":"1","operationType":"ProbePed","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"'+ timeout.value +'"}';
					ws.send(msg);
				}else{
					alert('check all the fields first!');
				}	
			}else{
				alert("start the connection then try again!")
			}
		});
		lastTransStatus.addEventListener("click", function(e){
			clearAll();
			if(connected){
				if(pedIp.value.length > 0 && pedPort.value.length > 0){
					var msg = '{"printFlag":"1","operationType":"LastTransactionStatus","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"'+ timeout.value +'"}';
					ws.send(msg);
				}else{
					alert('check all the fields first!');
				}	
			}else{
				alert("start the connection then try again!")
			}
		});
		firstDll.addEventListener("click", function(e){
			clearAll();
			if(connected){
				if(pedIp.value.length > 0 && pedPort.value.length > 0){
					var msg = '{"printFlag":"' + printFlag.value +'","operationType":"FirstDll","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"'+ timeout.value +'"}';
					ws.send(msg);
				}else{
					alert('check all the fields first!');
				}
			}else{
				alert("start the connection then try again!")
			}
		});
		updateDll.addEventListener("click", function(e){
			clearAll();
			if(connected){
				if(pedIp.value.length > 0 && pedPort.value.length > 0){
					var msg = '{"printFlag":"' + printFlag.value +'","operationType":"UpdateDll","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"'+ timeout.value +'"}';
					ws.send(msg);
				}else{
					alert('check all the fields first!');
				}
			}else{
				alert("start the connection then try again!")
			}
		});
		reprintReceipt.addEventListener("click", function(e){
			clearAll();
			if(connected){
				if(pedIp.value.length > 0 && pedPort.value.length > 0){
					var msg = '{"printFlag":"' + printFlag.value +'","operationType":"ReprintReceipt","pedIp":"'+ pedIp.value +'","pedPort":"'+ pedPort.value +'","timeOut":"'+ timeout.value +'"}';
					ws.send(msg);
				}else{
					alert('check all the fields first!');
				}	
			}else{
				alert("start the connection then try again!")
			}
		});
		stop.addEventListener("click", function(e){ 
			if(connected){
				var msg = '{"Stop":"stop"}';
				ws.send(msg);
				connected = false;
			}else{
				alert("No Active connection to stop!")
			}
		});
		start.addEventListener("click", function(e){
			if(!connected){
				if(linkIp.value.length > 0 && linkPort.value.length > 0){
					var msg = '{"Start":"'+ linkIp.value + '-' + linkPort.value +'"}';
					ws.send(msg);
				}else{
					alert('please enter link-service Ip and Port');
				}
				
			}else{
				alert("already connected!")
			}
		});
		print.addEventListener("click", function(e){
			printElem();
		});
	} else {

		// The browser doesn't support WebSocket
		alert("Application NOT supported by your Browser!");
	}
	
	
}

