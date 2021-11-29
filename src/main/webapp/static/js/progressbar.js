(function() {
    var leaseMeter, meterBar, meterBarWidth, meterValue, progressNumber;

    /*Get value of value attribute*/
    var valueGetter = function(i) {
        leaseMeter = document.getElementsByClassName('leaseMeter');
        meterValue = leaseMeter[i].getAttribute('value');
        maxValue = leaseMeter[i].getAttribute('max');
        return meterValue;
    }

    /*Get max of value attribute*/
    var maxGetter = function(i) {
        leaseMeter = document.getElementsByClassName('leaseMeter');
        meterValue = leaseMeter[i].getAttribute('max');
        return meterValue;
    }

    /*Convert value of value attribute to percentage*/
    var getPercent = function(i) {
        var val = parseInt(valueGetter(i));
        var max = parseInt(maxGetter(i));
        if(val>max){
            val = max;
        }
        meterBarWidth = val * (100/max);
        return meterBarWidth;
    }

    /*Apply percentage to width of .meterBar*/
    var adjustWidth = function() {
        meterBar = document.getElementsByClassName('meterBar');
        for (var i=0; i<meterBar.length; i++) {
            var width = getPercent(i);
            if(width<11){
                width=11;
            }
            meterBar[i].style['width'] = width.toString()+"%";
        }
    }

    /*Update value indicator*/
    /*var indicUpdate = function() {
        progressNumber = document.getElementsByClassName('progressNumber');
        for (var i=0; i<progressNumber.length; i++) {
            progressNumber[i].innerHTML = valueGetter();
        }
    }*/

    adjustWidth();
    //indicUpdate();
})();