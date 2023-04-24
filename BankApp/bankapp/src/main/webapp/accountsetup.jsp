<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Bank Account Setup Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            form {
                width: 100%;
                height: auto;
                max-width: 520px;
                margin: 0 auto;
            }

            form input {
                height: 37px;
                font-size: 13px;
                outline: none;
            }

            @media (max-width: 768px) {
                form {
                    max-width: 100%;
                }
                form input {
                    font-size: 16px;
                }
            }
        </style>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            function showAlert(msg) {
                alert(msg);
            }
        </script>
    </head>

    <body>
        <div id="container" class="d-flex justify-content-center align-items-center min-vh-100 bg-primary">
            <form action="accountsetup" class="bg-light p-4 round shadow" method="post">
                <h2 class="text-center my-3 text-primary">Bank Account Information</h2>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="accountHolderName">Account Holder Name</label>
                        <input type="text" name="accountHolderName" class="form-control" id="accountHolderName" placeholder="Account Holder Name" required>                  
                    </div>
                    <div class="form-group col-md-6">
                        <label for="accountNumber">Account Number</label>
                        <input type="number" name="accountNumber" class="form-control" id="accountNumber" placeholder="Account Number" min="100000000" max="999999999" required> 
                    </div>
                </div>
                <h2 class="text-center my-3 text-primary">Card Information</h2>
                <div class="form-group">
                    <label for="card">Card</label>
                    <select name="card" id="card" class="form-control" required>
                        <option value="">Choose...</option>
                        <option value="credit card">Credit Card</option>
                        <option value="debit card">Debit Card</option>
                    </select>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="cardHolderName">Card Holder Name</label>
                        <input type="text" name="cardHolderName" class="form-control" id="cardHolderName" placeholder="Card Holder Name" required>   
                    </div>
                    <div class="form-group col-md-6">
                        <label for="cardNumber">Card Number</label>
                        <input type="text" name="cardNumber" class="form-control" id="cardNumber" placeholder="XXXX XXXX XXXX XXXX" required>   
                    </div>
                </div>
                <div class="form-group">
                    <label for="cardType">Card Type</label>
                    <select name="cardType" id="cardType" class="form-control" required>
                        <option value="">Choose...</option>
                        <option value="mastercard">Mastercard</option>
                        <option value="visa">Visa</option>
                        <option value="american express">American Express</option>
                        <option value="discover">Discover</option>
                    </select>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="exprDate">Experiation Date</label>
                        <input type="month" name="exprDate" class="form-control" id="exprDate" value="2018-05" required>  
                    </div>
                    <div class="form-group col-md-6">
                        <label for="cvv">CVV</label>
                        <input type="number" name="cvv" class="form-control" id="cvv" placeholder="123" min="000" max="999" required>  
                    </div>
                </div>
                <input type="submit" class="w-100 my-2 px-2 border-0 bg-primary text-light">
            </form>
        </div>
        <script>
            const accNum = document.getElementById("accountNumber");
            accNum.addEventListener("input", (event) => {
                if (accNum.value.length > 9) {
                    accNum.value = accNum.value.slice(0, 9);
                }
            });

            const accHolderName =  document.getElementById("accountHolderName");
            accHolderName.addEventListener("input", (event) => {
                if (accHolderName.value.length > 50) {
                    accHolderName.value = accHolderName.value.slice(0, 50);
                }
            });

            const cardHolderName =  document.getElementById("cardHolderName");
            cardHolderName.addEventListener("input", (event) => {
                if (cardHolderName.value.length > 50) {
                    cardHolderName.value = cardHolderName.value.slice(0, 50);
                }
            });

            const cardNum =  document.getElementById("cardNumber");
            cardNum.addEventListener("input", (event) => {
                if (cardNum.value.length > 19) {
                    cardNum.value = cardNum.value.slice(0, 19);
                }
            });

            cardNum.addEventListener('keyup', (event) => {
                const input = event.target;
                const inputValue = input.value.replace(/\s/g, ''); // Remove any existing spaces
                // Add a space after every 4 characters
                if (inputValue.length > 0) {
                    input.value = inputValue.match(/.{1,4}/g).join(' ');
                } else {
                    input.value = '';
                }
            });

            const cvv = document.getElementById("cvv");
            cvv.addEventListener("input", (event) => {
                if (cvv.value.length > 3) {
                    cvv.value = cvv.value.slice(0, 3);
                }
            })
        </script>
         <% String message = (String) request.getAttribute("message"); %>
         <% if (message != null) { %>
             <script>showAlert("<%= message %>");</script>
         <% } %>
    </body>
</html>