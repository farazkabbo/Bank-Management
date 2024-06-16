function checkBalance(accountId) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'BalanceServlet?accountId=' + accountId, true);
    xhr.onload = function() {
        if (this.status == 200) {
            document.getElementById('balanceDisplay').innerText = 'Balance: ' + this.responseText;
        } else {
            alert('Error retrieving balance!');
        }
    };
    xhr.send();
}

function deposit(accountId, amount) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'TransactionServlet', true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.onload = function() {
        alert(this.responseText);
        if (this.status == 200) {
            checkBalance(accountId);  // Refresh balance after deposit
        }
    };
    xhr.send('action=deposit&accountId=' + accountId + '&amount=' + amount);
}

function withdraw(accountId, amount) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'TransactionServlet', true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.onload = function() {
        alert(this.responseText);
        if (this.status == 200) {
            checkBalance(accountId);  // Refresh balance after withdrawal
        }
    };
    xhr.send('action=withdraw&accountId=' + accountId + '&amount=' + amount);
}
