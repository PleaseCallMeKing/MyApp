angular.module('invoice2', ['finance2'])
    .controller('InvoiceController', ['currencyConverter', function (currencyConverter) {
        this.qty = 11;
        this.cost = 2;
        this.inCurr = 'EUR';
        this.currencies = currencyConverter.currencies;

        this.total = function total(outCurr) {
            return currencyConverter.convert(this.qty * this.cost, this.inCurr, outCurr);
        };
        $httpProvider
        this.pay = function pay(){
            window.alert("thanks!");
        };
    }]);
