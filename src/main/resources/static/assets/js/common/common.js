$(document).ready(function() {
 // Edit Merchant Functionality
 	$(document).on('click', ".editMerchant", function (e) {
         e.preventDefault();
    	 $("#editMerchantModal").modal();
    	 $.get(this.href, function (merchantDetails, status) {
    	 	// console.log(merchantDetails);
    	 	// Merchant details
            $("input[name=merchantName]").val(merchantDetails[0].merchantName);
			$("input[name=registrationNumber]").val(merchantDetails[0].registrationNumber);
			$("input[name=companyType]").val(merchantDetails[0].companyType);
			$("input[name=panNumber]").val(merchantDetails[0].panNumber);
			$("input[name=tan]").val(merchantDetails[0].tan);
			$("input[name=gstNumber]").val(merchantDetails[0].gstNumber);
			$("input[name=address]").val(merchantDetails[0].address);
			$("input[name=websiteAddress]").val(merchantDetails[0].websiteAddress);
			$("input[name=emailId]").val(merchantDetails[0].emailId);
			$("input[name=agreementOwnerName]").val(merchantDetails[0].agreementOwnerName);
			$("input[name=tid]").val(merchantDetails[0].tid);
			$("input[name=mid]").val(merchantDetails[0].mid);
			$("input[name=mobile]").val(merchantDetails[0].mobile);
			$("input[name=tdr]").val(merchantDetails[0].tdr);
			$("input[value="+merchantDetails[0].tdrType+"]").prop("checked", true);
			// Merchant Documents

			$("#MerchantId").val(merchantDetails[0].merchantId);
			$('.agreement').html('').siblings().removeClass('d-none');
			$('.boardResolutionWithDirectorSignature').html('').siblings().removeClass('d-none');
			$('.companyPanCard').html('').siblings().removeClass('d-none');
			$('.listOfDirectorsDetailWithDinOnCompanyLetterhead').html('').siblings().removeClass('d-none');
			$('.directorKycPanCardAadharCard').html('').siblings().removeClass('d-none');
			$('.companyAddressProofElectricityBill').html('').siblings().removeClass('d-none');
			$('.companyMoaAndAoa').html('').siblings().removeClass('d-none');
			$('.certificateOfIncorporation').html('').siblings().removeClass('d-none');
			$('.gstCertificate').html('').siblings().removeClass('d-none');

			if( typeof merchantDetails[1] != 'undefined' && merchantDetails[1] != null) {

				if(merchantDetails[1].agreement != null && typeof merchantDetails[1].agreement != 'undefined') {
					$('.agreement').html('<a target="_blank" href="'+merchantDetails[1].agreement +'" download>'+ merchantDetails[1].agreement.replace(/^.*(\\|\/|\:)/, '') +' &nbsp;&nbsp;<i class="fa fa-download"></i></a>');
					$('.agreement').siblings().addClass('d-none');

				}
				if(merchantDetails[1].boardResolutionWithDirectorSignature != null && typeof merchantDetails[1].boardResolutionWithDirectorSignature != 'undefined') {
					$('.boardResolutionWithDirectorSignature').html('<a target="_blank" href="'+merchantDetails[1].boardResolutionWithDirectorSignature +'" download>'+ merchantDetails[1].boardResolutionWithDirectorSignature.replace(/^.*(\\|\/|\:)/, '') +' &nbsp;&nbsp;<i class="fa fa-download"></i></a>');
					$('.boardResolutionWithDirectorSignature').siblings().addClass('d-none');

				}
				if(merchantDetails[1].companyPanCard != null && typeof merchantDetails[1].companyPanCard != 'undefined') {
					$('.companyPanCard').html('<a target="_blank" href="'+merchantDetails[1].companyPanCard +'" download>'+ merchantDetails[1].companyPanCard.replace(/^.*(\\|\/|\:)/, '') +' &nbsp;&nbsp;<i class="fa fa-download"></i></a>');
					$('.companyPanCard').siblings().addClass('d-none');

				}
				if(merchantDetails[1].listOfDirectorsDetailWithDinOnCompanyLetterhead != null && typeof merchantDetails[1].listOfDirectorsDetailWithDinOnCompanyLetterhead != 'undefined') {
					$('.listOfDirectorsDetailWithDinOnCompanyLetterhead').html('<a target="_blank" href="'+merchantDetails[1].listOfDirectorsDetailWithDinOnCompanyLetterhead +'" download>'+ merchantDetails[1].listOfDirectorsDetailWithDinOnCompanyLetterhead.replace(/^.*(\\|\/|\:)/, '') +' &nbsp;&nbsp;<i class="fa fa-download"></i></a>');
					$('.listOfDirectorsDetailWithDinOnCompanyLetterhead').siblings().addClass('d-none');

				}
				if(merchantDetails[1].directorKycPanCardAadharCard != null && typeof merchantDetails[1].directorKycPanCardAadharCard != 'undefined') {
					$('.directorKycPanCardAadharCard').html('<a target="_blank" href="'+merchantDetails[1].directorKycPanCardAadharCard +'" download>'+ merchantDetails[1].directorKycPanCardAadharCard.replace(/^.*(\\|\/|\:)/, '') +' &nbsp;&nbsp;<i class="fa fa-download"></i></a>');
					$('.directorKycPanCardAadharCard').siblings().addClass('d-none');

				}
				if(merchantDetails[1].companyAddressProofElectricityBill != null && typeof merchantDetails[1].companyAddressProofElectricityBill != 'undefined') {
					$('.companyAddressProofElectricityBill').html('<a target="_blank" href="'+merchantDetails[1].companyAddressProofElectricityBill +'" download>'+ merchantDetails[1].companyAddressProofElectricityBill.replace(/^.*(\\|\/|\:)/, '') +' &nbsp;&nbsp;<i class="fa fa-download"></i></a>');
					$('.companyAddressProofElectricityBill').siblings().addClass('d-none');

				}
				if(merchantDetails[1].companyMoaAndAoa != null && typeof merchantDetails[1].companyMoaAndAoa != 'undefined') {
					$('.companyMoaAndAoa').html('<a target="_blank" href="'+merchantDetails[1].companyMoaAndAoa +'" download>'+ merchantDetails[1].companyMoaAndAoa.replace(/^.*(\\|\/|\:)/, '') +' &nbsp;&nbsp;<i class="fa fa-download"></i></a>');
					$('.companyMoaAndAoa').siblings().addClass('d-none');

				}
				if(merchantDetails[1].certificateOfIncorporation != null && typeof merchantDetails[1].certificateOfIncorporation != 'undefined') {
					$('.certificateOfIncorporation').html('<a target="_blank" href="'+merchantDetails[1].certificateOfIncorporation +'" download>'+ merchantDetails[1].certificateOfIncorporation.replace(/^.*(\\|\/|\:)/, '') +' &nbsp;&nbsp;<i class="fa fa-download"></i></a>');
					$('.certificateOfIncorporation').siblings().addClass('d-none');

				}
				if(merchantDetails[1].gstCertificate != null && typeof merchantDetails[1].gstCertificate != 'undefined') {
					$('.gstCertificate').html('<a target="_blank" href="'+merchantDetails[1].gstCertificate +'" download>'+ merchantDetails[1].gstCertificate.replace(/^.*(\\|\/|\:)/, '') +' &nbsp;&nbsp;<i class="fa fa-download"></i></a>');
					$('.gstCertificate').siblings().addClass('d-none');

				}
			}
			if( typeof merchantDetails[3] != 'undefined' && merchantDetails[3] != null) {
				 $("#mandate-details .table").DataTable().destroy();
                 $("#mandate-details").find('tbody').html(merchantDetails[3]);
                 $("#mandate-details .table").DataTable().draw();
			}

			if( typeof merchantDetails[4] != 'undefined' && merchantDetails[4] != null) {
				 $("#transaction-details .table").DataTable().destroy();
                 $("#transaction-details").find('tbody').html(merchantDetails[4]);
                 $("#transaction-details .table").DataTable().draw();
			}


         })
    });

	$(document).on('click', ".collect-money", function (e) {
         e.preventDefault();
    	 $("#presentmentModal").modal();
    	 $.get(this.href, function (mandateDetails, status) {
    	 	console.log(mandateDetails);
    	 	// Merchant details
            $("input[name=umrn]").val(mandateDetails[0].umrn);
            $("input[name=corporateAccountNumber]").val(corporate_account_number);
			$("input[name=customerName]").val(mandateDetails[0].customer_account_name);
			var destination_account_type = mandateDetails[0].customer_account_type == '2' ? 'savings' : 'current';
			$("input[name=customerAccountNumber]").val(mandateDetails[0].customer_account_number);
			$("input[name=destinationAccountType]").val(destination_account_type);
			$("input[name=destinationBankId]").val(mandateDetails[0].ifsc_code);
			$("input[name=maximum_amount]").val(mandateDetails[0].maximum_amount);
			$("input[name=frequency]").val(mandateDetails[0].frequency_name);
			$("input[name=uniqueKey]").val(mandateDetails[1]);
			$("input[name=mid]").val(mandateDetails[0].mid);
			$("input[name=amount]").val("");
			$("input[name=narration]").val("");
         });
    });
    $(document).on('click', ".complete-physical-process", function (e) {
         e.preventDefault();
         $("#physicalMandateModal").modal();
         $('.download_physical_form').attr('id',$(this).attr('id'));
    });
    $(document).on('click', "#upload-file", function (event) {
        if($('#tdrFile').val() == "") {
            alert('Please select excel to upload.');
            return false;
        }
        $('#cover-spin').show();
        event.preventDefault();
        var form = $('#fileUploadForm')[0];
        var data = new FormData(form);
        $.ajax({
            url: "/admin/transaction/upload-tdr-transaction",
            enctype: 'multipart/form-data',
            type: 'POST',
            data: data,
            beforeSend: function () {
                console.log("Starting...");
            },
            success: function (data) {
                $('#cover-spin').hide();
                alert(data);
                $('#upload-file-modal').modal('toggle');
                $('[for=tdrFile]').html("");
                $('#tdrFile').val("");
            },
            error: function (err) {
                $('#cover-spin').hide();
                alert(err.responseText);
                $('[for=tdrFile]').html("");
                $('#tdrFile').val("");
            },
            cache: false,
            contentType: false,
            processData: false
        });
    });
});
