$(document).ready(function(){
    var pdata = {};
    var today = new Date();
    $('input[name="date_range"]').daterangepicker({
        locale: {format: 'YYYY-MM-DD'},
        drops: 'down',
        opens: 'right',
        minDate:today
    });

    $('.select2').select2({
        dropdownAdapter: $.fn.select2.amd.require('select2/selectAllAdapter')
    });

    $("#result").hide();

    $('.target-potentials-block').hide();
    $("#inlineCheckbox1").click(function(){
        if($(this).is(':checked')){
            $('.target-potentials-block').show();
        } else {
            $('.target-potentials-block').hide();
        }
    });
    // Restricts input for each element in the set of matched elements to the given inputFilter.
    (function($) {
        $.fn.inputFilter = function(inputFilter) {
            return this.on("input keydown keyup mousedown mouseup select contextmenu drop", function() {
                if (inputFilter(this.value)) {
                    this.oldValue = this.value;
                    this.oldSelectionStart = this.selectionStart;
                    this.oldSelectionEnd = this.selectionEnd;
                } else if (this.hasOwnProperty("oldValue")) {
                    this.value = this.oldValue;
                    this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
                } else {
                    this.value = "";
                }
            });
        };
    }(jQuery));

    $('input[name="cashbackType"]').click(function(){
        $('#cashback').val('0');
    });

    $("#cashback").inputFilter(function(value) {
        if($('#exampleRadios1').is(':checked'))
            return /^\d*$/.test(value) && (value === "" || parseInt(value) <= 100);
        else
            return /^\d*$/.test(value) && (value === "" || parseInt(value) <= 10000);
    });
    $("#lat,#long").inputFilter(function(value) {
        return /^-?\d*[.,]?\d*$/.test(value); });

    $('form').submit(function(e){
        e.preventDefault();
        if($(".needs-validation")[0].checkValidity()) {
            var url = MerchantHost+"/merchant/get-publish-details";
            pdata = {
                    "mid": jQuery("input[name='mid']").val(),
                    "program_ids": jQuery("select[name='program_ids']").val(),
                    "from_date": jQuery("input[name='date_range']").data('daterangepicker').startDate.format('YYYY-MM-DD'),
                    "to_date": jQuery("input[name='date_range']").data('daterangepicker').endDate.format('YYYY-MM-DD'),
                    "mid": jQuery("input[name='mid']").val(),
                    "tids": jQuery("select[name='terminal_ids']").val(),
                    "cashback": jQuery("input[name='cashback']").val(),
                    "cashback_type": jQuery("input[name='cashbackType']:checked").val(),
                    "push_notifications":jQuery("input[name='notification']:checked").val(),
                    /*"location": jQuery("select[name='location']").val()*/
                };
            doAjax(url,pdata);
        }
    });

    $('#publish').click(function(){
        url = MerchantHost+"/merchant/publish-save";
        $.extend(pdata, {
            "target_high_potential_customers": $('#high_potential_cust').is(':checked'),
            "customer_mobiles": [],
            "campaign_id": jQuery("input[name='campaignNumber']").val(),
        });
        console.log(pdata);
        doAjax(url,pdata);
    });

    function doAjax(url,pdata) {
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : url,
            data : JSON.stringify(pdata),
            beforeSend : function(xhr) {
                $(".loading").show();
                xhr.setRequestHeader("Authorization", AuthHeader);
                xhr.setRequestHeader("role", "USER_APP_ROLE");
            },
            success : function(response) {
                console.log(response);
                if(response.status == 'Success') {
                    alert(response.message);
                    $(".loading").hide();
                    $("#total_customers_targetted").html(response.merchant_publish_details.total_customers_targetted ?? 0);
                    $("#total_customers_targetted_program_1").html(response.merchant_publish_details.ZOKUDOPROGRAM ?? 0);
                    $("#total_customers_targetted_program_2").html(response.merchant_publish_details.TESTCOPPG ?? 0);
                    /*$("#total_targetted_customers_in_your_city").html(response.merchant_publish_details.total_sales);*/
                    $("#result").show();
                }
            },
            error : function(error) {
                alert(error);
                $(".loading").hide();
            }
        });
    }
});