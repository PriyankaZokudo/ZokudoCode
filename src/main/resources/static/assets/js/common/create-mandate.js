$(document).ready(function() {
    $('#document-msg').html("").hide();
    $('#upload-image-tab-msg').html("").hide();
    $('#mandate-download-tab-msg').html("").hide();
    $('#mandate-download-physical-tab-msg').html("").hide();
    $( ".datepicker" ).datepicker({
        minDate:0,
        changeMonth: true,
        changeYear: true,
        dateFormat: 'yy-mm-dd'
    });
    $('.dataTable').DataTable({
        order: [[ 0, 'desc' ]]
    });

     $('form').validate({
            onkeyup: function(element) { $(element).valid(); },
            onfocusout: function(element) { $(element).valid(); },
    });

    function base64ToArrayBuffer(base64) {
        var binaryString = window.atob(base64);
        var binaryLen = binaryString.length;
        var bytes = new Uint8Array(binaryLen);
        for (var i = 0; i < binaryLen; i++) {
           var ascii = binaryString.charCodeAt(i);
           bytes[i] = ascii;
        }
        return bytes;
     }

    function saveByteArray(reportName, byte,blob_type) {
        var blob = new Blob([byte], {type: blob_type});
        var link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        var fileName = reportName;
        link.download = fileName;
        link.click();
    };

    $("input[name=ifscCode]").rules("add", { regex: "[A-Z]{4}[0-9]{6}" });
    $("input[name=collectionAmount]").rules("add", { regex: "^\[0-9]+(\.\[0-9]{1,2})?$" });
    $("input[name=maximumAmount]").rules("add", { regex: "^\[0-9]+(\.\[0-9]{1,2})?$" });
    $("input[name=customerAccountNumber]").rules("add", { regex: "[0-9]$" });
    var physical_mandate_id;
    $(document).on('click', '#createMandatebtn', function(event) {
    	 if(! $('#createMandateForm').valid()) return false;
		 event.preventDefault();
	     dataString = $("#createMandateForm").serialize();
	     $('#cover-spin').show();
	     console.log(dataString);
	     $.ajax({
	        type: "post",
	        url: "/admin/add-mandate",
	        dataType:"json",
	        data: dataString,
	        success: function (response) {
	            if(response.status === "success") {
					if(response.mode === 'physical') {
					     console.log(response);
                         $.ajax({
                            type: "post",
                            url: "/admin/get-mandate-status",
                            dataType:"json",
                            data: {"mandate_id": response.mandate_id },
                            success: function (status_response) {
                                console.log(status_response);
                                $('#cover-spin').hide();
                                if(status_response.status === "success") {
                                    console.log(status_response);
                                    $('#document-msg').html("Your physical mandate request is processed please download your NACH form and continue the process.").show();
                                    $('#document-tab').click();
                                    $('.download_physical_form').attr('id', response.mandate_id);
                                    physical_mandate_id = response.mandate_id;
                                } else if(status_response.status === "error") {
                                    alert('Error in mandate status update');
                                }
                            }
                         });
					} else {
					    $('#cover-spin').hide();
					    console.log("else ----"+response);
					     // do something with response.message or whatever other data on success
                        var options = {
                        //default environment staging (sandbox)
                        //for production include: "environment" : "production"; here
                        "callback": function(t){
                            if(t.error_code!=undefined)
                                alert("failed to register with error :"+t.message);
                            else {
                                alert("register successful for "+t.digio_doc_id);
                                window.location.href = "/admin/get-mandate-status?mandate_id="+t.digio_doc_id;
                            }
                        },
                            "logo":"https://www.zokudo.com/public/new_site/img/logo.png", //Pass Logo URL here
                            "is_iframe" : true //open in iframe
                        };
					    var digio = new Digio(options);
                        digio.init();  // Call init on user action (eg.button press and proceed to asynchronous upload)
                        digio.submit(response.mandate_id,response.customer_identifier);
                        // Get the documentId by calling Digio upload APIs. Email/Mobile is signer’s identifier
                        //In case of failure to retrieve DocumentId call digio.cancel();
					}
	            } else if(response.status === "error") {
	                $('#cover-spin').hide();
	            	alert(response.message);
	                // do something with response.message or whatever other data on error
	            }
	        }
	    });
    });

    $(document).on('click', '.download_physical_form', function(event) {
        var mandate_id = ($(this).attr('id') != "")? $(this).attr('id') : physical_mandate_id;
        if(mandate_id.trim() == '') {
            alert("Mandate id not found!!");
            return false;
        }
        $('#cover-spin').show();
        $.ajax({
            type: "post",
            url: "/admin/download-natch-form",
            dataType:"json",
            data: {"mandate_id": mandate_id },
            success: function (status_response) {
                $('#cover-spin').hide();
                console.log(status_response);
                if(status_response.status === "success") {
                    $('#upload-image-tab-msg').html("Your physical form successfully downloaded.").show();
                     $('#upload-image-tab').click();
                     var sampleArr = base64ToArrayBuffer(status_response.data);
                     saveByteArray(status_response.fileName, sampleArr,"application/pdf");
//                     window.open(status_response.filepath , '_blank');
                } else {
                    alert("Error in downloading physical form!!"+status_response);
                }
            }
        });
    });

    $(document).on('click', '.upload-signed-image', function(event) {
        var id = 'inputGroupFile09';
        var tab = 'mandate-download-tab';
        if (typeof $(this).attr('data') !== 'undefined' && $(this).attr('data') == 'inputGroupFile10') {
            id = 'inputGroupFile10';
            tab = 'mandate-download-physical-tab';
        }
       var base64 = document.querySelector('#'+id).files[0];
       if(base64 == '' || typeof base64 == 'undefined') {
           alert("Please choose the file");
           return false;
       } else {
            var fileExtension = ['jpeg', 'jpg', 'png'];
            if ($.inArray($('#'+id).val().split('.').pop().toLowerCase(), fileExtension) == -1) {
                alert("Only formats are allowed : "+fileExtension.join(', '));
                return false;
            }
            if($('#'+id).prop('files')[0].size > 1000000) {
                alert("Please upload file less than 2MB.");
                return false;
            }
        }
       getBase64(base64).then(function(result){
            $('#cover-spin').show();
            var strImage = result.replace(/^data:image\/[a-z]+;base64,/, "");
            var ext = base64.name.split('.').pop();
            var mandate_id = ($(".download_physical_form").attr('id') != "")? $(".download_physical_form").attr('id') : physical_mandate_id;
            $.ajax({
                type: "post",
                url: "/admin/mandate/upload-sign-natch-form",
                dataType:"json",
                data: {"digio_mandate_id": mandate_id ,"file_data":strImage,"type":ext},
                success: function (upload_response) {
                    $('#cover-spin').hide();
                    console.log(upload_response);
                    if(upload_response.success) {
                         $('#'+tab+'-msg').html("Your signed image file uploaded successfully!!.").show();
                         $('#'+tab).click();
                    } else {
                        alert("Error in uploading physical sign form!!"+upload_response.message);
                    }
                }
            });
       })
       .catch(function(error) {
            $('#cover-spin').hide();
            alert(error);
       });
    });
    function getBase64(file) {
      return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
      });
    }
     $(document).on('click', '.download-mandate-file', function(event) {
        $('#cover-spin').show();
        var mandate_id = ($(".download_physical_form").attr('id') != "")? $(".download_physical_form").attr('id') : physical_mandate_id;
        $.ajax({
            type: "post",
            url: "/admin/mandate/download-mandate-file",
            dataType:"json",
            data: {"digio_mandate_id": mandate_id},
            success: function (dl_response) {
                $('#cover-spin').hide();
                console.log(dl_response);
                if(dl_response.status === "success") {
                    var sampleArr = base64ToArrayBuffer(dl_response.data);
                    saveByteArray(dl_response.fileName, sampleArr,"application/octet-stream");
                } else {
                    alert("Error in download physical form!!"+dl_response.message);
                }
            }
        });
     });

    $('#createPresentmentForm').validate({
            onkeyup: function(element) { $(element).valid(); },
            onfocusout: function(element) { $(element).valid(); },
    });
    $("input[name=amount]").rules("add", { regex: "^\[0-9]+(\.\[0-9]{1,2})?$" });
    $("input[name=uniqueKey]").rules("add", { regex: "^\[a-z0-9\-\s]+$" });
    $(document).on('click', '#createPresentmentBtn', function(event) {
         if(! $('#createPresentmentForm').valid()) return false;
         if(parseFloat($("input[name=amount]").val()) > parseFloat($("input[name=maximum_amount]").val())) {
            alert("Transaction amount can not be greater than maximum amount");
            return false;
         }
         $('#cover-spin').show();
         dataString = $("#createPresentmentForm").serialize();
         $.ajax({
             type: "post",
             url: "/admin/transaction/add-presentment",
             dataType:"json",
             data: dataString,
             success: function (t_response) {
                 $('#cover-spin').hide();
                 console.log(t_response);
                 if(t_response.status === "success") {
                     alert('Transaction presented successfully!!');
                     location.reload();
                 } else {
                     alert("Error in transaction!!"+t_response.message);
                 }
             }
         });
      });
});
