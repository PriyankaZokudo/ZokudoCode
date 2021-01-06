$(document).ready(function(){
  $('.select2').select2({
      dropdownAdapter: $.fn.select2.amd.require('select2/selectAllAdapter')
  });

  $("#result,#graph").hide();
  $('form').submit(function(e){
    e.preventDefault();
    if($(".needs-validation")[0].checkValidity()) {
        $("#graph").hide();
        doAjax();
    }
  });
  $('input[type="radio"]').click(function(){
    $("#graph").hide();
    doAjax(true);
  });
   function doAjax(graph = false) {
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : MerchantHost+"/merchant/get-dashboard-details",
            data : JSON.stringify({
                    "mid": jQuery("input[name='mid']").val(),
                    "tids": jQuery("select[name='terminal_ids']").val(),
                    "transaction_channels": jQuery("select[name='terminal_channels']").val(),
                    "from_date": jQuery("input[name='date_range']").data('daterangepicker').startDate.format('YYYY-MM-DD'),
                    "to_date": jQuery("input[name='date_range']").data('daterangepicker').endDate.format('YYYY-MM-DD'),
                    "transaction_status": jQuery("select[name='transaction_status']").val(),
                    "is_graph": graph
            }),
            beforeSend : function(xhr) {
                $(".loading").show();
                xhr.setRequestHeader("Authorization", AuthHeader);
                xhr.setRequestHeader("role", "USER_APP_ROLE");
            },
            success : function(response) {
                if (graph) {
                    prepareGraph(response.merchant_dashboard_details_graph);
                } else if(response.status == 'Success') {
                    $(".loading").hide();
                    $("#total_transactions").html(response.merchant_dashboard_details.total_transactions);
                    $("#unique_customers").html(response.merchant_dashboard_details.unique_customers);
                    $("#transactions_ticket_size").html(response.merchant_dashboard_details.transactions_ticket_size);
                    $("#total_sales").html(response.merchant_dashboard_details.total_sales);
                    $("#unique_cards").html(response.merchant_dashboard_details.unique_cards);
                    $("#new_customers").html(response.merchant_dashboard_details.new_customers);
                    $("#result").show();
                }
            },
            error : function(error) {
                alert(error);
            }
        });
   }
   function prepareGraph(graphData) {
        // var data = [$("#"+$(this).attr('data-id')).html()];
        // var labels = [$('input[name="date_range"]').val()];
        // var dataname = $(this).attr('data-name');
        var dataname = $('input[type="radio"]:checked').attr('data-name');
        var dataid   = $('input[type="radio"]:checked').attr('data-id');
        const groups = (() => {
            const byDay = (item) => moment(item.transaction_date).format('dddd'),
                forHour = (item) => /* moment(item.transaction_date).format('YYYY-MM-DD') byDay(item)*/  moment(item.transaction_date).format('hh a'),
                by6Hour = (item) => {
                    const m = moment(item.transaction_date);
                    return byDay(item) + ' ' + ['first', 'second', 'third', 'fourth'][Number(m.format('k')) % 6] + ' 6 hours';
                },
                forMonth = (item) => moment(item.transaction_date).format('YYYY-MM'),
                forWeek = (item) => moment(moment(item.transaction_date)).startOf('week').format('YYYY-MM-DD')
                +' - '+ moment(moment(item.transaction_date)).endOf('week').format('YYYY-MM-DD');
                // forMonth(item) + ' ' + moment(item.transaction_date).format('ww');
            return {
                byDay,
                forHour,
                by6Hour,
                forMonth,
                forWeek,
            };
        })();   

        var groupKey = 'forHour';
        var daysdiff = jQuery("input[name='date_range']").data('daterangepicker').endDate.diff(jQuery("input[name='date_range']").data('daterangepicker').startDate, "days");
        if(daysdiff > 1 )
            groupKey = 'byDay';   
        if(daysdiff > 7 )
            groupKey = 'forWeek';     
        if(daysdiff > 30 )
            groupKey = 'forMonth';

        // _(_.groupBy(graphData, groups[groupKey])).map(function(g, key) {
        //     return { 
        //        label: key, 
        //        val: _(g).reduce(function(m,x) {
        //          return m + x[dataid];
        //        }, 0) 
        //     };
        //   });
        
        var finalArray = function(sumByKeyName) { return _(_.groupBy(graphData, groups[groupKey])).map(function(g, key) {
            return { 
               label: key, 
               val: _(g).reduce(function(m,x) {
                 return m + x[sumByKeyName];
               }, 0) 
            };
          });
        }
        var arr = ["transactions_ticket_size", "new_customers", "unique_cards", "unique_customers"];
        if(jQuery.inArray( dataid, arr ) > -1) {
            // console.log(dataid,graphData[0][dataid])
            var data = [$('#'+dataid).html()];
            var labels = [jQuery("input[name='date_range']").val()];
        } else {
            var result = finalArray(dataid);
            var data = _.pluck(result, 'val');
            var labels = _.pluck(result, 'label');
        }
//        console.log(result,data,labels,dataname);
        $(".loading").hide();
        $("#graph").show();
        $('#myChart2').remove(); // this is my <canvas> element
        $('#chartContainer').append('<canvas id="myChart2"><canvas>');
        generatChart("myChart2",data,labels,dataname);
   }
});